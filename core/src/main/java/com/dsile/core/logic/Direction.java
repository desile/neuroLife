package com.dsile.core.logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by DeSile on 09.12.2015.
 */
public enum Direction {
    NORTH(),
    EAST,
    SOUTH,
    WEST;



    private static final List<Direction> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Direction randomDirection()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
