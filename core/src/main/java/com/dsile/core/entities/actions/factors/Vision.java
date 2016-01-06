package com.dsile.core.entities.actions.factors;

import com.dsile.core.entities.TestBeast;
import com.dsile.core.neural.Brain;

/**
 * Created by DeSile on 1/6/2016.
 */
public class Vision {

    TestBeast entity;

    public Vision(TestBeast entity){
        this.entity = entity;
    }

    public int[] getEnvironment(){
        int[] env = new int[9];
        for(int e : env) e = 0;
        int x = entity.getCurrentCell().getX();
        int y = entity.getCurrentCell().getY();

        if (entity.getWorld().getCell(x-1,y+1).getEntityList(entity).size() > 0){
            env[0] = 1;
        }
        if (entity.getWorld().getCell(x,y+1).getEntityList(entity).size() > 0){
            env[1] = 1;
        }
        if (entity.getWorld().getCell(x+1,y+1).getEntityList(entity).size() > 0){
            env[2] = 1;
        }
        if (entity.getWorld().getCell(x-1,y).getEntityList(entity).size() > 0){
            env[3] = 1;
        }
        if (entity.getWorld().getCell(x,y).getEntityList(entity).size() > 0){
            env[4] = 1;
        }
        if (entity.getWorld().getCell(x+1,y).getEntityList(entity).size() > 0){
            env[5] = 1;
        }
        if (entity.getWorld().getCell(x-1,y-1).getEntityList(entity).size() > 0){
            env[6] = 1;
        }
        if (entity.getWorld().getCell(x,y-1).getEntityList(entity).size() > 0){
            env[7] = 1;
        }
        if (entity.getWorld().getCell(x+1,y-1).getEntityList(entity).size() > 0){
            env[8] = 1;
        }

        return env;
    }

    public double[] accessSituation(){
        int[] environment = getEnvironment();
        Brain brain = entity.getBrain();
        brain.setInput(environment);
        brain.think();
        double[] thinks = brain.getOutput();
        return thinks;
    }
}
