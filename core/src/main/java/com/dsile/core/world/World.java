package com.dsile.core.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

/**
 * Created by DeSile on 07.12.2015.
 */
public class World {

    Random randomize = new Random();

    private int cellsX;
    private int cellsY;
    private Cell[][] cells;

    public World(int cellsX, int cellsY, int cellSize){
        this.cellsX = cellsX;
        this.cellsY = cellsY;

        //TODO: Cell fabric?
        cells = new Cell[cellsX][cellsY];
        for(int i = 0; i < cellsX; i++){
            for(int j = 0; j < cellsY; j++){
                cells[i][j] = new GroundCell(cellSize,i,j);
            }
        }
    }

    public void drawMap(SpriteBatch batch){
        for(Cell[] cc : cells){
            for(Cell c : cc){
                c.drawCell(batch);
            }
        }
    }

    //DANGEROUS METHOD
    public GroundCell getRandomGroundCell(){
        int randomX = randomize.nextInt(cellsX);
        int randomY = randomize.nextInt(cellsY);
        while(!(cells[randomX][randomY] instanceof GroundCell)){
            randomX = randomize.nextInt(cellsX);
            randomY = randomize.nextInt(cellsY);
        }
        return (GroundCell) cells[randomX][randomY];
    }


    //TODO: ADD EXCEPTIONS
    public Cell getCell(int x, int y){
        return cells[x][y];
    }

    public Cell getCell(double origX, double origY){
        int x = (int) (origX/cells[0][0].getSize());
        int y = (int) (origY/cells[0][0].getSize());
        return cells[x][y];
    }

    public int getWorldXsize(){
        return cellsX;
    }

    public int getWorldYsize(){
        return cellsY;
    }

}
