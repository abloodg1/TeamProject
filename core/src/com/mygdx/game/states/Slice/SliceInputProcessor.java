package com.mygdx.game.states.Slice;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.utils.Handler;
import com.mygdx.game.utils.Utils;

import java.util.ArrayList;

//At least X amount of points for a swipe to be considered.
//if (Number of overlapping points > X), book gets cut.

public class SliceInputProcessor implements InputProcessor {
    private final Handler handler;
    ArrayList<Vector2> points;
    Vector2 getLast;
    ShapeRenderer swipeRender;

    public SliceInputProcessor() {
        handler = Handler.getInstance();

    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 mathVector = Utils.getCameraPosFromMousePos(handler.camera, screenX, screenY);
        float translatedX = mathVector.x;
        float translatedY = mathVector.y;
        this.points.add(new Vector2(translatedX, translatedY)); //first point

        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3 getPos = Utils.getCameraPosFromMousePos(handler.camera, screenX, screenY);
        Vector2 newPoint = new Vector2(getPos.x, getPos.y);
        float distanceX = (getPos.x - getLast.x);
        float distanceY = (getPos.y - getLast.y);
        double distance = Math.sqrt((distanceX * distanceX ) - (distanceY - distanceY)); //calculate distance between two points
        if(distance > 5){ //only register new point if swipe points are far enough apart
            points.add(0, newPoint); //automatically inserts at beginning and shifts
            getLast = newPoint; //store last point for next input comparison
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
