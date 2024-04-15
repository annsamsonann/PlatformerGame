import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.util.ArrayList;
//JFrame
public class framePlatforms {
    static int x = 1000;
    static int y = 1000;
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE);
        frame.setSize(x,y);
        frame.add(new platformCanvas());
        frame.setVisible(true);
        }
}
//Drawing 
class platformCanvas extends JPanel  {
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawRectangle(g);
        }

    public void drawRectangle(Graphics g){
        int m= 7;
        double xCoord;
        double yCoord = 850;
        Randomization rand = new Randomization();

        final int height = 30;

        ArrayList <Rectangle2D> platformsList = new ArrayList<>();
        Color c = Color.BLUE; 
        Graphics2D g2d = (Graphics2D) g;

        //creates an array of leveled platfroms (have the same yCoord)
        for(int l= 0; l<5; l++){
            for (int i=0; i<m;i++){
                double width = Randomization.randomWidth();
                xCoord = Randomization.randomXCoord();
                Rectangle2D rect = new Rectangle2D.Double(xCoord, yCoord, width, height);
                platformsList.add(rect);
                //xCoord+=300; 
                //random increment insrted of full random?

            }
            Randomization.setXCoord(0);
            yCoord-=200;
        }
        g2d.setColor(c );
        for (Rectangle2D rects: platformsList){
            g2d.fill(rects);
            g2d.draw(rects); }

    }
   
}

   
}
