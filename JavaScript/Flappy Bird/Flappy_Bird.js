var bird;
var blocks = [];
var gameover = false;
var score = 0;

function Block(x_, y_){
  this.x = x_;
  this.y = y_;
  
  Block.gap = 175;
  Block.fat = 50;
  Block.tall = height;
  
  this.yb = this.y + height + Block.gap;
  
  this.move = function(){
    this.x -= 3;
  }
}

Block.spawn = function(x){
  let rand = -Math.random() * height;
  
  if( (rand + height > height * 0.1) && (rand + height + 175 < height * 0.9) )
      blocks.push(new Block(x,rand));
  else
    Block.spawn(x);
}

function Bird(){
  this.x = width / 4;
  this.y = height / 2;
  this.fat = 20;
  this.tall = 20;
  
  this.vel = 0;
  this.gravity = 1;
  
  this.fall = function(){
    this.y += this.vel;
    this.vel += this.gravity;
    
    if(this.y + this.tall > height)
      this.y = height - this.tall;
    if(this.y < 0)
      this.y = 0;
  }
  
   this.intersects = block => {
    let topBlock =  {left: block.x,   right: block.x + Block.fat,   top: block.y,   bottom: block.y + Block.tall};
    let botBlock =  {left: block.x,   right: block.x + Block.fat,   top: topBlock.bottom + Block.gap,   bottom: topBlock.bottom + Block.gap + Block.tall};
    
    let player =  {left: this.x,   right: this.x + this.fat,         top: this.y,   bottom: this.y + this.tall};
    
    return (topBlock.left <= player.right && topBlock.right >= player.left && topBlock.top <= player.bottom && topBlock.bottom >= player.top) || 
           (botBlock.left <= player.right && botBlock.right >= player.left && botBlock.top <= player.bottom && botBlock.bottom >= player.top);
  }
}

function setup() {
  createCanvas(480, 640);
  bird = new Bird();
  
  for(var i = 0; i < 3; i++){
    Block.spawn((width / 2) * (2 + i));
  }
}

function draw() {
  background(0);
  noStroke();
  
  for(block of blocks){
    fill(255,255,255);
    rect(block.x, block.y, Block.fat, Block.tall);
    rect(block.x, block.yb, Block.fat, Block.tall);
    
    if(!gameover)
      block.move();
    
    if(bird.intersects(block)){
      gameover = true;
    }
      
    
  }
  
  if(blocks[0].x + Block.fat < 0){
    Block.spawn(blocks[2].x + width / 2);
    blocks.splice(0,1);
    score++;
  }
  
  rect(bird.x, bird.y, bird.fat, bird.tall);
  if(!gameover)
    bird.fall();
  
  
  if(!gameover){
    fill(0, 102, 153);
    textSize(32);
    text("Score: " + score, 10, 80);
  }else{
    let result = "Score: " + score;
    fill(250, 100, 10, 80);
    textSize(100);
    text(result, width / 2 - textWidth(result) / 2, height / 2);
  }
}

function keyPressed(){
  if(key == ' ' && !gameover){
    bird.vel = -10;
  }
}
