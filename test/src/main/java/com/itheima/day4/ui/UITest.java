package com.itheima.day4.ui;

import javax.swing.*;

// 在 UI 设计时的应用
public class UITest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        JButton button = new JButton("点我");
        button.addActionListener(e-> System.out.println("已点击"));
        frame.add(button);

        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
