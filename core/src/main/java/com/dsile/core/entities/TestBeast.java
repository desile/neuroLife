package com.dsile.core.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.dsile.core.entities.actions.factors.Vision;
import com.dsile.core.entities.actions.movement.DirectionValues;
import com.dsile.core.entities.actions.movement.Movement;
import com.dsile.core.entities.actions.movement.Direction;
import com.dsile.core.neural.Brain;
import com.dsile.core.world.Cell;
import com.dsile.core.world.World;

/**
 * Тестовое существо для отладки функций приложения.
 * Впоследствии будет развита иерархия существ и деления на падальщиков, хищинков и травоядных.
 *
 * Created by DeSile on 08.12.2015.
 */
//TODO: Создать абстрактный класс, чтобы избежать создания анонимного класса в WorldScreen.
public class TestBeast extends Actor implements Entity {


    protected Direction direction = new Direction(DirectionValues.EAST);

    private Texture texture = new Texture("testbeast.png");
    private Brain brain;
    private int size = 32;

    protected Movement movement;
    protected Vision vision;
    private World world;
    private Cell currentCell;


    /**
     * Создание существа (актера) в клеточном мире.
     * @param world мир, в котором создается существо.
     * @param x клетка по x
     * @param y клетка по y
     */
    public TestBeast(World world, int x, int y) {

        this.world = world;
        this.currentCell = world.getCell(x, y).setEntity(this);
        this.brain = new Brain();
        this.movement = new Movement(this);
        this.vision = new Vision(this);

        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                direction.angle += 45;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });

        setOrigin(size / 2, size / 2);
        setRotation();

        movement.setVelocityVector();

        setBounds(currentCell.getDisplayX(), currentCell.getDisplayY(), size, size);

        System.out.println(getX() + "," + getY() + "," + getOriginX() + "," + getOriginY());

    }

    /**
     * Отрисовка существо с учетом текстуры, масштабов и угла поворота.
     * @param batch спрайтбатч-отрисовщик
     * @param parentAlpha ???
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, this.getX(), getY(), this.getOriginX(), this.getOriginY(), this.getWidth(),
                this.getHeight(), this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0,
                texture.getWidth(), texture.getHeight(), false, false);
    }

    /**
     * Поворачивает ось и изображение существа на установленный в direction угол.
     */
    public void setRotation() {
        if (getRotation() != direction.getAngle()) {
            super.setRotation(direction.getAngle());
            movement.setVelocityVector();
        }
    }

    /**
     * Метод-тик выполняющийся непрерывно в ходе работы программы.
     * Содержит в себе всю логику, которую существо способно выполнить за тик.
     * @param delta задержка по времени между выполнением тика
     */
    @Override
    public void act(float delta) {
        //Оценка окружающие обстановки
        vision.accessSituation();
        //Движение.
        movement.perform();
    }



    @Override
    public Brain getBrain() {
        return brain;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell cell) {
        this.currentCell = cell;
    }

    public World getWorld() {
        return world;
    }

    public Direction getDirection(){
        return direction;
    }




}
