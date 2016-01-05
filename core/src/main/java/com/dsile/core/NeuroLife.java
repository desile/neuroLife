package com.dsile.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.dsile.core.screens.WorldScreen;

public class NeuroLife extends Game {
	SpriteBatch batch;
	Texture img;

	@Override
	public void create () {
		//batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		setScreen(new WorldScreen());
	}

    /*
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	*/
}
