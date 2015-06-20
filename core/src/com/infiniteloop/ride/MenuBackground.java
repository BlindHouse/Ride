package com.infiniteloop.ride;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by jackthebones on 20/06/15.
 */
public class MenuBackground extends Actor {
    private TextureRegion textureRegion;

    public MenuBackground() {
        textureRegion = new TextureRegion(Assets.menubackround);
        setWidth(320);
        setHeight(960 / 2);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
                getHeight(), getScaleX(), getScaleY(), getRotation());
    }

}
