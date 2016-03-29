package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by philo on 16.03.2016.
 */
public interface minigame {

    public void refresh();
    public Sprite paintpic();
    public void pause();
    public void resume();
    public void setDifficulty(int f);
    public void fireEvent();
    public void dispose();
    public int getPoints();
}
