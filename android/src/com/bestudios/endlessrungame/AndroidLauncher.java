package com.bestudios.endlessrungame;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;


import game.EndlessRunGame;

@SuppressWarnings("ALL")
public class AndroidLauncher extends AndroidApplication {

	private static final String TAG = "AndroiLauncher";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		//Gdx.app.log("hello","111111111111");
		//Gdx.app.log("hello","2222222222222222222222222222");



		//Gdx.app.log("hello","33333333333333333333333");


		initialize(new EndlessRunGame(), config);

		//Gdx.app.log("hello","4444444444444444444444444444");


		//Gdx.app.log("hello","5555555555555555555555");


	}


}
