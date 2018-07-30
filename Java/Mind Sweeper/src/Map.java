import java.awt.*;
import java.awt.event.KeyEvent;

public class Map implements Runnable {

    private final int SIDE = 20;
    int x, y, bombNum;
    boolean fillingComplete = false, gameOver = false, victory = false, showInstruction = true;
    boolean cell[][] = new boolean[SIDE][SIDE]; //true if the cell has a bomb.  if no bomb, false
    int[][] bombCell = new int[SIDE][SIDE];     //counts the surrounding based on cell[][]'s true/false info
    Rectangle[][] bomb = new Rectangle[SIDE][SIDE]; //draws the BOMB cell based on cell[][]'s true/false info


    Rectangle[][] layerBox = new Rectangle[SIDE][SIDE]; //layer above cell[][].
    boolean[][] layerBool = new boolean[SIDE][SIDE];    //true if it has been revealed by the user.  false if not revealed yet.

    Rectangle[][] declareBox = new Rectangle[SIDE][SIDE]; //layer above layerBox[][]
    boolean[][] declareBool = new boolean[SIDE][SIDE];  //declares that there is a bomb in the cell


    Rectangle clicker;

    public Map(int bombNum){
        this.bombNum = bombNum;
        int counter = 0;
        clicker = new Rectangle(200,140,20,20);

        do {
            for (int r = 0; r < cell.length; r++) {
                for (int c = 0; c < cell[0].length; c++) {
                    if ((int) (Math.random() * bombNum) == 0) {
                        cell[r][c] = true;
                        bomb[r][c] = new Rectangle(r * SIDE, c * SIDE, SIDE, SIDE);
                        counter++;
                        if(counter == bombNum){
                            fillingComplete = true;
                        }
                        if(fillingComplete == true){
                            break;
                        }
                    }
                }
                if(fillingComplete == true){
                    break;
                }
            }
        }while(fillingComplete == false);

        for(int i = 0; i < cell.length; i++){
            for(int j = 0; j < cell[0].length; j++){
                layerBox[i][j] = new Rectangle(i * SIDE, j * SIDE, SIDE, SIDE);
            }
        }
    }

    public void keyPressed(KeyEvent e){
        if(!gameOver) {
            int keyCode = e.getKeyCode();
            if (keyCode == e.VK_RIGHT) {
                clicker.x += 20;
            }
            if (keyCode == e.VK_LEFT) {
                clicker.x += -20;
            }
            if (keyCode == e.VK_UP) {
                clicker.y += -20;
            }
            if (keyCode == e.VK_DOWN) {
                clicker.y += 20;
            }
            if (keyCode == e.VK_SPACE) {
                //reveal the number depending on the case
                layerBool[(clicker.x - 20) / 20][(clicker.y - 40) / 20] = true;
            }
            if(keyCode == e.VK_SHIFT){
                declareBool[(clicker.x - 20) / 20][(clicker.y - 40) / 20] =
                        !declareBool[(clicker.x - 20) / 20][(clicker.y - 40) / 20];
                if(true) {
                    declareBox[(clicker.x - 20) / 20][(clicker.y - 40) / 20] =
                            new Rectangle(((clicker.x - 20) / 20) * 20, ((clicker.y - 40) / 20) * 20, 20, 20);
                }
            }

            showInstruction = false;
        }
    }

