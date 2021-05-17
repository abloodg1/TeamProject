package com.mygdx.game.states.End;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameState;
import com.mygdx.game.utils.Handler;
import com.mygdx.game.utils.Utils;
import com.mygdx.game.widgets.Button;
import com.mygdx.game.widgets.Dimension;

public class EndState extends GameState {
    private final Texture backgroundImage;
    private final Texture quitButtonImage;
    private final Texture retryButtonImage;
    private final Dimension backgroundSize;
    private final Dimension quitButtonSize;
    private final Dimension retryButtonSize;
    private final Button quitButton;
    private final Button retryButton;
    private final EndInputProcessor inputProcessor;
    private final Handler handler;
    private int scoreVal;
    private final BitmapFont score;
    private final BitmapFont highScore;
    private GlyphLayout highbox;
    private GlyphLayout scorebox;
    private final FileHandle font = Gdx.files.internal("inkfree.TTF");
    private final Preferences preferences;

    public EndState(Preferences pref) {
        handler = Handler.getInstance();
        this.preferences = pref;
        backgroundImage = new Texture("TextbookSlasherEndScreen.png");
        quitButtonImage = new Texture("quitbutton.png");
        retryButtonImage = new Texture("retrybutton.png");
        backgroundSize = new Dimension(0, 0, 800, 1600);
        retryButtonSize = new Dimension(560, 1500 - 1100, 160, 160);
        quitButtonSize = new Dimension(560, 1500 - 1280, 160, 160);
        quitButton = new Button(quitButtonSize, quitButtonImage) {
            @Override
            public void onClick(float x, float y) {
                Gdx.app.exit();
            }
        };
        retryButton = new Button(retryButtonSize, retryButtonImage) {

            @Override
            public void onClick(float x, float y) {
                handler.sliceState.resetGame();
                handler.setActiveState(handler.sliceState);
            }
        };

        inputProcessor = new EndInputProcessor();
        inputProcessor.addButton(retryButton);
        inputProcessor.addButton(quitButton);
        score = new BitmapFont();
        score.setColor(Color.RED);
        score.getData().setScale(8, 15);
        highScore = new BitmapFont();
        highScore.setColor(Color.RED);
        highScore.getData().setScale(4, 7);
    }

    public void setActiveInputProcessor() {
        Gdx.input.setInputProcessor(inputProcessor);
    }

    public void setScore(int scoreNum) {
        scoreVal = scoreNum;
    }

    public void render(SpriteBatch batch) {
        Utils.drawImg(batch, backgroundImage, backgroundSize);
        scorebox = new GlyphLayout(score, Integer.toString(scoreVal));
        score.draw(batch, scorebox, 550, 1600 - 500);
        highbox = new GlyphLayout(highScore, Integer.toString(preferences.getInteger("High Score", 0)));
        highScore.draw(batch, highbox, 360, 1600 - 250);
        quitButton.render(batch);
        retryButton.render(batch);
        saveHighScore(scoreVal);
    }

    public void tick() {

    }

    public void saveHighScore(int highScore) {
        if (preferences.getInteger("High Score", 0) < highScore) {
            preferences.putInteger("High Score", highScore);
        }
        preferences.flush();
    }


}

