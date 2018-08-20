const fileURL = "res/wordlist.txt";
const NUMBER_OF_WORDS = 10;
const PUZZLE_SIZE = [20, 15]; //(height , width)
const MIN_LENGTH = 5; //MIN LENGTH of a word is 5

let words;
let system;
let words_in_question;
let answer;

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
    console.log("COMPLETED!");
  }
}
