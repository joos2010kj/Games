/*
 * Copyright © 2019 Hyekang Joo. All rights reserved. Jan. 13, 2019.
 * The program was made with p5.js library
 */

function Food(x, y) {
  this.pos = createVector(x, y);
  this.dead = false;
  this.score = 0;

  this.show = function() {
    fill(0);
    ellipse(this.pos.x, this.pos.y, 10);
  }

  this.update = function() {
    if (this.dead) {
      this.pos = createVector(random(width), random(height));
      this.dead = false;
      this.score++;
    }

    return this.score;
  }
}
