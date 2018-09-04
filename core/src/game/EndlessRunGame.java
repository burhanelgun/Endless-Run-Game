package game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import java.io.File;


import game.Sprites.Bird;
import game.States.GameStateManager;
import game.States.MenuState;

public class EndlessRunGame extends ApplicationAdapter {
	public static final int WIDTH=480; //480
	public static final int HEIGHT=800; //800

	public static final String TITLE="flappy bird";
	private GameStateManager gsm;
	private SpriteBatch batch;

	private static Music music;

	@Override
	public void create () {
		switch(Gdx.app.getType()) {
			case Android:
				FileHandler file = new FileHandler("assets/userDatas.properties");
				break;
			case Desktop:
				file = new FileHandler("userDatas.properties");
				break;

		}

		batch = new SpriteBatch();
		music=Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		if(FileHandler.isMusicOn()){
			music.play();
		}
		else{
			music.dispose();
		}
		if(FileHandler.isSoundOn()){
			Bird.isFlapSoundActive=true;
		}
		else {
			Bird.isFlapSoundActive=false;
		}
		gsm=new GameStateManager();
		gsm.push(new MenuState(gsm));
	}

	public static void stopMusic(){
			music.dispose();

	}
	public static void playMusic(){
			music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
			music.setLooping(true);
			music.setVolume(0.1f);
			music.play();

	}

	public static boolean isMusicPlay(){
		return music.isPlaying();
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
		/*batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		music.dispose();
	}
}
