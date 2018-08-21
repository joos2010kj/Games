function Game(num){
  this.option = {
    num: num,
    list: [],
    longest: 0,
    key: [],
    setup: function(){
      while(this.list.length < this.num){
        let rand = random(words);
        if(rand.match(/^[0-9a-z]+$/) &&
           rand.length <= Math.max(PUZZLE_SIZE[0] - 3, PUZZLE_SIZE[1] - 3) &&
           //rand.length == 5){
           rand.length >= MIN_LENGTH){
          if(rand.length > this.longest){
            this.longest = rand.length;
          }
          this.list.push(rand);
        }
      }
    }
  };
  this.field = [];

  //-----------------//

  this.constructor = function(){
    this.option.setup();
    this.design();
    this.registerAll();
    this.fillIn();
    //console.log(this.option.list);
  }

  this.show = function(){
    for(let i = 0; i < this.field.length; i++){
      for(let j = 0; j < this.field[0].length; j++){
        this.field[i][j].show();
      }
    }
  }

  this.design = function(){ //design the map
    let grid = {
      x: width / PUZZLE_SIZE[1],
      y: height / PUZZLE_SIZE[0]
    }

    let counter = 0;

    for(let i = 0; i < PUZZLE_SIZE[0]; i++){
      this.field.push(new Array(PUZZLE_SIZE[1]));
      for(let j = 0; j < PUZZLE_SIZE[1]; j++){
        let coord = {
          x: j * grid.x,
          y: i * grid.y,
          postX: grid.x,
          postY: grid.y
        }

        this.field[i][j] = new Block('-', counter++, coord.x, coord.y, coord.postX, coord.postY);
      }
    }
  }

  this.access = function(row, col){
    return this.field[row][col];
  }

  this.registerAll = function(){
    for(let i = 0; i < NUMBER_OF_WORDS; i++){
      //console.log(this.option.list[i]);
      this.register(this.option.list[i]);
    }
  }

  this.register = function(word){
    const tp = 2 * PI;
    let ang = Math.random() * tp;
    let cutoff = tp / 8;
    let dir = "";
    let offsetR, offsetC;
    let chars = word.split("");
    let finalWord = word;

    if(ang < tp - 7 * cutoff){
      dir = "E"; offsetR = 0; offsetC = 1;
    }else if(ang < tp - 6 * cutoff){
      dir = "NE"; offsetR = -1; offsetC = 1;
    }else if(ang < tp - 5 * cutoff){
      dir = "N"; offsetR = -1; offsetC = 0;
    }else if(ang < tp - 4 * cutoff){
      dir = "NW"; offsetR = -1; offsetC = -1;
    }else if(ang < tp - 3 * cutoff){
      dir = "W"; offsetR = 0; offsetC = -1;
    }else if(ang < tp - 2 * cutoff){
      dir = "SW"; offsetR = 1; offsetC = -1;
    }else if(ang < tp - cutoff){
      dir = "S"; offsetR = 1; offsetC = 0;
    }else{
      dir = "SE"; offsetR = 1; offsetC = 1;
    }

    //console.log(`ang: ${ang}, tp: ${tp}, cutoff: ${cutoff}, dir: ${dir}`);

    let counter = 0;
    let rand = [floor(random(PUZZLE_SIZE[0])), floor(random(PUZZLE_SIZE[1]))];
    let r = rand[0], c = rand[1];
    let loc = [];
    let fail = false;
    let failCounter = 0;

    while(counter < chars.length){
      if(r >= PUZZLE_SIZE[0] || c >= PUZZLE_SIZE[1] || r < 0 || c < 0 || fail){
        //console.log(word + ", TRIGGERED");
        failCounter++;
        loc = [];
        fail = false;
        counter = 0;
        rand = [floor(random(PUZZLE_SIZE[0])), floor(random(PUZZLE_SIZE[1]))];
        r = rand[0];
        c = rand[1];
      }

      //console.log(r + ", " + c + ", " + chars[counter]);
      let accessedChar = this.access(r, c).char;
      if(accessedChar === '-' || accessedChar === chars[counter]){
        //accessedChar = chars[counter++];
        loc.push({r, c});
        counter++;
      }else{
        fail = true;
      }
      r += offsetR;
      c += offsetC;

      if(failCounter >= 1000){
        let rand;
        failCounter = 0;
        while(true){
          rand = random(words);
          finalWord = rand;
          if(rand.match(/^[0-9a-z]+$/) &&
             rand.length <= Math.max(PUZZLE_SIZE[0] - 3, PUZZLE_SIZE[1] - 3) &&
             rand.length >= MIN_LENGTH){
            let ind = this.option.list.indexOf(word);
            this.option.list.splice(ind, 1, rand);
            chars = rand.split("");
            break;
          }
        }

        //console.log(word + " changed to " + rand);
      }
    }

    this.option.key[finalWord.toUpperCase()] = [];

    for(let i = 0; i < loc.length; i++){
      this.access(loc[i].r, loc[i].c).char = chars[i];
      this.access(loc[i].r, loc[i].c).golden = true;
      this.option.key[finalWord.toUpperCase()].push([loc[i].r, loc[i].c]);
    }
  }

  this.solution = function(){
    Object.keys(this.option.key).forEach(ele =>{
      let arr = this.option.key[ele];
      for(let i = 0; i < arr.length; i++){
        //console.log(arr[i][0] + ", " + arr[i][1]);
        this.access(arr[i][0], arr[i][1]).col.r = 100;
        this.access(arr[i][0], arr[i][1]).col.g = 200;
        this.access(arr[i][0], arr[i][1]).col.b = 255;
      }
    });
  }

  this.fillIn = function(){
    let str = "abcdefghijklmnopqrstuvwxyz";

    for(let i = 0; i < this.field.length * this.field[0].length; i++){
      let element = this.field[floor(i / PUZZLE_SIZE[1])][i % PUZZLE_SIZE[1]];
      if(element.char == "-"){
        element.char = str.charAt(floor(random(str.length)));
      }
    }
  }

  this.getWords = function(){
    return Object.keys(this.option.key);
  }

  this.completion_check = function(){
    for(let i = 0; i < this.field.length; i++){
      for(let j = 0; j < this.field[0].length; j++){
        let element = this.field[i][j];
        if(element.golden){
          if(!element.visited){
            return false;
          }
        }else if(!element.golden){
          if(element.visited){
            return false;
          }
        }
      }
    }

    return true;
  }

  this.constructor();
}
