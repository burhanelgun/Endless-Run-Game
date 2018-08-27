package game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
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
    private Texture stopIcon;
    Image stopIconImg;
    Stage stage = new Stage();
    public static boolean isPaused=false;

    ShapeRenderer shapeRenderer = new ShapeRenderer();

    public PlayState(GameStateManager gsm) {
        super(gsm);
        System.out.println("asasasasasasasasasasasasasasasasasasasasasasasasasas");
        score = 0;
        yourScoreName = "score: 0";
        yourBitmapFontName = new BitmapFont();
        bird = new Bird(50,62);
        cam.setToOrtho(false, EndlessRunGame.WIDTH/2,EndlessRunGame.HEIGHT/2);
        bg = new Texture("bg.png");


        stopIcon =new Texture(Gdx.files.internal("stopIcon.png"));
        stopIconImg=new Image(stopIcon);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(stopIconImg);
        stopIconImg.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                isPaused=true;
            }
        });

        ground = new Texture("ground.png");
        bird = new Bird(50,ground.getHeight()+GROUND_Y_OFFSET);

        groundPos1= new Vector2(cam.position.x-cam.viewportWidth/2,GROUND_Y_OFFSET);
        groundPos2= new Vector2((cam.position.x-cam.viewportWidth/2)+ground.getWidth(),GROUND_Y_OFFSET);

        tubes=new Array<Tube>();

       for(int i=1;i<TUBE_COUNT;i++) {
           tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
       }

    }

    @Override
    protected void handleInput() {

        if(bird.getPosition().y<=ground.getHeight()+GROUND_Y_OFFSET) {
            if (Gdx.input.justTouched()) {
                    Bird.GRAVITY = -15;
                    bird.jump();
            }
        }

    }

    @Override
    public void update(float dt) {


            if(!isPaused){
                score++;
            }
            yourScoreName = "score: " + score;
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
                    System.out.println("bbbbbbbbbbbbbb= " + (tube.getBoundsBot().x - bird.getBounds().x));
                    gsm.set(new MenuState(gsm));
                }



                //System.out.println("bird.getBirdTexture().getWidth() = " + bird.getBirdTexture().getWidth());


      /*    else if(bird.getBounds().y==ground.getHeight()+GROUND_Y_OFFSET && bird.getBounds().x+bird.getBirdTexture().getWidth()/2>tube.getPosBotTube().x){
               gsm.set(new PlayState(gsm));
           }*/
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
                sb.draw(tube.getBottomTube(),tube.getPosBotTube().x,tube.getPosBotTube().y);
            }
            sb.draw(ground,groundPos1.x,groundPos1.y);
            sb.draw(ground,groundPos2.x,groundPos2.y);
            yourBitmapFontName.setColor(250.0f, 0, 0, 19.0f);
            yourBitmapFontName.draw(sb, yourScoreName, cam.position.x-80, 400);
            sb.end();

            stopIconImg.setHeight(40f);
            stopIconImg.setWidth(30f);

            stopIconImg.setPosition(Gdx.graphics.getWidth()/10+Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/1.15f);

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
