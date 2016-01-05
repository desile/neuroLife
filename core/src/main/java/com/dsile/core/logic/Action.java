package com.dsile.core.logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by DeSile on 09.12.2015.
 */
public enum Action {
    DO_NOTHING,

    MOVE_FORWARD;

    boolean done = false;

    Action(){
        done=false;
    }

    private static final List<Action> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Action randomAction()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
