package com.dsile.core.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dsile.core.entities.Creature;
import com.dsile.core.entities.Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by DeSile on 07.12.2015.
 */
public abstract class Cell{

    protected int size;
    protected Texture texture;
    protected Set<Entity> entities;

    //Позиция ячейки в массиве мира.
    private int x;
    private int y;

    public Cell(int size, int x, int y){
        this.size = size;
        this.x = x;
        this.y = y;

        this.entities = new HashSet<>();
    }

    public Set<Entity> getEntityList(){
        return entities;
    }

    public Set<Entity> getEntityList(Entity self){
        Set<Entity> entitiesWithoutSelf = new HashSet<>(entities);
        entitiesWithoutSelf.remove(self);
        return  entitiesWithoutSelf;
    }

    public Cell setEntity(Entity e){
        entities.add(e);
        return this;
    }

    public Cell removeEntity(Entity e){
        entities.remove(e);
        return this;
    }

    public Texture getTexture(){
        return texture;
    }

    public void drawCell(SpriteBatch batch){
        batch.draw(texture, x*size, y*size, size, size);
    }

    public int getSize(){
        return size;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getDisplayX(){
        return size*x;
    }

    public int getDisplayY(){
        return size*y;
    }

}
