class Monster {
  constructor(coord, size) {
    this.pos = coord;
    this.col = Math.random();
    this.rad = size;

    if (this.col > 0.3) {
      this.col = {
        r: 255,
        g: 255,
        b: 255
      };
    } else if (this.col > 0.1) {
      this.col = {
        r: 200,
        g: 100,
        b: 200
      };
    } else if (this.col > 0.05) {
      this.col = {
        r: random(255),
        g: random(255),
        b: random(255)
      };
    } else {
      this.col = {
        r: 0,
        g: 0,
        b: 0
      };
    }
  }

  display() {
    fill(this.col.r, this.col.g, this.col.b);
    noStroke();
    ellipse(this.pos.x, this.pos.y, this.rad);
  }
}
