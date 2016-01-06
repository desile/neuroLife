package com.dsile.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dsile.core.entities.TestBeast;
import com.dsile.core.neural.BrainTrainer;
import com.dsile.core.world.World;

/**
 * Created by DeSile on 07.12.2015.
 */
public class WorldScreen implements Screen, InputProcessor {

    BrainTrainer bt = new BrainTrainer();
    SpriteBatch batch;
    World world;
    Stage stage;

    @Override
    public void show() {
        batch = new SpriteBatch();
        world = new World(10, 10, 32);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        TestBeast beast1 = new TestBeast(world, 4, 2);
        TestBeast beast2 = new TestBeast(world, 2, 2) {
            @Override
            public void act(float delta) {

                //BY VECTORS MOVE
                //setX((float) (getX() + velocity.x));
                //setY((float) (getY() + velocity.y));
            }
        };

        bt.train(beast1);
        bt.train(beast2);

        stage.addActor(beast1);
        stage.addActor(beast2);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        world.drawMap(batch);
        batch.end();
        stage.draw();
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
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


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}
