/*
 * Copyright © 2019 Hyekang Joo. All rights reserved. Jan. 13, 2019.
 * The program was made with p5.js library
 */

 function Map() {
  this.art = [];
  this.ball = new Ball(30);
  this.food = new Food(random(width), random(height));
  this.score = 0;

  this.update = function() {
    this.ball.update();
    this.ball.show();
    this.ball.eat(this.food);
    this.food.show();

    this.collide();

    let before = this.score;
    let after = this.food.update();

    if (before != after) {
      this.score = after;

      if (this.art.length == 0) {
        this.art = [];
      } else {
        let last = this.art[this.art.length - 1];
        this.art = [];
        this.art.push(last);
      }
    }

  }

  this.show = function() {
    stroke(0);
    noFill();

    for (let i = 0; i < this.art.length; i++) {
      beginShape();
      for (let j = 0; j < this.art[i].length; j++) {
        vertex(this.art[i][j].x, this.art[i][j].y);
      }
      endShape();
    }
  }

  this.paint = function() {
    this.art[this.art.length - 1].push({
      x: mouseX,
      y: mouseY
    });
  }

  this.prologue = function() {
    this.art.push(new Array());
  }

  this.collide = function() {
    for (let i = 0; i < this.art.length; i++) {
      for (let j = 0; j < this.art[i].length; j++) {
        if (dist(this.ball.pos.x, this.ball.pos.y,
                 this.art[i][j].x, this.art[i][j].y) < this.ball.r / 2) {
          let slope = this.slope(this.art[i], j);
          let ortho = -1 / slope;

          if (ortho < 0) {
            this.ball.vel.x = 1;
            this.ball.vel.y = ortho;
          } else {
            this.ball.vel.x = -1;
            this.ball.vel.y = -ortho;
          }

          this.ball.vel.y = constrain(this.ball.vel.y, -5, 5);
        }
      }
    }
  }

  this.slope = function(pointArr, ind) {
    let ptOne = pointArr[ind];
    let ptTwo;

    if (pointArr.length <= 1)
      return 1;

    if (ind <= 0) {
      ptTwo = pointArr[ind + 1];
    } else {
      ptTwo = pointArr[ind - 1];
    }

    let slope = (ptTwo.y - ptOne.y) / (ptTwo.x - ptOne.x);

    return slope;
  }
}
