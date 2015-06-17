package com.infiniteloop.ride;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by jackthebones on 12/06/15.
 */
public class Assets {

    //Disposables
    public static TextureAtlas atlas;
    public static SpriteBatch batch;

    //Non Disposables
    public static TextureRegion ship;
    public static TextureRegion spacebackground;

    public static void load(){
        atlas = new TextureAtlas("pack.atlas");
        batch = new SpriteBatch();

        ship = atlas.findRegion("ship");
        spacebackground = atlas.findRegion("spacebackground");

    }

    public static void dispose(){
        if(atlas != null){
            atlas.dispose();
        }
        if(batch != null){
            batch.dispose();
        }
    }
}
