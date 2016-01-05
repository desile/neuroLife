package com.dsile.html;

import com.dsile.core.NeuroLife;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class NeuroLifeHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new NeuroLife();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
