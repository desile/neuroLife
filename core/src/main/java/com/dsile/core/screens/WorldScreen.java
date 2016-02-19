package com.dsile.core.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dsile.core.entities.TestBeast;
import com.dsile.core.neural.BrainTrainer;
import com.dsile.core.world.World;

import java.util.Arrays;

/**
 * Created by DeSile on 07.12.2015.
 */
public class WorldScreen implements Screen {

    BrainTrainer bt = new BrainTrainer();
    SpriteBatch batch;
    World world;
    Stage stage;
    MyInputProcessor keysProcessor;


    @Override
    public void show() {
        batch = new SpriteBatch();
        world = new World(10, 10, 32);
        stage = new Stage();

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        keysProcessor = new MyInputProcessor();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(keysProcessor);
        Gdx.input.setInputProcessor(inputMultiplexer);


        world.getEntities().stream().forEach(bt::train);
        world.getEntities().stream().forEach(stage::addActor);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        world.drawMap(batch);
        batch.end();
        stage.draw();
        if (keysProcessor.isSpaceClicked())
            stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
