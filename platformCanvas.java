import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
//сделать чтобы рандомный аррей создавался один раз и не репейнтился каждый раз 

//сделать чтоб он двигался вверх 
public class platformCanvas {
    static Color c = Color.BLUE; 
    final static int numOfLevels = 10;
    final static int maxNumOfPlatforms =7;
    final static int heightOfPlatform = 30;
    final static int yCoordInterval = 150; 
    ArrayList <Rectangle2D> platformsList = new ArrayList<>();
    double yCoord = 850;

    public platformCanvas(){
        createPlatforms();
    }
    public void drawRectangle(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(c);
        for (Rectangle2D rects: platformsList){
            g2d.fill(rects);
            g2d.draw(rects);}
    }
    public void createPlatforms(){
        double xCoord= 0;
        double interval= 0;
        double width = 0;
        for (int l= 0; l<numOfLevels; l++){
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
                platformsList.add(rect);
                if (Randomization.movedCoord>1001){
                    break;
                }
            }
            Randomization.setXCoord(0);
            yCoord-=yCoordInterval;
        }
    }
    public static void setColor(Color c2){
        c = c2;
    }

    public void movePlatforms(){

    }
}

 