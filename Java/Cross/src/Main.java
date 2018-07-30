import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JFrame
{

    private Image dbImage;
    private Graphics dbg;
    final int GWIDTH = 1000, GHEIGHT = 500;

    static Wood w1 = new Wood(200,(int)(Math.random()*350)+50);
    static Wood w2 = new Wood(300,(int)(Math.random()*350)+50);
    static Wood w3 = new Wood(400,(int)(Math.random()*350)+50);
    static Wood w4 = new Wood(500,(int)(Math.random()*350)+50);
    static Wood w5 = new Wood(600,(int)(Math.random()*350)+50);
    static Wood w6 = new Wood(700,(int)(Math.random()*350)+50);
    static Wood w7 = new Wood(800,(int)(Math.random()*350)+50);

    static Time time1 = new Time();

    Rectangle floor = new Rectangle(100,0,700,500);

    public Main(){
        setTitle("Cross!");
        setBackground(Color.BLACK);
        setResizable(false);
        setVisible(true);
        setSize(GWIDTH, GHEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new AL());
    }

    public void paint(Graphics g){
       dbImage = createImage(getWidth(),getHeight());
       dbg = dbImage.getGraphics();
       paintComponents(dbg);
       g.drawImage(dbImage,0,0,this);
    }

    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){

            w1.c1.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            w1.c1.keyReleased(e);

        }
    }

    public void paintComponents(Graphics g){

        w1.f1.draw(g);
        w1.draw(g);
        w2.draw(g);
        w3.draw(g);
        w4.draw(g);
        w5.draw(g);
        w6.draw(g);
        w7.draw(g);
        w1.c1.draw(g);
        g.setColor(Color.WHITE);
        if(!w1.done) {
            g.drawString("Death: " + w1.c1.death, 10, 50);
            g.drawString("Time: " + time1.sec, 10, 80);
        }
        if(w1.done){
            g.drawString("CONGRATS! YOU MADE IT", 10, 250);
        }

        repaint();

    }

    public static void main(String[] args) {
        Main game = new Main();
        Thread character = new Thread(w1.c1);
        character.start();

        Thread wood1 = new Thread(w1);
        Thread wood2 = new Thread(w2);
        Thread wood3 = new Thread(w3);
        Thread wood4 = new Thread(w4);
        Thread wood5 = new Thread(w5);
        Thread wood6 = new Thread(w6);
        Thread wood7 = new Thread(w7);


        wood1.start();
        wood2.start();
        wood3.start();
        wood4.start();
        wood5.start();
        wood6.start();
        wood7.start();

        Thread time = new Thread(time1);
        time.start();


    }
}
