package com.bestudios.endlessrungame;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.example.games.basegameutils.GameHelper;


import game.EndlessRunGame;

@SuppressWarnings("ALL")
public class AndroidLauncher extends AndroidApplication {



	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Gdx.app.log("hello","111111111111");
		//Gdx.app.log("hello","2222222222222222222222222222");



		//Gdx.app.log("hello","33333333333333333333333");


		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new EndlessRunGame(), config);

		//Gdx.app.log("hello","4444444444444444444444444444");


		//Gdx.app.log("hello","5555555555555555555555");


	}


}
