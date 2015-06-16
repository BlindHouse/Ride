package com.infiniteloop.ride.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.infiniteloop.ride.RideGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = RideGame.WIDHT;
		config.height = RideGame.HEIGHT;

		new LwjglApplication(new RideGame(), config);
	}
}
