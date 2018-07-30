
#include "Player.hpp"

Player::Player(Texture& text):
    shape(text),
    WIDTH(text.getSize().x),
    HEIGHT(text.getSize().y),
    scaleX(0.3f),
    scaleY(0.3f)
{
    
    shape.setPosition(0, 0);
    shape.setScale(scaleX, scaleY);
    WIDTH *= scaleX;
    HEIGHT *= scaleY;
    
}

void Player::setPosition(float x, float y){
    
    shape.setPosition(x, y);
    
}

void Player::move(float x, float y){
    shape.move(x, y);
}

Vector2f Player::getCenter(){
    return Vector2f(shape.getPosition().x + WIDTH / 2, shape.getPosition().y + HEIGHT / 2);
}


void Player::vectorInit(){
    endVector = Vector2f(shape.getPosition());
}
