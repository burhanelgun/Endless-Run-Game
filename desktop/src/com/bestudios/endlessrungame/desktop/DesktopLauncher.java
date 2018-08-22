package com.bestudios.endlessrungame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import game.EndlessRunGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=EndlessRunGame.WIDTH;
		config.height=EndlessRunGame.HEIGHT;
		new LwjglApplication(new EndlessRunGame(), config);
	}
}
