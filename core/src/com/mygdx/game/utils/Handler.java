package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.Slice.SliceState;
import com.mygdx.game.states.Title.TitleState;
//import future states

public class Handler {

    private static Handler handler = null;
    public GameState activeState;
    public SliceState sliceState;
    public TitleState titleState;
    public OrthographicCamera camera;
    public float screenWidth;
    public float screenHeight;

    public Handler() {

    }

    public static Handler getInstance() {
        if (Handler.handler == null) {
            Handler.handler = new Handler();
        }
        return Handler.handler;
    }

    public void setActiveState(GameState state) {
        this.activeState = state;
        this.activeState.setActiveInputProcessor();
    }
}
