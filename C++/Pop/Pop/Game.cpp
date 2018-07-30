

#include "Game.hpp"

Game::Game():
    window(VideoMode(2000,1600), "Pop!"),
    bubble(manager.getTex("Bubble")),
    player(manager.getTex("Snowman")),
    coin(manager.getTex("Coin")),
    pickle(manager.getTex("Pickle")),
    enemy(manager.getTex("Bat"))
{
    init();
    run();
}

void Game::run(){
    while(window.isOpen()){
        
        windowEvent();
        
        window.clear(Color::Black);
        
        update();
        
        draw();
        
        window.display();
        
    }
}

void Game::spawnCheck(){
    if( spawnBubbleCounter < spawnBubbleDeclare){
        spawnBubbleCounter++;
    }
    
    if(spawnCoinCounter < spawnCoinDeclare){
        spawnCoinCounter++;
    }else{
        
        coin.setPos(rand() % window.getSize().x, rand() % window.getSize().y);
        coins.push_back(coin);
        
        spawnCoinCounter = 0;
    }
    
    if(spawnPickleCounter < spawnPickleDeclare)
        spawnPickleCounter++;
    else{
        pickle.setPos(rand() % window.getSize().x, rand() % window.getSize().y);
        pickles.push_back(pickle);
        
        spawnPickleCounter = 0;
    }
    
    if(spawnEnemyCounter < spawnEnemyDeclare){
        spawnEnemyCounter++;
    }else{
        int temp = rand() % 100;
        
        if(temp < 25){
            enemy.setPos(rand() % window.getSize().x, -enemy.getSize().y);
        }else if(temp < 50){
            enemy.setPos(window.getSize().x + enemy.getSize().x, rand() % window.getSize().y);
        }else if(temp < 75){
            enemy.setPos(rand() % window.getSize().x, window.getSize().y + enemy.getSize().y);
        }else{
            enemy.setPos(-enemy.getSize().x, rand() % window.getSize().y);
        }
        
        enemy.vectorInit();
        
        enemy.setAimDir(player.getEndVector() - enemy.getStartVec());
        enemy.setAimDirNorm(enemy.getAimDir() / (float)sqrt(pow(enemy.getAimDir().x,2) + pow(enemy.getAimDir().y,2)));
        enemy.setVelocity(enemy.getAimDirNorm() * (float)enemy.getPower());
        
        
        
        enemies.push_back(enemy);
        
        spawnEnemyCounter = 0;

    }
}


void Game::init(){
    window.setFramerateLimit(60);
    
    player.setPosition(window.getSize().x / 2, window.getSize().y / 2);
    
    bubble.produce(player);
    
    coin.setPos(rand() % window.getSize().x, rand() % window.getSize().y);
    coins.push_back(coin);
    
    pickle.setPos(rand() % window.getSize().x, rand() % window.getSize().y);
    pickles.push_back(pickle);
    
    enemy.setPos(rand() % window.getSize().x, rand() % window.getSize().y);
    enemy.vectorInit();
    
    enemy.setAimDir(player.getEndVector() - enemy.getStartVec());
    enemy.setAimDirNorm(enemy.getAimDir() / (float)sqrt(pow(enemy.getAimDir().x,2) + pow(enemy.getAimDir().y,2)));
    enemy.setVelocity(enemy.getAimDirNorm() * (float)enemy.getPower());
    
    enemies.push_back(enemy);
}

void Game::update(){
    
    spawnCheck();
    
    player.vectorInit();
    
    updateAndDestroy();
    
    collision();
    
    isKeyPressed();
    
    
    
}
 
void Game::updateAndDestroy(){
    for(int i =0 ; i < bubbles.size(); i++){
        bubbles[i].update();
        if(bubbles[i].getStatus()){
            bubbles.erase(bubbles.begin() + i);  //<--- bubble destroyer
            bubbleNum--;
        }
    }
    
    for(int i = 0; i < enemies.size(); i++){
        enemies[i].move(enemies[i].getVelocity());
        if(enemies[i].getPos().x < - 3 * enemy.getSize().x ||
           enemies[i].getPos().x > window.getSize().x + 3 * enemy.getSize().x ||
           enemies[i].getPos().y < - 3 * enemy.getSize().y ||
           enemies[i].getPos().y > window.getSize().y + 3 * enemy.getSize().y){
            enemies.erase(enemies.begin() + i);
        }
    }
    
    
    
}

void Game::collision(){
    for(int i = 0 ; i < coins.size(); i++){
        if(player.getShape().getGlobalBounds().intersects(coins[i].getShape().getGlobalBounds())){
            player.addPower();
            coins.erase(coins.begin() + i);
        }
    }
    for(int i = 0 ; i < pickles.size(); i++){
        if(player.getShape().getGlobalBounds().intersects(pickles[i].getShape().getGlobalBounds())){
            player.addCapacity();
            pickles.erase(pickles.begin() + i);
        }
    }
    
    for(int i = 0; i < enemies.size(); i++){
        for(int j = 0; j < bubbles.size(); j++){
            if(bubbles[j].getWaveH().getGlobalBounds().intersects(enemies[i].getShape().getGlobalBounds())){
                //add point
                player.addScore();
                enemies.erase(enemies.begin() + i);
            }
            
            if(bubbles[j].getWaveV().getGlobalBounds().intersects(enemies[i].getShape().getGlobalBounds())){
                //add point
                player.addScore();
                enemies.erase(enemies.begin() + i);
            }
        }
    }
    
    
}

void Game::isKeyPressed(){
    
    if(Keyboard::isKeyPressed(Keyboard::W)){
        player.move(0.f, -10.f);
    }
    if(Keyboard::isKeyPressed(Keyboard::A)){
        player.move(-10.f, 0.f);
    }
    if(Keyboard::isKeyPressed(Keyboard::S)){
        player.move(0.f, 10.f);
    }
    if(Keyboard::isKeyPressed(Keyboard::D)){
        player.move(10.f, 0.f);
    }
    
    if(Keyboard::isKeyPressed(Keyboard::Space)){
        if(spawnBubbleCounter >= spawnBubbleDeclare){
            
            if(bubbleNum < player.getMyMaxBubbles()){
                makeNewBubble();
                spawnBubbleCounter = 0;
                bubbleNum++;
            }
        }
    }
}


void Game::draw(){
    window.draw(player.getShape());
    
    for(Bubble x: bubbles){
        window.draw(x.getShape());
        window.draw(x.getWaveH());
        window.draw(x.getWaveV());
    }
    
    for(Coin x: coins){
        window.draw(x.getShape());
    }
    
    for(Pickle x: pickles){
        window.draw(x.getShape());
    }
    
    for(Enemy x: enemies){
        window.draw(x.getShape());
    }
    
}

void Game::makeNewBubble(){
    bubble.produce(player);
    bubble.setPower(player.getMyPower());
    
    bubbles.push_back(bubble);
}


void Game::windowEvent(){
    Event e;
    while(window.pollEvent(e)){
        
        if(e.type == Event::Closed){
            window.close();
        }
        
    }
}
