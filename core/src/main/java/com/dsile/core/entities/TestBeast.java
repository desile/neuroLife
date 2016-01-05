package com.dsile.core.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.dsile.core.logic.ActionQuery;
import com.dsile.core.logic.Point;
import com.dsile.core.neural.Brain;
import com.dsile.core.world.Cell;
import com.dsile.core.world.World;

/**
 * Created by DeSile on 08.12.2015.
 */
public class TestBeast extends Actor implements Entity {


    private Brain brain;

    Texture texture = new Texture("testbeast.png");
    private int size = 32;
    protected int rotation = 0; //0,90,180,270

    private float speed = 0.5f;
    private Point velocity = new Point();

    private World world;
    private Cell currentCell;

    private ActionQuery actionQuery = new ActionQuery();

    //В отдельный класс?
    private double stepsInCell = 0;

    public TestBeast(World world, int x, int y){

        this.world = world;
        this.currentCell = world.getCell(x,y).setEntity(this);
        this.brain = new Brain();

        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rotation += 45;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });

        setOrigin(size/2,size/2);
        setRotation(rotation);
        //calc direction
        /*
        if (direction.length() > 0) {
            direction = direction.normalise();
        }
         */
        velocity.calcVelocity(getRotation(), speed);

        setBounds(currentCell.getDisplayX(),currentCell.getDisplayY(),size,size);

        System.out.println(getX() + "," + getY() + "," + getOriginX() + "," + getOriginY());

    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(texture,this.getX(),getY(),this.getOriginX(),this.getOriginY(),this.getWidth(),
                this.getHeight(),this.getScaleX(), this.getScaleY(),this.getRotation(),0,0,
                texture.getWidth(),texture.getHeight(),false,false);
    }


    @Override
    public void setRotation(float a) {
        if (getRotation() != a) {
            super.setRotation(a);
            velocity.calcVelocity(getRotation(), speed);
        }
    }

    @Override
    public void act(float delta){
        //только при движении
        if(isStepToNewCell())
            setRotation(rotation);

        //всегда
        checkVision();

        //BY VECTORS MOVE
        setX((float) (getX() + velocity.x));
        setY((float) (getY() + velocity.y));
    }


    protected boolean isStepToNewCell(){
        double stepPixels = (Math.abs(velocity.x) > Math.abs(velocity.y))?Math.abs(velocity.x):Math.abs(velocity.y);

        stepsInCell += stepPixels;
        if(stepsInCell >= currentCell.getSize()){
            currentCell.removeSelf(this);
            currentCell = world.getCell(getX(),getY()).setEntity(this);
            System.out.printf("New cell (%d,%d)\n",currentCell.getX(),currentCell.getY());
            stepsInCell = 0;
            return true;
        }
        else return false;
    }

    //В отдельный класс
    protected boolean checkVision(){
        Point direction = new Point().calcVelocity(getRotation(),1);
        if (world.getCell(getX() + currentCell.getSize()*direction.x + 2, getY() + currentCell.getSize()*direction.y + 2).getEntityList(this).size() > 0){
            System.out.println("See forward");
            return true;
        }
        if (world.getCell(getX(), getY()).getEntityList(this).size() > 0){
            System.out.println("SEE");
            return true;
        }
        return false;
    }


    @Override
    public Brain getBrain(){
        return brain;
    }




}
