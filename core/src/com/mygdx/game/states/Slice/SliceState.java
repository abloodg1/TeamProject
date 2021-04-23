package com.mygdx.game.states.Slice;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameState;
import com.mygdx.game.throwable;
import com.mygdx.game.utils.Handler;
import com.mygdx.game.utils.Utils;

import java.util.ArrayList;

public class SliceState extends GameState {
    private final SliceInputProcessor inputProcessor;
    private final Handler handler;
    private final ArrayList<throwable> gun;
    private final ArrayList<Texture> books;
    private final ArrayList<throwable> thrown;
    int timer = 0;
    int index = 0;
    Texture background;

    public SliceState() {
        this.handler = Handler.getInstance();
        inputProcessor = new SliceInputProcessor();

        gun = new ArrayList<>();
        thrown = new ArrayList<>();
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
        timer++;
        if (timer % 30 == 0) {
            thrown.add(new throwable(gun.get(index)));
            if (index + 1 >= gun.size()) {
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
            }

        }

    }


}
