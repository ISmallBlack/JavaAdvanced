package javaknowledge.File;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.LineHandler;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 功能说明: <br>
 * 系统版本: 1.0 <br>
 * 开发人员: zhanch
 * 开发时间: 2019/12/19<br>
 * <br>
 */
public class FileUtilTest {
   //E:\FileTest
    @Test
    public void FileDownload() throws IOException {
        String url = "http://ww1.sinaimg.cn/large/8154e929gy1g8pq78mcgrg20dw0boaja.gif";
        //远程文件下载
        FileUtil.writeFromStream(URLUtil.url(url).openStream(),FileUtil.file("E:\\FileTest\\aa.gif"));
       //
        InputStream fileInputStream =  HttpUtil.createGet(url).header("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                .execute().bodyStream();
        FileUtil.writeFromStream(fileInputStream,FileUtil.file("E:\\FileTest\\bb.gif"));
    }

    @Test
    public void FileRead() throws IOException {
        String  onlyFirstLine = FileUtil.getReader(FileUtil.file("E:\\FileTest\\sss.txt"), CharsetUtil.CHARSET_UTF_8).readLine();
        System.out.println(onlyFirstLine);
        FileUtil.getReader(FileUtil.file("E:\\FileTest\\sss.txt"), CharsetUtil.CHARSET_UTF_8).lines().forEach(System.out::println);
        String url = "http://ww1.sinaimg.cn/large/8154e929gy1g8pq78mcgrg20dw0boaja.gif";
        //远程文件下载
        FileUtil.readUtf8Lines(URLUtil.url(url)).stream().map(str->str.getBytes()).forEach(System.out::println);
        String alllines = FileReader.create(FileUtil.file("E:\\FileTest\\sss.txt")).read((BufferedReader reader) -> reader.lines().collect(Collectors.joining(";")));
        System.out.println(alllines);
        FileReader.create(FileUtil.file("E:\\FileTest\\sss.txt")).readLines((LineHandler) System.out::println);
        System.out.println( FileUtil.readableFileSize(FileUtil.file("E:\\FileTest\\sss.txt")));
        //其实就是个覆盖方法
        File file =  FileUtil.writeToStream(FileUtil.file("E:\\FileTest\\111.txt"),FileUtil.getOutputStream(FileUtil.file("E:\\FileTest\\ssss.txt")));
        System.out.println(FileUtil.mainName(FileUtil.file("E:\\FileTest\\111.txt")));
    }

    @Test
    public void FileWriter() throws IOException {
        File file = FileUtil.file("E:\\FileTest\\111.txt");
        FileUtil.writeLines(Arrays.asList("","ssss","ccc"),file,CharsetUtil.CHARSET_UTF_8,true);
    }

}
