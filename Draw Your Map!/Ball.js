/*
 * Copyright © 2019 Hyekang Joo. All rights reserved. Jan. 13, 2019.
 * The program was made with p5.js library
 */

function Ball(r) {
  this.pos = createVector(width / 2,   0);
  this.vel = createVector(0        ,   0);
  this.acc = createVector(0        , 0.1);
  this.r = r;
  this.col = {
    r: 255,
    g: 255,
    b: 255
  };
  this.dead = false;

  this.update = function() {
    this.vel.add(this.acc);
    this.pos.add(this.vel);

    if (this.pos.x + this.r < 0 || this.pos.x - this.r > width ||
        this.pos.y - this.r > height) {
      console.log("GAME OVER!");
      this.dead = true;


      textAlign(CENTER);
      textSize(40);
      text("GAME OVER!", width / 2, height / 2);
    }
  };

  this.show = function() {
    fill(this.col.r, this.col.g, this.col.b);
    ellipse(this.pos.x, this.pos.y, this.r);
  };

  this.eat = function(food) {
    if (dist(this.pos.x, this.pos.y, food.pos.x, food.pos.y) < this.r) {
      food.dead = true;
    }
  }
}
