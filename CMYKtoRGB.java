/*
 * Kyle Krstulich
 * 8/28/23
 * CSCI152
 * CMYKtoRGB.java
 * 
 * Takes 4 command line arguments as a CMYK color code. Converts it to RGB.
 */

public class CMYKtoRGB{


    public static void main(String[] args) {

        float white = 1 - Float.parseFloat(args[3]);
        float red = 255 * white * (1-(Float.parseFloat(args[0])));
        float green = 255 * white * (1-Float.parseFloat(args[1]));
        float blue = 255 * white * (1-Float.parseFloat(args[2]));

        System.out.println("Red = " + (int)red);
        System.out.println("Green = " + (int)green);
        System.out.println("Blue = " + (int)blue);
    }
}