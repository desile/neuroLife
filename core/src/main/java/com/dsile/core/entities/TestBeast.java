package com.dsile.core.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.dsile.core.entities.actions.factors.Vision;
import com.dsile.core.entities.actions.movement.DirectionValues;
import com.dsile.core.entities.actions.movement.Movement;
import com.dsile.core.entities.actions.movement.Direction;
import com.dsile.core.neural.Brain;
import com.dsile.core.world.Cell;
import com.dsile.core.world.World;

/**
 * Тестовое существо для отладки функций приложения.
 * Впоследствии будет развита иерархия существ и деления на падальщиков, хищинков и травоядных.
 *
 * Created by DeSile on 08.12.2015.
 */
public class TestBeast extends Actor implements Entity {


    public Direction direction = new Direction(DirectionValues.EAST);

    private Texture texture = new Texture("testbeast.png");
    private Brain brain;
    private int size = 32;

    protected Movement movement = new Movement(this);
    protected Vision vision = new Vision(this);
    private World world;
    private Cell currentCell;


    public TestBeast(World world, int x, int y) {

        this.world = world;
        this.currentCell = world.getCell(x, y).setEntity(this);
        this.brain = new Brain();

        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                direction.angle += 45;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });

        setOrigin(size / 2, size / 2);
        setRotation();

        movement.setVelocityVector();

        setBounds(currentCell.getDisplayX(), currentCell.getDisplayY(), size, size);

        System.out.println(getX() + "," + getY() + "," + getOriginX() + "," + getOriginY());

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, this.getX(), getY(), this.getOriginX(), this.getOriginY(), this.getWidth(),
                this.getHeight(), this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0,
                texture.getWidth(), texture.getHeight(), false, false);
    }


    public void setRotation() {
        if (getRotation() != direction.getAngle()) {
            super.setRotation(direction.getAngle());
            movement.setVelocityVector();
        }
    }

    @Override
    public void act(float delta) {
        //всегда
        double[] thinks = vision.accessSituation();
        //System.out.println(Arrays.toString(thinks));
        //vision.getEnvironment();

        //только при движении
        movement.perform();
    }


    @Override
    public Brain getBrain() {
        return brain;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell cell) {
        this.currentCell = cell;
    }

    public World getWorld() {
        return world;
    }

    public Direction getDirection(){
        return direction;
    }




}
