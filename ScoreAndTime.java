import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class ScoreAndTime {
    public static int min;
    public static int sec; 
    public static String time ="You have 2:00 left ";
    public static String scoreSt ="You have 0 point ";

    static Color c = Color.GRAY; 

    public ScoreAndTime(int secLeft, int score){
        //calculate min and sec from sec 
        min = (secLeft % 3600) / 60;
        sec = secLeft % 60;   

        //string format in min and sec
        time =  String.format("You have %02d:%02d left ", min, sec);
        scoreSt = String.format("You have %d point", score);
    }

    public static void drawTime(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(g2.getFont().deriveFont(20f));
        g2.setColor(c);
        g2.drawString(time,730,100); 
    }
    public static void drawScore(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(g2.getFont().deriveFont(20f));
        g2.setColor(c);
        g2.drawString(scoreSt,730,150); 
    }
    
}
