package main;

import javax.swing.*;

public class PigAndCoin {
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setBounds(400,200,950,800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PigAndCoinPanel panel=new PigAndCoinPanel();
        frame.add(panel);

        frame.setVisible(true);
    }
}
