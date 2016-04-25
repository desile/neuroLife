package com.dsile.core.entities;

import com.badlogic.gdx.graphics.Texture;
import com.dsile.core.neural.Brain;
import com.dsile.core.world.World;

import java.util.Arrays;

/**
 * Тестовое существо для отладки функций приложения.
 * Впоследствии будет развита иерархия существ и деления на падальщиков, хищинков и травоядных.
 *
 * Created by DeSile on 08.12.2015.
 */
//TODO: Создать абстрактный класс, чтобы избежать создания анонимного класса в WorldScreen.
public class Lizard extends Creature {

    protected int maxHp = 50;
    protected int maxEnergy = 20;
    protected int hp = maxHp;
    protected int energy = maxEnergy;

    /**
     * Создание существа (актера) в клеточном мире.
     */
    public Lizard(World world, int x, int y) {
        super(world,x,y);
    }

    @Override
    public void learn() {

        //TODO: Генерировать массивы в отдельном классе

        /*
        *    Input Array: (на 4ой ячейке находимся мы)
        *    |---|---|---|
        *    | 0 | 1 | 2 |
        *    |---|---|---|
        *    | 3 | 4 | 5 |
        *    |---|---|---|
        *    | 6 | 7 | 8 |
        *    |---|---|---|
        *
        *    Значение - событие на ячейке (0 - ничего, 1 - растение)
        *
        *    Output Array: (первые четыре ячейки)
        *
        *         1
        *         ^
        *         |
        *   2 <-- * --> 0
        *         |
        *         v
        *         3
        *
        *
        *  Остальные ячейки: действия (идти, атакатовать, есть)
        *
        *  4 - перемещение
        *  5 - атака
        *  6 - кушать
        *
        **/

        brain.addRowToTrainingSet(new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0}, new double[]{0, 0, 0, 0, 1, 0, 0});


        brain.addRowToTrainingSet(new double[]{0, 0, 0, 0, 0, 0, 0, 0, 1}, new double[]{1, 0, 0, 1, 0, 0, 1});
        brain.addRowToTrainingSet(new double[]{0, 0, 0, 0, 0, 0, 0, 1, 0}, new double[]{0, 0, 0, 1, 0, 0, 1});
        brain.addRowToTrainingSet(new double[]{0, 0, 0, 0, 0, 0, 1, 0, 0}, new double[]{0, 0, 1, 1, 0, 0, 1});
        brain.addRowToTrainingSet(new double[]{0, 0, 0, 0, 0, 1, 0, 0, 0}, new double[]{1, 0, 0, 0, 0, 0, 1});
        brain.addRowToTrainingSet(new double[]{0, 0, 0, 0, 1, 0, 0, 0, 0}, new double[]{0, 0, 0, 0, 0, 0, 1});
        brain.addRowToTrainingSet(new double[]{0, 0, 0, 1, 0, 0, 0, 0, 0}, new double[]{0, 0, 1, 0, 0, 0, 1});
        brain.addRowToTrainingSet(new double[]{0, 0, 1, 0, 0, 0, 0, 0, 0}, new double[]{1, 1, 0, 0, 0, 0, 1});
        brain.addRowToTrainingSet(new double[]{0, 1, 0, 0, 0, 0, 0, 0, 0}, new double[]{0, 1, 0, 0, 0, 0, 1});
        brain.addRowToTrainingSet(new double[]{1, 0, 0, 0, 0, 0, 0, 0, 0}, new double[]{0, 1, 1, 0, 0, 0, 1});

        brain.learn();
        //logger.info("Brain successfuly learned");
    }

    @Override
    protected void attack() {

    }

    @Override
    protected void move(double[] signal) {
        movement.perform(signal);
    }

    @Override
    protected void eat(double[] signal) {
        System.out.println("om-nom-nom: ()" + Arrays.toString(signal));
    }

    @Override
    protected void decomposed() {
        currentCell.removeEntity(this);
        remove();
    }

    @Override
    protected void setTexture(){
        texture = new Texture("lizard.png");
    }

    @Override
    protected void dead() {
        if(alive) {
            alive = false;
            energy = maxEnergy;
            texture = new Texture("lizard.png");//deadlizard.png
        } else { //процесс разложения

        }
    }

    /**
     * Метод-тик выполняющийся непрерывно в ходе работы программы.
     * Содержит в себе всю логику, которую существо способно выполнить за тик.
     * @param delta задержка по времени между выполнением тика
     */
    @Override
    public void act(float delta) {
        if(hp <= 0){
            dead();
            return;
        }
        double[] thoughts = vision.accessSituation();
        if(thoughts[4] > 0.5){
            move(Arrays.copyOfRange(thoughts,0,4));
        }
        else if(thoughts[5] > 0.5){

        }
        else if(thoughts[6] > 0.5){
            eat(Arrays.copyOfRange(thoughts,0,4));
        }

    }



    @Override
    public Brain getBrain() {
        return brain;
    }

}
