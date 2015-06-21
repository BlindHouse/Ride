package com.infiniteloop.ride;

import Calses3.Gnodo;
import Calses3.GraphMap;
import com.badlogic.gdx.Game;

public class RideGame extends Game {

	public static int WIDHT = 300;
	public static int HEIGHT = 480;

	public static GraphMap Map = new GraphMap();

	@Override
	public void create () {
		Map.InitMapGraph();
		Assets.load();
		setScreen(new MenuState(this));
	}



	@Override
	public void dispose () {
		Assets.dispose();
	}


}
