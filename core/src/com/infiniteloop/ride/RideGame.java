package com.infiniteloop.ride;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RideGame extends Game {

	public static int WIDHT = 300;
	public static int HEIGHT = 480;
	
	@Override
	public void create () {
		Assets.load();
		setScreen(new GraphState(this));
	}

	@Override
	public void dispose () {
		Assets.dispose();
	}


}
