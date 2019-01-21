class Player {
  constructor(size) {
    this.size = size;
  }

  show() {
    beginShape();
    fill(0,0,0);
    vertex(0, 0);
    vertex(0, height);
    vertex(width, height);
    vertex(width, height / 2);

    for (let i = 0; i < 2 * PI; i += 0.01) {
      let x = mouseX + this.size * cos(i);
      let y = mouseY + this.size * sin(i);

      vertex(x, y);
    }

    vertex(width, height / 2 + 1);
    vertex(width, 0);
    endShape(CLOSE);
  }
}
