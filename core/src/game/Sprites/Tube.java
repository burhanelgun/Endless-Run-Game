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
        TUBE_WIDTH = r.nextInt(350)+250;
        bottomTube = new Texture("engel0.png");

        posBotTube = new Vector2(x, TUBE_GAP-bottomTube.getHeight());
        boundsBot=new Rectangle(posBotTube.x,posBotTube.y,bottomTube.getWidth(),bottomTube.getHeight());
    }
    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public Rectangle getBoundsBot() {
        return boundsBot;
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsBot);
    }

    public void reposition(float x){
        PlayState.i++;

        if(PlayState.i<3) {
            bottomTube = new Texture("engel0.png");
            TUBE_WIDTH = r.nextInt(350) + 250;
        }

         if(PlayState.i>=3){
            Random t = new Random();
            int a = t.nextInt((2 - 0) + 1) + 0;
            if(a==0){
                bottomTube=new Texture("engel1.png");
            }
            else if(a==1){
                bottomTube=new Texture("engel2.png");
            }
            else if(a==2){
                bottomTube=new Texture("engel3.png");
            }
            TUBE_WIDTH = r.nextInt(250)+200;
        }

         if(PlayState.i>6){
            Random t = new Random();
            int a = t.nextInt((3 - 0) + 1) + 0;
            if(a==0){
                bottomTube=new Texture("engel4.png");
            }
            else if(a==1){
                bottomTube=new Texture("engel5.png");
            }
            else if(a==2){
                bottomTube=new Texture("engel6.png");
            }
            else if(a==3){
                bottomTube=new Texture("engel7.png");
            }
            TUBE_WIDTH = r.nextInt(200)+150;
        }
         if(PlayState.i>8){
            Random t = new Random();
            int a = t.nextInt((4 - 0) + 1) + 0;
            if(a==0){
                bottomTube=new Texture("engel8.png");
            }
            else if(a==1){
                bottomTube=new Texture("engel9.png");
            }
            else if(a==2){
                bottomTube=new Texture("engel10.png");
            }
            else if(a==3){
                bottomTube=new Texture("engel11.png");
            }
            else if(a==4){
                bottomTube=new Texture("engel12.png");
            }
            TUBE_WIDTH = r.nextInt(350)+250;

        }
         if(PlayState.i>15){
            Random t = new Random();
            int a = t.nextInt((5 - 0) + 1) + 0;
            if(a==0){
                bottomTube=new Texture("engel13.png");
            }
            else if(a==1){
                bottomTube=new Texture("engel12.png");
            }
            else if(a==2){
                bottomTube=new Texture("engel11.png");
            }
            else if(a==3){
                bottomTube=new Texture("engel10.png");
            }
            else if(a==4){
                bottomTube=new Texture("engel9.png");
            }
            else if(a==5){
                bottomTube=new Texture("engel8.png");
            }
            TUBE_WIDTH = r.nextInt(350)+250;

        }
        posBotTube.set(x,TUBE_GAP - bottomTube.getHeight());
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }
    public void dispose() {
        bottomTube.dispose();
    }
}
