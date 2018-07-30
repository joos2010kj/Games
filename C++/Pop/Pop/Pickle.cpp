
#include "Pickle.hpp"


Pickle::Pickle(Texture& text):
    shape(text),
    WIDTH(text.getSize().x),
    HEIGHT(text.getSize().y),
    scaleX(0.05f),
    scaleY(0.05f)
{
    shape.setScale(scaleX, scaleY);

    WIDTH *= scaleX;
    HEIGHT *= scaleY;

}

void Pickle::setPos(float x, float y){
    shape.setPosition(x, y);
}
