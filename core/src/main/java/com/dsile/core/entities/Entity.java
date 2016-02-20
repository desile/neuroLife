package com.dsile.core.entities;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dsile.core.entities.actions.movement.DirectionValues;
import com.dsile.core.world.Cell;
import com.dsile.core.world.World;

/**
 * Created by DeSile on 2/21/2016.
 */
public abstract class Entity extends Actor {
    protected static int SIZE = 32;
    protected DirectionValues direction;
    protected Cell currentCell;
    protected World world;

    public Entity(World world, int x, int y){
        this.world = world;
        this.currentCell = world.getCell(x, y).setEntity(this);

        this.setDirection(DirectionValues.EAST);

    }


    public World getWorld(){
        return world;
    }

    public int x(){
        return currentCell.getX();
    }

    public int y(){
        return currentCell.getY();
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(int x, int y){
        currentCell.removeEntity(this);
        currentCell = world.getCell(x,y);
        currentCell.setEntity(this);
    }

    public DirectionValues getDirection(){
        return direction;
    }

    public void setDirection(DirectionValues val){
        direction = val;
        setRotation(direction.getAngle());
    }
}
