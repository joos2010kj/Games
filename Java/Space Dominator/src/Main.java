import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JFrame
{
    private Image dbImage;
    private Graphics dbg;
    static Spaceship s1 = new Spaceship(250,450);

    public static void main(String[] args) {
        Main game = new Main();
        Thread ship = new Thread(s1);
        ship.start();
        Thread mons1 = new Thread(s1.monsters[0]);
        Thread mons2 = new Thread(s1.monsters[1]);
        Thread mons3 = new Thread(s1.monsters[2]);
        Thread mons4 = new Thread(s1.monsters[3]);
        Thread mons5 = new Thread(s1.monsters[4]);
        mons1.start();
        mons2.start();
        mons3.start();
        mons4.start();
        mons5.start();
    }


    @Override
    public void paintComponents(Graphics g) {
        s1.draw(g);
        s1.monsters[0].draw(g);
        s1.monsters[1].draw(g);
        s1.monsters[2].draw(g);
        s1.monsters[3].draw(g);
        s1.monsters[4].draw(g);
        repaint();
    }


    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            s1.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            s1.keyReleased(e);
        }
    }

    public Main(){
        setBackground(Color.BLACK);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setTitle("Space Dominator");
        addKeyListener(new AL());
    }

    @Override
    public void paint(Graphics g) {
        dbImage = createImage(getWidth(),getHeight());
        dbg = dbImage.getGraphics();
        paintComponents(dbg);
        g.drawImage(dbImage,0,0,this);
    }

}
