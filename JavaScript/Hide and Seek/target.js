function Target(){
  this.pos = createVector();
  this.xoff = 0;
  this.yoff = 1000;

  this.update = function(){
    let noiseX = noise(this.xoff) * width;
    let noiseY = noise(this.yoff) * height;
    this.xoff += 0.01;
    this.yoff += 0.01;

    this.pos = createVector(noiseX, noiseY);
  }

  this.show = function(){
    push();
    fill(255, 0, 0);
    //ellipse(this.pos.x, this.pos.y, 10, 10);
    pop();
  }
}
