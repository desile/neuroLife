package com.dsile.core.entities;

import com.badlogic.gdx.graphics.Texture;
import com.dsile.core.world.World;

/**
 * Created by DeSile on 2/21/2016.
 */
public class Herb extends Entity {

    public Herb(World world, int x, int y) {
        super(world, x, y);
    }

    @Override
    protected void setTexture() {
        texture = new Texture("herb.png");
    }

    @Override
    protected void dead() {
        remove();
        currentCell.removeEntity(this);
    }

}
