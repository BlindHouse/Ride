package com.infiniteloop.ride;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by jackthebones on 20/06/15.
 */
public class StaticBackground extends Actor {
    private TextureRegion textureRegion;

    public StaticBackground(int x) {
        if(x  == 1){
            textureRegion = new TextureRegion(Assets.menubackround);
            setWidth(300);
            setHeight(960 / 2);
        }
        if(x == 3){
            textureRegion = new TextureRegion(Assets.winbackground);
            setWidth(300);
            setHeight(960 / 2);
        }
        else{
            textureRegion = new TextureRegion(Assets.gameoverbackground);
            setWidth(300);
            setHeight(960 / 2);
        }


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
                getHeight(), getScaleX(), getScaleY(), getRotation());
    }

}
