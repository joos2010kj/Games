import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JFrame{

    static final int GWIDTH = 500, GHEIGHT = 700;
    Dimension screenSize = new Dimension(GWIDTH,GHEIGHT);
    private Image dbImage;
    private Graphics dbg;
    static Ball b = new Ball(GWIDTH/2,GHEIGHT/2);


    public Main(){
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Pong!");
        setSize(screenSize);
        setVisible(true);

        addKeyListener(new AL());
    }

    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            b.p1.keyPressed(e);
            b.p2.keyPressed(e);

        }
        public void keyReleased(KeyEvent e){
            b.p1.keyReleased(e);
            b.p2.keyReleased(e);

        }

    }

    public void paintComponent(Graphics g){

        b.draw(g);
        b.p1.draw(g);
        b.p2.draw(g);
        g.drawString("Player 1: " + b.p1Score,40,50);
        g.drawString("Player 2: " + b.p2Score,500-100,50);

        repaint();

    }

    public void paint(Graphics g){
        dbImage = createImage(getWidth(),getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage,0,0,this);
    }

    public static void main(String[] args) {
        Main game1 = new Main();
        Thread ball = new Thread(b);
        ball.start();

        Thread p1 = new Thread(b.p1);
        Thread p2 = new Thread(b.p2);
        p1.start();
        p2.start();

    }


}
