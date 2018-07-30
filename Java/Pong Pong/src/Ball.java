import java.awt.*;

public class Ball implements Runnable {

    int x, y, xDir, yDir;
    Rectangle ball;
    Paddle p1 = new Paddle(15,140,1);
    Paddle p2 = new Paddle(500-10-15, 140,2);
    int p1Score, p2Score;

    public Ball(int x_, int y_){
        p1Score = p2Score = 0;

        this.x = x_;
        this.y = y_;

        //make the ball more randomly
        int rDir, yrDir;
        if(Math.random() > 0.5)
            rDir = 1;
        else
            rDir = -1;

        setxDir(rDir);

        if(Math.random() > 0.5)
            yrDir = 1;
        else
            yrDir = -1;

        setyDir(yrDir);
        //Create a ball

        ball = new Rectangle(x,y, 15,15);
    }

    public void collision(){

        if(ball.intersects(p1.paddle) || ball.intersects(p2.paddle)){
            this.xDir *= -1;
        }
    }

    public void setxDir(int xDir){
        this.xDir = xDir;
    }

    public void setyDir(int yDir){
        this.yDir = yDir;
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(ball.x,ball.y,ball.width,ball.height);

    }

    public void move(){
        collision();

        ball.x += xDir;
        ball.y += yDir;

        if( ball.x > 500 - 15 ){
            xDir *= -1;
            p1Score++;
        }
        if( ball.x < 0 ){
            xDir *= -1;
            p2Score++;
        }
        if( ball.y < 0 ){
            yDir *= -1;
        }
        if( ball.y > 700-15 ){
            yDir *= -1;
        }


    }

    public void run() {
        try{

            while(true){
                move();
                Thread.sleep(5);
            }
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

}
