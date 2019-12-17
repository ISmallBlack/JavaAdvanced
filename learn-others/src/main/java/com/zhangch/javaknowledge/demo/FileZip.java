package com.zhangch.javaknowledge.demo;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 功能说明: 文件压缩 <br>
 * 系统版本: 1.0 <br>
 * 开发人员: zhanch
 * 开发时间: 2019/11/28<br>
 * <br>
 */
public class FileZip {
    private final static  String ZIP_FILE = "E:\\tmp\\tmp.zip";
    private final static  String JPG_FILE = "E:\\Tencent\\WeChat\\WeChat Files\\zhangchenhui-xiaohei\\FileStorage\\File\\2019-11\\Charles_4.2.8.dmg";
    private final static  String FILE_NAME = "Test";

    public static void zipFileNoBuffer() {
        File zipFile = new File(ZIP_FILE);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile))) {
            //开始时间
            long beginTime = System.currentTimeMillis();
            try (InputStream input = new FileInputStream(JPG_FILE)) {
                zipOut.putNextEntry(new ZipEntry(FILE_NAME ));
                int temp = 0;
                while ((temp = input.read()) != -1) {
                    zipOut.write(temp);
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime-beginTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zipFileBuffer() {
        File zipFile = new File(ZIP_FILE);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOut)) {
            //开始时间
            long beginTime = System.currentTimeMillis();
            try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(JPG_FILE))) {
                zipOut.putNextEntry(new ZipEntry(FILE_NAME +".dmg"));
                int temp = 0;
                while ((temp = bufferedInputStream.read()) != -1) {
                    bufferedOutputStream.write(temp);
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime-beginTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 性能最好 大文件压缩一定要用这个
     */
    public static void zipFileChannel() {
        //开始时间
        long beginTime = System.currentTimeMillis();
        File zipFile = new File(ZIP_FILE);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
             WritableByteChannel writableByteChannel = Channels.newChannel(zipOut)) {
            try (FileChannel fileChannel = new FileInputStream(JPG_FILE).getChannel()) {
                zipOut.putNextEntry(new ZipEntry(FILE_NAME +".dmg"));
                fileChannel.transferTo(0, fileChannel.size(), writableByteChannel);
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime-beginTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
      // zipFileBuffer();
       /* long beginTime = System.currentTimeMillis();
        ZipUtil.zip(JPG_FILE);
        long endTime = System.currentTimeMillis();*/
        System.out.println(Runtime.getRuntime().availableProcessors());

      //  zipFileChannel();
    }
}
