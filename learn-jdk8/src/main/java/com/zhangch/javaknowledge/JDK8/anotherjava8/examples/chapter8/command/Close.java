package com.zhangch.javaknowledge.JDK8.anotherjava8.examples.chapter8.command;

public class Close implements Action {

    private final Editor editor;

    public Close(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void perform() {
        editor.close();
    }

}
