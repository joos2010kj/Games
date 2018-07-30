import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JFrame{

    private Image dbImage;
    private Graphics dbg;

    static Bird b1 = new Bird(100,250);

    @Override
    public void paintComponents(Graphics g) {

        b1.draw(g);
        b1.block1.draw(g);

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        dbImage = createImage(getWidth(),getHeight());
        dbg = dbImage.getGraphics();
        paintComponents(dbg);
        g.drawImage(dbImage,0,0,this);
    }

    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){

            b1.keyPressed(e);

        }
        public void keyReleased(KeyEvent e){

            b1.keyReleased(e);

        }

    }

    public Main(){
        setTitle("Flappy Bird");
        setBackground(Color.DARK_GRAY);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        addKeyListener(new AL());

    }

    public static void main(String[] args) {
        Main game = new Main();

        Thread bird = new Thread(b1);
        bird.start();
        Thread block1 = new Thread(b1.block1);
        block1.start();



    }

}
