package com.mygdx.game.states.Slice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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
    public final ArrayList<throwable> thrown;
    public final ArrayList<Texture> halves;
    public final ArrayList<throwable> halfMove;
    Dimension sliceDim;
    int timer = 0;
    int index = 0;
    Texture background;
    Texture sliceDot;
    Texture strike, unfilledStrike;
    int strikeCount = 0;
    int score = 0;
    BitmapFont scorebox;
    GlyphLayout scoreLayout;

    public SliceState() {
        this.handler = Handler.getInstance();
        inputProcessor = new SliceInputProcessor();

        thrown = new ArrayList<>();
        background = new Texture("collegeback.jpg");
        strike = new Texture("F.png");
        unfilledStrike = new Texture("darkF.png");
        books = new ArrayList<>();
        halves = new ArrayList<>();
        halfMove = new ArrayList<>();
        sliceDot = new Texture("SliceDot.png");
        sliceDim = new Dimension(0, 0, 75, 200);
        books.add(new Texture("ScienceTextbook.png"));
        books.add(new Texture("MathTextbook.png"));
        books.add(new Texture("EnglishTextbook.png"));
        halves.add(new Texture("ScienceTextbookHalf1.png"));
        halves.add(new Texture("ScienceTextbookHalf2.png"));
        halves.add(new Texture("MathTextbookHalf1.png"));
        halves.add(new Texture("MathTextbookHalf2.png"));
        halves.add(new Texture("EnglishTextbookHalf1.png"));
        halves.add(new Texture("EnglishTextbookHalf2.png"));


        //int index = (int) (Math.random() * books.size());
        scorebox = new BitmapFont();
        scorebox.setColor(Color.RED);
        scorebox.getData().setScale(1, 3);
    }

    public void render(SpriteBatch batch) {
        batch.draw(background, 0, 0, handler.screenWidth, handler.screenHeight);
        scoreLayout = new GlyphLayout(scorebox, "Books Destroyed: " + score);
        scorebox.draw(batch, scoreLayout, 25, 1500);
        int strikeX = 725;
        int strikeY = 1450;
        for(int i = 0; i < strikeCount; i++){
            batch.draw(strike, strikeX, strikeY, 50, 125);
            strikeX -= 60;
        }
        for (int i = strikeCount; i < 3; i++)
        {
            batch.draw(unfilledStrike, strikeX, strikeY, 50, 125);
            strikeX -= 60;
        }
        timer++;
        if (timer % 60 == 0) {
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
                if (strikeCount < 2) {
                    strikeCount++;
                } else {
                    handler.setActiveState(handler.endState);
                }
            }

        }
        for(int i =0; i< halfMove.size();i++){
            throwable half = halfMove.get(i);
            half.draw(batch);
            half.update();
            if (half.getY() < 0 - half.getImgHeight()) {
                halfMove.remove(half);
                i--;
            }
        }
        if (inputProcessor.isTouched()) {
            int halfInd = 0;
            float xV;
            for (int i = 0; i < 10 && i < inputProcessor.points.size(); i++) {
                Utils.drawCenter(batch, sliceDot, sliceDim, inputProcessor.points.get(i));
                if(i < 3 && inputProcessor.isDragged())  {
                    for (int j = 0; j < thrown.size(); j++) {
                        if (inputProcessor.points.get(i).x > thrown.get(j).getX()
                                && inputProcessor.points.get(i).x < thrown.get(j).getX() + (thrown.get(j).getImgWidth())
                                && inputProcessor.points.get(i).y > thrown.get(j).getY()
                                && inputProcessor.points.get(i).y < thrown.get(j).getY() + (thrown.get(j).getImgHeight())) {


                            score++;

                            if(thrown.get(j).getImg() == books.get(0)) halfInd = 0;
                            if(thrown.get(j).getImg() == books.get(1)) halfInd = 2;
                            if(thrown.get(j).getImg() == books.get(2)) halfInd = 4;
                            halfMove.add(new throwable(halves.get(halfInd)));
                            halfMove.add(new throwable(halves.get(halfInd + 1)));
                            xV = Math.abs(thrown.get(j).getxV());
                            halfMove.get(halfMove.size()-2).setAll(thrown.get(j).getX(), thrown.get(j).getY(), -xV, thrown.get(j).getyV());
                            halfMove.get(halfMove.size()-1).setAll(thrown.get(j).getX(), thrown.get(j).getY(), xV, thrown.get(j).getyV());

                            thrown.remove(j);
                            j--;
                            inputProcessor.points.clear();
                            break;
                        }

                    }
                }
            }
        } else {
            inputProcessor.points.clear();
        }



    }

    public void setActiveInputProcessor() {
        Gdx.input.setInputProcessor(inputProcessor);
    }


}
