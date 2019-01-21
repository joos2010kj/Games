let map;
let score;
let shot = 0;

const data = {
  SCOPE: 50,
  SOLDIER_SIZE: 30,
  PLANE_SIZE: 40
};

function setup(){
  createCanvas(1000, 500);
  map = new Map(data, score);
  map.preload();
  score = document.getElementById('score');
}

function draw(){
  background(51);
  map.show();
  map.update();

  score.innerHTML = "Score: " + shot;
}

function mouseClicked() {
  shot += map.attack(mouseX, mouseY);
}
