package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.Input;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;


	private Pixmap pixmap;
	private Texture texture;
	private Sprite sprite;




	//Levelbooleans
	boolean geldlevel=true;
	boolean room=false;
	boolean dream=false;

	//Ressourcen
	int geld=0;
	int jobLevel=0;



	ArrayList<minigame> minigames = new ArrayList<minigame>();








	@Override
	public void create () {


		generateMinigames(5);
		batch = new SpriteBatch();

	}

	@Override
	public void render () {
	//	if(sprite.getTexture()!=null){

	//	}


		try {

			sprite.getTexture().dispose();

		} catch (Exception e) {

		}

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();


		for(int i =0;i<minigames.size();i++){

			minigames.get(i).refresh();
			 sprite=minigames.get(i).paintpic();
			if(i<3){
				batch.draw(minigames.get(i).paintpic(), i*260,0);

			}else{
				batch.draw(sprite, i*260-780,260);
			}
		//	sprite.getTexture().dispose();
		}

		batch.end();

		if (Gdx.input.isTouched()) {

			int n=0;


			if(Gdx.input.getX()<260&&Gdx.input.getX()>0){
                n=2;
			}
			if(Gdx.input.getX()<520&&Gdx.input.getX()>260){
				n=1;
			}
			if(Gdx.input.getX()<780&&Gdx.input.getX()>520){
				n=0;
			}



			if(Gdx.input.getY()<260&&Gdx.input.getY()>0){
                n=n+0;
			}
			if(Gdx.input.getY()<520&&Gdx.input.getY()>260){
				n=n+3;
			}

			minigames.get(5-n).fireEvent();
		}
		System.out.println("" +  Gdx.app.getJavaHeap() + "" +
				Gdx.app.getNativeHeap());


	}

	public void generateMinigames(int num){







		for(int i=0;i<=num;i++){

			//if(i!=1){
				linejumper jumper=new linejumper(256,256,i);
				minigames.add(jumper);
			/*}else {
				audioMinigame mini=new audioMinigame();
				minigames.add(mini);
			}*/

		}

	}

	@Override
	public void dispose() {
		for(int i =0;i<minigames.size();i++) {
			minigames.get(i).dispose();
		}

		batch.dispose();
	}



}
