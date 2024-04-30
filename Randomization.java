import java.util.Random;
public class Randomization {
    public static double movedCoord =0; 
    //Upper and lower range for the intervals between platforms
    final static double MaxInterval = 100;
    final static double MinInterval = 20;

    //Upper and lower range for the width of platforms
    final static double MaxWidth = 300;
    final static double MinWidth = 100;

    //creates a random interval within a range
    public static double randomInteval(){
        Random random = new Random();
        double interval = random.nextDouble(MaxInterval)+MinInterval;
        return interval;
    }

    //creates an xCoordinate based on the random interval
    public static double xCoordNext(double randomInteval, double width){
        double xCoord = movedCoord+randomInteval;
        movedCoord += (randomInteval+width);
        return xCoord;
    }
    //x corrdinate for the first platfom on the level
    public static double xCoordFirst(double randomInteval, double width){
        double xCoord = 0+ randomInteval;
        movedCoord = movedCoord + (xCoord+width);
        return xCoord;
    }
    //created random width
    public static double width(){
        Random random = new Random();
        double width = random.nextDouble(MaxWidth)+MinWidth;
        return width;
    }
    public static void setXCoord(double x){
        movedCoord = 0;
    }
}
