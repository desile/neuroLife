package com.dsile.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.dsile.core.NeuroLife;

public class NeuroLifeDesktop {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = NeuroLife.WIDTH;
		config.height = NeuroLife.HEIGHT;
		new LwjglApplication(new NeuroLife(), config);
	}
}
