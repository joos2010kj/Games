import java.awt.*;
import java.awt.event.KeyEvent;

public class Character implements Runnable {

    int x,y, xDirection, yDirection, death;
    Rectangle character;




    public Character(int x, int y) {
        this.x = x;
        this.y = y;
        character = new Rectangle(this.x,this.y,10,10);
        this.death = 0;
    }

    public void setxDirection(int xDirection){
        this.xDirection = xDirection;
    }

    public void setyDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(character.x,character.y,10,10);


    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == e.VK_UP) {
            //setyDirection(-1);
        }
        if (keyCode == e.VK_DOWN) {
            //setyDirection(1);
        }
        if (keyCode == e.VK_RIGHT) {
            setxDirection(1);
        }
        if (keyCode == e.VK_LEFT) {
            setxDirection(-1);
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == e.VK_UP) {
            setyDirection(0);
        }
        if (keyCode == e.VK_DOWN) {
            setyDirection(0);
        }
        if (keyCode == e.VK_RIGHT) {
            setxDirection(0);
        }
        if (keyCode == e.VK_LEFT) {
            setxDirection(0);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                move();
                Thread.sleep(3);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    public void move() {

        character.x += xDirection;
        character.y += yDirection;

    }

}