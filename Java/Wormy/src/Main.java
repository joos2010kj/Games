import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JFrame {

    private Image dbImage;
    private Graphics dbg;
    int lineX = 0, lineY;
    static Worm wormy = new Worm(260,260);

    @Override
    public void paintComponents(Graphics g) {

        for(int i = 0; i < 25; i++) {
            g.drawLine(lineX + i*20, 0, lineX + i*20, 500);
            g.drawLine(0, lineY + i*20, 500, lineY+i*20);
        }

        wormy.draw(g);
        repaint();

    }

    public class AL extends KeyAdapter{

        public void keyPressed(KeyEvent e){
            wormy.keyPressed(e);
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
        setTitle("Wormy");
        setVisible(true);
        setResizable(false);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new AL());
    }

    public static void main(String[] args) {
        Main game = new Main();
        Thread wormy1 = new Thread(wormy);
        wormy1.start();
    }

}
