package com.dsile.core.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.async.ThreadUtils;
import com.dsile.core.NeuroLife;
import com.dsile.core.entities.Creature;
import com.dsile.core.entities.HasBrain;
import com.dsile.core.neural.BrainTrainer;
import com.dsile.core.world.World;

/**
 * Created by DeSile on 07.12.2015.
 */
public class WorldScreen implements Screen {

    private BrainTrainer bt = new BrainTrainer();
    private SpriteBatch batch;
    private World world;
    private Stage stage;
    private MyInputProcessor keysProcessor;
    private OrthographicCamera cam;


    @Override
    public void show() {

        batch = new SpriteBatch();
        world = new World(35, 35, 32);
        stage = new Stage();
        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        cam = new OrthographicCamera(30, 30 * (NeuroLife.HEIGHT / NeuroLife.WIDTH));

        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        keysProcessor = new MyInputProcessor();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(keysProcessor);
        Gdx.input.setInputProcessor(inputMultiplexer);

        stage.getViewport().setCamera(cam);

        world.getEntities().stream().filter(e-> e instanceof Creature).forEach(e -> bt.train((Creature)e));
        world.getEntities().stream().forEach(stage::addActor);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        batch.setProjectionMatrix(cam.combined);

        batch.begin();
        world.drawMap(batch);
        batch.end();
        stage.draw();

        if (keysProcessor.isSpaceClicked())
            stage.act(delta);

        keysProcessor.moveCameraByKeys(cam);



    }

    @Override
    public void resize(int width, int height) {
        cam.viewportWidth = width;
        cam.viewportHeight = height;
        cam.position.set(width/2f, height/2f, 0); //by default camera position on (0,0,0)
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
