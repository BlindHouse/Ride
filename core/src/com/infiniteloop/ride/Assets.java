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
    public static TextureRegion bird;
    public static TextureRegion background;
    public static TextureRegion ground;
    public static TextureRegion pipe;

    public static void load(){
        atlas = new TextureAtlas("pack.atlas");
        batch = new SpriteBatch();

        bird = atlas.findRegion("bird");
        background = atlas.findRegion("background");
        ground = atlas.findRegion("ground");
        pipe = atlas.findRegion("pipe");

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
