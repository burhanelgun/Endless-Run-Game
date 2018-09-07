package com.bestudios.endlessrungame;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;


import game.EndlessRunGame;
import game.PlayServices;

@SuppressWarnings("ALL")
public class AndroidLauncher extends AndroidApplication implements PlayServices {

	private GameHelper gameHelper;
	private static final String leaderboard = "CgkIzOmWjuMLEAIQAQ";


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Gdx.app.log("hello","111111111111");
		gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
		gameHelper.enableDebugLog(true);
		//Gdx.app.log("hello","2222222222222222222222222222");


		GameHelper.GameHelperListener gameHelperListener = new GameHelper.GameHelperListener() {
			@Override
			public void onSignInFailed() {
			}

			@Override
			public void onSignInSucceeded() {
			}
		};
		//Gdx.app.log("hello","33333333333333333333333");


		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new EndlessRunGame(this), config);

		//Gdx.app.log("hello","4444444444444444444444444444");


		gameHelper.setup(gameHelperListener);
		//Gdx.app.log("hello","5555555555555555555555");


	}

	@Override
	protected void onStart() {
		super.onStart();
		//Gdx.app.log("hello","6666666666666666666666");

		gameHelper.onStart(this); // You will be logged in to google play services as soon as you open app , i,e on start
		//Gdx.app.log("hello","777777777777777777777777");

	}

	@Override
	public void onStartMethod() {
		super.onStart();
		gameHelper.onStart(this); // This is similar method but I am using this if i wish to login to google play services
		// from any other screen and not from splash screen of my code
	}

	@Override
	protected void onStop() {
		super.onStop();
		gameHelper.onStop();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		gameHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void signIn() {
		try {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {

					gameHelper.beginUserInitiatedSignIn();

				}
			});
		} catch (Exception e) {
			Gdx.app.log("Burhan", "Log in failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public void signOut() {
		try {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {

					gameHelper.signOut();
				}
			});
		} catch (Exception e) {
			Gdx.app.log("Burhan", "Log out failed: " + e.getMessage() + ".");

		}
	}

	@Override
	public void rateGame() {

		String str = "Your PlayStore Link";
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
	}

	@Override
	public void unlockAchievement(String str) {
		Games.Achievements.unlock(gameHelper.getApiClient(), str);
	}

	@Override
	public void submitScore(String LeaderBoard,int highScore) {
		if (isSignedIn()) {

			Games.Leaderboards.submitScore(gameHelper.getApiClient(), LeaderBoard, highScore);
		}
		else{
			Gdx.app.log("Burhan", "Not Signed Yet");
		}

	}



	@Override
	public void submitLevel(int highLevel) {
		if (isSignedIn()) {
			Games.Leaderboards.submitScore(gameHelper.getApiClient(),leaderboard, highLevel);
		}
	}

	@Override
	public void showAchievement() {
		if (isSignedIn()) {
			startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), 1);
		} else {
			signIn();
		}
	}

	@Override
	public void showScore(String leaderboard_Easy) {
		if (isSignedIn()) {
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), leaderboard_Easy), 1);
		} else {
			signIn();
		}
	}



	@Override
	public void showLevel() {
		if (isSignedIn()) {
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), leaderboard), 1);
		} else {
			signIn();
		}
	}

	@Override
	public boolean isSignedIn() {
		return gameHelper.isSignedIn();
	}
}
