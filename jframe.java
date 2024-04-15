import javax.swing.JFrame;

public class jframe {
    static int x = 1000;
    static int y = 1000;
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE);
        frame.setSize(x,y);
        //after add in brakets insert your canvas class 
        frame.add(new platformCanvas());
        frame.setVisible(true);
        }
}

