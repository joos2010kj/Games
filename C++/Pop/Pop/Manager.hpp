
#include <SFML/Graphics.hpp>
using namespace sf;

#include <string>
#include <map>

#ifndef Manager_hpp
#define Manager_hpp

#include <stdio.h>


class Manager{
    
public:
    
    Manager();
    
    Texture& getTex(std::string name);
    Font& getFont();
    
private: //Methods
    void loadTex();
    
    
private: //Textures
    
    Texture snowmanTex, bubbleTex, coinTex, pickleTex, batTex;
    std::map <std::string, Texture> textureFolder;
    
private:
    Font ralewayFont;
    
    
    
};

#endif /* Manager_hpp */
