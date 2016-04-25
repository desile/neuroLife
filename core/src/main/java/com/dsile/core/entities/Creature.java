package com.dsile.core.entities;

import com.dsile.core.entities.actions.factors.Vision;
import com.dsile.core.entities.actions.movement.Movement;
import com.dsile.core.neural.Brain;
import com.dsile.core.world.World;

/**
 * Created by DeSile on 2/18/2016.
 */
public abstract class Creature extends Entity implements HasBrain {
    protected Brain brain;
    protected Vision vision;
    protected Movement movement;

    protected int hungrines = 0;


    public Creature(World world, int x, int y){
        super(world,x,y);
        this.brain = new Brain();
        this.vision = new Vision(this);
        this.movement = new Movement(this);
    }

    public abstract void learn();

    protected abstract void attack();

    protected abstract void move(double[] signal);

    protected abstract void eat(double[] signal);

    protected abstract void decomposed();

}
