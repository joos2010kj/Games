

#include <SFML/Graphics.hpp>
#include "DEFINITIONS.h"
#include "Player.hpp"
using namespace sf;


#ifndef Bubble_hpp
#define Bubble_hpp

#include <stdio.h>

class Bubble{
public:
    Bubble(Texture& text);
    
    Sprite getShape(){ return shape; }
    void setRad(int r);
    void update();
    void produce(Player& player);
    void setPower(int p);
    
    void setPos(float x, float y);
    
    bool getStatus() { return destroy; }
    
    RectangleShape& getWaveV(){ return waveVertical; }
    RectangleShape& getWaveH(){ return waveHorizontal; }
    
private:

private:
    bool destroy = false;
    int popCounter = 0, power = 10;
    int rad;
    Sprite shape;
    float scaleX, scaleY;
    int WIDTH, HEIGHT;
    RectangleShape waveVertical, waveHorizontal;
    
};

#endif /* Bubble_hpp */
