package com.mygdx.game.states.End;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameState;
import com.mygdx.game.utils.Handler;
import com.mygdx.game.utils.Utils;
import com.mygdx.game.widgets.Button;
import com.mygdx.game.widgets.Dimension;

public class EndState extends GameState {
    private final Texture backgroundImage;
    private final Dimension backgroundSize;
    private final EndInputProcessor inputProcessor;
    private final Handler handler;

    public EndState() {
        handler = Handler.getInstance();
        backgroundImage = new Texture("TextbookSlasherEndScreen.png");
        backgroundSize = new Dimension(0, 0, 800, 1600);
        inputProcessor = new EndInputProcessor();
    }

    public void setActiveInputProcessor() {
        Gdx.input.setInputProcessor(inputProcessor);
    }

    public void render(SpriteBatch batch) {
        Utils.drawImg(batch, backgroundImage, backgroundSize);
    }

    public void tick() {

    }


}

