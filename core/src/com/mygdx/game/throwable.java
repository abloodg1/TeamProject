package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class throwable {
    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();
    Texture img;
    double grav = 5;
    double x = Math.random() * screenWidth;
    double y = 0;
    double minHeight;
    double maxHeight = screenHeight - 100;
    double xV;
    double yV;

    public throwable(Texture sprite){
        img = sprite;
        xV = 1 + (Math.random() * 5);
        yV = 6 + (Math.random() * 3);
        if(x > screenWidth/2){
            xV = xV * -1;
        }
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getGrav(){
        return grav;
    }

    public double getxV(){
        return xV;
    }

    public double getxY(){
        return yV;
    }

    public void update(){
        x = x + xV;
        y = y + yV;
        yV = yV - grav;
    }
}
