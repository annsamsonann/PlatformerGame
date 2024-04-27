
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rocket {
    int x, y;
    public static List<Rocket> rockets = new ArrayList<>();
    public Rocket(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //Made this move method assuming that rockets fly from right border of the screen to the left
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 10, 10); // Draw rocket as a red square
    }
    public void move() {
        x -= 5; // Move rocket leftward
    }
    public static void addRocket(){
        rockets.add(new Rocket(1000, 200));
        rockets.add(new Rocket(1000, 400));
        rockets.add(new Rocket(1000, 600));
        rockets.add(new Rocket(1000, 800));
    }
    public static void drawRockets(Graphics g) {
        for (Rocket rocket : rockets) {
            rocket.draw(g);
        }
    }
}
