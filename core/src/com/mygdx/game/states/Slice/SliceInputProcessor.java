package com.mygdx.game.states.Slice;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.utils.Handler;
import com.mygdx.game.utils.Utils;

import java.util.ArrayList;
import java.util.List;

//At least X amount of points for a swipe to be considered.
//if (Number of overlapping points > X), book gets cut.

public class SliceInputProcessor extends ArrayList<Vector2> implements InputProcessor {
    private final Handler handler;
    ArrayList<Vector2> points;
    Vector2 getLast;
    ShapeRenderer swipeRender;
    ArrayList<Vector2> safety;
    ArrayList<Vector2> simplified;
    public static int iter = 2;
    private static final float tolerance = 35f;

    public boolean isTouched() {
        return isTouched;
    }

    public boolean isTouched = false;

    public SliceInputProcessor() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        handler = Handler.getInstance();
        this.points = new ArrayList<>();
        this.safety = new ArrayList<>();
        this.simplified = new ArrayList<>();
    }

    public static float distSquared(Vector2 a, Vector2 b) {
        float distX = a.x - b.x;
        float distY = a.y - b.y;
        return distX * distX + distY * distY;
    }

    public void execute() {
        linemaker(points, simplified, tolerance);
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
        Vector2 newPoint = new Vector2(translatedX, translatedY);
        this.points.add(newPoint); //first point
        getLast = newPoint;
        execute();
        isTouched = true;
        String test1 = "Successfully added point: ";
        String test2 = newPoint.toString();
        Gdx.app.debug("Touchdown", test1 + test2);
        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        isTouched = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3 getPos = Utils.getCameraPosFromMousePos(handler.camera, screenX, screenY);
        Vector2 newPoint = new Vector2(getPos.x, getPos.y);
        float distanceX = (getPos.x - getLast.x);
        float distanceY = (getPos.y - getLast.y);
        double distance = Math.sqrt((distanceX * distanceX) + (distanceY * distanceY)); //calculate distance between two points
        if(distance > 5){ //only register new point if swipe points are far enough apart
            points.add(0, newPoint); //automatically inserts at beginning and shifts
            getLast = newPoint; //store last point for next input comparison
            execute();
            return true;
        }
        isTouched = true;
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

    public static void straightened(ArrayList<Vector2> points, float tolerance, ArrayList<Vector2> output) {
        int length = points.size();

        Vector2 point = new Vector2();
        Vector2 prevPoint = points.get(0);

        output.clear();
        output.add(prevPoint);

        for (int i = 1; i < length; i++) {
            point = points.get(i);
            if (distSquared(point, prevPoint) > tolerance) {
                output.add(point);
                prevPoint = point;
            }
        }
        if (!prevPoint.equals(point)) {
            output.add(point);
        }
    }

    public static void smoothing(ArrayList<Vector2> input, ArrayList<Vector2> output) {
        output.clear();
        output.ensureCapacity(input.size() * 2);
        output.add(input.get(0));
        for (int i = 0; i < input.size() - 1; i++) {
            Vector2 pointA = input.get(i);
            Vector2 pointB = input.get(i + 1);
            //Implementation of Chaikin Smoothing Algorithm
            Vector2 Quinn = new Vector2(0.75f * pointA.x + 0.25f * pointB.x, 0.75f * pointA.y + 0.25f * pointB.y);
            Vector2 Russell = new Vector2(0.25f * pointA.x + 0.75f * pointB.x, 0.25f * pointA.y + 0.75f * pointB.y);
            output.add(Quinn);
            output.add(Russell);
        }
        output.add(input.get(input.size() - 1));
    }

    public void linemaker(ArrayList<Vector2> input, ArrayList<Vector2> output, float tolerance) {
        output.clear();
        if (input.size() <= 2) {
            output.addAll(input);
            return;
        }

        if (tolerance > 0 && input.size() > 3) {
            straightened(input, tolerance * tolerance, safety);
            input = safety;
        }

        if (iter <= 0) {
            output.addAll(input);
        } else if (iter == 1) {
            smoothing(input, output);
        } else {
            int temp = iter;
            do {
                smoothing(input, output);
                safety.clear();
                safety.addAll(output);
                ArrayList<Vector2> retire = output;
                input = safety;
                output = retire;
            } while (--temp > 0);
        }
    }
}
