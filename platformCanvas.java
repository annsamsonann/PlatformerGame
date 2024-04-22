import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class platformCanvas  {
    public static void drawRectangle(Graphics g){
        double xCoord= 0;
        double interval= 0;
        double width = 0;
        double yCoord = 850;
        final int numOfLevels = 6;
        final int maxNumOfPlatforms =7;
        final int heightOfPlatform = 30;
        final int yCoordInterval = 150; 
        ArrayList <Rectangle2D> platformsList = new ArrayList<>();
        Color c = Color.BLUE; 
        Graphics2D g2d = (Graphics2D) g;

        //creates an array of leveled platfroms (have the same yCoord)
        for (int l= 0; l<numOfLevels; l++){
            //System.out.printf("\n LEVEL # %d", l);
            for (int i=0; i<maxNumOfPlatforms;i++){
                //System.out.printf(" \nPlatform #%d ",i);
                if (i==0){
                    interval = Randomization.randomInteval();
                    width = Randomization.width();
                    xCoord = Randomization.xCoordFirst(interval,width);
                }
                else{
                    interval = Randomization.randomInteval();
                    width = Randomization.width();
                    xCoord = Randomization.xCoordNext(interval, width);
                }
                //for debugging
                //System.out.printf("Interval is:%f    xCoord is: %f      width is: %f      moved coord is: %f",interval, xCoord, width, RandomizationTrial2.movedCoord);
                Rectangle2D rect = new Rectangle2D.Double(xCoord, yCoord, width, heightOfPlatform);
                platformsList.add(rect);
                if (Randomization.movedCoord>1001){
                    break;
                }
            }
            Randomization.setXCoord(0);
            yCoord-=yCoordInterval;
        }
        g2d.setColor(c );
        for (Rectangle2D rects: platformsList){
            g2d.fill(rects);
            g2d.draw(rects); }

    }
   
}

 