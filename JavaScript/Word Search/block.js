function Block(char, num, x, y, postX, postY){
  this.coord = {x, y, postX, postY};
  this.char = char;
  this.num = num;
  this.visited = false;
  this.golden = false;
  this.col = {
    r: 0,
    g: 255,
    b: 0
  }

  this.show = function(){
    textAlign(CENTER, CENTER);
    textSize(300 / Math.min(PUZZLE_SIZE[0], PUZZLE_SIZE[1]));
    fill(this.col.r, this.col.g, this.col.b);
    rect(this.coord.x, this.coord.y, this.coord.postX, this.coord.postY);
    fill(0);
    text(this.char.toUpperCase(), this.coord.x + this.coord.postX / 2, this.coord.y + this.coord.postY / 2);
  }
}
