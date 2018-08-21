const fileURL = "res/wordlist.txt";

let words;
let system;
let words_in_question;
let answer;
let result = {
  won: false
};

function preload(){
  words = loadStrings(fileURL);
  /*
  fetch(fileURL).
  then( url => {
  return url.text();
}).then( text =>{
words = text.split("\n");
});
*/
}

function setup(){
  createCanvas(500, 500);
  system = new Game(NUMBER_OF_WORDS);
  words_in_question = createP("");
  words_in_question.parent("#enlarge");
  answer = document.getElementById("answer");
}

function draw(){
  background(51);
  system.show();

  let arr = system.getWords();
  let sentence = "";
  for(let i = 0; i < system.getWords().length - 1; i++){
    sentence += arr[i] + ", ";
  }
  words_in_question.html("WORDS: <br />" + sentence + arr[arr.length - 1]);

  if(result.won){
      push();
      textSize(Math.min(width, height) / 15);
      fill(255, 0 , 255);
      text("MISSION ACCOMPLISHED!", width / 2 , height / 2);
      pop();
  }
}

function mousePressed(){
  let option = {
    r: floor(map(mouseY, 0, height, 0, PUZZLE_SIZE[0])),
    c: floor(map(mouseX, 0, width, 0, PUZZLE_SIZE[1]))
  }

  if(option.r >= 0 && option.r < PUZZLE_SIZE[0] && option.c >= 0 && option.c < PUZZLE_SIZE[1]){
    let element = system.access(option.r, option.c);
    element.visited = !element.visited;

    if(element.visited){
      element.col.r = 100;
      element.col.g = 100;
      element.col.b = 100;
    }else{
      element.col.r = 0;
      element.col.g = 255;
      element.col.b = 0;
    }
  }

  if(system.completion_check()){
    result.won = true;
  }
}
