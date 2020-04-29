package helper;


import cn.hutool.core.io.IORuntimeException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import wrapper.BitMatrixEx;
import wrapper.QrCodeOptions;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

/**
 * 二维码生成辅助类，主要两个方法，一个是生成二维码矩阵，一个是渲染矩阵为图片
 * Created by zhangch on 2018/3/23.
 */
@Slf4j
public class QrCodeGenerateHelper {
    private static final int QUIET_ZONE_SIZE = 4;


    /**
     * 对 zxing 的 QRCodeWriter 进行扩展, 解决白边过多的问题
     * <p/>
     * 源码参考 {@link com.google.zxing.qrcode.QRCodeWriter#encode(String, BarcodeFormat, int, int, Map)}
     */
    public static BitMatrixEx encode(QrCodeOptions qrCodeConfig) throws WriterException {
        ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
        int quietZone = 1;
        if (qrCodeConfig.getHints() != null) {
            if (qrCodeConfig.getHints().containsKey(EncodeHintType.ERROR_CORRECTION)) {
                errorCorrectionLevel = ErrorCorrectionLevel
                        .valueOf(qrCodeConfig.getHints().get(EncodeHintType.ERROR_CORRECTION).toString());
            }
            if (qrCodeConfig.getHints().containsKey(EncodeHintType.MARGIN)) {
                quietZone = Integer.parseInt(qrCodeConfig.getHints().get(EncodeHintType.MARGIN).toString());
            }

            if (quietZone > QUIET_ZONE_SIZE) {
                quietZone = QUIET_ZONE_SIZE;
            } else if (quietZone < 0) {
                quietZone = 0;
            }
        }

        QRCode code = Encoder.encode(qrCodeConfig.getMsg(), errorCorrectionLevel, qrCodeConfig.getHints());
        return renderResult(code, qrCodeConfig.getW(), qrCodeConfig.getH(), quietZone);
    }


    /**
     * 对 zxing 的 QRCodeWriter 进行扩展, 解决白边过多的问题
     * <p/>
     * 源码参考 {@link com.google.zxing.qrcode.QRCodeWriter#renderResult(QRCode, int, int, int)}
     *
     * @param code
     * @param width
     * @param height
     * @param quietZone 取值 [0, 4]
     * @return
     */
    private static BitMatrixEx renderResult(QRCode code, int width, int height, int quietZone) {
        ByteMatrix input = code.getMatrix();
        if (input == null) {
            throw new IllegalStateException();
        }

        // xxx 二维码宽高相等, 即 qrWidth == qrHeight
        int inputWidth = input.getWidth();
        int inputHeight = input.getHeight();
        int qrWidth = inputWidth + (quietZone * 2);
        int qrHeight = inputHeight + (quietZone * 2);


        // 白边过多时, 缩放
        int minSize = Math.min(width, height);
        int scale = calculateScale(qrWidth, minSize);
        if (scale > 0) {
            if (log.isDebugEnabled()) {
                log.debug("qrCode scale enable! scale: {}, qrSize:{}, expectSize:{}x{}", scale, qrWidth, width, height);
            }

            int padding, tmpValue;
            // 计算边框留白
            padding = (minSize - qrWidth * scale) / QUIET_ZONE_SIZE * quietZone;
            tmpValue = qrWidth * scale + padding;
            if (width == height) {
                width = tmpValue;
                height = tmpValue;
            } else if (width > height) {
                width = width * tmpValue / height;
                height = tmpValue;
            } else {
                height = height * tmpValue / width;
                width = tmpValue;
            }
        }

        int outputWidth = Math.max(width, qrWidth);
        int outputHeight = Math.max(height, qrHeight);

        int multiple = Math.min(outputWidth / qrWidth, outputHeight / qrHeight);
        int leftPadding = (outputWidth - (inputWidth * multiple)) / 2;
        int topPadding = (outputHeight - (inputHeight * multiple)) / 2;


        BitMatrixEx res = new BitMatrixEx();
        res.setByteMatrix(input);
        res.setLeftPadding(leftPadding);
        res.setTopPadding(topPadding);
        res.setMultiple(multiple);

        res.setWidth(outputWidth);
        res.setHeight(outputHeight);
        return res;
    }


