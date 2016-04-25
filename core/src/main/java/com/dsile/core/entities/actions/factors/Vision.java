package com.dsile.core.entities.actions.factors;

import com.dsile.core.entities.Creature;
import com.dsile.core.neural.Brain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Класс, отслеживающий сущностей в окружении.
 *
 * Created by DeSile on 1/6/2016.
 */
public class Vision {

    private Creature creature;

    public Vision(Creature creature){
        this.creature = creature;
    }

    /**
     * "Сканирование" окружающего пространства вокруг существа.
     * Если что-то обнаружено в ходе сканирования, данные об этом заносятся в результат.
     * @return массив результатов.
     */
    public int[] getEnvironment(){
        //Создаем массив наполненный нулевыми элементами
        int[] env = new int[9];
        for(int e : env) e = 0;
        int x = creature.x();
        int y = creature.y();

        //Отмечаем в соответствующих ячейках наличие существ
        if (creature.getWorld().getCell(x-1,y+1).isHerb()){
            env[0] = 1;
        }
        if (creature.getWorld().getCell(x,y+1).isHerb()){
            env[1] = 1;
        }
        if (creature.getWorld().getCell(x+1,y+1).isHerb()){
            env[2] = 1;
        }
        if (creature.getWorld().getCell(x-1,y).isHerb()){
            env[3] = 1;
        }
        if (creature.getWorld().getCell(x,y).isHerb()){
            env[4] = 1;
        }
        if (creature.getWorld().getCell(x+1,y).isHerb()){
            env[5] = 1;
        }
        if (creature.getWorld().getCell(x-1,y-1).isHerb()){
            env[6] = 1;
        }
        if (creature.getWorld().getCell(x,y-1).isHerb()){
            env[7] = 1;
        }
        if (creature.getWorld().getCell(x+1,y-1).isHerb()){
            env[8] = 1;
        }

        return env;
    }

    /**
     * Обработка окружения нейронной сетью (мозгом существа)
     * @return результат работы нейронной сети
     */
    public double[] accessSituation(){
        int[] environment = getEnvironment();
        Brain brain = creature.getBrain();
        brain.setInput(environment);
        brain.think();
        double[] thinks = brain.getOutput();
        System.out.println("thinks: " + Arrays.toString(thinks));
        return thinks;
    }
}