    public void draw(Graphics g){



        g.setColor(Color.BLACK);
        for(int i = 0; i < SIDE; i++){
            for(int j = 0; j < SIDE; j++){
                if(cell[i][j] == true) {
                    g.fillRect(bomb[i][j].x + 20, bomb[i][j].y + 40, bomb[i][j].width, bomb[i][j].height);
                }
            }
        }

        g.setColor(Color.BLACK);
        for(int r = 0; r < SIDE; r++) {
            for (int c = 0; c < SIDE; c++) {
                if(!cell[r][c])
                    g.drawString(" " + bombCell[r][c],SIDE*r + 20 + 2,SIDE*c + 60 - 5);
            }
        }

        g.setColor(Color.DARK_GRAY);
        for(int r = 0; r < SIDE; r++){
            for(int c = 0; c < SIDE; c++){
                if(layerBool[r][c] == false){
                    g.fillRect(layerBox[r][c].x + 20,layerBox[r][c].y + 40,SIDE, SIDE);
                }
            }
        }

        g.setColor(Color.BLACK);
        for(int i = 0; i < SIDE+1; i++){
            g.drawLine(i*SIDE + 20, 40, i*SIDE + 20, 440);
            g.drawLine(20,i*SIDE + 40, 420, i*SIDE + 40);
        }

        g.setColor(Color.GREEN);
        for(int i = 0; i < SIDE; i++){
            for(int j = 0; j < SIDE; j++){
                if(declareBool[i][j] == true){
                    g.fillRect(declareBox[i][j].x + 20 ,declareBox[i][j].y + 40, SIDE,SIDE);
                }
            }
        }

        if(showInstruction){
            g.drawString("Use arrow keys to move around cells.", 105,200);
            g.drawString("If you would like to \"reveal\" the cell you are on,", 70,230);
            g.drawString("press the spacebar.",160,260);
            g.drawString("If you would like to declare the cell as", 100, 290);
            g.drawString("the bomb-containing cell, press shift.",102,320);
            g.drawString("Once you start moving, this instruction will disappear.", 50, 350);
            g.drawString(" Enjoy!",200,380);
        }

        g.setColor(Color.RED);
        g.drawRect(clicker.x,clicker.y,clicker.width,clicker.height);
        //g.drawString("" + clicker.x + " " + clicker.y, 300,300);

        if(victory) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("YOU WIN!", 120,220);
        }
        if(gameOver){
            g.setColor(Color.BLACK);
            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("YOU LOSE!", 120,220);
        }

    }



    public void surroundingCounter(){

        for(int r = 0; r < SIDE; r++){
            for(int c = 0; c < SIDE; c++) {

                if (r == 0 && c == 0) { //district 1
                    int bombCounter = 0;
                    if (cell[r][c + 1] == true)  // 1,2    E
                        bombCounter++;
                    if (cell[r + 1][c] == true)   //2,1     S
                        bombCounter++;
                    if (cell[r + 1][c + 1] == true) //2,2     SE
                        bombCounter++;
                    bombCell[r][c] = bombCounter;
                } else if (r == 0 && c == SIDE - 1) { // district 3
                    int bombCounter = 0;
                    if (cell[r + 1][c - 1] == true) //2,0     SW
                        bombCounter++;
                    if (cell[r][c - 1] == true)   //  1,0   W
                        bombCounter++;
                    if (cell[r + 1][c] == true)   //2,1     S
                        bombCounter++;
                    bombCell[r][c] = bombCounter;
                } else if (r == 0 && (c >= 1) && (c <= SIDE - 2)) { //district 2
                    int bombCounter = 0;
                    if (cell[r + 1][c] == true)   //2,1     S
                        bombCounter++;

                    if (cell[r + 1][c + 1] == true) //2,2     SE
                        bombCounter++;
                    if (cell[r + 1][c - 1] == true) //2,0     SW
                        bombCounter++;
                    if (cell[r][c + 1] == true)  // 1,2    E
                        bombCounter++;
                    if (cell[r][c - 1] == true)   //  1,0   W
                        bombCounter++;
                    bombCell[r][c] = bombCounter;
                } else if (c == 0 && (r >= 1 && r <= SIDE - 2)) { //district 4
                    int bombCounter = 0;

                    if (cell[r][c + 1] == true)  // 1,2    E
                        bombCounter++;

                    if (cell[r - 1][c] == true)    //  0,1   N
                        bombCounter++;

                    if (cell[r - 1][c + 1] == true) //  0,2   NE
                        bombCounter++;

                    if (cell[r + 1][c] == true)   //2,1     S
                        bombCounter++;
                    if (cell[r + 1][c + 1] == true) //2,2     SE
                        bombCounter++;
                    bombCell[r][c] = bombCounter;
                } else if (c == SIDE - 1 && r >= 1 && r <= SIDE - 2) { //district 5
                    int bombCounter = 0;

                    if (cell[r - 1][c - 1] == true) // 0,0    NW
                        bombCounter++;

                    if (cell[r - 1][c] == true)    //  0,1   N
                        bombCounter++;
                    if (cell[r][c - 1] == true)   //  1,0   W
                        bombCounter++;
                    if (cell[r + 1][c - 1] == true) //2,0     SW
                        bombCounter++;

                    if (cell[r + 1][c] == true)   //2,1     S
                        bombCounter++;
                    bombCell[r][c] = bombCounter;

                } else if (r == SIDE - 1 && c == 0) { //district 6
                    int bombCounter = 0;
                    if (cell[r - 1][c] == true)    //  0,1   N
                        bombCounter++;

                    if (cell[r - 1][c + 1] == true) //  0,2   NE
                        bombCounter++;
                    if (cell[r][c + 1] == true)  // 1,2    E
                        bombCounter++;
                    bombCell[r][c] = bombCounter;
                } else if (r == SIDE - 1 && c >= 1 && c <= SIDE - 2){ //district 7
                    int bombCounter = 0;

                    if (cell[r - 1][c - 1] == true) // 0,0    NW
                        bombCounter++;

                    if (cell[r - 1][c] == true)    //  0,1   N
                        bombCounter++;

                    if (cell[r - 1][c + 1] == true) //  0,2   NE
                        bombCounter++;
                    if (cell[r][c - 1] == true)   //  1,0   W
                        bombCounter++;

                    if (cell[r][c + 1] == true)  // 1,2    E
                        bombCounter++;
                    bombCell[r][c] = bombCounter;
                }else if(r == SIDE-1 && c == SIDE -1){  //district 8
                    int bombCounter = 0;

                    if (cell[r - 1][c - 1] == true) // 0,0    NW
                        bombCounter++;

                    if (cell[r - 1][c] == true)    //  0,1   N
                        bombCounter++;
                    if (cell[r][c - 1] == true)   //  1,0   W
                        bombCounter++;
                    bombCell[r][c] = bombCounter;
                }
                else {  //rest
                    int bombCounter = 0;

                    if (cell[r - 1][c - 1] == true) // 0,0    NW
                        bombCounter++;

                    if (cell[r - 1][c] == true)    //  0,1   N
                        bombCounter++;

                    if (cell[r - 1][c + 1] == true) //  0,2   NE
                        bombCounter++;

                    if (cell[r][c - 1] == true)   //  1,0   W
                        bombCounter++;

                    if (cell[r][c + 1] == true)  // 1,2    E
                        bombCounter++;

                    if (cell[r + 1][c - 1] == true) //2,0     SW
                        bombCounter++;

                    if (cell[r + 1][c] == true)   //2,1     S
                        bombCounter++;

                    if (cell[r + 1][c + 1] == true) //2,2     SE
                        bombCounter++;

                    bombCell[r][c] = bombCounter;
                }
            }
        }
    }

    public boolean win(){
        for(int i = 0; i < SIDE; i++){
            for(int j = 0; j < SIDE; j++){
                if(declareBool[i][j] != cell[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public void gameOverCheck(){

        for(int i = 0; i < SIDE; i++){
            for(int j = 0;j < SIDE; j++){
                if(cell[i][j] && layerBool[i][j]){
                    gameOver = true;
                }
            }
        }
    }

    public void outsidePreventer(){

        if(clicker.x <= 20){
            clicker.x = 20;
        }
        if(clicker.y <= 40){
            clicker.y = 40;
        }
        if(clicker.x + clicker.width >= 420){
            clicker.x = 420 - clicker.width;
        }
        if(clicker.y + clicker.height >= 440){
            clicker. y = 440 - clicker.height;
        }

    }



    public void spread(){

        for(int r = 0; r < SIDE ; r++){
            for(int c = 0; c < SIDE ; c++){
                if(layerBool[r][c] == true && bombCell[r][c] == 0) {
                    if (r == 0 && c == 0) {   //district 1
                        if (bombCell[r][c + 1] == 0 && !cell[r][c + 1]) { //east
                            layerBool[r][c + 1] = true;
                        }
                        if (bombCell[r + 1][c] == 0 && !cell[r + 1][c]) { //south
                            layerBool[r + 1][c] = true;
                        }
                    } else if (r == 0 && c == SIDE - 1) { //district 3
                        if (bombCell[r + 1][c] == 0 && !cell[r + 1][c]) { //south
                            layerBool[r + 1][c] = true;
                        }
                        if (bombCell[r][c - 1] == 0 && !cell[r][c - 1]) { // west
                            layerBool[r][c - 1] = true;
                        }
                    } else if (r == 0 && c >= 1 && c <= SIDE - 2) { //district 2
                        if (bombCell[r][c - 1] == 0 && !cell[r][c - 1]) { // west
                            layerBool[r][c - 1] = true;
                        }
                        if (bombCell[r][c + 1] == 0 && !cell[r][c + 1]) { //east
                            layerBool[r][c + 1] = true;
                        }
                        if (bombCell[r + 1][c] == 0 && !cell[r + 1][c]) { //south
                            layerBool[r + 1][c] = true;
                        }
                    } else if (r >= 1 && r <= SIDE - 2 && c == 0) { // district 4
                        if (bombCell[r - 1][c] == 0 && !cell[r - 1][c]) { //north
                            layerBool[r - 1][c] = true;
                        }
                        if (bombCell[r][c + 1] == 0 && !cell[r][c + 1]) { //east
                            layerBool[r][c + 1] = true;
                        }
                        if (bombCell[r + 1][c] == 0 && !cell[r + 1][c]) { //south
                            layerBool[r + 1][c] = true;
                        }
                    } else if (r >= 1 && r <= SIDE - 2 && c == SIDE - 1) { // district 5
                        if (bombCell[r - 1][c] == 0 && !cell[r - 1][c]) { //north
                            layerBool[r - 1][c] = true;
                        }
                        if (bombCell[r][c - 1] == 0 && !cell[r][c - 1]) { // west
                            layerBool[r][c - 1] = true;
                        }
                        if (bombCell[r + 1][c] == 0 && !cell[r + 1][c]) { //south
                            layerBool[r + 1][c] = true;
                        }
                    } else if (r == SIDE - 1 && c == 0) { //district 6
                        if (bombCell[r - 1][c] == 0 && !cell[r - 1][c]) { //north
                            layerBool[r - 1][c] = true;
                        }
                        if (bombCell[r][c + 1] == 0 && !cell[r][c + 1]) { //east
                            layerBool[r][c + 1] = true;
                        }
                    } else if (r == SIDE - 1 && c >= 1 && c <= SIDE - 2) { //district 7
                        if (bombCell[r - 1][c] == 0 && !cell[r - 1][c]) { //north
                            layerBool[r - 1][c] = true;
                        }
                        if (bombCell[r][c - 1] == 0 && !cell[r][c - 1]) { // west
                            layerBool[r][c - 1] = true;
                        }
                        if (bombCell[r][c + 1] == 0 && !cell[r][c + 1]) { //east
                            layerBool[r][c + 1] = true;
                        }
                    } else if (r == SIDE - 1 && c == SIDE - 1) { // district 8
                        if (bombCell[r - 1][c] == 0 && !cell[r - 1][c]) { //north
                            layerBool[r - 1][c] = true;
                        }
                        if (bombCell[r][c - 1] == 0 && !cell[r][c - 1]) { // west
                            layerBool[r][c - 1] = true;
                        }
                    } else {


                        if (bombCell[r - 1][c] == 0 && !cell[r - 1][c]) { //north
                            layerBool[r - 1][c] = true;
                        }
                        if (bombCell[r][c - 1] == 0 && !cell[r][c - 1]) { // west
                            layerBool[r][c - 1] = true;
                        }
                        if (bombCell[r][c + 1] == 0 && !cell[r][c + 1]) { //east
                            layerBool[r][c + 1] = true;
                        }
                        if (bombCell[r + 1][c] == 0 && !cell[r + 1][c]) { //south
                            layerBool[r + 1][c] = true;
                        }


                    }
                }
            }
        }




        for(int r = 0; r < SIDE; r++){
            for(int c = 0; c < SIDE; c++) {
                if (!cell[r][c]) {

                    if(r == 0 && c == 0){   //S, SE, E -- district 1
                        if (layerBool[r][c + 1] == true && bombCell[r][c + 1] == 0) {    //E
                            layerBool[r][c] = true;
                        }
                        else if (layerBool[r + 1][c] == true && bombCell[r + 1][c] == 0) {    //S
                            layerBool[r][c] = true;
                        }
                        else if (layerBool[r + 1][c + 1] == true && bombCell[r + 1][c + 1] == 0) {    //SE
                            layerBool[r][c] = true;
                        }
                    }

                    else if(r == 0 && c == SIDE-1) { //S, SW, W -- district 3
                        if (layerBool[r][c - 1] == true && bombCell[r][c - 1] == 0) {    //W
                            layerBool[r][c] = true;
                        }
                        else if (layerBool[r + 1][c - 1] == true && bombCell[r + 1][c - 1] == 0) { //SW
                            layerBool[r][c] = true;
                        }
                        else if (layerBool[r + 1][c] == true && bombCell[r + 1][c] == 0) {    //S
                            layerBool[r][c] = true;
                        }
                    }

                    else if(r == 0 && c >= 1 && c <= SIDE -2) { //W, SW, S, SE, E -- district 2

                       if (layerBool[r][c - 1] == true && bombCell[r][c - 1] == 0) {    //W
                            layerBool[r][c] = true;
                        } else if (layerBool[r][c + 1] == true && bombCell[r][c + 1] == 0) {    //E
                            layerBool[r][c] = true;
                        } else if (layerBool[r + 1][c - 1] == true && bombCell[r + 1][c - 1] == 0) { //SW
                            layerBool[r][c] = true;
                        } else if (layerBool[r + 1][c] == true && bombCell[r + 1][c] == 0) {    //S
                            layerBool[r][c] = true;
                        } else if (layerBool[r + 1][c + 1] == true && bombCell[r + 1][c + 1] == 0) {    //SE
                            layerBool[r][c] = true;
                        }
                    }
                    else if(r >= 1 && r <= SIDE - 2 && c == 0){ // N, NE, E, SE, S -- district 4

                       if (layerBool[r - 1][c] == true && bombCell[r - 1][c] == 0) {        //N
                            layerBool[r][c] = true;
                        } else if (layerBool[r - 1][c + 1] == true && bombCell[r - 1][c + 1] == 0) {    //NE
                            layerBool[r][c] = true;
                        } else if (layerBool[r][c + 1] == true && bombCell[r][c + 1] == 0) {    //E
                            layerBool[r][c] = true;
                        } else if (layerBool[r + 1][c] == true && bombCell[r + 1][c] == 0) {    //S
                            layerBool[r][c] = true;
                        } else if (layerBool[r + 1][c + 1] == true && bombCell[r + 1][c + 1] == 0) {    //SE
                            layerBool[r][c] = true;
                        }

                    }
                    else if(r >= 1 && r <= SIDE -2 && c == SIDE-1) { //N, NW, W, SW, S -- district 5
                        if (layerBool[r - 1][c - 1] == true && bombCell[r - 1][c - 1] == 0) {       //NW
                            layerBool[r][c] = true;
                        } else if (layerBool[r - 1][c] == true && bombCell[r - 1][c] == 0) {        //N
                            layerBool[r][c] = true;
                        } else if (layerBool[r][c - 1] == true && bombCell[r][c - 1] == 0) {    //W
                            layerBool[r][c] = true;
                        } else if (layerBool[r + 1][c - 1] == true && bombCell[r + 1][c - 1] == 0) { //SW
                            layerBool[r][c] = true;
                        } else if (layerBool[r + 1][c] == true && bombCell[r + 1][c] == 0) {    //S
                            layerBool[r][c] = true;
                        }
                    }

                    else if(r == SIDE - 1 && c == 0){ //N, NE, E -- district 6

                        if (layerBool[r - 1][c] == true && bombCell[r - 1][c] == 0) {        //N
                            layerBool[r][c] = true;
                        } else if (layerBool[r - 1][c + 1] == true && bombCell[r - 1][c + 1] == 0) {    //NE
                            layerBool[r][c] = true;
                        } else if (layerBool[r][c + 1] == true && bombCell[r][c + 1] == 0) {    //E
                            layerBool[r][c] = true;
                        }
                    }
                    else if(r == SIDE -1 && c >= 1 && c <= SIDE-2) { //W, NW, N, NE, E -- district 7
                        if (layerBool[r - 1][c - 1] == true && bombCell[r - 1][c - 1] == 0) {       //NW
                            layerBool[r][c] = true;
                        } else if (layerBool[r - 1][c] == true && bombCell[r - 1][c] == 0) {        //N
                            layerBool[r][c] = true;
                        } else if (layerBool[r - 1][c + 1] == true && bombCell[r - 1][c + 1] == 0) {    //NE
                            layerBool[r][c] = true;
                        } else if (layerBool[r][c - 1] == true && bombCell[r][c - 1] == 0) {    //W
                            layerBool[r][c] = true;
                        } else if (layerBool[r][c + 1] == true && bombCell[r][c + 1] == 0) {    //E
                            layerBool[r][c] = true;
                        }
                    }
                    else if(r == SIDE -1 && c == SIDE -1) { //W, NW, N -- district 8
                        if (layerBool[r - 1][c - 1] == true && bombCell[r - 1][c - 1] == 0) {       //NW
                            layerBool[r][c] = true;
                        } else if (layerBool[r - 1][c] == true && bombCell[r - 1][c] == 0) {        //N
                            layerBool[r][c] = true;
                        } else if (layerBool[r][c - 1] == true && bombCell[r][c - 1] == 0) {    //W
                            layerBool[r][c] = true;
                        }
                    }
                    else{

                        if (layerBool[r - 1][c - 1] == true && bombCell[r - 1][c - 1] == 0) {       //NW
                            layerBool[r][c] = true;
                        } else if (layerBool[r - 1][c] == true && bombCell[r - 1][c] == 0) {        //N
                            layerBool[r][c] = true;
                        } else if (layerBool[r - 1][c + 1] == true && bombCell[r - 1][c + 1] == 0) {    //NE
                            layerBool[r][c] = true;
                        } else if (layerBool[r][c - 1] == true && bombCell[r][c - 1] == 0) {    //W
                            layerBool[r][c] = true;
                        } else if (layerBool[r][c + 1] == true && bombCell[r][c + 1] == 0) {    //E
                            layerBool[r][c] = true;
                        } else if (layerBool[r + 1][c - 1] == true && bombCell[r + 1][c - 1] == 0) { //SW
                            layerBool[r][c] = true;
                        } else if (layerBool[r + 1][c] == true && bombCell[r + 1][c] == 0) {    //S
                            layerBool[r][c] = true;
                        } else if (layerBool[r + 1][c + 1] == true && bombCell[r + 1][c + 1] == 0) {    //SE
                            layerBool[r][c] = true;
                        }

                    }


                }
            }
        }





    }


    public void move(){
        surroundingCounter();
        outsidePreventer();
        spread();
        gameOverCheck();
        if(win()) {
            victory = true;
        }
    }



    @Override
    public void run() {
        try{
            while(true){
                move();
                Thread.sleep(5);
            }
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
