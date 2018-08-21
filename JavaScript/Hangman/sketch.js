var words = [];
var word = {
  name: undefined,
  leng: undefined
};
var boxes = {
  characters: [], //holds rect coordiates of each character of the chosen word
  correctLetter: [],
  revealed: [],
  guessed: []
};
var player = {
  life: 7,
  constLife: 7 //update this too!
}
var input;

function preload(){
  words = loadStrings('wordlist.txt');
}

function setup(){
  createCanvas(800, 300);

  word["name"] = random(words);
  word["leng"] = word["name"].length;

  let space = width / word["leng"];
  word["space"] = space;
  for(let i = 0; i < width - 1; i += space){
    boxes["characters"].push([i, 100, space, 100]);
  }

  for(let i = 0; i < word.name.length; i++){
    boxes["correctLetter"].push(word.name.charAt(i));
    boxes["revealed"].push(0);
  }
  createP();
  input = createInput('');
  // input.input(
  //   () =>
  //   console.log(input.value())
  // );
}

function draw(){
  background(51);

  for(let char of boxes.characters){
    rect(char[0], char[1], char[2], char[3]);
  }

  textSize(100);
  textAlign(CENTER, CENTER);

  let counter = 0;
  for(let i = 0; i < boxes.correctLetter.length; i++){
    if(boxes.revealed[i] == 1){
      text(boxes.correctLetter[i],
        boxes.characters[i][0] + word["space"] / 2,
        height / 2 );
        counter++;
    }
  }

  if(counter == boxes.correctLetter.length){
    push();
    textSize(100);
    fill(255, 0, 0, 100);
    text("YOU WIN!", width / 2, height / 2);
    pop();
  }

  push();
  textSize(30);
  for(let i = 0;i < boxes.guessed.length; i++){
    text(boxes.guessed[i].toLowerCase(), 30 + i * 50, height - 30);
  }
  text("LIFE: " + player.life, 70,30);
  pop();
  if(player.life <= 0){
    for(let i = 0; i < boxes.correctLetter.length; i++){
      if(boxes.revealed[i] == 0){
        fill(0, 255, 0);
        text(boxes.correctLetter[i],
          boxes.characters[i][0] + word["space"] / 2,
          height / 2 );
      }
    }

    push();
    textSize(100);
    fill(255, 0, 0, 100);
    text("GAME OVER", width / 2, height / 2);
    pop();

    noLoop();
  }
}

function guess(char){
  if(char.length != 1){
    return;
  }

  for(let i = 0;i < boxes.guessed.length; i++){
    if(boxes.guessed[i].toLowerCase() == char.toLowerCase()){
      return;
    }
  }

  boxes.guessed.push(char);
  let correct = false;

  for(let i = 0;i < boxes.correctLetter.length; i++){
    if(boxes.correctLetter[i].toLowerCase() == char.toLowerCase()){
      boxes.revealed[i] = 1;
      correct = true;
    }
  }

  if(correct == false){
    player.life--;
    player.life = constrain(player.life, 0, player.constLife);
  }
}

function keyPressed(){
  if(keyCode == ENTER){
    guess(input.value());
    input.elt.value = "";
  }
}
