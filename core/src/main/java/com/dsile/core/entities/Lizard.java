package com.dsile.core.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.dsile.core.neural.Brain;
import com.dsile.core.world.World;

/**
 * Тестовое существо для отладки функций приложения.
 * Впоследствии будет развита иерархия существ и деления на падальщиков, хищинков и травоядных.
 *
 * Created by DeSile on 08.12.2015.
 */
//TODO: Создать абстрактный класс, чтобы избежать создания анонимного класса в WorldScreen.
public class Lizard extends Creature {

    private Texture texture = new Texture("testbeast.png");

    /**
     * Создание существа (актера) в клеточном мире.
     */
    public Lizard(World world, int x, int y) {
        super(world,x,y);

        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });

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

    /**
     * Метод-тик выполняющийся непрерывно в ходе работы программы.
     * Содержит в себе всю логику, которую существо способно выполнить за тик.
     * @param delta задержка по времени между выполнением тика
     */
    @Override
    public void act(float delta) {
        //Оценка окружающие обстановки
        double[] thoughts = vision.accessSituation();
        //Движение.
        movement.perform(thoughts);
    }



    @Override
    public Brain getBrain() {
        return brain;
    }

}
