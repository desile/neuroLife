package com.dsile.core.entities.actions.movement;

/**
 * Created by DeSile on 1/6/2016.
 */
public class Rotation {

    public int angle = 0;
    private Direction direction;

    public Rotation(Direction dir){
        setDirection(dir);
    }

    public void setDirection(Direction dir){
        direction = dir;
        switch(dir){
            case EAST:
                angle=0;
                break;
            case NORTH_EAST:
                angle=45;
                break;
            case NORTH:
                angle=90;
                break;
            case NORTH_WEST:
                angle=135;
                break;
            case WEST:
                angle=180;
                break;
            case SOUTH_WEST:
                angle=225;
                break;
            case SOUTH:
                angle=270;
                break;
            case SOUTH_EAST:
                angle=315;
                break;
        }
    }

    public int getAngle(){
        return angle;
    }

    public Direction getDirection(){
        return direction;
    }

}
