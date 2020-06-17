package com.zhangch.javaknowledge.commonscli;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.cli.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Properties;


public class Sftp {
    private static Options OPTIONS = new Options();
    private static CommandLine commandLine;
    private static String HELP_STRING = null;
    private static ScpInfo scpInfo = new ScpInfo();

    public static void main(String[] args) {
//         String[] argss = {"-h","127.0.0.1","-port","22","-Dext=ext"
//         ,"-s","/user/sss,/user/ssdede","-d","/user/dddd"};
        initCliArgs(args);
//        try {
//            String host = scpInfo.getHost();
//            String user = scpInfo.getUser();
//            String password = scpInfo.getPassword();
//            int port = scpInfo.getPort();
//            String srcPath = scpInfo.getSrcPath();
//            String dstPath = scpInfo.getDstPath();
//
//            JSch jsch = new JSch();
//            Session session = jsch.getSession(user, host, port);
//            session.setConfig("StrictHostKeyChecking", "no");
//            session.setPassword(password);
//            session.connect();
//
//            Channel channel = session.openChannel("sftp");
//            channel.connect();
//            ChannelSftp c = (ChannelSftp) channel;
//
//            c.put(srcPath, dstPath, ChannelSftp.OVERWRITE);
//            session.disconnect();
//            System.exit(0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /**
     * init args
     *
     * @param args args
     */
    private static void initCliArgs(String[] args) {
        // validate args
        {
            CommandLineParser commandLineParser = new DefaultParser();
            // help
            OPTIONS.addOption("help","usage help");
            // host
            OPTIONS.addOption(Option.builder("h").required().hasArg(true).longOpt("host").type(String.class).desc("the host of remote server").build());

            Option property = Option.builder("D").hasArgs()
                    .valueSeparator().desc("use value for a property").argName("property=name").build();
            OPTIONS.addOption(property);


            // port
            OPTIONS.addOption(Option.builder("P").hasArg(true).longOpt("port").type(Short.TYPE).desc("the port of remote server").build());
//            // user
//            OPTIONS.addOption(Option.builder("u").required().hasArg(true).longOpt("user").type(String.class).desc("the user of remote server").build());
//            // password
//            OPTIONS.addOption(Option.builder("p").required().hasArg(true).longOpt("password").type(String.class).desc("the password of remote server").build());
//            // srcPath
            OPTIONS.addOption(Option.builder("s").required().hasArg(true).hasArgs().valueSeparator(',')
                    .longOpt("src_path").type(String.class).desc("the srcPath of local").build());
            // dstPath
            OPTIONS.addOption(Option.builder("d").required().hasArg(true)
                    .longOpt("dst_path").type(String.class).desc("the dstPath of remote").build());

            try {
                commandLine = commandLineParser.parse(OPTIONS, args);
            } catch (ParseException e) {
                System.out.println(e.getMessage() + "\n" + getHelpString());
                System.exit(0);
            }
        }

        // init serverConfigure
        {
            if(commandLine.hasOption("help")){
                System.out.println("\n" + getHelpString());
                System.exit(1);
            }

            // host
            scpInfo.setHost(commandLine.getOptionValue("h"));

            Properties properties = commandLine.getOptionProperties("D");
            String ext = properties.getProperty("ext");
            String dir = properties.getProperty("dir");
            System.out.println("property ext: " + ext + "\tdir:" + dir);
            // port
            String portOptionValue = commandLine.getOptionValue("P");
            short port = portOptionValue == null || "".equals(portOptionValue) ? 22 : Short.parseShort(portOptionValue);
            scpInfo.setPort(port);
//            // user
//            scpInfo.setUser(commandLine.getOptionValue("u"));
//            // password
//            scpInfo.setPassword(commandLine.getOptionValue("p"));
            // srcPath
             String[] src = commandLine.getOptionValues("s");
             scpInfo.setSrcPath(src);
            // dstPath
            scpInfo.setDstPath(commandLine.getOptionValue("d"));
            System.out.println(scpInfo.toString());
        }

    }

    /**
     * get string of help usage
     *
     * @return help string
     */
    private static String getHelpString() {
        if (HELP_STRING == null) {
            HelpFormatter helpFormatter = new HelpFormatter();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintWriter printWriter = new PrintWriter(byteArrayOutputStream);
            helpFormatter.printHelp(printWriter, HelpFormatter.DEFAULT_WIDTH, "scp -help", null,
                    OPTIONS, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD, null);
            printWriter.flush();
            HELP_STRING = new String(byteArrayOutputStream.toByteArray());
            printWriter.close();
        }
        return HELP_STRING;
    }

    /**
     * scp info
     */
    private static class ScpInfo{
        /* connect info */
        String host;
        String user;
        String password;
        int port = 22;

        /* scp info */
        String[] srcPath;
        String dstPath;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String[] getSrcPath() {
            return srcPath;
        }

        public void setSrcPath(String[] srcPath) {
            this.srcPath = srcPath;
        }

        public String getDstPath() {
            return dstPath;
        }

        public void setDstPath(String dstPath) {
            this.dstPath = dstPath;
        }

        @Override
        public String toString() {
            return "ScpInfo{" +
                    "host='" + host + '\'' +
                    ", user='" + user + '\'' +
                    ", password='" + password + '\'' +
                    ", port=" + port +
                    ", srcPath=" + Arrays.toString(srcPath) +
                    ", dstPath='" + dstPath + '\'' +
                    '}';
        }
    }
}