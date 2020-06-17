package com.zhangch.javaknowledge.commonscli;

import org.apache.commons.cli.*;

public class CLI {
    public static void main(String[] args) throws ParseException {
        //定义
        Options options = new Options();
        options.addOption("h",false,"list help");//false代表不强制有
        options.addOption("t",true,"set time on system");



        //解析
        //1.3.1中已经弃用针对不同格式入参对应的解析器
        //CommandLineParser parser = new PosixParser();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options,args);

        //查询交互
        //你的程序应当写在这里，从这里启动
        if (cmd.hasOption("h")){
            String formatstr = "CLI  cli  test";
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp(formatstr, "", options, "");
            return;
        }

        if (cmd.hasOption("t")){
            System.out.printf("system time has setted  %s \n",cmd.getOptionValue("t"));
            return;
        }

        System.out.println("error");
    }
}