    /**
     * 如果留白超过15% , 则需要缩放
     * (15% 可以根据实际需要进行修改)
     *
     * @param qrCodeSize 二维码大小
     * @param expectSize 期望输出大小
     * @return 返回缩放比例, <= 0 则表示不缩放, 否则指定缩放参数
     */
    private static int calculateScale(int qrCodeSize, int expectSize) {
        if (qrCodeSize >= expectSize) {
            return 0;
        }

        int scale = expectSize / qrCodeSize;
        int abs = expectSize - scale * qrCodeSize;
        if (abs < expectSize * 0.15) {
            return 0;
        }

        return scale;
    }


    /**
     * 根据二维码配置 & 二维码矩阵生成二维码图片
     *
     * @param qrCodeConfig
     * @param bitMatrix
     * @return
     * @throws IOException
     */
    public static BufferedImage toBufferedImage(QrCodeOptions qrCodeConfig, BitMatrixEx bitMatrix) throws IOException {
        int qrCodeWidth = bitMatrix.getWidth();
        int qrCodeHeight = bitMatrix.getHeight();
        BufferedImage qrCode = QrCodeRenderHelper.drawQrInfo(qrCodeConfig, bitMatrix);

        // 若二维码的实际宽高和预期的宽高不一致, 则缩放
        int realQrCodeWidth = qrCodeConfig.getW();
        int realQrCodeHeight = qrCodeConfig.getH();
        if (qrCodeWidth != realQrCodeWidth || qrCodeHeight != realQrCodeHeight) {
            BufferedImage tmp = new BufferedImage(realQrCodeWidth, realQrCodeHeight, BufferedImage.TYPE_INT_RGB);
            tmp.getGraphics()
                    .drawImage(qrCode.getScaledInstance(realQrCodeWidth, realQrCodeHeight, Image.SCALE_SMOOTH), 0, 0,
                            null);
            qrCode = tmp;
        }


        /**
         * 说明
         *  在覆盖模式下，先设置二维码的透明度，然后绘制在背景图的正中央，最后绘制logo，这样保证logo不会透明，显示清晰
         *  在填充模式下，先绘制logo，然后绘制在背景的指定位置上；若先绘制背景，再绘制logo，则logo大小偏移量的计算会有问题
         */
        boolean logoAlreadyDraw = false;
        // 绘制背景图
        if (qrCodeConfig.getBgImgOptions() != null) {
            if (qrCodeConfig.getBgImgOptions().getBgImgStyle() == QrCodeOptions.BgImgStyle.FILL &&
                    qrCodeConfig.getLogoOptions() != null) {
                // 此种模式，先绘制logo
                qrCode = QrCodeRenderHelper.drawLogo(qrCode, qrCodeConfig.getLogoOptions());
                logoAlreadyDraw = true;
            }

            qrCode = QrCodeRenderHelper.drawBackground(qrCode, qrCodeConfig.getBgImgOptions());
        }


        // 插入logo
        if (qrCodeConfig.getLogoOptions() != null && !logoAlreadyDraw) {
            qrCode = QrCodeRenderHelper.drawLogo(qrCode, qrCodeConfig.getLogoOptions());
        }

        return qrCode;
    }


    public static List<ImmutablePair<BufferedImage, Integer>> toGifImages(QrCodeOptions qrCodeConfig,
            BitMatrixEx bitMatrix) {
        if (qrCodeConfig.getBgImgOptions() == null ||
                qrCodeConfig.getBgImgOptions().getGifDecoder().getFrameCount() <= 0) {
            throw new IllegalArgumentException("animated background image should not be null!");
        }

        int qrCodeWidth = bitMatrix.getWidth();
        int qrCodeHeight = bitMatrix.getHeight();
        BufferedImage qrCode = QrCodeRenderHelper.drawQrInfo(qrCodeConfig, bitMatrix);

        // 若二维码的实际宽高和预期的宽高不一致, 则缩放
        int realQrCodeWidth = qrCodeConfig.getW();
        int realQrCodeHeight = qrCodeConfig.getH();
        if (qrCodeWidth != realQrCodeWidth || qrCodeHeight != realQrCodeHeight) {
            BufferedImage tmp = new BufferedImage(realQrCodeWidth, realQrCodeHeight, BufferedImage.TYPE_INT_RGB);
            tmp.getGraphics()
                    .drawImage(qrCode.getScaledInstance(realQrCodeWidth, realQrCodeHeight, Image.SCALE_SMOOTH), 0, 0,
                            null);
            qrCode = tmp;
        }

        boolean logoAlreadyDraw = false;
        if (qrCodeConfig.getBgImgOptions().getBgImgStyle() == QrCodeOptions.BgImgStyle.FILL &&
                qrCodeConfig.getLogoOptions() != null) {
            // 此种模式，先绘制logo
            qrCode = QrCodeRenderHelper.drawLogo(qrCode, qrCodeConfig.getLogoOptions());
            logoAlreadyDraw = true;
        }


        // 绘制动态背景图
        List<ImmutablePair<BufferedImage, Integer>> bgList =
                QrCodeRenderHelper.drawGifBackground(qrCode, qrCodeConfig.getBgImgOptions());

        // 插入logo

        if (qrCodeConfig.getLogoOptions() != null && !logoAlreadyDraw) {
            List<ImmutablePair<BufferedImage, Integer>> result = new ArrayList<>(bgList.size());
            for (ImmutablePair<BufferedImage, Integer> pair : bgList) {
                result.add(ImmutablePair.of(QrCodeRenderHelper.drawLogo(pair.getLeft(), qrCodeConfig.getLogoOptions()),
                        pair.getRight()));
            }
            return result;
        } else {
            return bgList;
        }
    }

