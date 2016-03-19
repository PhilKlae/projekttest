package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Created by philo on 17.03.2016.
 */
public class disposer {
    public static void feedlist(ArrayList<Object> l){
        for(int i =0;i<l.size();i++) {

            try {
                Texture t=(Texture) l.get(i);
                t.dispose();

            } catch (Exception e) {

            }
        }


    }

    public static void feedTex( Texture t){


            try {

                t.dispose();

            } catch (Exception e) {

            }
        }



}
