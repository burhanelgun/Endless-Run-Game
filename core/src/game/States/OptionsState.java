package game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import game.EndlessRunGame;
import game.FileHandler;
import game.PlayServices;
import game.Sprites.Bird;

public class OptionsState extends State {
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture musicOn,musicOn0,soundOff0,soundOn0,musicOff,musicOff0,soundOn1,soundOff1;
    Image musicOn0Img,musicOnImg,soundOn0Img,soundOff0Img,soundOn1Img,soundOff1Img,musicOff0Img,musicOffImg;
    Stage stage = new Stage();
    private BitmapFont white,black;
    private Skin skin;
    private Table table;
    private TextureAtlas atlas;
    private TextButton buttonStart;
    private PlayServices playServices;



    protected OptionsState(final GameStateManager gsm, final PlayServices playServices) {
        super(gsm);

        this.playServices=playServices;
        white = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
        atlas = new TextureAtlas("button.pack");
        skin= new Skin(atlas);

        table = new Table(skin);
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("normalbuton");
        textButtonStyle.down = skin.getDrawable("pressbuton");
        textButtonStyle.pressedOffsetX=1;
        textButtonStyle.pressedOffsetY=-1;
        textButtonStyle.font = white;
        textButtonStyle.fontColor= Color.BLACK;
        buttonStart = new TextButton("BACK",textButtonStyle);

        buttonStart.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.set(new MenuState(gsm,playServices));
            }
        });
        buttonStart.pad(20);

        buttonStart.getLabel().setFontScale(Gdx.graphics.getWidth()/380, Gdx.graphics.getHeight()/800);
        table.add(buttonStart).size(Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/10);
        stage.addActor(table);


        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);

        musicOn =new Texture(Gdx.files.internal("musicon.png"));
        musicOn0=new Texture(Gdx.files.internal("musicon0.png"));
        musicOff=new Texture(Gdx.files.internal("musicoff.png"));
        musicOff0=new Texture(Gdx.files.internal("musicoff0.png"));


        soundOn0=new Texture(Gdx.files.internal("soundon1.png"));
        soundOff0=new Texture(Gdx.files.internal("soundoff0.png"));
        soundOn1=new Texture(Gdx.files.internal("soundon1.png"));
        soundOff1=new Texture(Gdx.files.internal("soundoff1.png"));



        musicOn0Img=new Image(musicOn0);
        musicOnImg=new Image(musicOn);
        musicOff0Img = new Image(musicOff0);
        musicOffImg=new Image(musicOff);


        soundOn0Img=new Image(soundOn0);
        soundOff0Img=new Image(soundOff0);
        soundOn1Img=new Image(soundOn1);
        soundOff1Img=new Image(soundOff1);





        Gdx.input.setInputProcessor(stage);
        if(EndlessRunGame.isMusicPlay()){
            stage.addActor(musicOn0Img);
            stage.addActor(musicOnImg);
        }
        else {
            stage.addActor(musicOff0Img);
            stage.addActor(musicOffImg);
        }

        if(Bird.isFlapSoundActive) {
            stage.addActor(soundOff0Img);
            stage.addActor(soundOn1Img);
        }
        else {
            stage.addActor(soundOff1Img);
            stage.addActor(soundOn0Img);
        }



        if(EndlessRunGame.isMusicPlay()) {

            musicOn0Img.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    musicOnImg.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("musicoff0.png")))));
                    musicOn0Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("musicoff.png")))));

                    EndlessRunGame.stopMusic();
                    FileHandler.setMusic(false);
                }
            });
            musicOnImg.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    musicOnImg.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("musicon.png")))));
                    musicOn0Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("musicon0.png")))));


                   // EndlessRunGame.playMusic();
                   // FileHandler.setMusic(true);



                }
            });
        }
        else{

            musicOff0Img.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    musicOffImg.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("musicon0.png")))));
                    musicOff0Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("musicon.png")))));


                    if(!EndlessRunGame.isMusicPlay()){
                        EndlessRunGame.playMusic();
                        FileHandler.setMusic(true);
                    }


                }
            });
            musicOffImg.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    musicOffImg.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("musicoff.png")))));
                    musicOff0Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("musicoff0.png")))));


                    EndlessRunGame.stopMusic();
                    FileHandler.setMusic(false);

                }
            });

        }
//////////////////////////////////////////////////////////////////////////////////////////////////////

        if(Bird.isFlapSoundActive==true){
            soundOff0Img.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y){
                    soundOff0Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("soundoff1.png")))));
                    soundOn1Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("soundon0.png")))));

                    Bird.isFlapSoundActive=false;
                    FileHandler.setSound(false);

                }
            });
            soundOn1Img.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y){
                    soundOn1Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("soundon1.png")))));
                    soundOff0Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("soundoff0.png")))));

                    Bird.isFlapSoundActive=true;
                    FileHandler.setSound(true);

                }
            });
        }
        else {
            soundOff1Img.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y){
                    soundOff1Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("soundoff1.png")))));
                    soundOn0Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("soundon0.png")))));

                    Bird.isFlapSoundActive=false;
                    FileHandler.setSound(false);

                }
            });
            soundOn0Img.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y){
                    soundOn0Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("soundon1.png")))));
                    soundOff1Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("soundoff0.png")))));
                    Bird.isFlapSoundActive=true;
                    FileHandler.setSound(true);


                }
            });
        }
//////////////////////////////////////////////////////////////////////////////////////////////////////



        //font.setColor(Color.WHITE);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        font.draw(batch, "Music", Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/1.2f);
        font.draw(batch, "Sound", Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/1.2f+Gdx.graphics.getHeight()/10);
        batch.end();

        if(EndlessRunGame.isMusicPlay()){
            musicOnImg.setPosition(Gdx.graphics.getWidth()/10+Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/1.15f);
            musicOn0Img.setPosition(Gdx.graphics.getWidth()/10+Gdx.graphics.getWidth()/2+Gdx.graphics.getWidth()/5.5f, Gdx.graphics.getHeight()/1.15f);
        }
        else {
            musicOff0Img.setPosition(Gdx.graphics.getWidth()/10+Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/1.15f);
            musicOffImg.setPosition(Gdx.graphics.getWidth()/10+Gdx.graphics.getWidth()/2+Gdx.graphics.getWidth()/5.5f, Gdx.graphics.getHeight()/1.15f);
        }
        if(Bird.isFlapSoundActive){
            soundOn1Img.setPosition(Gdx.graphics.getWidth()/10+Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/1.15f-+Gdx.graphics.getHeight()/9);
            soundOff0Img.setPosition(Gdx.graphics.getWidth()/10+Gdx.graphics.getWidth()/2+Gdx.graphics.getWidth()/5.5f, Gdx.graphics.getHeight()/1.15f-+Gdx.graphics.getHeight()/9);
        }
        else {
            soundOn0Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("soundon0.png")))));
            soundOn0Img.setPosition(Gdx.graphics.getWidth()/10+Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/1.15f-+Gdx.graphics.getHeight()/9);
            soundOff1Img.setPosition(Gdx.graphics.getWidth()/10+Gdx.graphics.getWidth()/2+Gdx.graphics.getWidth()/5.5f, Gdx.graphics.getHeight()/1.15f-+Gdx.graphics.getHeight()/9);
        }




        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}

