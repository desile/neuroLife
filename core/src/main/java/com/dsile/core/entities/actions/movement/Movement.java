package com.dsile.core.entities.actions.movement;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dsile.core.entities.TestBeast;
import com.dsile.core.logic.Point;

import java.util.Arrays;

/**
 * Created by DeSile on 1/6/2016.
 */
//TODO: Unit-тесты
public class Movement {

    public static double COORDINATION_ERROR = 1;

    private float speed = 0.5f;
    private Point velocity = new Point();

    private double stepsInCell = 32;

    private TestBeast entity;

    public Movement(TestBeast entity){
        this.entity = entity;
        velocity.calcVelocity(entity.getRotation(), speed);
    }

    public void setVelocityVector(){
        velocity.calcVelocity(entity.getRotation(), speed);
    }

    /**
     * Выполнение действия передвижения без учета нейронной сети
     */
    public void perform(){
        if(isStepToNewCell())
            entity.setRotation();

        entity.setX((float) (entity.getX() + velocity.x));
        entity.setY((float) (entity.getY() + velocity.y));
    }

    /**
     * Выполнение действия передвижения с учетом нейронной сети
     */
    public void perform(double[] brainOutput){
        //Определение направления
        for(int i = 0; i < 4; i++){
            brainOutput[i] = Math.abs(brainOutput[i]);
        }
        int[] order = {0,1,2,3};
        //узнаем порядок индексов по возрастанию
        for (int i = 0; i < order.length-1; i++) {
            for (int j = 0; j < order.length-1; j++) {
                if (brainOutput[j] > brainOutput[j+1]) {
                    int b = order[j];
                    order[j] = order[j+1];
                    order[j+1] = b;
                }
            }
        }

        boolean fstDir = false;
        boolean sndDir = false;
        Direction dir;
        System.out.println(Arrays.toString(order));
        if(brainOutput[order[2]] > 0.7){
            fstDir = true;
        }
        if(brainOutput[order[3]] > 0.7){
            sndDir = true;
        }
        if((fstDir | sndDir) == false){
            dir = choseDirection(order[2],order[3]);
        } else {
            if(fstDir){
                dir = choseDirection(order[2],order[3]);
            } else {
                dir = choseDirection(order[3]);
            }
        }
        if(brainOutput[order[2]] > 0.15 || brainOutput[order[3]] > 0.15) {
            entity.rotation.setDirection(dir);
            System.out.println(dir);
            perform();
        }
    }

    private Direction choseDirection(int... dir){ //либо одно, либо два числа
        if(dir.length == 2){
            if((dir[0] == 0 && dir[1] == 1)||(dir[0] == 1 && dir[1] == 0)){
                return Direction.NORTH_EAST;
            }
            if((dir[0] == 1 && dir[1] == 2)||(dir[0] == 2 && dir[1] == 1)){
                return Direction.NORTH_WEST;
            }
            if((dir[0] == 2 && dir[1] == 3)||(dir[0] == 3 && dir[1] == 2)){
                return Direction.SOUTH_WEST;
            }
            if((dir[0] == 3 && dir[1] == 0)||(dir[0] == 0 && dir[1] == 3)){
                return Direction.SOUTH_EAST;
            }
        }
        if(dir.length == 1){
            if(dir[0] == 0){
                return Direction.EAST;
            }
            if(dir[0] == 1){
                return Direction.NORTH;
            }
            if(dir[0] == 2){
                return Direction.WEST;
            }
            if(dir[0] == 3){
                return Direction.SOUTH;
            }
        }
        return Direction.EAST; //в случае нештатной ситуации жебошить вправо
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
