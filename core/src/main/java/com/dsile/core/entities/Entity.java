package com.dsile.core.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dsile.core.entities.actions.movement.DirectionValues;
import com.dsile.core.world.Cell;
import com.dsile.core.world.World;

/**
 * Created by DeSile on 2/21/2016.
 */
public abstract class Entity extends Actor {
    protected static int SIZE = 32;
    protected DirectionValues direction;
    protected Cell currentCell;
    protected World world;
    protected Texture texture;


    protected boolean alive = true;
    protected int maxHP = 1;
    protected int maxEnergy = 1;
    protected int HP = maxHP;
    protected int energy = maxEnergy;

    public Entity(World world, int x, int y){
        this.world = world;
        this.currentCell = world.getCell(x, y).setEntity(this);
        this.setDirection(DirectionValues.EAST);
        this.setTexture();

        setOrigin(SIZE / 2, SIZE / 2);
        setBounds(currentCell.getDisplayX(), currentCell.getDisplayY(), SIZE, SIZE);

        System.out.println(getX() + "," + getY() + "," + getOriginX() + "," + getOriginY());

    }

    /**
     * Отрисовка существо с учетом текстуры, масштабов и угла поворота.
     * @param batch спрайтбатч-отрисовщик
     * @param parentAlpha ???
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, currentCell.getDisplayX(), currentCell.getDisplayY(), this.getOriginX(), this.getOriginY(), this.getWidth(),
                this.getHeight(), this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0,
                texture.getWidth(), texture.getHeight(), false, false);
    }

    protected abstract void setTexture();

    protected abstract void dead();



    public World getWorld(){
        return world;
    }

    public int x(){
        return currentCell.getX();
    }

    public int y(){
        return currentCell.getY();
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(int x, int y){
        currentCell.removeEntity(this);
        currentCell = world.getCell(x,y);
        currentCell.setEntity(this);
    }

    public DirectionValues getDirection(){
        return direction;
    }

    public void setDirection(DirectionValues val){
        direction = val;
        setRotation(direction.getAngle());
    }
}
