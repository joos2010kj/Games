
#include "Manager.hpp"


Manager::Manager(){
    loadTex();
    ralewayFont.loadFromFile("Font/RalewayFont/Raleway-Black.ttf");
}

void Manager::loadTex(){
    try{
        snowmanTex.loadFromFile("res/snowman.png");
        bubbleTex.loadFromFile("res/bubble.png");
        coinTex.loadFromFile("res/coin.png");
        pickleTex.loadFromFile("res/pickle.png");
        batTex.loadFromFile("res/bat.png");
    }
    catch(std::exception e){
        throw("Image Loading Error");
    }
    
    textureFolder["Bubble"]  = bubbleTex;
    textureFolder["Snowman"] = snowmanTex;
    textureFolder["Coin"]    = coinTex;
    textureFolder["Pickle"]  = pickleTex;
    textureFolder["Bat"]     = batTex;
    
}


Texture& Manager::getTex(std::string name){
    
    return textureFolder[name];
    
}


Font& Manager::getFont(){
    return ralewayFont;
}
