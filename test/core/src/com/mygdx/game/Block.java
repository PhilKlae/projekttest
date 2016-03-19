package com.mygdx.game;

/**
 * Created by philo on 17.03.2016.
 */

public class Block{
    int speed;
    int pos;
    boolean right;
    boolean hit;
    int blocksize;
    public Block(boolean r,double b,double s,int w,int l){
        right=r;
        pos=0;
        blocksize=(int)(b*w);
        speed =(int) (s*l);
    }

}