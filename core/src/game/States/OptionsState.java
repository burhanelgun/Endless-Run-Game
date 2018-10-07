package game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import game.EndlessRunGame;
import game.FileHandler;
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
    Preferences musicAndSoundPreferencess = Gdx.app.getPreferences("musicAndSoundPreferencess");




    protected OptionsState(final GameStateManager gsm) {
        super(gsm);
        if(!musicAndSoundPreferencess.contains("musicOn")){
            musicAndSoundPreferencess.putBoolean("musicOn", true);
            musicAndSoundPreferencess.putBoolean("soundOn", true);
        }

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
                gsm.set(new MenuState(gsm));
            }
        });
        buttonStart.pad(20);

        buttonStart.getLabel().setFontScale(Gdx.graphics.getWidth()/380, Gdx.graphics.getHeight()/800);
        table.add(buttonStart).size(Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/10);
        stage.addActor(table);


        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);

        musicOn =new Texture(Gdx.files.internal("musicon.jpg"));
        musicOn0=new Texture(Gdx.files.internal("musicon0.jpg"));
        musicOff=new Texture(Gdx.files.internal("musicoff.jpg"));
        musicOff0=new Texture(Gdx.files.internal("musicoff0.jpg"));


        soundOn0=new Texture(Gdx.files.internal("soundon1.jpg"));
        soundOff0=new Texture(Gdx.files.internal("soundoff0.jpg"));
        soundOn1=new Texture(Gdx.files.internal("soundon1.jpg"));
        soundOff1=new Texture(Gdx.files.internal("soundoff1.jpg"));



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
                    musicOnImg.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("musicoff0.jpg")))));
                    musicOn0Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("musicoff.jpg")))));

                    EndlessRunGame.stopMusic();
                    //**FileHandler.setMusic(false);
                    musicAndSoundPreferencess.putBoolean("musicOn", false);
                    musicAndSoundPreferencess.flush();

                }
            });
            musicOnImg.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    musicOnImg.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("musicon.jpg")))));
                    musicOn0Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("musicon0.jpg")))));


                   // EndlessRunGame.playMusic();
                   // FileHandler.setMusic(true);



                }
            });
        }
        else{

            musicOff0Img.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    musicOffImg.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("musicon0.jpg")))));
                    musicOff0Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("musicon.jpg")))));


                    if(!EndlessRunGame.isMusicPlay()){
                        EndlessRunGame.playMusic();
                        //**FileHandler.setMusic(true);
                        musicAndSoundPreferencess.putBoolean("musicOn", true);
                        musicAndSoundPreferencess.flush();


                    }


                }
            });
            musicOffImg.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    musicOffImg.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("musicoff.jpg")))));
                    musicOff0Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("musicoff0.jpg")))));


                    EndlessRunGame.stopMusic();
                    //FileHandler.setMusic(false);
                    musicAndSoundPreferencess.putBoolean("musicOn", false);
                    musicAndSoundPreferencess.flush();

                }
            });

        }
//////////////////////////////////////////////////////////////////////////////////////////////////////

        if(Bird.isFlapSoundActive==true){
            soundOff0Img.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y){
                    soundOff0Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("soundoff1.jpg")))));
                    soundOn1Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("soundon0.jpg")))));

                    Bird.isFlapSoundActive=false;
                    //**FileHandler.setSound(false);
                    musicAndSoundPreferencess.putBoolean("soundOn", false);
                    musicAndSoundPreferencess.flush();



                }
            });
            soundOn1Img.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y){
                    soundOn1Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("soundon1.jpg")))));
                    soundOff0Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("soundoff0.jpg")))));

                    Bird.isFlapSoundActive=true;
                    //**FileHandler.setSound(true);
                    musicAndSoundPreferencess.putBoolean("soundOn", true);
                    musicAndSoundPreferencess.flush();



                }
            });
        }
        else {
            soundOff1Img.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y){
                    soundOff1Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("soundoff1.jpg")))));
                    soundOn0Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("soundon0.jpg")))));

                    Bird.isFlapSoundActive=false;
                    //**FileHandler.setSound(false);
                    musicAndSoundPreferencess.putBoolean("soundOn", false);
                    musicAndSoundPreferencess.flush();



                }
            });
            soundOn0Img.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y){
                    soundOn0Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("soundon1.jpg")))));
                    soundOff1Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("soundoff0.jpg")))));
                    Bird.isFlapSoundActive=true;
                    //**FileHandler.setSound(true);
                    musicAndSoundPreferencess.putBoolean("soundOn", true);
                    musicAndSoundPreferencess.flush();




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
        font.getData().setScale(3,3);
        font.draw(batch, "SOUND", Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/1.2f-50);
        font.draw(batch, "MUSIC", Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/1.2f+Gdx.graphics.getHeight()/10-50);
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
            soundOn0Img.setDrawable(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("soundon0.jpg")))));
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

