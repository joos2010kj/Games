import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JFrame{

    private Image dbImage;
    private Graphics dbg;

    static Map m1 = new Map(50); //type in the number of bombs you want there to be in the argument

    @Override
    public void paintComponents(Graphics g) {

        m1.draw(g);
        repaint();
    }


    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            m1.keyPressed(e);
        }
    }

    @Override
    public void paint(Graphics g) {
        dbImage = createImage(getWidth(),getHeight());
        dbg = dbImage.getGraphics();
        paintComponents(dbg);
        g.drawImage(dbImage,0,0,this);
    }

    public Main(){
        setTitle("Mind Sweeper");
        setVisible(true);
        setResizable(false);
        setSize(440,460);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new AL());

    }

    public static void main(String[] args) {
        Main game = new Main();
        Thread mover = new Thread(m1);
        mover.start();

    }
}
