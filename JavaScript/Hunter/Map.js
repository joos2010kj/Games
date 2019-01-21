class Map {
  constructor(data) {
    this.data = data;

    this.cashN = 0;
    this.bulletsN = 20;
    this.HPN = 100;
    this.cost = 15;

    this.cash = document.getElementById('cash');
    this.bullets = document.getElementById('bullets');
    this.HP = document.getElementById('HP');
    this.purchaseHP = document.getElementById("purchaseHP");
    this.purchaseBullet = document.getElementById("purchaseBullet");

    this.gameover = false;
  }

  preload() {
    this.vectors = [];
    this.monsters = [];
    this.birds = [];
    this.player = new Player(this.data.SCOPE);

    for (let i = 0; i < width; i += 30) {
      this.vectors.push({x: i,
                         y: 10 * sin(i) + height / 2});
    }
  }

  show() {
    this.drawField();
    this.displayMonsters();
    this.player.show();
    this.html();

    if (this.gameover)
      console.log("GAMEOVER");
  }

  html() {
    this.cash.innerHTML = "Cash: $" + this.cashN;
    this.bullets.innerHTML = "Bullets: " + this.bulletsN;
    this.HP.innerHTML = "HP: " + this.HPN;

    this.purchaseHP.onclick = function(){
      if (this.cashN >= this.cost) {
        this.cashN -= this.cost;
        this.HPN += 15;
      }
    }.bind(this);

    this.purchaseBullet.onclick = function(){
      if (this.cashN >= this.cost) {
        this.cashN -= this.cost;
        this.bulletsN += 10;
      }
    }.bind(this);
  }

  update() {
    if (Math.random() < 0.005) {
      let coord = this.vectors[floor(Math.random() * this.vectors.length)];
      this.monsters.push(new Monster(coord, this.data.SOLDIER_SIZE));
    }

    if (Math.random() < 0.003) {
      this.birds.push(new Bird(this.data.PLANE_SIZE));
    }

    if (this.birds.length != 0) {
      for (let i = this.birds.length - 1; i >= 0; i--) {
        this.birds[i].update();
      }
    }

    if (this.HPN < 0 || (this.bulletsN <= 0 && this.cashN <= this.cost) ){
      this.gameover = true;
    }

    if (this.gameover) {
      textAlign(CENTER);
      text("GAMEOVER", mouseX, mouseY);
      noLoop();
    }
  }

  drawField() {
    beginShape();
    fill(0);
    for (let i = 0; i < this.vectors.length; i++) {
      vertex(this.vectors[i].x, this.vectors[i].y);
    }
    vertex(width, height / 2);
    vertex(width, height);
    vertex(0, height);
    vertex(0, height / 2);
    endShape();
  }

  displayMonsters() {
    if (this.monsters.length != 0) {
      for (let i = this.monsters.length - 1; i >= 0; i--) {
        this.monsters[i].display();
      }
    }

    if (this.birds.length != 0) {
      for (let i = this.birds.length - 1; i >= 0; i--) {
        if (this.birds[i].pos.x + 40 < 0) {
          this.birds.splice(i, 1);
          this.HPN -= 5;
        } else
          this.birds[i].display();
      }
    }
  }

  attack(x, y) {
    if (0 < x && x < width && 0 < y && y < height && this.bulletsN > 0) {
      this.bulletsN--;
    }

    if (this.bulletsN <= 0) {
      return 0;
    }

    for (let i = this.monsters.length - 1; i >= 0; i--) {
      let mons = this.monsters[i];
      if (dist(x, y, mons.pos.x, mons.pos.y) < 15) {
        let pt;

        if (mons.col.r == 0)
          pt = 5;
        else if(mons.col.r == 200)
          pt = 3;
        else if (mons.col.r == 255)
          pt = 1;
        else
          pt = 4;

        this.monsters.splice(i, 1);

        this.cashN += pt;
        return pt;
      }
    }

    for (let i = this.birds.length - 1; i >= 0; i--) {
      let bird = this.birds[i];
      if (bird.pos.x < x && x < bird.pos.x + 40 && bird.pos.y < y && y < bird.pos.y + 10) {
        let pt = floor(abs(bird.vel.x) / 5 * 15);

        this.birds.splice(i, 1);

        this.cashN += pt;
        return pt;
      }
    }
    return 0;
  }
}
