package com.dsile.core.entities.actions.movement;

/**
 * Класс интерпретирующий направления в значения угла поворота существа.
 *
 * Created by DeSile on 1/6/2016.
 */
public class Rotation {

    /**
     * Угол поворота
     */
    public int angle = 0; //TODO: в private
    private Direction direction;

    public Rotation(Direction dir){
        setDirection(dir);
    }

    /**
     * Установка угла поворота в зависимости от переданного направления
     * @param dir направление.
     */
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

    /**
     * Получение значения угла.
     */
    public int getAngle(){
        return angle;
    }

    /**
     * Получение направления.
     */
    public Direction getDirection(){
        return direction;
    }

}
