package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import sun.rmi.runtime.Log;

public class FileHandler {
    private static boolean isMusicOn;
    private static boolean isSoundOn;
    static Properties props = new Properties();
    static OutputStream fileOut;


    FileHandler(String filename) {
        try {
            Gdx.app.log("hello","11111111111");
            FileHandle file = Gdx.files.local(filename);

            props.load(file.reader());

            Gdx.app.log("hello","2222222222222");

            //isMusicOn = Boolean.valueOf(props.getProperty("music"));
            //isSoundOn = Boolean.valueOf(props.getProperty("sound"));
            fileOut = file.write(true);
            Gdx.app.log("hello","33333333333333");

        } catch (IOException e) {
            Gdx.app.log("hello","4444444444");

            e.printStackTrace();
            Gdx.app.log("hello","555555555555");

        }


    }

    public static boolean isMusicOn() {
        Gdx.app.log("hello","6666666666666666");

        isMusicOn = Boolean.valueOf(props.getProperty("music"));
        Gdx.app.log("hello","77777777777");

        return isMusicOn;
    }

    public static boolean isSoundOn() {
        Gdx.app.log("hello","88888888888888888");

        isSoundOn = Boolean.valueOf(props.getProperty("sound"));
        Gdx.app.log("hello","99999999999999999999");

        return isSoundOn;
    }

    public static void setMusic(boolean musicState) {
        Gdx.app.log("hello","1010101010");

        boolean t = isSoundOn();

        props.setProperty("music",musicState+"");
        props.setProperty("sound",t+"");
        Gdx.app.log("hello","12121212121212");

        try {
            props.store(fileOut,null);
            Gdx.app.log("hello","13131313131313131313");

        } catch (IOException e) {
            Gdx.app.log("hello","141414141414141414");

            e.printStackTrace();
        }
    }
    public static void setSound(boolean soundState) {
        Gdx.app.log("hello","1515151515151515");

        boolean t =isMusicOn();

        props.setProperty("music",t+"");
        props.setProperty("sound",soundState+"");

        Gdx.app.log("hello","161616116161616");

        try {
            props.store(fileOut,null);
            Gdx.app.log("hello","171717171717171717");

        } catch (IOException e) {
            Gdx.app.log("hello","181818181818181818");

            e.printStackTrace();
        }

    }






}
