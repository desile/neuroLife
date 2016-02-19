package com.dsile.core.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by DeSile on 2/20/2016.
 */
public class MyInputProcessor implements InputProcessor {

    private int currentKey = -1;

    @Override
    public boolean keyDown(int keycode) {
        currentKey = keycode;
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        currentKey = -1;
        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }

    public boolean isSpacePressed(){
        if(currentKey == Input.Keys.SPACE){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isSpaceClicked(){
        if(currentKey == Input.Keys.SPACE){
            currentKey = -1;
            return true;
        } else {
            return false;
        }
    }


}
