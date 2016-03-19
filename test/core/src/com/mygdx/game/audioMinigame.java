package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by philo on 16.03.2016.
 */
public class audioMinigame implements minigame, Disposable {
    Sound mp3Sound ;
    Texture texture;
    Sprite sprite;
    int framecounter=0;
     int lastSignal=0;
     int buffertime=200;
     int nextsignal=1000;


    public audioMinigame(){
        Sound mp3Sound = Gdx.audio.newSound(Gdx.files.internal("sounds/minigamesounds/ring.mp3"));
        Texture texture = new Texture(Gdx.files.internal("pics/minigamepics/button.png"));
        sprite =new Sprite(texture);
       // mp3Sound.play();
    }


    public void refresh(){
        framecounter++;
        if(lastSignal+buffertime>=framecounter){
            //Punktabzug TODO
            System.out.println("fail");
           // nextsignal=(int)(Math.random() * 1000) + 300;

        }
        if(framecounter==nextsignal){
            //sound machen
            Sound mp3Sound = Gdx.audio.newSound(Gdx.files.internal("sounds/minigamesounds/ring.mp3"));
            mp3Sound.play();
            mp3Sound.dispose();
            nextsignal=(int)(Math.random() * 1000) + 300;
            lastSignal=framecounter;
        }

    }


    public Sprite paintpic(){



        return sprite;
    }
    public void pause(){

    }

    public void resume(){

    }

    public void setDifficulty(int f){

    }

    public void fireEvent(){

        if(framecounter<nextsignal+buffertime&&framecounter>nextsignal){
                //TODO Punkte geben
            System.out.println("punkte");
        }else{
            System.out.println("fail");
        }

    }

    public void dispose(){
        texture.dispose();

    }
}
