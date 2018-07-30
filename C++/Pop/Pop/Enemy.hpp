
#include <SFML/Graphics.hpp>
using namespace sf;

#ifndef Enemy_hpp
#define Enemy_hpp

#include <stdio.h>

class Enemy{
    
public:
    Enemy(Texture& text);
    
    Sprite getShape(){ return shape; }
    Vector2f getPos();
    Vector2f getStartVec(){ return startVector; }
    Vector2f getAimDir(){ return aimDir; }
    Vector2f getAimDirNorm(){ return aimDirNorm; }
    Vector2f getVelocity(){ return velocity; }
    int getPower(){ return power; }
    Vector2f getSize(){ return Vector2f(WIDTH, HEIGHT);}
    
    void vectorInit();
    void setAimDir(Vector2f vec){ aimDir = vec;}
    void setAimDirNorm(Vector2f vec){ aimDirNorm = vec;}
    void move(Vector2f vec){ shape.move(vec); }
    void setVelocity(Vector2f vec){ velocity = vec;}
    
    void setPos(float x, float y);
    
private:
    Sprite shape;
    void update();
    float WIDTH, HEIGHT, scaleX, scaleY, power = 10.f;
    Vector2f startVector, aimDir, aimDirNorm, velocity;
};

#endif /* Enemy_hpp */
