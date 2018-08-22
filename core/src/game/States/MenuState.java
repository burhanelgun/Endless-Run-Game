package game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.EndlessRunGame;

public class MenuState extends State {
    private Texture backgroud;
    private Texture playBtn;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, EndlessRunGame.WIDTH/2,EndlessRunGame.HEIGHT/2);
        backgroud = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(backgroud,0,0);
        sb.draw(playBtn,cam.position.x-playBtn.getWidth()/2,cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        backgroud.dispose();
        playBtn.dispose();
        System.out.println("Menu State Disposed");

    }
}
//0534 593 03 84

//0531 670 32 47