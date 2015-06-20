package com.infiniteloop.ride;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by jackthebones on 20/06/15.
 */
public class Graph extends Actor{

    public static final int GraphWidth = RideGame.WIDHT;
    public static final int GraphHeight = RideGame.HEIGHT;

    private TextureRegion textureRegion;

    public Graph(){
        textureRegion = new TextureRegion(Assets.graph);

        setWidth(GraphWidth);
        setHeight(GraphHeight);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
                getHeight(), getScaleX(), getScaleY(), getRotation());
    }

}

