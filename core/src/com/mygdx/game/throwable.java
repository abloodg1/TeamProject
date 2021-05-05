package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.utils.Handler;

import javax.xml.soap.Text;

public class throwable {
    Handler handler = Handler.getInstance();
    float screenWidth = handler.screenWidth;
    float screenHeight = handler.screenHeight;
    Texture img;
    float grav = 2;

    public float getImgHeight() {
        return imgHeight;
    }

    public float getImgWidth() {
        return imgWidth;
    }

    public Texture getImg(){
        return img;
    }
    float imgHeight = 750;
    float imgWidth = 300;
    float x = (float) (Math.random() * (screenWidth - imgWidth));
    float y = 0 - imgHeight;
    double minHeight;
    double maxHeight = screenHeight - imgHeight;
    float xV;
    float yV;


    public throwable(Texture sprite) {
        img = sprite;
        xV = (float) (1 + (Math.random() * 5));
        yV = (float) (50 + (Math.random() * 3));
        if (x > (screenWidth - imgWidth) / 2) {
            xV = xV * -1;
        }
    }

    public throwable(throwable paperback) {
        img = paperback.img;
        xV = paperback.xV;
        yV = paperback.yV;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(img, x, y, imgWidth, imgHeight);
    }

    public double getX() {
        return x;
    }

    public double getY() {
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
        y = y + yV + 25;
        yV = yV - grav;
    }
}
