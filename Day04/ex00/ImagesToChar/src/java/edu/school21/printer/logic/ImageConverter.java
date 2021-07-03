package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageConverter {
    char white;
    char black;
    String imagePath;

    public ImageConverter(char w, char b, String path){
        white = w;
        black = b;
        imagePath = path;
    }

    public void makeConvert() throws IOException {
        File file = new File(imagePath);
        BufferedImage image = ImageIO.read(file);
        char [][]arr = new char[16][16];
        for (int i = 0; i < 16; i++){
            for (int j = 0; j < 16; j++){
                Color color = new Color(image.getRGB(j, i));
                if (color.getBlue() < 60){
                    arr[i][j] = black;
                } else {
                    arr[i][j] = white;
                }
            }
        }
        for (int i = 0; i < 16; i++){
            for (int j = 0; j < 16; j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}
