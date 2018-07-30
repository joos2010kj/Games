import java.awt.*;
import java.awt.event.KeyEvent;

public class Spaceship implements Runnable {

    int x,y,xDir,yDir;
    boolean b0R,b1R,b2R,b3R,b4R,b5R,b6R,b7R,b8R,b9R;
    int bx, by, counter, score = 0;
    boolean gameStart = false;

    Rectangle spaceship;
    Rectangle[] bullets;
    Monster[] monsters = new Monster[5];
    public Spaceship(int x, int y){
        this.x = x;
        this.y = y;
        spaceship = new Rectangle(this.x,this.y,30,20);

        bullets = new Rectangle[10];

        counter = 0;
        b0R = b1R = b2R = b3R = b4R = b5R = b6R = b7R = b8R = b9R = false;

        monsters[0] = new Monster((int)(Math.random()*450));
        monsters[1] = new Monster((int)(Math.random()*450));
        monsters[2] = new Monster((int)(Math.random()*450));
        monsters[3] = new Monster((int)(Math.random()*450));
        monsters[4] = new Monster((int)(Math.random()*450));

        bullets[0] = new Rectangle(spaceship.x, spaceship.y, 5, 5);
        bullets[1] = new Rectangle(spaceship.x, spaceship.y, 5, 5);
        bullets[2] = new Rectangle(spaceship.x, spaceship.y, 5, 5);
        bullets[3] = new Rectangle(spaceship.x, spaceship.y, 5, 5);
        bullets[4] = new Rectangle(spaceship.x, spaceship.y, 5, 5);
        bullets[5] = new Rectangle(spaceship.x, spaceship.y, 5, 5);
        bullets[6] = new Rectangle(spaceship.x, spaceship.y, 5, 5);
        bullets[7] = new Rectangle(spaceship.x, spaceship.y, 5, 5);
        bullets[8] = new Rectangle(spaceship.x, spaceship.y, 5, 5);
        bullets[9] = new Rectangle(spaceship.x, spaceship.y, 5, 5);
    }

    public void keyPressed(KeyEvent e){
        int keyIndex = e.getKeyCode();
        if(keyIndex == e.VK_LEFT){
            if(!Monster.gameOver)
                setxDir(-1);
        }
        if(keyIndex == e.VK_RIGHT){
            if(!Monster.gameOver)
                setxDir(1);
        }
        if(keyIndex == e.VK_UP){
            //setyDir(-1);
        }
        if(keyIndex == e.VK_DOWN){
           //setyDir(1);
        }
        if(keyIndex == e.VK_SPACE){
            if(!Monster.gameOver) {
                gameStart = true;

                monsters[0].start = true;
                monsters[1].start = true;
                monsters[2].start = true;
                monsters[3].start = true;
                monsters[4].start = true;

                bx = spaceship.x + spaceship.width / 2 - 3;
                by = spaceship.y;
                counter++;
                switch (counter % 10) {
                    case 0:
                        b0R = true;
                        break;
                    case 1:
                        b1R = true;
                        break;
                    case 2:
                        b2R = true;
                        break;
                    case 3:
                        b3R = true;
                        break;
                    case 4:
                        b4R = true;
                        break;
                    case 5:
                        b5R = true;
                        break;
                    case 6:
                        b6R = true;
                        break;
                    case 7:
                        b7R = true;
                        break;
                    case 8:
                        b8R = true;
                        break;
                    case 9:
                        b9R = true;
                        break;
                }


                if (b0R) {
                    bullets[counter % 10] = new Rectangle(bx, by, 5, 5);
                }

                if (b1R) {
                    bullets[counter % 10] = new Rectangle(bx, by, 5, 5);
                }

                if (b2R) {
                    bullets[counter % 10] = new Rectangle(bx, by, 5, 5);
                }

                if (b3R) {
                    bullets[counter % 10] = new Rectangle(bx, by, 5, 5);
                }

                if (b4R) {
                    bullets[counter % 10] = new Rectangle(bx, by, 5, 5);
                }

                if (b5R) {
                    bullets[counter % 10] = new Rectangle(bx, by, 5, 5);
                }

                if (b6R) {
                    bullets[counter % 10] = new Rectangle(bx, by, 5, 5);
                }

                if (b7R) {
                    bullets[counter % 10] = new Rectangle(bx, by, 5, 5);
                }

                if (b8R) {
                    bullets[counter % 10] = new Rectangle(bx, by, 5, 5);
                }
                if (b9R) {
                    bullets[counter % 10] = new Rectangle(bx, by, 5, 5);
                }


//            if(bullet == null){
//                ready = true;
//            }
//            if(ready){
//                by = spaceship.y;
//                bx = spaceship.x;
//                bullet = new Bullet(bx,by);
//                bullet.mobile = true;
//                shot = true;
//            }
            }
        }
    }

