
#include <SFML/Graphics.hpp>
using namespace sf;

#ifndef Coin_hpp
#define Coin_hpp

#include <stdio.h>

class Coin{
public:
    Coin(Texture& text);
    
    void setPos(float x, float y);
    
    Sprite getShape() { return shape; }
    
private:
    Sprite shape;
    float scaleX, scaleY;
    int WIDTH, HEIGHT;
};

#endif /* Coin_hpp */
