package com.dsile.core;

import com.badlogic.gdx.Game;
import com.dsile.core.screens.WorldScreen;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

public class NeuroLife extends Game {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    @Override
    public void create() {
        setScreen(new WorldScreen());
    }

}
