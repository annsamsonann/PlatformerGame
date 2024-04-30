import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.GradientPaint;  
public class platformCanvas {
    final static int numOfLevels = 46;
    final static int maxNumOfPlatforms = 3;
    final static int heightOfPlatform = 30;
    final static int yCoordInterval = 150; 
    ArrayList <Rectangle2D> platformsList = new ArrayList<>();
    public double yCoord = 850;
    double xCoord= 0;
    double interval= 0;
    double width = 0;
    Color grad2 = new Color( 255,  158,  158,  250); 

    public platformCanvas(){
        createPlatforms();
    }
    public void drawRectangle(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp1 = new GradientPaint(500, 1000, Color.ORANGE, 150, 150, grad2);  
        for (Rectangle2D rects: platformsList){
            g2d.setPaint(gp1);  
            g2d.fill(rects);
            g2d.draw(rects);}
    }
    // creates an array of Rectange2d objects 
    public void createPlatforms(){
        //loop for levels
        for (int l= 0; l<numOfLevels; l++){
            //loop for platforms in each level
            for (int i=0; i<maxNumOfPlatforms;i++){
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
                Rectangle2D rect = new Rectangle2D.Double(xCoord, yCoord, width, heightOfPlatform);
                if (rect.getX()>1001){
                    break;
                }
                platformsList.add(rect);
            }
            //resets the Xcoordintate so that the first platform of the next level is incremented from 0
            Randomization.setXCoord(0);
            yCoord-= yCoordInterval;
        }

    }
    //method used by the timer to make the platforms move. Change in each platform yCoord + 1 
    public void updateY(){
        double x,y, w,h;
        for (Rectangle2D platform : platformsList){
            x = platform.getX();
            w = platform.getWidth();
            h = platform.getHeight();
            y = platform.getY() + 1;
            platform.setRect( x,  y,  w,  h);
        }
    }
    //for the end of the game - removes platforms form the screen 
    public void End(){
        platformsList.clear();
    }
}

 
