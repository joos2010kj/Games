# Games
#### *TL;DR: Please read this README.md for an overview/description of each game in this repo.*

This repo holds a collection of games I have made in Java, JavaScript, and C++.  As a passionate coder who does not like to "play" games but love to "make and develop" games, I have uploaded a series of games I have made in multiple languages to this repo.

Quick Note: All of my works written in JavaScript use p5.js library!

### JAVA
1. [Wormy](https://github.com/joos2010kj/Games/tree/master/Java/Wormy)
    - A clone version fo the famous classic game Snake, Wormy is a game where the client needs to lead the in-game snake and help it grow as long as possible by leading it to food and away from its tail and the walls.
    
      CONTROLS: UP ARROW, RIGHT ARROW, DOWN ARROW, LEFT ARRROW
    
      [SCREENSHOT](https://github.com/joos2010kj/Games/blob/master/Java/Wormy/Screen%20Shot/SS.png)
    
2. [Mind Sweeper](https://github.com/joos2010kj/Games/tree/master/Java/Mind%20Sweeper)
    - A clone version of the famous classic game Minesweeper, Mind Sweeper requires the client to locate all the bombs in the field.  You can decide on the number of bombs you want there to be, and they will be hidden at random. Unfortunately, this game doesn't support mouse, but the control is very simple: ARROW KEYS to move across the 2D grid, SPACEBAR to reveal, SHIFT to flag the region.  
      
      CONTROLS: ARROW KEYS, SPACEBAR, SHIFT
      
      [SCREENSHOT](https://github.com/joos2010kj/Games/blob/master/Java/Mind%20Sweeper/Screen%20Shot/SS.png)

3. [Pong Pong](https://github.com/joos2010kj/Games/tree/master/Java/Pong%20Pong)
    - A clone version of the famous classic game Ping Pong, Pong Pong is a two-player game in which each player needs to control his or her own bat.  Each time the ball gets past a bat, the owner of the other bat will gain a point. Control is simple: the left bat is controlled by the keys 'W' and 'S', which moves it up and down, respectively.  The right bat is controlled by UP and DOWN ARROW, which moves it up and down, respectively.
    
      CONTROLS: 'W', 'S', UP ARROW, DOWN ARROW
      
      [SCREENSHOT](https://github.com/joos2010kj/Games/blob/master/Java/Pong%20Pong/Screen%20Shot/SS.png)
      
4. [Flying Bird](https://github.com/joos2010kj/Games/tree/master/Java/Flying%20Bird)
    - A simplified, clone version of the famous mobile game Flappy Bird, Flying Bird is a game where the client has to control a bird not to hit the bricks as the bird flies onward. The control is simple as usual (just a SPACEBAR)! Score will be based on the number of blocks you successfully got past, so try to fly past as many blocks as possible!
    
        CONTROLS: SPACEBAR
    
        [SCREENSHOT](https://github.com/joos2010kj/Games/blob/master/Java/Flying%20Bird/Screen%20Shot/SS.png)
      
5. [Space Dominator](https://github.com/joos2010kj/Games/tree/master/Java/Space%20Dominator)
    - A clone version of the famous classic game Space Invader, Space Dominator is a game where the client needs to control the plane and shoot the bullets at the aliens coming downward from above. Aliens move frantically, so try to act fast! The plane is controlled by LEFT and RIGHT ARROWS, and bullets are shot upon pressing SPACEBAR. Of course, the aliens die on contact with a bullet.
    
      CONTROLS: LEFT ARROW, RIGHT ARROW, SPACEBAR
      
      [SCREENSHOT](https://github.com/joos2010kj/Games/blob/master/Java/Space%20Dominator/Screen%20Shot/SS.png)

6. [Cross](https://github.com/joos2010kj/Games/tree/master/Java)
    - A simplified, clone version of the classic game Frogger, Cross is a game where the player has to cross a series of disjointed platforms and get to the right-end side from the left-end side as quickly as possible using only two keys -- LEFT ARROW & RIGHT ARROW.  The clock will be ticking, the number of deaths will be recorded, and the player will be respawned in the very beginning after each death, so try to cross as quickly yet calmly as possible!
    
      CONTROLS: LEFT ARROW, RIGHT ARROW
    
      [SCREENSHOT](https://github.com/joos2010kj/Games/blob/master/Java/Cross/Screen%20Shot/SS.png)
    
### JAVASCRIPT

1. [Roll and Roll](https://github.com/joos2010kj/Games/tree/master/JavaScript/Roll%20and%20Roll)
    - Roll and Roll, the Game of a Rolling Ball and Ascending Platforms!

        OBJECTIVE: Make the ball last as long as possible.

        CONTROLS: You can change the direction of the ball with...

            Mouse Click OR
            Brief Loud Noise (e.g. Clap, Tapping on any hard object with a fingernail, etc.)
    
        HOW TO PLAY: Try to lead the ball to the gap in each level and let it get to the lowest level.

        NOTE:
        1) Touching the bottom is fine, but touching the top leads to GAME OVER.
        2) If you want to use the brief loud noise as a means of changing the ball's direction, you must ALLOW the microphone usage in the beginning.
        3) You can start the game by clicking anywhere in the canvas once it's loaded.
       
        [SCREENSHOT 1](https://github.com/joos2010kj/Games/blob/master/JavaScript/Roll%20and%20Roll/Screen%20Shot/SS1.png)
        
        [SCREENSHOT 2](https://github.com/joos2010kj/Games/blob/master/JavaScript/Roll%20and%20Roll/Screen%20Shot/SS2.png)
        
2. [Hangman](https://github.com/joos2010kj/Games/tree/master/JavaScript/Hangman)
    - A clone version of the classic game Hangman, this game randomly picks a word from an array of words and lets the client take a stab at fully guessing the word right.  Just like the original Hangman, it does give the client several chances to guess, and the correctly-guessed letters will be revealed in the corresponding box of the word in question.
    
        CONTROLS: KEYBOARD
        
        [SCREENSHOT 1](https://github.com/joos2010kj/Games/blob/master/JavaScript/Hangman/Screen%20Shot/SS1.png)
        
        [SCREENSHOT 2](https://github.com/joos2010kj/Games/blob/master/JavaScript/Hangman/Screen%20Shot/SS2.png)
        
3. [Crossword](https://github.com/joos2010kj/Games/tree/master/JavaScript/Crossword)
    - A clone version of the classic game Crossword, this game randomly picks a specified number of words and lets the client locate all the words in a big map/grid filled with random letters.  All they need to do is to click on all the boxes in which the randomly-chosen words' letters lie! The client may specify the number of words he/she wants there to be (NUMBER_OF_WORDS in client_data.js), specify the size of the map (PUZZLE_SIZE in client_data.js), and also specify the minimum length of a word (MIN_LENGTH in client_data.js).
    
        CONTROLS: MOUSE
        
        [SCREENSHOT 1](https://github.com/joos2010kj/Games/blob/master/JavaScript/Crossword/Screen%20Shot/SS1.png)
        
        [SCREENSHOT 2](https://github.com/joos2010kj/Games/blob/master/JavaScript/Crossword/Screen%20Shot/SS2.png)
        
        [SCREENSHOT 3](https://github.com/joos2010kj/Games/blob/master/JavaScript/Crossword/Screen%20Shot/SS3.png)

4. [Hide and Seek](https://github.com/joos2010kj/Games/tree/master/JavaScript/Hide%20and%20Seek)
    - Hide and Seek is a game where the client (mouse cursor) has to hide from being discovered by an in-game robot, which searches around.  As the controller of the cursor, your ultimate goal is to not be within the robot's angle of vision.  Quick note for you, clients initially tend to believe that hide behind the robot is the best, but it's actually the worst idea because the robot immediately turns around, too!
    
        CONTROLS: MOUSE
        
        [SCREENSHOT](https://github.com/joos2010kj/Games/blob/master/JavaScript/Hide%20and%20Seek/Screen%20Shot/SS.png)
        
5. [Rain Evasion](https://github.com/joos2010kj/Games/tree/master/JavaScript/Rain%20Evasion)
    - Rain Evasion is a game where the player has to swiftly move around (horizontally only) so as to avoid the contact with rain.  Score is primarily based on the number of raindrops you successfully evaded from.  
        
        CONTROLS: MOUSE
        
        [SCREENSHOT](https://github.com/joos2010kj/Games/blob/master/JavaScript/Rain%20Evasion/Screen%20Shot/SS.png)

6. [Rhythm](https://github.com/joos2010kj/Games/tree/master/JavaScript/Rhythm)
    - Rhythm is a game of five rings and a cursor where the player has to move the cursor quickly enough to dodge all the rings coming from all around the places to any of the five center rings.  Once a ring snaps into any of the center rings, it disappears.   
    
        CONTROLS: MOUSE
        
        [SCREENSHOT](https://github.com/joos2010kj/Games/blob/master/JavaScript/Rhythm/Screen%20Shot/SS.png)

7. [Flappy Bird Clone](https://github.com/joos2010kj/Games/tree/master/JavaScript/Flappy%20Bird)
    - A simplified, clone version of the famous mobile game Flappy Bird, this is a game where the client has to control a bird not to hit the bricks as the bird flies onward. The bird is controlled by just a SPACEBAR. Score will be based on the number of blocks you successfully got past, so try to fly past as many blocks as possible!
      
      CONTROLS: SPACEBAR
      
      [SCREENSHOT](https://github.com/joos2010kj/Games/blob/master/Java/Flying%20Bird/Screen%20Shot/SS.png)
      
8. [Snake Clone](https://github.com/joos2010kj/Games/tree/master/JavaScript/Snake%20(Incomplete))
    - A clone version fo the famous classic game Snake, this is a game where the client needs to lead the in-game snake and help it grow as long as possible by leading it to food and away from its tail and the walls.
    
        CONTROLS: ARROW KEYS
        
        [SCREENSHOT](https://github.com/joos2010kj/Games/blob/master/JavaScript/Snake%20(Incomplete)/Screen%20Shot/SS.png)
        
9. [Catch the Moles](https://github.com/joos2010kj/Games/tree/master/JavaScript/Catch%20the%20Moles)

    - A simplified, clone version of the classic game Whack-a-Mole, Catch the Moles is a game where the player has to catch as many moles as possible in a given period of time.  Several factors can be adjusted by the player, including the period of game (60 seconds by default), the chance of moles’ appearances (0.1% per frame by default), and the duration of a mole’s appearance (0.5 seconds by default),  by changing the values in "option" object literal inside sketch.js.  The moles can be caught by two means — a mouse and a keyboard.  Using a mouse, the moles can be caught if any of them is clicked on time — that is, after it appears but before it disappears.  Also, because the moles in the game are located in a similar manner to how the keys Q, W, E, A, S, D, Z, X, and D are placed in a keyboard, a mole is made to be caught if a respective key is pressed on time, too.  For example, pressing ‘Q’ on time catches the mole in the upper left corner, ’S’ catches the one in the center, ‘C’ catches the one in the lower right corner.
        
        CONTROLS: 
        
            Mouse OR
            ‘Q’, ‘W’, ‘E’, ‘A’, ’S’, ‘D’, ‘Z’, ‘X’, ‘C’

        [SCREENSHOT 1](https://github.com/joos2010kj/Games/blob/master/JavaScript/Catch%20the%20Moles/Screen%20Shot/SS1.png)
    
        [SCREENSHOT 2](https://github.com/joos2010kj/Games/blob/master/JavaScript/Catch%20the%20Moles/Screen%20Shot/SS2.png)
        
### C++

1. [Pop](https://github.com/joos2010kj/Games/tree/master/C%2B%2B/Pop)
    - A game where the player needs to dodge all the obstacles.
    
        CONTROLS: ARROW KEYS
