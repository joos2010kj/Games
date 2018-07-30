
#include <SFML/Window.hpp>
#include <SFML/System.hpp>
#include <SFML/Graphics.hpp>
#include <iostream>
#include <vector>

#include <stdio.h>
#include <cmath>
using namespace sf;

#include "Manager.hpp"
#include "Bubble.hpp"
#include "Player.hpp"
#include "Coin.hpp"
#include "Pickle.hpp"
#include "Enemy.hpp"
#include "DEFINITIONS.h"

#ifndef Game_hpp
#define Game_hpp

class Game{
    
public:
    Game();
    
private:
    void init();
    void update();
    void draw();
    void run();
    void windowEvent();
    void isKeyPressed();
    void makeNewBubble();
    void spawnCheck();
    void collision();
    void updateAndDestroy();
    
private:    //Variables
    RenderWindow window;
    Manager manager;
    Player player;
    Coin coin;
    Bubble bubble;
    Pickle pickle;
    Enemy enemy;
    
    std::vector<Bubble> bubbles;
    std::vector<Coin> coins;
    std::vector<Pickle> pickles;
    std::vector<Enemy> enemies;
    
    
    int spawnBubbleCounter = 0, spawnCoinCounter = 0, spawnPickleCounter = 0, spawnEnemyCounter = 0;
    int bubbleNum = 0;
    int score = 0;
    
};


#endif /* Game_hpp */
