var buttons = [];
var bubbles = [];
var numberOfBubbles = 30;
var numberOfButtons = 5;
var score = 0;
var gameover = false;
const RAD = 25;

function Button(x) {
  this.x = x;
  Button.y = height / 2;

  Button.prototype.touching = function(x_, y_) {
    let distancesquared = (x_ - this.x) * (x_ - this.x) + (y_ - Button.y) * (y_ - Button.y);
    return distancesquared <= RAD * RAD;
  }
}

function Bubble(x, y, but) {
  this.x = x;
  this.y = y;

  this.button = buttons[but];
  this.angle = atan2(this.y - Button.y, this.x - this.button.x);
  this.mag = 1;

  this.velX = Math.cos( this.angle ) * this.mag;
  this.velY = Math.sin( this.angle ) * this.mag;

  this.over = false;

  Bubble.prototype.update = function() {
    this.x -= this.velX;
    this.y -= this.velY;

    if ( (Math.floor(this.x) == this.button.x || Math.ceil(this.x) == this.button.x)
      && (Math.floor(this.y) == Button.y || Math.ceil(this.y) == Button.y) )
      this.over = true;
  }

  Bubble.prototype.touching = function(x_, y_) {
    var distancesquared = (x_ - this.x) * (x_ - this.x) + (y_ - this.y) * (y_ - this.y);
    return distancesquared <= RAD * RAD;
  }
}

function setup() {
  console.log("changed3");

  createCanvas(600, 400);

  let counter = 1;
  buttons = new Array(numberOfButtons).fill().map(x => new Button( width / (numberOfButtons + 1) * counter++ ) );

  for (let i = 0; i < numberOfBubbles; i++) {
    addBubble();
  }
}

function draw() {
  background(0);

  if (!gameover) {
    for (let button of buttons) {
      stroke(255, 30, 100);
      noFill();

      ellipse(button.x, Button.y, RAD * 2, RAD * 2);

      if (button.touching(mouseX, mouseY))
        gameover = true;
    }

    for (let i = 0; i < bubbles.length; i++) {
      stroke(255, 255, 255);
      noFill();

      ellipse(bubbles[i].x, bubbles[i].y, RAD * 2, RAD * 2);

      bubbles[i].update();

      if (bubbles[i].over) {
        bubbles.splice(i, 1);
        score++;
        addBubble();
      }

      if (bubbles[i].touching(mouseX, mouseY))
        gameover = true;
    }
  }
}

function addBubble() {
  let x = Math.random() * width * 2 - (width / 2);
  let y = Math.random() * height * 2 - (height / 2);

  if ( !(0 < x && x < width && 0 < y && y < height) ) {
    bubbles.push(new Bubble( x, y, 
      Math.floor( Math.random() * numberOfButtons) ) );
  } else {
    addBubble();
  }
}
