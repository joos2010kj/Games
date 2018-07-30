import java.awt.*;
import java.awt.Rectangle;
public class Wood implements Runnable {

    int x, y, yDirection, width, height;
    Rectangle block;
    boolean done = false;

    static Character c1 = new Character(100,250);
    static Floor f1 = new Floor(200,0, 700,500);


    public Wood(int x_, int y_){
        this.x = x_;
        this.y = y_;
        this.width = 100;
        this.height = (int)(Math.random()*30)+50;


        if(Math.random() > 0.5){
            setyDirection(1);
        }
        else{
            setyDirection(-1);
        }

        block = new Rectangle(x, y, this.width, this.height);


    }

    public void setyDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    public void draw(Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(block.x,block.y,block.width,block.height);


    }
    public void collision(){
        if(c1.character.intersects(block)){
            c1.character.y = block.y + block.height/2;
        }
        if(c1.character.intersects(900,0,100,500)){
            done = true;
        }

        if( c1.character.x > block.x &&
            c1.character.x < (block.x+block.width) &&
           (c1.character.y + c1.character.height) <= block.y ){
            //caught above!
            c1.character.x = 100;
            c1.character.y = 250;
            f1.level += 1;
            c1.death += 1;
        }
        if( c1.character.x > block.x &&
            c1.character.x < (block.x+block.width) &&
            c1.character.y >= block.y + block.height){
            //caught above!
            c1.character.x = 100;
            c1.character.y = 250;
            f1.level += 1;
            c1.death += 1;
        }



    }
    public void move(){
        collision();
        block.y += yDirection;

        if(block.y >= 500-block.height || block.y <= 20){
            this.yDirection *= -1;
        }

    }

    public void run() {
        try{
            while(true) {
                move();
                Thread.sleep((int) (Math.random() * 5)+ 2);
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }




}
