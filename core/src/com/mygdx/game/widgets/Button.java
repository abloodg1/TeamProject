package com.mygdx.game.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Button implements Clickable {

    private final Dimension dimButton;
    public Boolean isPushed = false;

    public Texture getImgButton() {
        return imgButton;
    }

    private Texture imgButton;

    public Button(Dimension dimVal, Texture imgVal) {
        this.dimButton = dimVal;
        this.imgButton = imgVal;
    }

    public void render(SpriteBatch batch) {
        batch.draw(imgButton, dimButton.x, dimButton.y, dimButton.width, dimButton.height);

    }

    public void heldClick() {
    }

    public boolean wasClicked(float clickX, float clickY) {
        return clickX <= dimButton.x + dimButton.width && clickX >= dimButton.x
                && clickY <= dimButton.y + dimButton.height && clickY >= dimButton.y;
    }

    public void setImage(Texture img) {
        this.imgButton = img;
    }
}
