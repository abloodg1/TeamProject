package com.mygdx.game.states.Slice;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameState;
import com.mygdx.game.throwable;
import com.mygdx.game.utils.Handler;
import com.mygdx.game.utils.Utils;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SliceState extends GameState {
    private final SliceInputProcessor inputProcessor;
    private final Handler handler;
    private final ArrayList<throwable> gun;
    private final ArrayList<Texture> books;
    Texture background;
    int timer = 0;
    int index = 0;

    public SliceState() {
        this.handler = Handler.getInstance();
        inputProcessor = new SliceInputProcessor();
        gun = new ArrayList<>();
        background = new Texture("collegeback.jpg");
        books = new ArrayList<>();
        books.add(new Texture("ScienceTextbook.png"));
        books.add(new Texture("MathTextbook.png"));
        books.add(new Texture("EnglishTextbook.png"));
        //int index = (int) (Math.random() * books.size());
        gun.add(new throwable(books.get(0)));
        gun.add(new throwable(books.get(1)));
        gun.add(new throwable(books.get(2)));
    }


    public void render(SpriteBatch batch) {
        //batch.draw(img, dim.x, dim.y, dim.width, dim.height);
        timer++;
        if (timer%60 == 0) {
            gun.get(index).draw(batch);
            gun.get(index).update();
            index++;
            //reset index here
        }

    }


}
