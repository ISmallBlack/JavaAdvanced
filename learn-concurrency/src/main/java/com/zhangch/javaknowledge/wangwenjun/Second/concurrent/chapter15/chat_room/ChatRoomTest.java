package com.zhangch.javaknowledge.wangwenjun.Second.concurrent.chapter15.chat_room;

import java.io.IOException;

public class ChatRoomTest {

    public static void main(String[] args) throws IOException {
        new ChatServer().startServer();
    }

}
