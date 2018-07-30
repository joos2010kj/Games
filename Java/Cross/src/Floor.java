import java.awt.*;

public class Floor{

    int x,y,length, height, level;
    Rectangle floor;


    public Floor(int x, int y, int length, int height){
        this.length = length;
        this.height = height;
        this.x = x;
        this.y = y;

        floor = new Rectangle(this.x,this.y,this.length,this.height);
        level = 0;

    }

    public void draw(Graphics g){
        switch(level%7) {
            case 0:g.setColor(Color.PINK);
            break;
            case 1: g.setColor(Color.CYAN);
            break;
            case 2: g.setColor(Color.ORANGE);
            break;
            case 3: g.setColor(Color.GREEN);
            break;
            case 4: g.setColor(Color.MAGENTA);
            break;
            case 5: g.setColor(Color.RED);
            break;
            case 6: g.setColor(Color.WHITE);
            break;
        }
        g.fillRect(floor.x,floor.y,floor.width,floor.height);

    }





}
