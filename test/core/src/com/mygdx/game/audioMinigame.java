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
     int nextsignal=500;
    boolean ringed=false;
    float deltatime=0;
    int punkte=0;
    int eventpoints=0;
    boolean paused=false;



    public audioMinigame(){
         mp3Sound = Gdx.audio.newSound(Gdx.files.internal("sounds/minigamesounds/ring.mp3"));
         texture = new Texture(Gdx.files.internal("pics/minigamepics/button.png"));
        sprite =new Sprite(texture);
       // mp3Sound.play();

    }


    public void refresh(){
        if(!paused) {
            punkte = eventpoints;
            deltatime = Gdx.graphics.getDeltaTime();
            framecounter += deltatime * 60;

            if (ringed) {
                buffertime -= deltatime;
                if (buffertime <= 0) {
                    //Punktabzug
                    punkte -= 20;

                    // nextsignal=(int)(Math.random() * 1000) + 300;
                    ringed = false;
                }
            }

            if (framecounter >= nextsignal) {
                //sound machen
                buffertime = 200;
                ringed = true;
                mp3Sound.dispose();
                Sound mp3Sound = Gdx.audio.newSound(Gdx.files.internal("sounds/minigamesounds/ring.mp3"));
                mp3Sound.play();

                nextsignal = (int) (Math.random() * 400) + 300;
                framecounter = 0;

            }
            eventpoints = 0;
        }
    }


    public Sprite paintpic(){



        return sprite;
    }
    public void pause(){
        paused=true;
    }

    public void resume(){
        paused=false;
    }

    public int getPoints(){

        return punkte;
    }

    public void setDifficulty(int f){

    }

    public void fireEvent(){
        if(ringed==false&&buffertime>=0) {
            if (framecounter > 10) {
                eventpoints-=20;
               // System.out.println("ooong");
                framecounter = 0;
            }
        }

        if(ringed) {
            // Punkte geben
            eventpoints+=20;

          //  System.out.println("huuui");
            ringed = false;
            framecounter=0;
        }


    }

    public void dispose(){


        try {

            texture.dispose();
            sprite.getTexture().dispose();

        } catch (Exception e) {

        }

    }
}