    public void keyReleased(KeyEvent e){
        int keyIndex = e.getKeyCode();
        if(keyIndex == e.VK_LEFT){
            setxDir(0);
        }
        if(keyIndex == e.VK_RIGHT){
            setxDir(0);
        }
        if(keyIndex == e.VK_UP){
            //setyDir(0);
        }
        if(keyIndex == e.VK_DOWN){
            //setyDir(0);
        }
        if(keyIndex == e.VK_SPACE){

        }
    }

    public void draw(Graphics g){

        if(true){
            g.setColor(Color.ORANGE);
            for(int i = 0; i < 20; i++)
                g.fillRect((int)(Math.random()*500),(int)(Math.random()*500),2,2);
        }

        if(!gameStart) {
            g.setColor(Color.WHITE);
            g.drawString("Press space to start", 180, 200);
        }
        if(gameStart) {
            g.setColor(Color.BLACK);
            g.drawString("Press space to start", 180, 200);
        }

        g.setColor(Color.WHITE);
        g.drawString("Score : " + score, 50,50);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(spaceship.x,spaceship.y,spaceship.width,spaceship.height);
        g.fillOval(spaceship.x-10,spaceship.y+spaceship.height-5, 20,10);
        g.fillOval(spaceship.x+spaceship.width-10,spaceship.y+spaceship.height-5, 20,10);
        g.fillOval(spaceship.x + spaceship.width/2 - 5,spaceship.y - 5,10,10);

        g.setColor(Color.RED);
        g.fillRect(bullets[0].x,bullets[0].y,bullets[0].width,bullets[0].height);
        g.setColor(Color.ORANGE);
        g.fillRect(bullets[1].x,bullets[1].y,bullets[1].width,bullets[1].height);
        g.setColor(Color.YELLOW);
        g.fillRect(bullets[2].x,bullets[2].y,bullets[2].width,bullets[2].height);
        g.setColor(Color.GREEN);
        g.fillRect(bullets[3].x,bullets[3].y,bullets[3].width,bullets[3].height);
        g.setColor(Color.BLUE);
        g.fillRect(bullets[4].x,bullets[4].y,bullets[4].width,bullets[4].height);
        g.setColor(Color.CYAN);
        g.fillRect(bullets[5].x,bullets[5].y,bullets[5].width,bullets[5].height);
        g.setColor(Color.PINK);
        g.fillRect(bullets[6].x,bullets[6].y,bullets[6].width,bullets[6].height);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(bullets[7].x,bullets[7].y,bullets[7].width,bullets[7].height);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(bullets[8].x,bullets[8].y,bullets[8].width,bullets[8].height);
        g.setColor(Color.WHITE);
        g.fillRect(bullets[9].x,bullets[9].y,bullets[9].width,bullets[9].height);
    }
    public void collision(){

        for(int i = 0; i < monsters.length; i++) {
            for(int j = 0; j < bullets.length; j++){
                if (monsters[i].monster.intersects(bullets[j])) {
                    //monsters[i] = new Monster((int)(Math.random()*450));
                    monsters[i].monster.x = (int)(Math.random()*450)+20;
                    monsters[i].monster.y = 0;
                    score++;
                }
            }
        }



    }

    public void bulletMove(){
        if( b0R ){
            bullets[0].y -= 1;
        }

        if( b1R ){
            bullets[1].y -= 1;
        }

        if( b2R ){
            bullets[2].y -= 1;
        }

        if( b3R ){
            bullets[3].y -= 1;
        }

        if( b4R ){
            bullets[4].y -= 1;
        }

        if( b5R ){
            bullets[5].y -= 1;
        }

        if( b6R ){
            bullets[6].y -= 1;
        }

        if( b7R ){
            bullets[7].y -= 1;
        }

        if( b8R ){
            bullets[8].y -= 1;
        }
        if( b9R ){
            bullets[9].y -= 1;
        }

        if(bullets[0].y <= 0){
            b0R = false;
        }
        if(bullets[1].y <= 0){
            b1R = false;
        }
        if(bullets[2].y <= 0){
            b2R = false;
        }
        if(bullets[3].y <= 0){
            b3R = false;
        }
        if(bullets[4].y <= 0){
            b4R = false;
        }
        if(bullets[5].y <= 0){
            b5R = false;
        }
        if(bullets[6].y <= 0){
            b6R = false;
        }
        if(bullets[7].y <= 0){
            b7R = false;
        }
        if(bullets[8].y <= 0){
            b8R = false;
        }
        if(bullets[9].y <= 0){
            b9R = false;
        }


    }
    public void move(){

        bulletMove();
        collision();

        spaceship.x += xDir;
        spaceship.y += yDir;

        if(spaceship.x + spaceship.width >= 500){
            spaceship.x = 500 - spaceship.width;
        }
        if(spaceship.y + spaceship.height >= 500){
            spaceship.y = 500 - spaceship.height;
        }
        if(spaceship.x <= 0){
            spaceship.x = 0;
        }
        if(spaceship.y <= 0){
            spaceship.y = 0;
        }

    }

    public void setxDir(int xDir) {
        this.xDir = xDir;
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
