package com.dsile.core.neural;

import com.dsile.core.entities.Creature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DeSile on 12/21/2015.
 */
public class BrainTrainer {

    /**
     * Обучение мозга существа
     * @param beast обучаемое существо
     */
    public void train(Creature beast){
        beast.learn();
    }

}
