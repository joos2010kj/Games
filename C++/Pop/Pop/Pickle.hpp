
#include <SFML/Graphics.hpp>
using namespace sf;


#ifndef Pickle_hpp
#define Pickle_hpp

#include <stdio.h>


class Pickle{

public:
    Pickle(Texture& text);
    
    void setPos(float x, float y);
    
    Sprite getShape() { return shape; }
    
private:
    Sprite shape;
    float scaleX, scaleY, WIDTH, HEIGHT;
    
};

#endif /* Pickle_hpp */
