package game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import game.EndlessRunGame;
import game.Sprites.Bird;
import game.Sprites.Tube;

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT=2;
    public static final int GROUND_Y_OFFSET=-50;
    public static int i=0;
    private Bird bird;
    private Texture bg;
    private Array<Tube> tubes;
    private Texture ground;
    private Vector2 groundPos1,groundPos2;
    private int score;
    private String yourScoreName;
    BitmapFont yourBitmapFontName;
    BitmapFont stopMessage;
    Stage stage = new Stage();
    public static boolean isPaused=false;
    private Group pauseGroup;
    private Group inputGroup;




    private Texture stopButtonUpTexture;
    private TextureRegion stopButtonUpTextureRegion;
    private TextureRegionDrawable stopButtonUpTextureRegionDrawable;

    private Texture stopButtonDownTexture;
    private TextureRegion stopButtonDownTextureRegion;
    private TextureRegionDrawable stopButtonDownTextureRegionDrawable;

    private ImageButton stopButton;


    private TextButton buttonResume;
    private TextButton buttonRetry;
    private TextButton buttonMenu;
    //private TextButton buttonSaveToScoreBoard;


    private TextureAtlas atlas;
    private BitmapFont white,black;
    private Skin skin;
    private Table table;
    private int rank;
    private int gamerCount;
    private BitmapFont white0;
    Preferences prefs = Gdx.app.getPreferences("My Preferences");



    public PlayState(final GameStateManager gsm) {
        super(gsm);

        //this.playServices = playServices;
        stopButtonUpTexture = new Texture(Gdx.files.internal("stopButtonUp.png"));
        stopButtonUpTextureRegion = new TextureRegion(stopButtonUpTexture);
        stopButtonUpTextureRegionDrawable = new TextureRegionDrawable(stopButtonUpTextureRegion);

        stopButtonDownTexture = new Texture(Gdx.files.internal("stopButtonDown.png"));
        stopButtonDownTextureRegion = new TextureRegion(stopButtonDownTexture);
        stopButtonDownTextureRegionDrawable = new TextureRegionDrawable(stopButtonDownTextureRegion);

        stopButton = new ImageButton(stopButtonUpTextureRegionDrawable,stopButtonDownTextureRegionDrawable); //Set the button up
        stopButton.setPosition(Gdx.graphics.getWidth()/15, Gdx.graphics.getHeight()/1.12f);
        stopButton.setSize(Gdx.graphics.getWidth()/9,Gdx.graphics.getHeight()/9);
        stopButton.setDisabled(false);


        white = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
        white0 = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);

        white.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        if(Gdx.graphics.getWidth()>800){
            white.getData().setScale(2);
        }
        else{
        }


        white0.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        if(Gdx.graphics.getWidth()>800){
            white0.getData().setScale(1.5f);
        }
        else{
            white0.getData().setScale(0.7f);
        }



        score = 0;
        yourScoreName = "SCORE: 0";
        yourBitmapFontName =  new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
        stopMessage = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
        yourBitmapFontName.getData().setScale(0.5f);
        stopMessage.getData().setScale(0.5f);
        bird = new Bird(50,62);
        cam.setToOrtho(false, EndlessRunGame.WIDTH/2,EndlessRunGame.HEIGHT/2);
        bg = new Texture("bg.png");





        stage.addActor(stopButton); //Add the button to the stage to perform rendering and take input.

        Gdx.input.setInputProcessor(stage);



        ground = new Texture("ground.png");
        bird = new Bird(50,ground.getHeight()+GROUND_Y_OFFSET);

        groundPos1= new Vector2(cam.position.x-cam.viewportWidth/2,GROUND_Y_OFFSET);
        groundPos2= new Vector2((cam.position.x-cam.viewportWidth/2)+ground.getWidth(),GROUND_Y_OFFSET);

        tubes=new Array<Tube>();

        for(int i=1;i<TUBE_COUNT;i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }

        stopButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                isPaused=true;
                pauseGroup = new Group();
                inputGroup=new Group();
                Image semiTransparentBG=new Image( new Texture(Gdx.files.internal("bgC.png")));
                semiTransparentBG.setSize(Gdx.graphics.getWidth()/1.5f,Gdx.graphics.getHeight()/1.8f);
                semiTransparentBG.setPosition(Gdx.graphics.getWidth()/2-semiTransparentBG.getWidth()/2, Gdx.graphics.getHeight()/2-semiTransparentBG.getHeight()/1.6f);


                atlas = new TextureAtlas("button.pack");
                skin= new Skin(atlas);
                table = new Table(skin);
                table.setBounds(Gdx.graphics.getWidth()/2-semiTransparentBG.getWidth()/2,Gdx.graphics.getHeight()/2-semiTransparentBG.getHeight()/1.6f,Gdx.graphics.getWidth()/1.5f,Gdx.graphics.getHeight()/1.8f);
                TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
                textButtonStyle.up = skin.getDrawable("normalbuton");
                textButtonStyle.down = skin.getDrawable("pressbuton");
                textButtonStyle.pressedOffsetX=1;
                textButtonStyle.pressedOffsetY=-1;
                textButtonStyle.font = white;
                textButtonStyle.fontColor= Color.BLACK;



                buttonResume = new TextButton("RESUME",textButtonStyle);
                buttonRetry = new TextButton("RETRY",textButtonStyle);
                buttonMenu = new TextButton("BACK TO MENU",textButtonStyle);

              /*  buttonResume.getLabel().setFontScale(1.5f, 1.5f);
                buttonRetry.getLabel().setFontScale(1.5f, 1.5f);
                buttonMenu.getLabel().setFontScale(1.5f, 1.5f);
*/
                buttonResume.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        isPaused=false;
                        pauseGroup.remove();
                    }
                });
                buttonResume.pad(20);
                table.add(buttonResume).width(semiTransparentBG.getWidth()-semiTransparentBG.getWidth()/5).height(semiTransparentBG.getHeight()/7);
                table.row();

                buttonRetry.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        isPaused = false;
                        gsm.set(new PlayState(gsm));
                    }
                });
                buttonRetry.pad(20);
                table.add(buttonRetry).height(10);
                table.row();
                table.add(buttonRetry).width(semiTransparentBG.getWidth()-semiTransparentBG.getWidth()/5).height(semiTransparentBG.getHeight()/7);
                table.row();
                buttonMenu.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        isPaused = false;
                        gsm.set(new MenuState(gsm));
                    }
                });
                buttonMenu.pad(20);
                table.add(buttonMenu).height(10);
                table.row();
                table.add(buttonMenu).width(semiTransparentBG.getWidth()-semiTransparentBG.getWidth()/5).height(semiTransparentBG.getHeight()/7);
                table.row();
                table.setDebug(true);

                pauseGroup.addActor(semiTransparentBG);
                pauseGroup.addActor(table);
                stage.addActor(pauseGroup);

            }
        });

    }

    @Override
    protected void handleInput() {

        if(isPaused==false){
            if(bird.getPosition().y<=ground.getHeight()+GROUND_Y_OFFSET) {
                if (Gdx.input.justTouched()) {
                    Bird.GRAVITY = -15;
                    bird.jump();
                }
            }
        }
    }




    @Override
    public void update(float dt) {




        if(!isPaused){
            score++;
        }
        yourScoreName = "SCORE: " + score;
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x=bird.getPosition().x+80;

        for(int i=0;i<tubes.size;i++){
            Tube tube = tubes.get(i);

            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosBotTube().x + tube.getBottomTube().getWidth()){
                tube.reposition(tube.getPosBotTube().x  + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
//burda çizdir

            if((tube.collides((bird.getBounds()))) || ((bird.getBounds().y<ground.getHeight()+GROUND_Y_OFFSET) && (tube.getBoundsBot().x - bird.getBounds().x<55))){
                // gsm.set(new MenuState(gsm));



                stopButton.setDisabled(true);
                isPaused=true;
                pauseGroup = new Group();
                Image semiTransparentBG=new Image( new Texture(Gdx.files.internal("bgC.png")));
                semiTransparentBG.setSize(Gdx.graphics.getWidth()/1.5f,Gdx.graphics.getHeight()/1.8f);
                semiTransparentBG.setPosition(Gdx.graphics.getWidth()/2-semiTransparentBG.getWidth()/2, Gdx.graphics.getHeight()/2-semiTransparentBG.getHeight()/1.6f);




                atlas = new TextureAtlas("button.pack");
                skin= new Skin(atlas);
                table = new Table(skin);
                table.setBounds(Gdx.graphics.getWidth()/2-semiTransparentBG.getWidth()/2,Gdx.graphics.getHeight()/2-semiTransparentBG.getHeight()/1.6f,Gdx.graphics.getWidth()/1.5f,Gdx.graphics.getHeight()/1.8f);


                TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
                textButtonStyle.up = skin.getDrawable("normalbuton");
                textButtonStyle.down = skin.getDrawable("pressbuton");
                textButtonStyle.pressedOffsetX=1;
                textButtonStyle.pressedOffsetY=-1;
                textButtonStyle.font = white;
                textButtonStyle.fontColor= Color.BLACK;


                TextButton.TextButtonStyle textButtonStyle0 = new TextButton.TextButtonStyle();
                textButtonStyle0.up = skin.getDrawable("normalbuton");
                textButtonStyle0.down = skin.getDrawable("pressbuton");
                textButtonStyle0.pressedOffsetX=1;
                textButtonStyle0.pressedOffsetY=-1;
                textButtonStyle0.font = white0;
                textButtonStyle0.fontColor= Color.BLACK;





                //buttonSaveToScoreBoard = new TextButton("HIGH SCORES",textButtonStyle0);
                buttonRetry = new TextButton("RETRY",textButtonStyle0);
                buttonMenu = new TextButton("BACK TO MENU",textButtonStyle0);

              /*  buttonResume.getLabel().setFontScale(1.5f, 1.5f);
                buttonRetry.getLabel().setFontScale(1.5f, 1.5f);
                buttonMenu.getLabel().setFontScale(1.5f, 1.5f);
*/
                Label.LabelStyle style = new Label.LabelStyle();
                style.font= new BitmapFont(Gdx.files.internal("font/black.fnt"),false);
                Label label = new Label("     YOU DIED",style);
                if(Gdx.graphics.getWidth()>800){
                    label.setFontScale(2);
                }
                else{
                    label.setFontScale(0.7f);
                }
                table.add(label).height(semiTransparentBG.getHeight()/7).width(semiTransparentBG.getWidth()-semiTransparentBG.getWidth()/5);
                table.row();




                Label label2 = new Label("YOUR SCORE "+score ,style);

                Gdx.app.debug("score",""+score);
                Gdx.app.debug("prefs.getInteger(\"rank\")",""+prefs.getInteger("rank"));

                if(score>prefs.getInteger("rank")){
                    prefs.putInteger("rank", score);
                    prefs.flush();
                }


                if(Gdx.graphics.getWidth()>800){
                    label2.setFontScale(1.5f);
                }
                else{
                    label2.setFontScale(0.7f);
                }
                table.add(label2).height(semiTransparentBG.getHeight()/7);  //
                table.row();

                Label label3 = new Label("HIGHEST SCORE : "+prefs.getInteger("rank") ,style);
                if(Gdx.graphics.getWidth()>800){
                    label3.setFontScale(1.6f);
                }
                else{
                    label3.setFontScale(0.7f);
                }
                table.add(label3).height(semiTransparentBG.getHeight()/5);  //
                table.row();





/*
                buttonSaveToScoreBoard.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        isPaused=false;
                        pauseGroup.remove();
                        gsm.set(new RankTable(gsm));


                        System.out.println("PlayServices: Setting Score as : " + score);
                        playServices.submitScore("CgkI2oGa78INEAIQAA",score); //
                        playServices.submitLevel(score + 1);

                        System.out.println("PlayServices: Submitted Score as : " + score );
                        playServices.showScore("CgkI2oGa78INEAIQAA");

                    }
                });
*/
              /*  buttonSaveToScoreBoard.pad(20);
                table.add(buttonSaveToScoreBoard).width(semiTransparentBG.getWidth()-semiTransparentBG.getWidth()/5).height(semiTransparentBG.getHeight()/8.5f);
                table.row();*/


                buttonRetry.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        isPaused = false;
                        gsm.set(new PlayState(gsm));
                    }
                });
                buttonRetry.pad(20);
                table.add(buttonRetry).height(10);
                table.row();
                table.add(buttonRetry).width(semiTransparentBG.getWidth()-semiTransparentBG.getWidth()/5).height(semiTransparentBG.getHeight()/8.5f);
                table.row();



                buttonMenu.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        isPaused = false;
                        gsm.set(new MenuState(gsm));
                    }
                });
                buttonMenu.pad(20);
                table.add(buttonMenu).height(10);
                table.row();
                table.add(buttonMenu).width(semiTransparentBG.getWidth()-semiTransparentBG.getWidth()/5).height(semiTransparentBG.getHeight()/8.5f);
                table.row();


                table.setDebug(true);

                pauseGroup.addActor(semiTransparentBG);
                pauseGroup.addActor(table);
                stage.addActor(pauseGroup);
            }


        }

        if(bird.getPosition().y<=ground.getHeight()+GROUND_Y_OFFSET){
            ////gsm.set(new PlayState(gsm));
            bird.setPosition(new Vector3(bird.getPosition().x,ground.getHeight()+GROUND_Y_OFFSET,0));

        }
        cam.update();



    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();


        sb.draw(bg,cam.position.x-(cam.viewportWidth/2),0);
        sb.draw(bird.getTexture(),bird.getPosition().x,bird.getPosition().y);

        for(Tube tube : tubes){
            sb.draw(tube.getBottomTube(),tube.getPosBotTube().x,tube.getPosBotTube().y-4);
        }
        sb.draw(ground,groundPos1.x,groundPos1.y);
        sb.draw(ground,groundPos2.x,groundPos2.y);
        yourBitmapFontName.draw(sb, yourScoreName, cam.position.x-Gdx.graphics.getWidth()/30, Gdx.graphics.getHeight()/5);
        sb.end();




        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();


    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for(Tube tube:tubes){
            tube.dispose();
        }
    }

    private void updateGround(){
        if(cam.position.x-(cam.viewportWidth/2)>groundPos1.x+ground.getWidth()){
            groundPos1.add(ground.getWidth()*2,0);
        }
        if(cam.position.x-(cam.viewportWidth/2)>groundPos2.x+ground.getWidth()){
            groundPos2.add(ground.getWidth()*2,0);
        }



    }
}
