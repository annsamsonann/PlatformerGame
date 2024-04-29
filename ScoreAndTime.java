
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.GradientPaint; 

import java.awt.geom.RoundRectangle2D;
public class ScoreAndTime {
    public static int min;
    public static int sec; 
    public static String time ="You have 2:00 left ";
    public static String scoreSt ="You have 0 point ";
    public static String end =" ";
    public static int xTime = 730;
    public static int yTime = 100 ; 
    public static int xScore = 730;
    public static int yScore = 150 ; 
    static RoundRectangle2D rect = new RoundRectangle2D.Double(-100,-100,1,1, 10,10);

    static Color grad2 = new Color( 255,  158,  158,  250); 
    static GradientPaint gp1 = new GradientPaint(500, 1000, Color.DARK_GRAY, 150, 150, Color.DARK_GRAY);  
    static GradientPaint gp2 = new GradientPaint(500, 1000, Color.ORANGE, 500, 150, grad2);  
    

    static Color c = Color.GRAY; 

    public ScoreAndTime(int secLeft, int score){
        //calculate min and sec from sec 
        min = (secLeft % 3600) / 60;
        sec = secLeft % 60;   

        //string format in min and sec
        time =  String.format("You have %02d:%02d left ", min, sec);
        scoreSt = String.format("You earned %d points", score);
    }
    public static int getTimeLeft(){
        int totalSecLeft = sec + (min*60);
        return totalSecLeft;

    }

    public static void drawTime(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(gp2); 
        g2.fill(rect);
        g2.draw(rect);
        g2.setFont(g2.getFont().deriveFont(20f));
        g2.setPaint(gp1); 
        g2.drawString(time,xTime,yTime); 
        g2.drawString(end,280,yTime-50); 

    }
    public static void drawScore(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(g2.getFont().deriveFont(20f));
        g2.setPaint(gp1); 
        g2.drawString(scoreSt,xScore,yScore); 
    }
    public static void EndDrawTime(){
        xTime = 400;
        yTime = 500;
        xScore = 400;
        yScore = 550;
       
       
        end =" Congratulations! You won with the following score: ";
        RoundRectangle2D rectEnd = new RoundRectangle2D.Double(230,yTime-100,600,200,50,50);
        rect=rectEnd;

        
    }
}
