package game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import game.States.PlayState;

public class Tube {
    public static int TUBE_WIDTH;
    private static final int TUBE_GAP = 100;
    private Rectangle boundsBot;
    private Texture bottomTube;
    private Vector2 posBotTube;
    Random r = new Random();


    public Tube(float x){
        bottomTube=new Texture("engel0.png");
        if(PlayState.i<7){
            TUBE_WIDTH = r.nextInt(350)+250;
        }
        if(PlayState.i>7){
            TUBE_WIDTH = r.nextInt(250)+200;
        }
        if(PlayState.i>14){
            TUBE_WIDTH = r.nextInt(200)+150;
        }
        if(PlayState.i>21){
            bottomTube=new Texture("engel10.png");
            TUBE_WIDTH = r.nextInt(350)+250;

        }


        posBotTube = new Vector2(x, TUBE_GAP-bottomTube.getHeight());
        boundsBot=new Rectangle(posBotTube.x-3,posBotTube.y-3,bottomTube.getWidth()-3,bottomTube.getHeight()-3);
    }
    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsBot);
    }

    public void reposition(float x){
        PlayState.i++;
        posBotTube.set(x,TUBE_GAP - bottomTube.getHeight());
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }
    public void dispose() {
        bottomTube.dispose();
    }
}
