

#include "Enemy.hpp"

Enemy::Enemy(Texture& text):
    shape(text),
    scaleY(0.07f),
    scaleX(0.07f),
    WIDTH(text.getSize().x),
    HEIGHT(text.getSize().y)
{
    WIDTH *= scaleX;
    HEIGHT *= scaleY;
    
    shape.setScale(scaleX, scaleY);
}


Vector2f Enemy::getPos(){
    return Vector2f(shape.getPosition().x, shape.getPosition().y);

}

void Enemy::update(){
    
    
}



void Enemy::setPos(float x, float y){
    shape.setPosition(x, y);
}


void Enemy::vectorInit(){
    startVector = Vector2f(shape.getPosition());
}
