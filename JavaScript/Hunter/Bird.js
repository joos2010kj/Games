class Bird {
  constructor(size) {
    this.size = size;

    this.pos = {
      x: width,
      y: random(height / 2)
    };

    this.vel = {
      x: random(-5, 0)
    }
  }
  update() {
    this.pos.x += this.vel.x;
  }
  display() {
    fill(255, 255, 255);
    rect(this.pos.x, this.pos.y, this.size, 10);
  }
}
