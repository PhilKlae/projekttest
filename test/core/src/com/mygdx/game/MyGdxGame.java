package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.DelayedRemovalArray;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {


	//test
	float zoom=0.7f;

	//Fractal test
	Fractal fractal;
	SpriteBatch batch;



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
     BitmapFont font;

	//timer
	double offset=0;

    //minigame values
	Sound themeMinigames;
	int countdown=10;
	double count=0;
	//DelayedRemovalArray<Block> t =new DelayedRemovalArray<Block>();

	//ASSETS
	AssetManager assets;


	ArrayList<minigame> minigames = new ArrayList<minigame>();








	@Override
	public void create () {

		font = new BitmapFont();
		font.setColor(Color.RED);
		generateMinigames(5);
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("pics/minigamepics/background.jpg"));
		fractal=new Fractal(700,700);
		themeMinigames= Gdx.audio.newSound(Gdx.files.internal("sounds/minigamesounds/Backgroundtrack.mp3"));
		themeMinigames.play();
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

		if(geldlevel) {
			count+=Gdx.graphics.getDeltaTime();
			batch.draw(texture, 0, 0);

			for (int i = 0; i < minigames.size(); i++) {

				minigames.get(i).refresh();
				geld += minigames.get(i).getPoints();
				sprite = minigames.get(i).paintpic();
				if (i < 3) {
					batch.draw(sprite, i * 260, (int)(0+offset));

				} else {
					batch.draw(sprite, i * 260 - 780, (int)(260+offset));
				}
				//sprite.getTexture().dispose();
			}
			font.draw(batch, "$ : " + geld, 0, Gdx.graphics.getHeight());


		/*	if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {


				fractal.setZoomx(-fractal.size / 10000);
			}

			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {


				fractal.setZoomx(fractal.size / 10000);
			}

			if (Gdx.input.isKeyPressed(Input.Keys.UP)) {


				fractal.setZoomy(fractal.size / 1000000);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {


				fractal.setZoomy(-fractal.size);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {

				zoom = 0.2f;
				fractal.setSize(1);
			}

			if (Gdx.input.isKeyPressed(Input.Keys.B)) {


				zoom += 0.02;


			} else {

				//	texture=new Texture(fractal.drawFractal());

			}*/


			//s.setRegion(0f,0f,2000f,2000f);
			//	TextureRegion r=new TextureRegion(texture,2500,2500,1000,1000);
			//	Sprite s=new Sprite(r.getTexture());
			//	s.setScale(zoom);
			//s.setPosition(0-(zoom*s.getWidth())/2,0-(zoom*s.getHeight())/2);
			//Sprite s=new Sprite(texture);
			//s.setScale(zoom);
			//	s.setCenter(0,0);
			//batch.draw(s,0,0);
			//	s.draw(batch);
			//  s.draw(batch);

			if(count>=countdown){
				for (int i = 0; i < minigames.size(); i++) {


					minigames.get(i).pause();



				}
				offset+=Gdx.graphics.getDeltaTime()*(Gdx.graphics.getHeight()/2);
				//themeMinigames.setVolume();
				//übergang zum zimmer level TODO



				/*	for (int i = 0; i < minigames.size(); i++) {


				minigames.get(0).dispose();
				minigames.remove(0);


			}
				geldlevel=false;*/
			}
		}







		batch.end();

				if (Gdx.input.isTouched()) {

					int n = 0;

					//Gdx.input.isKeyPressed();
					if (Gdx.input.getX() < 260 && Gdx.input.getX() > 0) {
						n = 2;
					}
					if (Gdx.input.getX() < 520 && Gdx.input.getX() > 260) {
						n = 1;
					}
					if (Gdx.input.getX() < 780 && Gdx.input.getX() > 520) {
						n = 0;
					}


					if (Gdx.input.getY() < 260 && Gdx.input.getY() > 0) {
						n = n + 0;
					}
					if (Gdx.input.getY() < 520 && Gdx.input.getY() > 260) {
						n = n + 3;
					}

					minigames.get(5 - n).fireEvent();
				}

				//System.out.println("" +  Gdx.app.getJavaHeap() + "         " +Gdx.app.getNativeHeap());
				//
				//System.out.println(geld);
			}


	public void generateMinigames(int num){







		for(int i=0;i<=num;i++){

			if(i!=1){
				linejumper jumper=new linejumper(256,256,i);
				minigames.add(jumper);
		}else {
				audioMinigame mini=new audioMinigame();
				minigames.add(mini);
			}

		}

	}

	@Override
	public void dispose() {
		for(int i =0;i<minigames.size();i++) {
			minigames.get(i).dispose();
		}
		texture.dispose();
		batch.dispose();
		assets.dispose();
	}






	public int checkC(double reC,double imC) {
		double reZ=0,imZ=0,reZ_minus1=0,imZ_minus1=0;
		int i =0;
		for (i=0;i<30;i++) {
			imZ=2*reZ_minus1*imZ_minus1+imC;
			reZ=reZ_minus1*reZ_minus1-imZ_minus1*imZ_minus1+reC;
			if (reZ*reZ+imZ*imZ>4) return i;
			reZ_minus1=reZ;
			imZ_minus1=imZ;
		}
		return i;
	}



}
