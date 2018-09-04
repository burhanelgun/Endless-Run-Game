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
        texture =new Texture("kedi.png");


        birdAnimation = new Animation(new TextureRegion(texture),8,0.5f);
        bounds = new Rectangle(x,y,texture.getWidth()/8,texture.getHeight());
        flap= Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
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
        if(PlayState.i>7){
            MOVEMENT=300;
        }
        if(PlayState.i>14){
            MOVEMENT=350;
        }
        if(PlayState.i>21){
            MOVEMENT=400;
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
        velocity.y=450;
        if(isFlapSoundActive){
            flap.play();
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
