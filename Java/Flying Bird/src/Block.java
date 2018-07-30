import java.awt.*;


public class Block implements Runnable{

    int x, y, width, height, xDir, score;
    final int GAP = 130;
    Rectangle block1, block2;
    static boolean start = false;


    public Block(){
        this.x = 500;
        this.y = 0;
        this.width = 100;
        this.height = (int)(Math.random()*300);

        block1 = new Rectangle(this.x,this.y,this.width,this.height);
        block2 = new Rectangle(this.x,block1.height+GAP,this.width,400-block1.y);

        xDir = -1;
        score = 0;
    }

    public void draw(Graphics g){

        g.setColor(Color.WHITE);
        g.drawString("Score: " + score,50,50);

        switch(score%5) {
            case 0: g.setColor(Color.ORANGE);
            break;
            case 1: g.setColor(Color.PINK);
            break;
            case 2: g.setColor(Color.GREEN);
            break;
            case 3: g.setColor(Color.BLACK);
            break;
            case 4: g.setColor(Color.RED);
            break;
            default:
        }

        g.fillRect(block1.x, block1.y, block1.width, block1.height);
        g.fillRect(block2.x, block2.y, block2.width, block2.height);



    }

    public void move(){
        if(start) {
            block1.x += xDir;
            block2.x += xDir;

            if (block1.x <= -block1.width) {
                block1.x = 500;
                block2.x = 500;
                block1.height = (int) (Math.random() * 300);
                block2.y = block1.height + GAP;
                block2.height = 400 - block1.y;
                score++;
            }
        }
    }

    @Override
    public void run() {
        try{
            while(true){
                move();
                Thread.sleep(6);
            }
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }

    }
}
