import java.awt.*;
import java.awt.event.KeyEvent;

public class Worm implements Runnable {

    final int STEP = 20;
    int x,y, xDir, yDir, score, lastMovement = 0, tailCode = 0;
    boolean vertical = false, gameOver = false;
    boolean oppositeDirectionAllowed = true;
    Rectangle worm;
    Rectangle food = new Rectangle((int)(Math.random()*25)*20,(int)(Math.random()*24)*20+20,20,20);

    int[] tailX = new int[100];
    int[] tailY = new int[100];

    public Worm(int x, int y){
        this.x = x;
        this.y = y;

        worm = new Rectangle(this.x, this.y,20,20);
        score = 0;

    }

    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        if(true) {
            g.setColor(Color.BLACK);
            g.drawString("Score :  " + score, 10, 35);
        }
        g.setColor(Color.ORANGE);
        g.fillRect(food.x,food.y,food.width,food.height);

        for(int i = 0; i < tailX.length; i++){
            g.setColor(Color.BLACK);
            g.fillRect(tailX[i],tailY[i],20,20);
        }
        //g.drawString("" + tailX[0] + " " + tailX[1] + " " + tailX[2] + " " + tailX[3] + " " + tailX[4],300,300);
        //g.drawString("" + tailY[0] + " " + tailY[1] + " " + tailY[2] + " " + tailY[3] + " " + tailY[4],300,320);

    }


    public void contact(){
        if(worm.intersects(food)){
            food.x = (int)(Math.random()*25)*20;
            food.y = (int)(Math.random()*24)*20 + 20;
            score++;
            tailCode = lastMovement;
            if(score >= 2) {
                oppositeDirectionAllowed = false;
            }

            if(tailCode == 1){
                tailX[score] = tailX[score - 1];
                tailY[score] = tailY[score - 1] + 20;
            }
            else if(tailCode == 2){
                tailX[score] = tailX[score - 1] - 20;
                tailY[score] = tailY[score - 1];
            }
            else if(tailCode == 3){
                tailX[score] = tailX[score - 1];
                tailY[score] = tailY[score - 1] - 20;
            }
            else if(tailCode == 4){
                tailX[score] = tailX[score - 1] + 20;
                tailY[score] = tailY[score - 1];
            }
        }


        if(tailX[0] < 0 || tailX[0] > 500 || tailY[0] < 10 || tailY[0] > 500){
            gameOver = true;
        }


        if(tailX.length > 3) {
            for (int i = 3; i < tailX.length; i++) {
                if (tailX[0] == tailX[i] && tailY[0] == tailY[i]) {
                    gameOver = true;
                }
            }
        }
    }

    public void update(){
        if(!gameOver) {
            tailX[0] = worm.x;
            tailY[0] = worm.y;

            for (int i = score; i > 0; i--) {
                tailX[i] = tailX[i - 1];
                tailY[i] = tailY[i - 1];
            }
        }

    }

    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();

        if(keyCode == e.VK_UP) {
            if(lastMovement != 3 && !oppositeDirectionAllowed) {
                vertical = true;
                setyDir(-STEP);

                lastMovement = 1;
            }
            else if(oppositeDirectionAllowed){
                vertical = true;
                setyDir(-STEP);

                lastMovement = 1;
            }
        }
        else if(keyCode == e.VK_DOWN) {
            if(lastMovement != 1 && !oppositeDirectionAllowed) {
                vertical = true;
                setyDir(STEP);

                lastMovement = 3;
            }
            else if(oppositeDirectionAllowed){
                vertical = true;
                setyDir(STEP);

                lastMovement = 3;
            }
        }
        else if(keyCode == e.VK_LEFT) {
            if(lastMovement != 2 && !oppositeDirectionAllowed) {
                vertical = false;
                setxDir(-STEP);

                lastMovement = 4;
            }
            else if(oppositeDirectionAllowed){
                vertical = false;
                setxDir(-STEP);

                lastMovement = 4;
            }
        }
        else if(keyCode == e.VK_RIGHT) {
            if(lastMovement != 4 && !oppositeDirectionAllowed) {
                vertical = false;
                setxDir(STEP);

                lastMovement = 2;
            }
            else if(oppositeDirectionAllowed){
                vertical = false;
                setxDir(STEP);

                lastMovement = 2;
            }
        }

    }

    public void gameOverChecker(){
        if(worm.y < 10) {
            worm.y = 10;
            gameOver = true;
        }
        if(worm.x < 0) {
            worm.x = 0;
            gameOver = true;
        }
        if(worm.x+worm.width > 500) {
            worm.x = 500 - worm.width;
            gameOver = true;
        }
        if(worm.y + worm.height > 500) {
            worm.y = 500 - worm.height;
            gameOver = true;
        }
    }

    public void movement() {
        if (!gameOver) {
            if (vertical == false)
                worm.x += xDir;
            if (vertical == true)
                worm.y += yDir;
        }
        else if(gameOver){
            worm.x += 0;
            worm.y += 0;
        }
    }

    public void move(){
        gameOverChecker();
        update();

        if(!gameOver) {
            contact();
            movement();

        }
    }

    public void setxDir(int xDir) {
        this.xDir = xDir;
    }

    public void setyDir(int yDir) {
        this.yDir = yDir;
    }

    @Override
    public void run() {
        try{
            while(true){
                move();
                Thread.sleep(100);
            }
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
