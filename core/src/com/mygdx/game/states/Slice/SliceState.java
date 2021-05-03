package com.mygdx.game.states.Slice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.states.GameState;
import com.mygdx.game.throwable;
import com.mygdx.game.utils.Handler;
import com.mygdx.game.utils.Utils;
import com.mygdx.game.widgets.Dimension;

import java.util.ArrayList;

public class SliceState extends GameState {
    private final SliceInputProcessor inputProcessor;
    private final Handler handler;
    private final ArrayList<Texture> books;
    private final ArrayList<throwable> thrown;
    Dimension sliceDim;
    int timer = 0;
    int index = 0;
    Texture background;
    Texture sliceDot;
    int score = 0;

    public SliceState() {
        this.handler = Handler.getInstance();
        inputProcessor = new SliceInputProcessor();

        thrown = new ArrayList<>();
        background = new Texture("collegeback.jpg");
        books = new ArrayList<>();
        sliceDot = new Texture("SliceDot.png");
        sliceDim = new Dimension(0, 0, 75, 200);
        books.add(new Texture("ScienceTextbook.png"));
        books.add(new Texture("MathTextbook.png"));
        books.add(new Texture("EnglishTextbook.png"));
        //int index = (int) (Math.random() * books.size());
    }


    public void render(SpriteBatch batch) {
        batch.draw(background, 0, 0, handler.screenWidth, handler.screenHeight);
        timer++;
        if (timer % 30 == 0) {
            thrown.add(new throwable(books.get(index)));
            if (index + 1 >= books.size()) {
                index = 0;
            } else {
                index++;
            }
        }
        for (int i = 0; i < thrown.size(); i++) {
            throwable book = thrown.get(i);
            book.draw(batch);
            book.update();
            if (book.getY() < 0 - book.getImgHeight()) {
                thrown.remove(book);
                i--;
                score++;
            }
        }
        if (inputProcessor.isTouched()) {
            for (int i = 0; i < 10 && i < inputProcessor.points.size(); i++)
                Utils.drawCenter(batch, sliceDot, sliceDim, inputProcessor.points.get(i));
        } else {
            inputProcessor.points.clear();
        }

    }

    public void setActiveInputProcessor() {
        Gdx.input.setInputProcessor(inputProcessor);
    }


}
