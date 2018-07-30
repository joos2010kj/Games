

#include <SFML/Graphics.hpp>
using namespace sf;

#ifndef Player_hpp
#define Player_hpp

#include <stdio.h>

class Player{
public:
    Player(Texture& text);
    
    Sprite getShape(){ return shape; }
    void setPosition(float x, float y);
    void move(float x, float y);
    Vector2f getCenter();
    Vector2f getEndVector(){ return endVector; }
    
    void addPower() { myPower++; }
    void addCapacity() { maxBubbles++; }
    void addScore() { score++; }
    int getMyPower() { return myPower; }
    int getMyMaxBubbles() { return maxBubbles; }
    int getScore(){ return score; }
    void vectorInit();
    
private:
    Sprite shape;
    float WIDTH, HEIGHT;
    float scaleX, scaleY;
    int myPower = 1, maxBubbles = 1;
    int score = 0;
    Vector2f endVector;
};

#endif /* Player_hpp */
