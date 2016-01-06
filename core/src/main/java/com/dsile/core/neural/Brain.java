package com.dsile.core.neural;


import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.transfer.Linear;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.NeuronProperties;
import org.neuroph.util.TransferFunctionType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Никита on 15.12.2015.
 */
public class Brain {
    private NeuralNetwork neuralNetwork;
    private DataSet trainingSet;

    public Brain(){
        List<Integer> neuronsInLayers = new ArrayList<>();
        neuronsInLayers.add(9);
        neuronsInLayers.add(9);
        neuronsInLayers.add(9);
        neuronsInLayers.add(4);
        neuralNetwork = new MultiLayerPerceptron(neuronsInLayers, new NeuronProperties(TransferFunctionType.SIGMOID, true));

        neuralNetwork.setLearningRule(new BackPropagation()); //раскомментирвал, т.к. должен быть метод обратного распространения ошибки

        Neuron[] OutputLayerNeurons = neuralNetwork.getOutputNeurons();
        for(int i = 0; i < OutputLayerNeurons.length; i++) {
            OutputLayerNeurons[i].setTransferFunction(new Linear());
        }

        trainingSet = new DataSet(9, 4);
    }
    public void clearTrainingSet(){
        trainingSet.clear();
    }
    public void addRowToTrainingSet(double[] inputVector, double[] outputVector){
        trainingSet.addRow (new DataSetRow(inputVector, outputVector));
    }
    public void learn(){
        System.out.println("going to learn...");
        neuralNetwork.learn(trainingSet);
        System.out.println("learning done");
    }
    public void setInput(double[] inputVector){
        neuralNetwork.setInput(inputVector);
    }
    public void setInput(int[] inputVector){
        double[] doubleVec = new double[inputVector.length];
        for(int i = 0; i < doubleVec.length; i++){
            doubleVec[i] = inputVector[i];
        }
        neuralNetwork.setInput(doubleVec);
    }
    public void think(){
        neuralNetwork.calculate();
    }
    public double[] getOutput(){
        return neuralNetwork.getOutput();
    }
    public void saveNNToFile(String fileName){
        neuralNetwork.save(fileName + ".nnet");
    }
}
