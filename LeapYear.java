/* 
Kyle Krstulich
9/1/23
LeapYear.java 

Write a program to determine if it's a leap year i.e. print out the year entered by the user (from input), and "Yes" or "No" depending on a boolean value telling you whether that year is a leap year or not.
 */
public class LeapYear {
    
    public static void main(String[] args) {
        boolean year = Integer.parseInt(args[0]) % 4 == 0;
        System.out.println(year);
    }
}
