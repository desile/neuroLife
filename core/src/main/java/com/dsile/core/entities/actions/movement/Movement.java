package com.dsile.core.entities.actions.movement;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dsile.core.entities.TestBeast;
import com.dsile.core.logic.Point;

/**
 * Created by DeSile on 1/6/2016.
 */
public class Movement {

    public static double COORDINATION_ERROR = 1;

    private float speed = 0.5f;
    private Point velocity = new Point();

    private double stepsInCell = 0;

    private TestBeast entity;

    public Movement(TestBeast entity){
        this.entity = entity;
        velocity.calcVelocity(entity.getRotation(), speed);
    }

    public void setVelocityVector(){
        velocity.calcVelocity(entity.getRotation(), speed);
    }

    public void perform(){
        if(isStepToNewCell())
            entity.setRotation();

        entity.setX((float) (entity.getX() + velocity.x));
        entity.setY((float) (entity.getY() + velocity.y));
    }

    protected boolean isStepToNewCell(){
        double stepPixels = (Math.abs(velocity.x) > Math.abs(velocity.y))?Math.abs(velocity.x):Math.abs(velocity.y);

        stepsInCell += stepPixels;
        if(stepsInCell >= entity.getCurrentCell().getSize()) {
            entity.getCurrentCell().removeSelf(entity);
            entity.setCurrentCell(entity.getWorld().getCell(entity.getX() + Movement.COORDINATION_ERROR,entity.getY() + Movement.COORDINATION_ERROR).setEntity(entity));
            System.out.printf("New cell (%d,%d)\n", entity.getCurrentCell().getX(),entity.getCurrentCell().getY());
            stepsInCell = 0;
            return true;
        }
        else return false;
    }



}
