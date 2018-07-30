function Player(x, y){
  this.pos = createVector(x, y);
  Player.resetVel = createVector(4, 5);
  this.vel = Player.resetVel;
  this.grav = createVector(1, 1);
  this.rad = 20;
  this.goRight = true;
  this.ground = true;
  this.gameover = false;

  this.update = function(){
    this.pos.y -= TRANSLATION;

    if(this.goRight){
      this.pos.x += this.vel.x;
    }else {
      this.pos.x -= this.vel.x;
    }

    this.pos.x = constrain(this.pos.x, this.rad, width - this.rad);
    this.pos.y = constrain(this.pos.y, -this.rad, height - this.rad);

    if(this.pos.y + this.rad == height){ //increase difficulty
      //TRANSLATION += 0.1;
      //Player.resetVel.x += 0.1;
    }

    if(this.pos.y + this.rad <= 0){
      this.gameover = true;
    }

    if(!this.ground){
      this.pos.y += this.vel.y;
    }else{
      this.vel = Player.resetVel;
    }
  }

  this.show = function(){
    fill(255, 0, 0);
    ellipse(this.pos.x, this.pos.y, this.rad * 2, this.rad * 2);
  }

  this.contactCheck = function(arr){
    let currentGroundPoint = createVector(this.pos.x, this.pos.y + this.rad);
    let isOnGround = false;

    for(let element of arr){
      if(element.pos.x <= currentGroundPoint.x &&
         currentGroundPoint.x <= element.pos.x + element.fat &&
         element.pos.y <= currentGroundPoint.y && currentGroundPoint.y <= element.pos.y + Platform.TALL){
        isOnGround = true;
      }

      else if(element.metadata.secondLeft <= currentGroundPoint.x &&
         currentGroundPoint.x <= width &&
         element.pos.y <= currentGroundPoint.y && currentGroundPoint.y <= element.pos.y + Platform.TALL){
        isOnGround = true;
      }
    }

    this.ground = isOnGround;
  }
}
