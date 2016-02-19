package com.dsile.core.neural;

import com.dsile.core.entities.HasBrain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DeSile on 12/21/2015.
 */
public class BrainTrainer {

    Logger logger = LoggerFactory.getLogger(BrainTrainer.class);

    //TODO: Обязательно важно составить четкую документацию по описанию нейронной сети
    private void trainRun(Brain brain){
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
        *    Output Array:
        *
        *         1
        *         ^
        *         |
        *   2 <-- * --> 0
        *         |
        *         v
        *         3
        *
        **/
        brain.addRowToTrainingSet(new double[]{1, 0, 0, 0, 0, 0, 0, 0, 0}, new double[]{1, 0, 0, 1});
        brain.addRowToTrainingSet(new double[]{0, 1, 0, 0, 0, 0, 0, 0, 0}, new double[]{0, 0, 0, 1});
        brain.addRowToTrainingSet(new double[]{0, 0, 1, 0, 0, 0, 0, 0, 0}, new double[]{0, 0, 1, 1});
        brain.addRowToTrainingSet(new double[]{0, 0, 0, 1, 0, 0, 0, 0, 0}, new double[]{1, 0, 0, 0});
        brain.addRowToTrainingSet(new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0}, new double[]{0, 0, 0, 0});
        brain.addRowToTrainingSet(new double[]{0, 0, 0, 0, 0, 1, 0, 0, 0}, new double[]{0, 0, 1, 0});
        brain.addRowToTrainingSet(new double[]{0, 0, 0, 0, 0, 0, 1, 0, 0}, new double[]{1, 1, 0, 0});
        brain.addRowToTrainingSet(new double[]{0, 0, 0, 0, 0, 0, 0, 1, 0}, new double[]{0, 1, 0, 0});
        brain.addRowToTrainingSet(new double[]{0, 0, 0, 0, 0, 0, 0, 0, 1}, new double[]{0, 1, 1, 0});

        brain.learn();
        logger.info("Brain successfuly learned");
        /*brain.setInput(new double[]{1, 1, 0, 1, 0, 0, 0, 0, 0});
        brain.think();
        logger.info("Brain successfuly thank");
        double[] brainOutput = brain.getOutput();
        for(int i = 0; i < brainOutput.length; i++){
            System.out.println(brainOutput[i]);
        }*/
    }

    /**
     * Обучение мозга существа
     * @param beast обучаемое существо
     */
    public void train(HasBrain beast){
        Brain brain = beast.getBrain();
        trainRun(brain);
    }

}
