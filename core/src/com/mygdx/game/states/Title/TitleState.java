package com.mygdx.game.states.Title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.game.states.GameState;
import com.mygdx.game.states.Title.TitleInputProcessor;
import com.mygdx.game.utils.Handler;
import com.mygdx.game.utils.Utils;
import com.mygdx.game.widgets.Button;
import com.mygdx.game.widgets.Dimension;

public class TitleState extends GameState {
    private final Texture backgroundImage;
    private final Texture startImage;
    private final Texture optionsImage;
    private final Dimension backgroundSize;
    private final Dimension startSize;
    private final Dimension optionsSize;
    private final Button startButton;
    private final Music backgroundMusic;
    private Button optionsButton;
    private final com.mygdx.game.states.Title.TitleInputProcessor inputProcessor;
    private final Handler handler;

    public TitleState() {
        handler = Handler.getInstance();
        backgroundImage = new Texture("TextbookSlasherTitle.png");
        startImage = new Texture("startPostIt.png");
        optionsImage = new Texture("optionsPostIt.png");
        backgroundSize = new Dimension(0, 0, 800, 1600);
        startSize = new Dimension(490, 1600 - 1464, 200, 550);
        optionsSize = new Dimension(574, 1600 - 744, 200, 550);
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("funky.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        startButton = new Button(startSize, startImage) {
            @Override
            public void onClick(float x, float y) {
                backgroundMusic.stop();
                handler.setActiveState(handler.sliceState);
            }

        };
        inputProcessor = new TitleInputProcessor();
        inputProcessor.addButton(startButton);

    }

    public void setActiveInputProcessor() {
        Gdx.input.setInputProcessor(inputProcessor);
    }

    public void render(SpriteBatch batch) {
        Utils.drawImg(batch, backgroundImage, backgroundSize);
        startButton.render(batch);
    }

    public void tick() {

    }


}
