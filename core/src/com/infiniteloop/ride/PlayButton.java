package com.infiniteloop.ride;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by jackthebones on 20/06/15.
 */
public class PlayButton extends Actor {
    private TextureRegion textureRegion;

    public PlayButton() {
        textureRegion = new TextureRegion(Assets.playbutton);
        setWidth(300);
        setHeight(100);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
                getHeight(), getScaleX(), getScaleY(), getRotation());
    }

}
