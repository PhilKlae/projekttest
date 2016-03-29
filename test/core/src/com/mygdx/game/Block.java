package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by philo on 17.03.2016.
 */

public class Block  implements Pool.Poolable {
    int speed;
    int pos;
    boolean right;
    boolean hit;
    int blocksize;
    public boolean alive;
    int width;
    int length;
    int playercol;
    int playerpos;
    int punkte=0;

    public int getpunkte(){
      return punkte;

    }


    public Block(){


        alive=false;
    }

   /* public Block(boolean r,double b,double s,int w,int l){
        right=r;
        pos=0;
        blocksize=(int)(b*w);
        speed =(int) (s*l);
    }*/



    public void reset() {
        right=true;
        pos=0;
        blocksize=0;
        speed =0;
        hit=false;
        alive=false;
        punkte=0;
    }

    public void init(int difficulty,int l,int w,int pc) {


        length=l;
        width=w;

        right=Math.random() < 0.5;
        pos=0;
        blocksize=(int )(0.3*width);
        //System.out.println(blocksize);
        speed =(int) (((-0.003 + Math.random()/200  ) + 0.01)*length);
        alive=true;

        playercol=pc;
        playerpos=0;
        punkte=0;


    }

    public void update (float delta,int playerpos) {



        // update bullet position


        // if bullet is out of screen, set it to dead
        //kollision testen
        for(int i=0;i<speed*delta*60;i++){


            if (pos-i == playercol) {

                //spieler ist auf der rechten seite und wird getrofeen
                if (right && playerpos > width - blocksize) {
                    //TODO punktabzug
                    if(punkte==0) {
                        punkte--;
                    }
                    hit = true;


                }else{
                    if(punkte==0){
                        punkte++;
                    }



                }


                //spieler ist auf der linken seite und wird getroffen
                if (!right && playerpos < blocksize) {
                    //TODO punktabzug
                    if(punkte==0) {
                        punkte--;
                    }
                    hit = true;

                }
            else{
                    if(punkte==0){
                        punkte++;
                    }

                }

            }

        }

        //blöcke werden verschoben und aussortiert wenn sie die maße des spielfelds überschreiten

       if (pos > length) {

            alive=false;

        } else {
            pos +=speed*delta*60;


       }


    }

}