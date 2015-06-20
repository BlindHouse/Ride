package com.infiniteloop.ride;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;

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
    public static TextureRegion shot;
    public static TextureRegion alienshot;
    public static TextureRegion alien;
    public static TextureRegion kamikaze;
    public static TextureRegion bridge;
    public static TextureRegion coin;
    public static TextureRegion gas;
    public static TextureRegion health;
    public static TextureRegion menubackround;
    public static TextureRegion playbutton;
    public static TextureRegion name;
    public static TextureRegion gameoverbackground;
    public static TextureRegion gobackbutton;
    public static TextureRegion graph;

    public static void load(){
        atlas = new TextureAtlas("pack.atlas");
        batch = new SpriteBatch();

        ship = atlas.findRegion("ship");
        spacebackground = atlas.findRegion("spacebackground");
        shot = atlas.findRegion("shot");
        alienshot = atlas.findRegion("alienshot");
        alien = atlas.findRegion("bigalien");
        kamikaze = atlas.findRegion("tinyalien");
        bridge = atlas.findRegion("bridge");
        coin = atlas.findRegion("coin");
        health = atlas.findRegion("health");
        gas = atlas.findRegion("gas");
        menubackround = atlas.findRegion("menubackground");
        playbutton = atlas.findRegion("playbutton");
        name = atlas.findRegion("name");
        gameoverbackground = atlas.findRegion("gameoverbackground");
        gobackbutton = atlas.findRegion("goback");
        graph = atlas.findRegion("graph");


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
