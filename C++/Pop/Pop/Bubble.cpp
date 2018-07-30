
#include "Bubble.hpp"


Bubble::Bubble(Texture& text):
    shape(text),
    scaleX(0.2f),
    scaleY(0.2f)
{
    WIDTH = text.getSize().x * scaleX;
    HEIGHT = text.getSize().y * scaleY;
    shape.setScale(scaleX, scaleY);
    this->rad = WIDTH / 2;
    
}

void Bubble::setPower(int p){
    this->power = p;
}


void Bubble::setRad(int r){
    this->rad = r;
}

void Bubble::update(){
    popCounter++;
    
    if(popCounter >= POP_TIME){
        shape.setColor(Color::Transparent);  // <--- Hides the sprite
        
        waveHorizontal.setSize(Vector2f(rad* 2 * power ,rad * 2));
        waveVertical.setSize(Vector2f(rad * 2,rad* 2 * power));
        waveHorizontal.setPosition(shape.getPosition().x - waveHorizontal.getSize().x / 2 + rad , shape.getPosition().y);
        waveVertical.setPosition(shape.getPosition().x, shape.getPosition().y - waveVertical.getSize().y / 2 + rad);
        
        
        if(popCounter >= BUBBLE_LIFE){
            destroy = true;
        }
    }
    
}

void Bubble::produce(Player &player){
    shape.setPosition(player.getCenter().x - rad, player.getCenter().y - rad);
}


void Bubble::setPos(float x, float y){
    shape.setPosition(x, y);
}

