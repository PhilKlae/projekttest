package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

/**
 * Created by philo on 15.03.2016.
 */
public class linejumper implements minigame, Disposable {

    //render kram
    Pixmap pixmap;
    Texture texture;
    Sprite sprite;

    boolean paused=false;


    int framecounter=0;
    int lastblock=0;
    int blockdist=100;

    //länge und breite des spielfeldes
    int length=0;
    int width=0;


    //schwierigkeit
    int difficulty=0;

    //liste mit blöcken
    ArrayList<Block> blocks=new ArrayList<Block>();

    //player conditions
    int playerpos=0;
    int playercol;
    int playerdirection=0; //-1= fliegt nach links -2 = steht still links 2 = steht still rechts 1=fliegt nach rechts
    int playerspeed=7;

    Color playercolor;

    public linejumper(int l, int w, int colorint){



        width=w;
        length=l;
         playercol=4* width/5;
        if(colorint==0){
           playercolor=Color.RED;
        }
        if(colorint==1){
            playercolor=Color.GREEN;
        }
        if(colorint==2){
            playercolor=Color.VIOLET;
        }
        if(colorint==3){
            playercolor=Color.BLUE;
        }
        if(colorint==4){
            playercolor=Color.GOLD;
        }
        if(colorint==5){
            playercolor=Color.CYAN;
        }
    }

    public void refresh(){
        if(!paused){
        framecounter++;

        //updateplayerdata
        if(playerdirection==1||playerdirection==-1){
            playerpos-=playerdirection + playerspeed*playerdirection;
        }

        if(playerpos<=0){
            playerdirection=-2;
        }

        if(playerpos>=width){
            playerdirection=2;
        }


        //block pos prüfen
        for(int i=0;i<blocks.size();i++) {

            //kollision testen
            if (blocks.get(i).pos == playercol) {

                //spieler ist auf der rechten seite und wird getrofeen
                if (blocks.get(i).right && playerpos > width - blocks.get(i).blocksize) {
                    //TODO punktabzug

                    blocks.get(i).hit = true;

                }


                //spieler ist auf der linken seite und wird getroffen
                if (!blocks.get(i).right && playerpos < blocks.get(i).blocksize) {
                    //TODO punktabzug

                    blocks.get(i).hit = true;
                }

            }

            //blöcke werden verschoben und aussortiert wenn sie die maße des spielfelds überschreiten

            if (blocks.get(i).pos > length) {


                   lastblock=framecounter - lastblock;
                framecounter=0;


                blocks.remove(i);
            } else {
                blocks.get(i).pos = blocks.get(i).pos + blocks.get(i).speed;
            }
        }
        }

        //blockdistanz erzeugen

        //neue blöcke spawnen
        if (lastblock+blockdist==framecounter){
            lastblock=framecounter;

            spawnBlock();
             blockdist=(int)(Math.random()*500+50);
        }

    }


    public void spawnBlock(){
        //easy

        //hier dificulty einfließen lassen TODO
       blocks.add(new Block(Math.random() < 0.5,0.3,0.01,width,length));
        //harder aber verbuggt
      // blocks.add(new Block(Math.random() < 0.5,0.5,Math.random()*2/100,width,length));
    }



    public void fireEvent(){
        if(playerdirection==2){
            playerdirection=1;
        }
        if(playerdirection==-2){
            playerdirection=-1;
        }
    }

    public Sprite paintpic(){


        try {

            sprite.getTexture().dispose();
            texture.dispose();

        } catch (Exception e) {

        }

        pixmap = new Pixmap(length ,width, Pixmap.Format.RGBA8888);



        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        //seitenlinien zeichnen

        pixmap.setColor(Color.BLACK);
        pixmap.drawLine(0, 0, 0, pixmap.getHeight()-1);
        pixmap.drawLine(pixmap.getWidth()-1, 0, pixmap.getWidth()-1, pixmap.getHeight()-1);

        for(int i=0;i<blocks.size();i++){
            if(blocks.get(i).hit){
                pixmap.setColor(Color.RED);
            }
            if(blocks.get(i).right){
                pixmap.fillRectangle(256-blocks.get(i).blocksize,blocks.get(i).pos-10,blocks.get(i).blocksize,10);
            }else{

                pixmap.fillRectangle(0,blocks.get(i).pos-10,blocks.get(i).blocksize,10);

            }
            pixmap.setColor(Color.BLACK);
        }

        //Draw a circle about the middle
        pixmap.setColor(playercolor);
       // pixmap.drawCircle(playerpos,playercol,3);
        if(playerdirection==2){
            pixmap.fillRectangle(playerpos-10,playercol,10,30);
            pixmap.setColor(Color.BLACK);
            pixmap.drawRectangle(playerpos-10,playercol,10,30);
        }else {
            pixmap.fillRectangle(playerpos, playercol, 10, 30);
            pixmap.setColor(Color.BLACK);
            pixmap.drawRectangle(playerpos,playercol,10,30);
        }

        texture = new Texture(pixmap);

        //It's the textures responsibility now... get rid of the pixmap
          pixmap.dispose();


        sprite = new Sprite(texture);
         //texture.dispose();
        return sprite;


    }

    @Override
    public void dispose() {
        texture.dispose();
        pixmap.dispose();
    }


    public void pause(){
           paused=true;
    }

    public void resume(){
            paused=false;
    }

    public void setDifficulty( int f){
         difficulty=f;
    }

}


