var platforms = [];
var player;
var MIC;
var turned = false;
var score = 0;

const GAP = 100;
const TRANSLATION = 2;
const THRESHOLD = 2.5;

function setup(){
    createCanvas(400,600);

    for(let i = 2; i < Math.floor(height / 100 + 3); i++){
      platforms.push(new Platform(0, GAP * i));
    }

    player = new Player(width / 2, 50);

    MIC = new p5.AudioIn();
    MIC.start();

    noLoop();
}

function draw(){
  background(0);
  noStroke();

  let volume = MIC.getLevel() * 100;

  if(volume > THRESHOLD){
    if(!turned){
      turned = true;
      player.goRight = !player.goRight;
    }
  }else{
    turned = false;
  }

  if(player.gameover){
    noLoop();
  }

  for(let i = platforms.length - 1; i >= 0; i--){
    platforms[i].update();
    platforms[i].show();

    if(platforms[i].over){
      platforms.splice(i, 1);
      removeElements();
      createP("SCORE: " + score++);
      platforms.push(new Platform(0, platforms[platforms.length - 1].pos.y + GAP));
    }
  }

  player.update();
  player.show();
  player.contactCheck(platforms);


  textSize(100);
  fill(255);
  noStroke();
  text(score, 0, height);
}

function mousePressed(){
  //if(player.ground){ //turn reversal is activated only when on platform
    player.goRight = !player.goRight;
    loop();
  //}
}
