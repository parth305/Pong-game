package Pong2;

import javax.swing.*;
import java.awt.*;

public class MyframePong2 extends JFrame {
    MyPnaelPong2  panel;
    MyframePong2(){
        panel=new MyPnaelPong2();

        this.add(panel);
       // this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setBackground(Color.black);
        this.pack();
    }

}
