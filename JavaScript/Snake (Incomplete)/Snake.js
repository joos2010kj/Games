var snake;
var PIXEL;
var field;
var food;
var scl = 30;
var score = 0;

function setup(){
  createCanvas(600, 600);
  frameRate(10);
  PIXEL = {FAT: width / scl, TALL: height / scl};

  snake = new Snake();

  food = {x: Math.floor(Math.random() * width / PIXEL.FAT) * PIXEL.FAT,
          y: Math.floor(Math.random() * height / PIXEL.TALL) * PIXEL.TALL,
          show: function(){
            fill(255,0,0);
            rect(this.x, this.y, PIXEL.FAT, PIXEL.TALL);
          },
          reassign: function(){
            this.x = Math.floor(Math.random() * width / PIXEL.FAT) * PIXEL.FAT;
            this.y = Math.floor(Math.random() * height / PIXEL.TALL) * PIXEL.TALL;
            score++;
          }
        };

  field = {
    draw: function(){
      for(let i = 0; i <= width; i += PIXEL.FAT){
        line(i, 0, i, height);
      }
      for(let i = 0; i < height; i += PIXEL.FAT){
        line(0, i, width, i);
      }
    }
  };

}

function draw(){
  background(0);

  stroke(255);
  field.draw();

  snake.show();
  snake.update();

  food.show();
}

function keyPressed(){
  if(keyCode === UP_ARROW){
    snake.dir(0,-1);
    snake.curr = 'U';
  }

  else if(keyCode === LEFT_ARROW){
    snake.dir(-1,0);
    snake.curr = 'L';
  }

  else if(keyCode === DOWN_ARROW){
    snake.dir(0,1);
    snake.curr = 'D';
  }

  else if(keyCode === RIGHT_ARROW){
    snake.dir(1,0);
    snake.curr = 'R';
  }
}

function Snake(){
  this.tail = [];
  this.curr = 'R';

  this.x = width / 2;
  this.y = height / 2;

  this.xVel = 1;
  this.yVel = 0;

  this.dir = function(x,y){
    this.xVel = x;
    this.yVel = y;
  }

  this.update = function(){
    this.x = this.x + this.xVel * PIXEL.FAT;
    this.y = this.y + this.yVel * PIXEL.TALL;

    this.x = constrain(this.x, 0 , width - PIXEL.FAT);
    this.y = constrain(this.y, 0, height - PIXEL.TALL);

    this.eat(food);

    for(let i = 0; i < this.tail.length - 1; i++){
      this.tail[i] = this.tail[i + 1];
    }

    this.tail[this.tail.length - 1] = createVector(this.x, this.y);


    for(let each of this.tail){
      fill(255);
      rect(each.x, each.y, PIXEL.FAT, PIXEL.TALL);
    }
  }

  this.show = function(){
    fill(255);
    rect(this.x, this.y, PIXEL.FAT, PIXEL.TALL);
  }

  this.eat = function(food){
    if(food.x == this.x && food.y == this.y){
      food.reassign();
      this.grow();
    }
  }

  this.grow = function(){
    let last = this.tail[this.tail.length - 1];
    this.tail.push(createVector(last.x, last.y));
  }


}
