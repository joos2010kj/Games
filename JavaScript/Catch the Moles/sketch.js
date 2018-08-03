let moles = [];
let field;
let score;
let option = {
  chance: 0.001,
  duration: 0.5,
  timer: 60
}
let setting = {
  play: false,
  first: false,
  second: false,
  last: false
}

function setup(){
  createCanvas(500, 500);

  for(let i = 0; i < 3; i++){
    for(let j = 0; j < 3; j++){
      moles.push(new Mole(width / 3 * j + width / 6, height / 3 * i + height / 6));
    }
  }

  Mole.chance = option.chance;
  Mole.duration = option.duration;

  score = createP("SCORE: " + Mole.points);

  setting.first = true;

  window.setTimeout(function(){
    setting.second = true;
    setting.first = false;
  }, 3 * 1000);
}

function draw(){
  background(51);

  if(setting.first){
    textSize(100);
    text("READY?", 50, 300);
  }

  if(setting.second){
    textSize(100);
    text("GO!", 150, 300);

    window.setTimeout(function(){
      setting.second = false;
      setting.play = true;

      window.setTimeout(function(){
        setting.play = false;
        setting.last = true;
      }, option.timer * 1000);
    }, 3 * 1000);
  }

  if(setting.play){
    for(let i = 0; i < moles.length; i++){
      let mole = moles[i];
      mole.update();
      mole.show();
    }

    score.html("SCORE: " + Mole.points);
  }

  if(setting.last){
    textSize(75);
    textAlign(CENTER, CENTER);
    text("SCORE: " + Mole.points, width / 2, height / 2);
  }

}

function mousePressed(){
  Mole.check(moles);
}

function keyPressed(){
  let mole;
  switch(key){
    case 'Q': mole = moles[0];
    break;
    case 'W': mole = moles[1];
    break;
    case 'E': mole = moles[2];
    break;
    case 'A': mole = moles[3];
    break;
    case 'S': mole = moles[4];
    break;
    case 'D': mole = moles[5];
    break;
    case 'Z': mole = moles[6];
    break;
    case 'X': mole = moles[7];
    break;
    case 'C': mole = moles[8];
    break;
  }

  if(mole.option.up && !mole.option.hit){
    mole.option.hit = true;
    Mole.points++;
  }
}
