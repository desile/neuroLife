package com.dsile.core;

import com.badlogic.gdx.Game;
import com.dsile.core.screens.WorldScreen;

public class NeuroLife extends Game {

    @Override
    public void create() {
        setScreen(new WorldScreen());
    }

}
