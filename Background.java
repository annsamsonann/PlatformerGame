/**
 * CSC 171
 * Platformer Game: Background Class
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.GradientPaint;  


public class Background extends JPanel implements KeyListener {
    static Color cloudsBlue = new Color( 111,  174,  255); 
    static GradientPaint gp2 = new GradientPaint(500, 0, Color.WHITE, 500, 150, cloudsBlue);  
    static int isDead = 0; 

    Timer movePlatforms = new Timer(20, new movePlatformsTimer());
    Timer moveRocket = new Timer(20, new TimerCallback2());
    Timer addRocket = new Timer(5000, new TimerCallback3());
    Timer timeElapsedTimerTimer = new Timer(1000, new timeElapsedTimerTimer());
    Timer checkIfAlive = new Timer(10, new checkIfAliveTimer());


        

    platformCanvas platforms = new platformCanvas();
    Player player = new Player();
    Image img = Toolkit.getDefaultToolkit().createImage("background.png");

	public ArrayList<Cloud> clouds;
    // creating clouds
    private static class Cloud {
        int x;
        int y;
        int size;

        public Cloud(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        public void drawCloud(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(gp2);
            g.fillOval(x, y, size, size / 2); // multiple ovals interact and create apprx cloud shape
            g.fillOval(x + size / 3, y - size / 4, size, size / 2);
            g.fillOval(x + size / 2, y, size, size / 2);
        }
    }
    
    // cloud generation
    public void generateClouds() {
        Random random = new Random();
        int numClouds = random.nextInt(5) + 5; // randomly generate between 3-5 clouds
        for (int i = 0; i < numClouds; i++) {
            int x = random.nextInt(1000);
            int y = random.nextInt(500);
            int size = random.nextInt(50) + 50; // random cloud size btwn 50-100 pixels
            clouds.add(new Cloud(x, y, size));
        }      
        
    }
    
    // draw clouds
    public void drawClouds(Graphics g) {
        for (Cloud cloud : clouds) {
            cloud.drawCloud(g);
        }
    }
    // create the background
    public Background() {
        clouds = new ArrayList<>();
        generateClouds();
        movePlatforms.start();
        moveRocket.start();
        addRocket.start();
        Rocket.addRocket();
      //timer for updating score and time left
        timeElapsedTimerTimer.start();
        checkIfAlive.start();
        setFocusable(true); // Ensure the panel can receive key events
        addKeyListener(this);
        // end game if player is not alive anymore

    }
    // override and draw clouds when panel is painted
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, -300, null);
        drawClouds(g);
        platforms.drawRectangle(g);
        player.drawPlayer(g);
        Rocket.drawRockets(g);
        ScoreAndTime.drawTime(g);
        ScoreAndTime.drawScore(g);
    }
    protected class checkIfAliveTimer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
            if (player.isAlive() == false) {
                System.out.println("END");
                movePlatforms.stop();
                
            }
        }
    }


    protected class movePlatformsTimer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
            boolean m = false;
            platforms.updateY();
            for (Rectangle2D platform: platforms.platformsList){
                m = player.collidesWithPlatform(platform);
                //if collision is detected then
                if(m==true){
                    //System.out.println("COLLISION");
                    
                    boolean side = player.collidesWithPlatformTop(platform);
                    if (side == true) {
                    	player.hitTopPlatform();
                    } else {
                    	player.hitBottomPlatform();
                    }
                    
                }
            }
            repaint();
        }
    }
    //time left and score event
    protected class timeElapsedTimerTimer implements ActionListener {
    static int totalTime = 120;
    static int timeLeft;
		public void actionPerformed(ActionEvent e) {
            timeLeft = totalTime -1;
            totalTime -=1;
            ScoreAndTime timeElapsed = new ScoreAndTime(timeLeft,player.getScore());
            repaint();
        }
    }
    protected class playerSmoothTimer implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }
    protected class TimerCallback2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			boolean r = false;
            for (Rocket rocket : Rocket.rockets) {
                rocket.move();
                r = player.collidesWithRocket(rocket);
                if (r==true) {
                	System.out.println("GAME OVER");
                	player.hitObstacle();
                    System.out.println(player.isAlive());
                }
                
            }
            repaint();
        }
    }
    protected class TimerCallback3  implements ActionListener {
		public void actionPerformed(ActionEvent e) {
            Rocket.addRocket();
            repaint();
        }
    }


    // key presses
    @Override
    public void keyPressed (KeyEvent e) {
        int keyCode = e.getKeyCode();
        int moveDistance = 10; // dist the player moves when key is pressed

        // check which arrow key was pressed
        switch (keyCode) {
            case KeyEvent.VK_UP:
                player.moveUp(10);
                break;
            case KeyEvent.VK_DOWN:
                player.moveDown(10);
                break;
            case KeyEvent.VK_LEFT:
                player.moveLeft(10);
                break;
            case KeyEvent.VK_RIGHT:
                player.moveRight(10);
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // handle key releases
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Handle key typing
    }

}
