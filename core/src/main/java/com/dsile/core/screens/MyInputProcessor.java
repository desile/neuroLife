package com.dsile.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by DeSile on 2/20/2016.
 */
public class MyInputProcessor implements InputProcessor {

    private Set<Integer> currentKey = new HashSet<>();

    @Override
    public boolean keyDown(int keycode) {
        currentKey.add(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        currentKey.remove(keycode);
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
        if(currentKey.contains(Input.Keys.SPACE)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isSpaceClicked(){
        if(currentKey.contains(Input.Keys.SPACE)){
            currentKey.remove(Input.Keys.SPACE);
            return true;
        } else {
            return false;
        }
    }

    public void moveCameraByKeys(OrthographicCamera cam){
        if (currentKey.contains(Input.Keys.LEFT)) {
            cam.translate(-3, 0, 0);
        }
        if (currentKey.contains(Input.Keys.RIGHT)) {
            cam.translate(3, 0, 0);
        }
        if (currentKey.contains(Input.Keys.DOWN)) {
            cam.translate(0, -3, 0);
        }
        if (currentKey.contains(Input.Keys.UP)) {
            cam.translate(0, 3, 0);
        }
    }



}
