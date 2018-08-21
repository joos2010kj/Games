function Platform(x, y){
  this.pos = createVector(x, y);
  this.fat = Math.random() * (width - 60) + 30;
  this.over = false;
  Platform.TALL = 20;
  Platform.SPACE = 75;

  this.metadata = {
    firstLeft: x,
    firstRight: x + this.fat,
    secondLeft: x + this.fat + Platform.SPACE,
    secondRight: width
  }

  this.update = function(){
      this.pos.y -= TRANSLATION;

      if(this.pos.y + Platform.TALL < 0){
        this.over = true;
      }
  }

  this.show = function(){
    fill(0, 255, 0);
    rect(this.metadata.firstLeft, this.pos.y, this.fat, Platform.TALL);
    rect(this.metadata.secondLeft, this.pos.y, width - this.metadata.secondLeft, Platform.TALL);
  }
}