    /**
     * 合并任数量的图片成一张图片
     *
     * @param isHorizontal
     *            true代表水平合并，fasle代表垂直合并
     * @param imgs
     *            待合并的图片数组
     * @return
     * @throws IOException
     */
    @Deprecated
    public static BufferedImage mergeImage(boolean isHorizontal, BufferedImage... imgs) throws IOException {
        // 生成新图片
        BufferedImage destImage = null;
        // 计算新图片的长和高
        int allw = 0, allh = 0, allwMax = 0, allhMax = 0;
        // 获取总长、总宽、最长、最宽
        for (int i = 0; i < imgs.length; i++) {
            BufferedImage img = imgs[i];
            allw += img.getWidth();
            allh += img.getHeight();
            if (img.getWidth() > allwMax) {
                allwMax = img.getWidth();
            }
            if (img.getHeight() > allhMax) {
                allhMax = img.getHeight();
            }
        }
        // 创建新图片
        if (isHorizontal) {
            destImage = new BufferedImage(allw, allhMax, BufferedImage.TYPE_INT_RGB);
        } else {
            destImage = new BufferedImage(allwMax, allh, BufferedImage.TYPE_INT_RGB);
        }
        // 合并所有子图片到新图片
        int wx = 0, wy = 0;
        for (int i = 0; i < imgs.length; i++) {
            BufferedImage img = imgs[i];
            int w1 = img.getWidth();
            int h1 = img.getHeight();
            // 从图片中读取RGB
            int[] ImageArrayOne = new int[w1 * h1];
            ImageArrayOne = img.getRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 逐行扫描图像中各个像素的RGB到数组中
            if (isHorizontal) { // 水平方向合并
                destImage.setRGB(wx, 0, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            } else { // 垂直方向合并
                destImage.setRGB(0, wy, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            }
            wx += w1;
            wy += h1;
        }
        return destImage;
    }

    /**
     * 根据文字创建PNG图片
     *
     * @param str             文字
     * @param font            字体{@link Font}
     * @param backgroundColor 背景颜色
     * @param fontColor       字体颜色
     * @throws IORuntimeException IO异常
     */
    @Deprecated
    public static BufferedImage createImage(String str, Font font, Color backgroundColor, Color fontColor,int width) throws IORuntimeException {
        // 获取font的样式应用在str上的整个矩形
        Rectangle2D rectangle2D = font.getStringBounds(str, new FontRenderContext(AffineTransform.getScaleInstance(1, 1), false, false));
        int unitHeight = (int) Math.floor(rectangle2D.getHeight());// 获取单个字符的高度
        // 获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度
        int height = unitHeight + 10;// 把单个字符的高度+3保证高度绝对能容纳字符串作为图片的高度
        // 创建图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.setColor(backgroundColor);
        g.fillRect(0, 0, width, height);// 先用背景色填充整张图片,也就是背景
        g.setColor(fontColor);
        g.setFont(font);// 设置画笔字体
        //文字居中
        FontMetrics fm = g.getFontMetrics();
        g.drawString(str, (width-fm.stringWidth(str))/2, unitHeight+(height - unitHeight)/2);// 画出字符串
        g.dispose();
        return image;
    }
}
