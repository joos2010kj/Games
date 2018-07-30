import java.awt.*;
import java.awt.event.KeyEvent;


public class Bird implements Runnable{

    int x, y, velocity = 0, gravity = 1;
    Rectangle bird;
    boolean alive = true, start = false;

     Block block1;

    public Bird(int x, int y){
        this.x = x;
        this.y = y;
        bird = new Rectangle(this.x,this.y,20,20);
        block1 = new Block();
    }

    public void draw(Graphics g){
        g.setColor(Color.PINK);
        g.fillRect(bird.x,bird.y,bird.width,bird.height);
    }

    public void move(){
        if(start) {
            contact();
            if (bird.y + bird.height >= 500 || bird.y < 0) {
                alive = false;
            }
            this.velocity += this.gravity;
            if (!alive) {
                velocity = 0;
                block1.xDir = 0;
            }
            bird.y += this.velocity;
        }

    }

    public void contact(){
        if(bird.intersects(block1.block1) || bird.intersects(block1.block2) ){
            alive = false;
        }
    }

    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode == e.VK_SPACE && alive && start){
            bird.y -= 80; //rises
        }
        this.start = true;
        block1.start = true;
    }
    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode == e.VK_SPACE){
            this.velocity = 0;
            gravity = 1;
        }
    }


    @Override
    public void run() {
        try{
            while(true){
                move();
                Thread.sleep(40);
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
