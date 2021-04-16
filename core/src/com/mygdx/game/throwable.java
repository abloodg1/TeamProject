package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class throwable {
    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();
    Texture img;
    double grav = 5;
    float x = (float)(Math.random() * screenWidth);
    float y = 0;
    double minHeight;
    double maxHeight = screenHeight - 100;
    float xV;
    float yV;

    public throwable(Texture sprite){
        img = sprite;
        xV = (float)(1 + (Math.random() * 5));
        yV = (float)(6 + (Math.random() * 3));
        if(x > screenWidth/2){
            xV = xV * -1;
        }
    }

    public void draw(SpriteBatch batch){
        batch.draw(img, x, y, (float)img.getWidth(), (float)img.getHeight());
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
