package game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import game.FileHandler;
import game.States.OptionsState;
import game.States.PlayState;

public class Bird {
    public static int GRAVITY =0;


    private static int MOVEMENT=250;
    public static Vector3 position;
    public static Vector3 velocity;


    private Rectangle bounds;



    private Animation birdAnimation;
    private Texture texture;
    private Sound flap;

    private Texture bird;
    public static boolean isFlapSoundActive=true;

    public static void setPosition(Vector3 position) {
        Bird.position = position;
    }


    public Bird(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        texture =new Texture("kedi.jpg");


        birdAnimation = new Animation(new TextureRegion(texture),8,1f);
        bounds = new Rectangle(x,y,texture.getWidth()/8,texture.getHeight());
        flap= Gdx.audio.newSound(Gdx.files.internal("jumpMusic.wav"));
    }

    public void stopBirdAnimation() {
        birdAnimation.currentFrameTime=9999;
    }

    public void setMOVEMENT(int MOVEMENT) {
        this.MOVEMENT = MOVEMENT;
    }

    public Texture getBirdTexture(){
        return texture;
    }

    public void update(float dt){
        if(PlayState.i<3){
            birdAnimation.maxFrameTime=0.08f;
            MOVEMENT=250;
        }
        else if(PlayState.i<5){
            birdAnimation.maxFrameTime=0.07f;
            MOVEMENT=310;
        }
        else if(PlayState.i<7){
            birdAnimation.maxFrameTime=0.06f;

            MOVEMENT=340;
        }
        else if(PlayState.i<9){
            birdAnimation.maxFrameTime=0.05f;

            MOVEMENT=370;
        }
        else if(PlayState.i<11){
            birdAnimation.maxFrameTime=0.04f;

            MOVEMENT=380;
        }
        else if(PlayState.i<13){
            birdAnimation.maxFrameTime=0.035f;

            MOVEMENT=390;
        }
        else if(PlayState.i<15){
            birdAnimation.maxFrameTime=0.030f;

            MOVEMENT=410;
        }
        else if(PlayState.i<17){
            birdAnimation.maxFrameTime=0.025f;

            MOVEMENT=425;
        }
        else if(PlayState.i<19){
            birdAnimation.maxFrameTime=0.020f;

            MOVEMENT=430;
        }
        else if(PlayState.i<21){
            birdAnimation.maxFrameTime=0.015f;

            MOVEMENT=445;
        }
        else if(PlayState.i<23){
            MOVEMENT=455;
        }
        else if(PlayState.i<25){
            MOVEMENT=465;
        }
        else if(PlayState.i>25){
            MOVEMENT=500;
        }
        if(!PlayState.isPaused) {
            birdAnimation.update(dt);
            if (position.y > 0) {
                velocity.add(0, GRAVITY, 0);
            }
            velocity.scl(dt);
            position.add(MOVEMENT * dt, velocity.y, 0);
            if (position.y < 0) {
                position.y = 0;
            }
            velocity.scl(1 / dt);
            bounds.setPosition(position.x, position.y);
        }
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void jump(){
        velocity.y=400;
        if(isFlapSoundActive){
            flap.play(0.08f);
        }

    }


    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose() {
        texture.dispose();
        flap.dispose();
    }
}
