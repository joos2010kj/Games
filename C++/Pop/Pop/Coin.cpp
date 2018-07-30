

#include "Coin.hpp"


Coin::Coin(Texture& text):
    shape(text),
    WIDTH(text.getSize().x),
    HEIGHT(text.getSize().y),
    scaleX(0.02),
    scaleY(0.02)
{
    shape.setScale(scaleX, scaleY);
    
    WIDTH *= scaleX;
    HEIGHT *= scaleY;
    
}



void Coin::setPos(float x, float y){
    shape.setPosition(x, y);
    
    
}
