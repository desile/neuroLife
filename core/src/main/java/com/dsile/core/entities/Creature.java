package com.dsile.core.entities;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dsile.core.entities.HasBrain;
import com.dsile.core.entities.actions.factors.Vision;
import com.dsile.core.entities.actions.movement.DirectionValues;
import com.dsile.core.entities.actions.movement.Movement;
import com.dsile.core.neural.Brain;
import com.dsile.core.world.Cell;
import com.dsile.core.world.World;

/**
 * Created by DeSile on 2/18/2016.
 */
public abstract class Creature extends Entity implements HasBrain {
    protected Brain brain;
    protected Vision vision;
    protected Movement movement;


    public Creature(World world, int x, int y){
        super(world,x,y);
        this.brain = new Brain();
        this.vision = new Vision(this);
        this.movement = new Movement(this);
    }
}
