package com.mygdx.game.states.Slice;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameState;
import com.mygdx.game.throwable;
import com.mygdx.game.utils.Handler;

import java.util.ArrayList;

public class SliceState extends GameState {
    private final SliceInputProcessor inputProcessor;
    private final Handler handler;
    private final ArrayList<throwable> gun;
    private final ArrayList<Texture> books;
    Texture background;

    public SliceState() {
        this.handler = Handler.getInstance();
        inputProcessor = new SliceInputProcessor();
        gun = new ArrayList<>();
        background = new Texture("collgeback.jpg");
        books = new ArrayList<>();
        books.add(new Texture("ScienceTextbook.png"));
        books.add(new Texture("MathTextbook.png"));
        books.add(new Texture("EnglishTextbook.png"));
        int index = (int) (Math.random() * books.size());
        gun.add(new throwable(books.get(index)));
    }


    public void render(SpriteBatch batch) {
        for (throwable bullet : gun) {
            bullet.draw(batch);
            bullet.update();
        }

    }


}
