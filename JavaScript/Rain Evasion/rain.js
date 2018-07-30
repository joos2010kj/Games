var rains = [];
var raindrop_size = 10;
var pro;
var gameover = false;
var score = 0;

function Rain(begin){
   this.x = begin;
   this.y = -Rain.rainHeight;
   this.vel = Math.random() * 10 + 5;
   
   this.R = Math.random() * 255
   this.G = Math.random() * 255;
   this.B = Math.random() * 255;
   
   Rain.rainWidth = 10;
   Rain.rainHeight = 50;
}

function Character(){
  this.x = width / 2;
  this.y = height * 0.8;
  this.fat = 50;
  this.tall = 50;
  
  this.R = this.G = this.B = 50;
  
  this.intersects = rain => {
    let tempRain =  {left: rain.x,   right: rain.x + Rain.rainWidth,   top: rain.y,   bottom: rain.y + Rain.rainHeight};
    let tempChar =  {left: this.x,   right: this.x + this.fat,         top: this.y,   bottom: this.y + this.tall};
    
    return tempRain.left <= tempChar.right && tempRain.right >= tempChar.left && tempRain.top <= tempChar.bottom && tempRain.bottom >= tempChar.top;
  }
}


function setup() {
  createCanvas(800,600);
  rains = new Array(raindrop_size).fill().map( x => new Rain(Math.random() * width) );
  pro = new Character();
}


function draw() {
  background(0);
  noStroke();
  
  for(rain of rains){
    fill(rain.R, rain.G, rain.B);
    rect(rain.x, rain.y, Rain.rainWidth, Rain.rainHeight);
    rain.y += rain.vel;
    
    if(pro.intersects(rain)){
      gameover = true; 
      rains.splice(0);
    }
  }
  
  for(var i = 0; i < rains.length; i++){
    if(rains[i].y > height){
      rains.splice(i,1);
      rains.push(new Rain(Math.random() * width));
      score++;
    }
  }
  
  fill(pro.R, pro.G, pro.B);
  
  pro.x = mouseX - pro.fat / 2;
  rect(pro.x, pro.y, pro.fat, pro.tall);
  
  if(!gameover){
    fill(0, 102, 153);
    textSize(32);
    text("Score: " + score, 10, 80);
  }else{
    let result = "Score: " + score;
    fill(250, 100, 10, 51);
    textSize(100);
    text(result, width / 2 - textWidth(result) / 2, height / 2);
  }
}

function mousePressed(){
  rains = new Array(rainLength).fill().map( x => new Rain(Math.random() * width) );
  pro = new Character();
  gameover = false;
  score = 0;
}
