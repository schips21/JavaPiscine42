package edu.school21.printer.app;

import edu.school21.printer.logic.ImageConverter;

import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        if (args.length != 3 || args[0].length() != 1 || args[1].length() != 1){
            System.err.println("Invalid arguments");
            System.exit(1);
        }
        ImageConverter imageConverter = new ImageConverter(args[0].charAt(0), args[1].charAt(0), args[2]);
        try {
            imageConverter.makeConvert();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
