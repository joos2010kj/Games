import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle implements Runnable{

    int x, y, yDir, id;
    Rectangle paddle;
    public static int PADDLE_WIDTH = 10, PADDLE_HEIGHT = 50;
    public Paddle(int x_, int y_,int id){
        this.x = x_;
        this.y = y_;
        this.id = id;

        paddle = new Rectangle(this.x,this.y,PADDLE_WIDTH,PADDLE_HEIGHT);
    }

    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();

        switch(this.id) {
            default: System.out.println("Please enter the correct ID");
                break;

            case 2:
                if (keyCode == e.VK_UP) {
                    setyDir(-1);
                }
                if (keyCode == e.VK_DOWN) {
                    setyDir(1);
                }
                break;
            case 1:
                if (keyCode == e.VK_W) {
                    setyDir(-1);
                }
                if (keyCode == e.VK_S) {
                    setyDir(1);
                }
                break;

        }
    }

    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();

        switch(this.id) {
            default: System.out.println("Please enter the correct ID");
                break;

            case 2:
                if (keyCode == e.VK_UP) {
                    setyDir(0);
                }
                if (keyCode == e.VK_DOWN) {
                    setyDir(0);
                }
                break;
            case 1:
                if (keyCode == e.VK_W) {
                    setyDir(0);
                }
                if (keyCode == e.VK_S) {
                    setyDir(0);
                }
                break;

        }
    }

    public void draw(Graphics g){
        switch(this.id) {
            default:
                System.out.println("Please enter the correct ID");
                break;
            case 1:
                g.setColor(Color.CYAN);
                //shape
                g.fillRect(paddle.x,paddle.y,paddle.width,paddle.height);
                break;
            case 2:
                g.setColor(Color.PINK);
                //shape
                g.fillRect(paddle.x,paddle.y,paddle.width,paddle.height);
                break;

        }
    }

    public void move(){
        paddle.y += yDir;
        if(paddle.y >= 700-paddle.height){   //paddle.y vs. this.y ???
            paddle.y = 700-paddle.height;    //What's the difference?
                                             /*
                                                I thought paddle.y and this.y were the same because they had the
                                                same value.  However, when I replace paddle.y with this.y, neither of
                                                paddles move at all.

                                              */
        }
        if(paddle.y <= 0){
            paddle.y = 0;
        }

    }

    public void setyDir(int yDir) {
        this.yDir = yDir;                   //for example, why is it not paddle.yDir ??
    }

    @Override
    public void run() {
        try{
            while(true){
                move();
                Thread.sleep(3);
            }
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
