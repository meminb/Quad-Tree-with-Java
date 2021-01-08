/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.quadtree;

import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author muham
 */
public class main {

    public static void main(String[] args) {

        int[][] picture = new int[250][250];

        for (int i = 0; i < 250; i++) {//creating an image with random pixels
            for (int j = 0; j < 250; j++) {
                int a = (int) (Math.random() * 256);
                int r = (int) (Math.random() * 256);
                int g = (int) (Math.random() * 256);
                int b = (int) (Math.random() * 256);
                int p = (a << 24) | (r << 16) | (g << 8) | b;
                picture[i][j] = p;//randomly created picture
            }
        }

        QuadTree qt = new QuadTree(picture);

        int[][] datas = qt.read(qt.parent, 0, 250, 0, 250);//decryption from quadTree object

        
        Frame frame0 = new Frame("Before the encrypting", picture);
        frame0.setSize(250, 250);
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.setVisible(true);
        
        
        
        Frame frame1 = new Frame("After the decrypting", datas);
        frame1.setSize(250, 250);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);

    }

    static class Frame extends JFrame {

        String title;
        Pan pen;

        public Frame(String title, int[][] datas) throws HeadlessException {
            super(title);
            this.pen = new Pan(datas);
            this.add(pen);
        }

    }

    static class Pan extends JPanel {

        BufferedImage image;

        public Pan(int[][] datas) {
            this.image = new BufferedImage(250, 250, BufferedImage.TYPE_INT_ARGB);
            for (int i = 0; i < 250; i++) {//converting int array to picture
                for (int j = 0; j < 250; j++) {
                    image.setRGB(i, j, datas[i][j]);
                }
            }

        }

        @Override
        public void paint(Graphics g) {
            super.paint(g); //To change body of generated methods, choose Tools | Templates.
            g.drawImage(image, 0, 0, this);
        }

    }
}
