package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

/**
 * Created by philo on 29.03.2016.
 */
public interface stuff {

    public int getSizex();
    public int getSizey();
    public boolean placed();
    public Vector2 getPos();
    public int getPrize();
    public Texture getTex();
    public String getShape();
    public boolean stapelbar();



}
