function Mole(x, y){
  this.pos = createVector(x, y);
  this.col = {
    r: Mole.counter * 20,
    g: Mole.counter * 20,
    b: Mole.counter * 20
  }
  Mole.counter++;
  Mole.r = width / 8;

  this.option = {
    up: false,
    started: false,
    hit: false,
    time: null
  }

  this.show = function(){
    push();
    fill(this.col.r, this.col.g, this.col.b);
    ellipse(this.pos.x, this.pos.y, 2 * Mole.r, 2 * Mole.r);
    pop();
  }

  this.update = function(){

    if(Math.random() < Mole.chance){
      if(!this.option.up)
        this.option.up = true;
    }

    if(this.option.up){
      let self = this;

      if(!this.option.hit){
        this.col.r = 0;
        this.col.g = 0;
        this.col.b = 0;
      }else{
        this.col.r = 255;
        this.col.g = 0;
        this.col.b = 0;
      }

      if(!this.option.started){
        window.setTimeout(function(){
          self.option.up = false;
          self.option.started = false;
          self.option.hit = false;
        }, Mole.duration * 1000);

        this.option.started = true;
      }
    }else{
      this.col.r = 255;
      this.col.g = 255;
      this.col.b = 255;
    }
  }

  Mole.check = function(moles){
    for(let i = 0; i < moles.length; i++){
      let mole = moles[i];
      let distance = dist(mole.pos.x, mole.pos.y, mouseX, mouseY);

      if(distance < Mole.r){
        if(mole.option.up && !mole.option.hit){
          mole.option.hit = true;
          Mole.points++;
        }
        return;
      }
    }
  }
}

Mole.counter = 0;
Mole.chance = 0.0005;
Mole.duration = 0.3;
Mole.points = 0;
