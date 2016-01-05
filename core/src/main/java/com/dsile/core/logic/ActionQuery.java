package com.dsile.core.logic;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by DeSile on 09.12.2015.
 */
public class ActionQuery {

    Queue<Action> actions;
    Direction dir;

    public ActionQuery(){
        actions = new LinkedBlockingQueue<Action>();
        actions.add(Action.DO_NOTHING);
        dir = Direction.SOUTH;
    }

    public void addAction(Action a){
        actions.add(a);
    }

    public Action popAction(){
        return actions.remove();
    }

    public Action currentAction(){
        return actions.element();
    }

    public void randomAction(){
        actions.add(Action.randomAction());
    }

    public Direction getDirection(){
        return dir;
    }

    public void setDirection(Direction dir){
        this.dir = dir;
    }

    public void randomDirection(Direction dir){
        dir = Direction.randomDirection();
    }



}
