package com.bestudios.endlessrungame.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.bestudios.endlessrungame.EndlessRunGame;

public class PlayScreen implements Screen {

    private EndlessRunGame game;
    Texture texture;

    public PlayScreen(EndlessRunGame game){
        this.game=game;
        texture=new Texture("badlogic.jpg");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(texture,0,0);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
