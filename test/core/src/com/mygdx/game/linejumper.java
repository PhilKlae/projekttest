package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;

    import java.util.ArrayList;

    /**
     * Created by philo on 15.03.2016.
     */
    public class linejumper implements minigame, Disposable {

    int[] oldpos=new int[5];
     float deltatime=0;
    //render kram
    Pixmap pixmap;
    Texture texture;
    Sprite sprite;

        int punkte=0;

    boolean paused=false;


    int framecounter=0;
    int lastblock=0;
    int blockdist=(int)(Math.random()*500+50);

    //länge und breite des spielfeldes
    int length=0;
    int width=0;


    //schwierigkeit
    int difficulty=0;

    //liste mit blöcken

    private final Array<Block> activeBlocks = new Array<Block>();

    // bullet pool.
    private final Pool<Block> blockPool = new Pool<Block>() {

        protected Block newObject() {
            return new Block();
        }
    };

    //player conditions
    int playerpos=0;
    int playercol;
    int playerdirection=0; //-1= fliegt nach links -2 = steht still links 2 = steht still rechts 1=fliegt nach rechts
    int playerspeed=7;

    Color playercolor;


    public linejumper(){

    }

    public linejumper(int l, int w, int colorint){
        //

        punkte=0;
        for(int i=0;i<oldpos.length;i++){

            oldpos[i]=0;

        }

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
        punkte=0;
        deltatime = Gdx.graphics.getDeltaTime();
        if(!paused){
        framecounter++;

        //updateplayerdata
        if(playerdirection==1||playerdirection==-1){
            playerpos-=playerdirection + playerspeed*deltatime*60*playerdirection;
        }

        if(playerpos<=0){
            playerdirection=-2;
            playerpos=0;
        }

        if(playerpos>=width){
            playerdirection=2;
            playerpos=width;
        }

            for(int i=0;i<activeBlocks.size;i++){
                activeBlocks.get(i).update(deltatime,playerpos);
            }

}

        //blockdistanz erzeugen

        //neue blöcke spawnen
        if (lastblock+blockdist==framecounter){
            lastblock=framecounter;

            spawnBlock();

             blockdist=(int)(Math.random()*500+50);
        }
        Block item;
        int len = activeBlocks.size;
        for (int i = len; --i >= 0;) {
            item = activeBlocks.get(i);
            if (item.alive == false) {
                punkte +=item.getpunkte();

                activeBlocks.removeIndex(i);
                blockPool.free(item);

            }
        }


    }


    public void spawnBlock(){
            Block item = blockPool.obtain();
            item.init(1,length,width,playercol);
            activeBlocks.add(item);

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


      pixmap.setColor(300,300,300,100);
      pixmap.fill();

        //seitenlinien zeichnen

        pixmap.setColor(Color.BLACK);
        pixmap.drawLine(0, 0, 0, pixmap.getHeight()-1);
        pixmap.drawLine(pixmap.getWidth()-1, 0, pixmap.getWidth()-1, pixmap.getHeight()-1);

        for(int i=0;i<activeBlocks.size;i++){
          //  System.out.println(activeBlocks.get(i).pos);
            if(activeBlocks.get(i).hit){
                pixmap.setColor(Color.RED);
            }
            if(activeBlocks.get(i).right){
                pixmap.fillRectangle(256-activeBlocks.get(i).blocksize,activeBlocks.get(i).pos-10,activeBlocks.get(i).blocksize,10);
            }else{

                pixmap.fillRectangle(0,activeBlocks.get(i).pos-10,activeBlocks.get(i).blocksize,10);

            }
            pixmap.setColor(Color.BLACK);
        }


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

        try {

            texture.dispose();
            pixmap.dispose();
            sprite.getTexture().dispose();

        } catch (Exception e) {

        }
    }

        @Override
        public int getPoints() {
            return punkte;
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


