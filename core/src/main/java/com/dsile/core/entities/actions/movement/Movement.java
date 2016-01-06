package com.dsile.core.entities.actions.movement;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dsile.core.entities.TestBeast;
import com.dsile.core.logic.Point;

import java.util.Arrays;

/**
 * Класс, отвечающий за перемещение.
 *
 * Created by DeSile on 1/6/2016.
 */
//TODO: Unit-тесты
public class Movement {

    /**
     * Возможная погрешность при расчете координат
     */
    public static double COORDINATION_ERROR = 1;

    /**
     * Верхняя и нижния границы для определения направлений по значениям из нейронной сети
     */
    private static double UPPER_LIMIT_OF_BRAIN_OUTPUT_DIRECTION = 0.7;
    private static double LOWER_LIMIT_OF_BRAIN_OUTPUT_DIRECTION = 0.15;

    /**
     * Скорость перемещения
     */
    private float speed = 0.5f;

    /**
     * Вектор направления движения
     */
    private Point velocity = new Point();

    /**
     * Текущее количество пройденных пикселей в текущей клетке.
     * Используется для определения момента, когда существо переходит из одной клетки в другую.
     */
    private double stepsInCell;

    /**
     * Перемещаемое существо.
     */
    private TestBeast entity;

    /**
     * Устанавливает текущее существо, устанавливает вектор скорости и начальное количество шагов в клетке.
     * @param entity перемещаемое существо.
     */
    public Movement(TestBeast entity){
        this.entity = entity;
        stepsInCell = entity.getCurrentCell().getSize();
        setVelocityVector();
    }

    /**
     * Устанавливает вектор скорости по данным угла направления существа и скорости.
     */
    public void setVelocityVector(){
        velocity.calcVelocity(entity.getRotation(), speed);
    }

    /**
     * Выполнение шага в пространтсве.
     * Проверяет не пора ли сменить текущую клетку, а следовательно и угол поворота.
     * Устанавливает для существа новые координаты в непрерывном пространстве
     */
    public void perform(){
        if(isStepToNewCell())
            entity.setRotation();

        entity.setX((float) (entity.getX() + velocity.x));
        entity.setY((float) (entity.getY() + velocity.y));
    }

    /**
     * Выполнение действия передвижения с учетом нейронной сети.
     * TODO: Логику следует как-то упростить
     */
    public void perform(double[] brainOutput){
        //Берем модули от элементов массива
        for(int i = 0; i < 4; i++){
            brainOutput[i] = Math.abs(brainOutput[i]);
        }
        //Массив означающий текущий порядок элементов массива brainOutput
        int[] order = {0,1,2,3};
        //Делаем сортировку массива order по значениям массива brainOutput
        //Таким образом мы получаем отсортированный массив индексов массива brainOutput
        for (int i = 0; i < order.length-1; i++) {
            for (int j = 0; j < order.length-1; j++) {
                if (brainOutput[j] > brainOutput[j+1]) {
                    int b = order[j];
                    order[j] = order[j+1];
                    order[j+1] = b;
                }
            }
        }

        //Как известно, нейронная сеть может вернуть нам в качестве результата сразу два возможных направления.
        boolean fstDir = false; //Наличие первого направления
        boolean sndDir = false; //Наличие второго направления
        Direction dir; //Результирующее направление
        System.out.println(Arrays.toString(order));
        //order[2] и order[3] - номера индексов самых больших значений массива brainOutput
        //Проверяем можно ли считать второе по величине значение направлением.
        if(brainOutput[order[2]] > UPPER_LIMIT_OF_BRAIN_OUTPUT_DIRECTION){
            sndDir = true;
        }
        //Проверяем можно ли считать первое по величине значение направлением.
        if(brainOutput[order[3]] > UPPER_LIMIT_OF_BRAIN_OUTPUT_DIRECTION){
            fstDir = true;
        }
        //Если направления не удовлетворили критериям, то все-равно берем результирующее направление из двух самых самых больших значений.
        if((fstDir | sndDir) == false){
            dir = choseDirection(order[2],order[3]);
        } else {
            //Если вторичное направление удовлетворило критериям, то первое и подавно (сортировка по возрастанию)
            if(sndDir){
                //Так что берем результирующее
                dir = choseDirection(order[2],order[3]);
            } else {
                //Иначе удовлетворило только первичное, значит двигаемся строго по нему.
                dir = choseDirection(order[3]);
            }
        }
        //Устанавливаем получившееся направления существу только если оно проходит нижний порог значений направлений.
        if(brainOutput[order[2]] > LOWER_LIMIT_OF_BRAIN_OUTPUT_DIRECTION|| brainOutput[order[3]] > LOWER_LIMIT_OF_BRAIN_OUTPUT_DIRECTION) {
            entity.rotation.setDirection(dir);
            System.out.println(dir);
            perform(); //и запускаем шаг
        }
    }

    /**
     * Конвертация численных значений в конкретное направление.
     * @param dir массив из одного или двух элементов со значениями от 0 до 3, которые интерпретируются в направления.
     * @return Конкретное направление.
     */
    private Direction choseDirection(int... dir){ //либо одно, либо два числа
        if(dir.length == 2){
            if((dir[0] == 0 && dir[1] == 1)||(dir[0] == 1 && dir[1] == 0)){
                return Direction.NORTH_EAST;
            }
            if((dir[0] == 1 && dir[1] == 2)||(dir[0] == 2 && dir[1] == 1)){
                return Direction.NORTH_WEST;
            }
            if((dir[0] == 2 && dir[1] == 3)||(dir[0] == 3 && dir[1] == 2)){
                return Direction.SOUTH_WEST;
            }
            if((dir[0] == 3 && dir[1] == 0)||(dir[0] == 0 && dir[1] == 3)){
                return Direction.SOUTH_EAST;
            }
        }
        if(dir.length == 1){
            if(dir[0] == 0){
                return Direction.EAST;
            }
            if(dir[0] == 1){
                return Direction.NORTH;
            }
            if(dir[0] == 2){
                return Direction.WEST;
            }
            if(dir[0] == 3){
                return Direction.SOUTH;
            }
        }
        return Direction.EAST; //в случае нештатной ситуации жебошить вправо
    }

    /**
     * Проверка перехода в новую клетку.
     * @return true - если переход совершился, false - если нет.
     */
    protected boolean isStepToNewCell(){
        double stepPixels = (Math.abs(velocity.x) > Math.abs(velocity.y))?Math.abs(velocity.x):Math.abs(velocity.y);

        stepsInCell += stepPixels;
        if(stepsInCell >= entity.getCurrentCell().getSize()) {
            entity.getCurrentCell().removeSelf(entity);
            entity.setCurrentCell(entity.getWorld().getCell(entity.getX() + Movement.COORDINATION_ERROR,entity.getY() + Movement.COORDINATION_ERROR).setEntity(entity));
            System.out.printf("New cell (%d,%d)\n", entity.getCurrentCell().getX(),entity.getCurrentCell().getY());
            stepsInCell = 0;
            return true;
        }
        else return false;
    }



}
