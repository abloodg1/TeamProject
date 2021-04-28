package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.widgets.Dimension;

public class Utils {

    public static Dimension inputToGraphics(Dimension earth) {
        float newX = earth.x;
        float newY;
        newY = Gdx.graphics.getHeight() - earth.y;
        return new Dimension(newX, newY, earth.width, earth.height);
    }

    public static float inputYToGraphicsY(float y) {
        return Gdx.graphics.getHeight() - y;
    }

    public static Vector3 getCameraPosFromMousePos(OrthographicCamera camera, int screenX, int screenY) {
        Vector3 vec = new Vector3();
        vec.set(screenX, screenY, 0);
        return camera.unproject(vec);
    }

    public static void drawImg(SpriteBatch batch, Texture img, Dimension dim) {
        batch.draw(img, dim.x, dim.y, dim.width, dim.height);
    }

    public static void drawCenter(SpriteBatch batch, Texture img, Dimension dim, Vector2 vec) {
        batch.draw(img, vec.x - dim.width / 2, vec.y - dim.height / 2, dim.width, dim.height);
    }


}
