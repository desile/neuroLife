package com.dsile.core.entities.actions.movement;

/**
 * Created by DeSile on 1/6/2016.
 */

/**
 * Возможные направления
 */
public enum DirectionValues {
    EAST(0),NORTH_EAST(45),NORTH(90),NORTH_WEST(135),WEST(180),SOUTH_WEST(225),SOUTH(270),SOUTH_EAST(315);

    private int angle;

    DirectionValues(int angle){
        this.angle = angle;
    }

    public int getAngle(){
        return angle;
    }

    public static DirectionValues random(){
        return values()[(int) (Math.random() * values().length)];
    }
}
