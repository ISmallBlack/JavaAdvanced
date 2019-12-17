package com.zhangch.javaknowledge.demo;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 功能说明: JSON传输二进制文件 <br>
 * 系统版本: 1.0 <br>
 * 开发人员: zhanch
 * 开发时间: 2019/11/18<br>
 * <br>
 */
public class JsonTranformByte {

    public static void main(String[] args) throws IOException {
       /* byte[] data = compress(loadFile());
        String json = Base64.encode(data);
       *//* String sss = "sssa车份儿说而VR被是儿女热";
        System.out.println("data0 length:" + json.length());
        System.out.println("data1 length:" + zipString(json).length());
        System.out.println("压缩率："+new BigDecimal(String.valueOf(zipString(json).length())).divide(new BigDecimal(String.valueOf(json.length())),2, BigDecimal.ROUND_HALF_UP).doubleValue());
        *//*Map<String,String> hash = new HashMap<>();
        hash.put("data",json);
        String jsons = JSONUtil.toJsonStr(hash);
        System.out.println("data length:" + jsons);
        JSONObject jsonObject = JSONUtil.parseObj(jsons);
        File file = new File("e:/银行明细生成交易单文档.pdf");
        FileUtil.getOutputStream(file).write(ZipUtil.unGzip(Base64.decode((String)jsonObject.get("data"))));*/
        String orgids = "0_36020000#6000001#e884dafd85e3475aa3205cfb4392d5dc#200704#200902#200901#200703#200706#200705#200501#200702#200701#200102#200103#200301#201002#201001#9d8ed5ad57e649d3bbf730d1b235f91a#019b66651b364b8f9e778db9648f8a26#a4b891fdaea24971b1eba5a52141ce6e#1c3cff2f28d44331b1201480af32d48b#7e483c5d1eb24558afcfe065dcb94de8#e7b7a6b4689c452d88bb6ef0fbe30c94#201003#6f65e3a171b5481da10b55ab4114be37#200608#200209#200605#200208#200604#200802#200607#200606#0_36240000#200601#200205#200204#200402#200801#200207#200603#200206#200602#200201#200401#200203#200202#201101#200210#b811a942fc334833af9a738fbd6f0783#3754644a4dd447af95b46366b676d3ae#0fdacb9ff20d40deacd4e55100ae3ee3#201102#26292be99aca46d2b8bdb157acde84d3#8d5a0f14b95c42eab1ff7024cae94c22#f81538b8fa3e4d2baa3c8c278df4de33#641a83eeb3164bb2b2d4b4ef6fdde7d3";

        System.out.println(Arrays.asList(orgids.split("#")).stream().map(org->"'"+org+"'").collect(Collectors.joining(",")));


    }

    public static String zipString(String iniString) {
        // write code here
        int low = 0 , high = 0 ;
        int len = iniString.length();
        StringBuilder sb = new StringBuilder();
        char c = ' ';
        int count = 0;
        while(low < len){
            high = low;
            c = iniString.charAt(low);
            while((high < len)&&(iniString.charAt(high) == c)){
                high ++;
            }
            count = high - low ;
            sb.append(c);
            sb.append(count);
            low = high;
        }
        return (sb.toString().length() < len)?sb.toString():iniString;
    }


    public static String compress(String str) throws IOException {
        if (null == str || str.length() <= 0) {
            return str;
        }
        // 创建一个新的 byte 数组输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 使用默认缓冲区大小创建新的输出流
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        // 将 b.length 个字节写入此输出流
        gzip.write(str.getBytes());
        gzip.close();
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return out.toString("utf-8");
    }

    public static String uncompress(String source) {
        if (source != null && source.length() != 0) {
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ByteArrayInputStream bis = new ByteArrayInputStream(source.getBytes("utf-8"));
                GZIPInputStream gis = new GZIPInputStream(bis);
                byte[] data = new byte[1024];

                for(int len = gis.read(data); len != -1; len = gis.read(data)) {
                    bos.write(data, 0, len);
                }

                return bos.toString();
            } catch (IOException var6) {
                System.out.println(var6.getStackTrace().toString());
                return source;
            }
        } else {
            return source;
        }
    }



    /**
     * 加载本地文件,并转换为byte数组
     * @return
     */
    public static byte[] loadFile() {
        File file = new File("f:/银行明细生成交易单文档.pdf");

        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        byte[] data = null ;

        try {
            fis = new FileInputStream(file);
            baos = new ByteArrayOutputStream((int) file.length());

            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            data = baos.toByteArray() ;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                    fis = null;
                }

                baos.close() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return data ;
    }

    /**
     * 对byte[]进行压缩
     *
     * @param data
     * @return 压缩后的数据
     */
    public static byte[] compress(byte[] data) {
        System.out.println("before:" + data.length);
        GZIPOutputStream gzip = null ;
        ByteArrayOutputStream baos = null ;
        byte[] newData = null ;

        try {
            baos = new ByteArrayOutputStream() ;
            gzip = new GZIPOutputStream(baos);

            gzip.write(data);
            gzip.finish();
            gzip.flush();

            newData = baos.toByteArray() ;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                gzip.close();
                baos.close() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("after:" + newData.length);
        return newData ;
    }
}
