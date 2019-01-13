/*
 * Copyright © 2019 Hyekang Joo. All rights reserved. Jan. 13, 2019.
 * The program was made with p5.js library
 */

let map;
let score;
let canvas;

function setup(){
  canvas = createCanvas(500, 500);
  canvas.parent("canvas");

  map = new Map();
}

function draw(){
  score = map.food.score;
  background(51);

  map.show();
  map.update();

  document.getElementById("score").innerHTML = "Score: " + score;
}

function mousePressed() {
  map.prologue();
}

function mouseDragged() {
  map.paint();
}
