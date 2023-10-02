/*
 * Kyle Krstulich
 * 9/29/23
 * Animation.java
 * 
 * Please create a short (10 - 15 second) animation using the StdDraw library in Java.  Please use at least 3 different shapes, and image, text, and different colors.  Lastly, incorporate user interaction into your animation.
 */

public class Animation {


    final static int ANIMATION_TIME = 50;//In milliseconds

    /*
     * Description:
     *      Using the StdDraw library this method draws an equallatreral triangle. It is drawn relevive 
     *      to its left most point. It is always drawn piramid style with a side length argument.
     * 
     *  Args:
     *      pointX: the x coordanate of the leftmost point
     *      pointY: the y coordanate of the leftmost point
     *      triangleSideLength: The side length of the equal triangle.
     * 
     *  Returns:
     *      None
     *  
     */
    public static void draw_triangle(double pointX, double pointY, double triangleSideLength){
        
        double[] xValues = {pointX, pointX+triangleSideLength, pointX+(triangleSideLength/2)};
        double[] yValues = {pointY, pointY, pointY+triangleSideLength};
        StdDraw.filledPolygon(xValues, yValues);

    }
    public static void main(String[] args) {


        boolean mousePressed = false;



        // Triangle Variables
        double triPointX = 0;
        double triPointY = 0;
        double triangleSideLength = .25;
        double theta = Math.PI * 2;
        double radius = 1;
        double triMod = 0;

        // Octigon Variables
        double octigonSideLength = .75;
        double S = (1+Math.sqrt(2)) * octigonSideLength;
        double[] octigonXPoints = {-octigonSideLength/2 + triangleSideLength/2,
                                    octigonSideLength/2 + triangleSideLength/2,
                                    S/2 + triangleSideLength/2,
                                    S/2 + triangleSideLength/2,
                                    octigonSideLength/2 + triangleSideLength/2,
                                    -octigonSideLength/2 + triangleSideLength/2,
                                    -(S/2) + triangleSideLength/2,
                                    -(S/2) + triangleSideLength/2};
        double[] octigonYPoints = {S/2 + triangleSideLength/2,
                                   S/2 + triangleSideLength/2,
                                   octigonSideLength/2 + triangleSideLength/2,
                                   -(octigonSideLength/2) + triangleSideLength/2,
                                   -(S/2) + triangleSideLength/2,
                                   -(S/2) + triangleSideLength/2,
                                   -(octigonSideLength/2) + triangleSideLength/2,
                                    octigonSideLength/2 + triangleSideLength/2};
        
        //Square Variables
        double squarePointX = 0;
        double squarePointY = 0;
        double squareMod = 0;
        double squareSideLength = .15;


        // set the scale of the coordinate system
        StdDraw.setXscale(-1, 1.0+triangleSideLength);
        StdDraw.setYscale(-1, 1.0+triangleSideLength);
        StdDraw.enableDoubleBuffering();



        while(true){

            /* 
             * ----------------------------------------------
             * Mouse Events 
             * ----------------------------------------------
            */
            if(StdDraw.isMousePressed()){
                if(mousePressed){
                    System.out.println("X: " + StdDraw.mouseX() + "\nY: " + StdDraw.mouseY());
                    theta = -theta;
                    mousePressed = false;
                }
                else{
                    mousePressed = true;
                }
            }

            /* 
             * ----------------------------------------------
             * Clear Screen for Animation 
             * ----------------------------------------------
            */
            StdDraw.clear();

            /* 
             * ----------------------------------------------
             * Animation Logic
             * ----------------------------------------------
            */

            //Picture
            StdDraw.picture(triangleSideLength/2, triangleSideLength/2, "Monkey.jpeg", 4, 4, 0);

            // Octogon 
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius(.025);
            StdDraw.polygon(octigonXPoints, octigonYPoints);
    
            //Triangle
            triPointX = radius *  Math.cos(triMod);
            triPointY = radius *  Math.sin(triMod);
            triMod += theta/60;
            StdDraw.setPenColor(StdDraw.RED);
            draw_triangle(triPointX, triPointY, triangleSideLength);

            //Square
            squarePointX = (radius/2) * Math.cos(squareMod)+triangleSideLength/2;
            squarePointY = (radius/2) * Math.sin(squareMod) + triangleSideLength/2;
            squareMod += -theta/120;
            StdDraw.setPenColor(StdDraw.PINK);
            StdDraw.filledSquare(squarePointX, squarePointY, squareSideLength);
            
            // Text
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(triangleSideLength/2, triangleSideLength/2, "Click to change direction!", Math.toDegrees(-triMod));


            /* 
             * ----------------------------------------------
             * Show Screen for Animation 
             * ----------------------------------------------
            */
            StdDraw.show();
            StdDraw.pause(ANIMATION_TIME);
        }

    }
    
}