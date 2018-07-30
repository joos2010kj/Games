import java.awt.*;

public class Monster implements Runnable{
    Rectangle monster;
    int x,y;
    boolean start = false;
    static boolean gameOver = false;
    public Monster(int x){
        this.x = x;
        this.y = 0;

        monster = new Rectangle(this.x, this.y, 30,10);
    }
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(monster.x,monster.y,monster.width,monster.height);
        if(gameOver){
            g.setColor(Color.WHITE);
            g.drawString("GAME OVER", 210, 200);
        }

        if(true) {
            g.setColor(Color.CYAN);
            g.fillRect(0, 450, 500, 5);
        }
    }

    public void move(){
        if(start) {
            if(!gameOver) {
                monster.x += (int) (Math.random() * 16) - 8;
                monster.y += 1;
            }

            if (monster.x < 0) {
                monster.x = 0;
            }

            if (monster.x + monster.width >= 500) {
                monster.x = 500 - monster.width;
            }
            if (monster.y + monster.height >= 450) {
                monster.y = 450 - monster.height;
                gameOver = true;
            }
        }
    }

    @Override
    public void run() {

        try{
            while(true){
                move();
                Thread.sleep(10);
            }
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
