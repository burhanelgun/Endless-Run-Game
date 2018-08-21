package com.bestudios.endlessrungame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bestudios.endlessrungame.EndlessRunGame;
import com.bestudios.endlessrungame.Scenes.Hud;

public class PlayScreen implements Screen {

    private EndlessRunGame game;
    private Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    public PlayScreen(EndlessRunGame game){
        this.game=game;
        gamecam=new OrthographicCamera();

        //gamePort=new StretchViewport(800,400,gamecam);
        //gamePort=new ScreenViewport(gamecam);
        gamePort=new FitViewport(EndlessRunGame.V_WIDTH,EndlessRunGame.V_HEIGHT,gamecam);
        hud=new Hud(game.batch);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
          gamePort.update(width,height);
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
