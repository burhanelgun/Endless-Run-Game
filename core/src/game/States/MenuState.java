package game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import game.EndlessRunGame;

public class MenuState extends State {
    private Stage stage;
    private Texture backgroud;
    private TextButton buttonStart,buttonExit,buttonOptions,buttonHighScores ;
    private TextureAtlas atlas;
    private BitmapFont white,black;
    private Skin skin;
    private Table table;
    public MenuState(final GameStateManager gsm) {
        super(gsm);
        white = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
        atlas = new TextureAtlas("button.pack");
        skin= new Skin(atlas);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        table = new Table(skin);
        table.setBounds(0,Gdx.graphics.getHeight()/1.9f,Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/1.1f);
        table.setDebug(true);


        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("normalbuton");
        textButtonStyle.down = skin.getDrawable("pressbuton");
        textButtonStyle.pressedOffsetX=1;
        textButtonStyle.pressedOffsetY=-1;
        textButtonStyle.font = white;
        textButtonStyle.fontColor= Color.BLACK;

        buttonStart = new TextButton("START",textButtonStyle);
        buttonExit = new TextButton("EXIT",textButtonStyle);
        buttonOptions = new TextButton("OPTIONS",textButtonStyle);
        buttonHighScores = new TextButton("HIGH SCORES",textButtonStyle);


        buttonStart.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.set(new PlayState(gsm));
                gsm.set(new PlayState(gsm));

            }
        });
        buttonStart.pad(20);

        buttonHighScores.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {


            }
        });
        buttonHighScores.pad(20);


        buttonExit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        buttonExit.pad(20);

        buttonOptions .addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.set(new OptionsState(gsm));
            }
        });
        buttonExit.pad(20);

        buttonStart.getLabel().setFontScale(Gdx.graphics.getWidth()/380, Gdx.graphics.getHeight()/800);
        buttonExit.getLabel().setFontScale(Gdx.graphics.getWidth()/380, Gdx.graphics.getHeight()/800);

        table.add(buttonStart).size(Gdx.graphics.getWidth()/1.1f,Gdx.graphics.getHeight()/10);
        table.row();
        table.add(buttonHighScores).height(10);
        table.row();
        table.add(buttonHighScores).size(Gdx.graphics.getWidth()/1.1f,Gdx.graphics.getHeight()/10);
        table.row();
        table.add(buttonOptions).height(10);
        table.row();
        table.add(buttonOptions).size(Gdx.graphics.getWidth()/1.1f,Gdx.graphics.getHeight()/10);
        table.row();
        table.add(buttonExit).height(10);
        table.row();
        table.add(buttonExit).size(Gdx.graphics.getWidth()/1.1f,Gdx.graphics.getHeight()/10);
        //table.debug();
        stage.addActor(table);


        cam.setToOrtho(false, EndlessRunGame.WIDTH/2,EndlessRunGame.HEIGHT/2);
        backgroud = new Texture("bg.png");
    }

    @Override
    public void handleInput() {
      /*  if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }*/
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        sb.setProjectionMatrix(cam.combined);

    }

    @Override
    public void dispose() {
        stage.dispose();
        backgroud.dispose();
        atlas.dispose();
        skin.dispose();

        System.out.println("Menu State Disposed");

    }
}
//0534 593 03 84

//0531 670 32 47