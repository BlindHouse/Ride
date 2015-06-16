package com.infiniteloop.ride;

import com.badlogic.gdx.math.MathUtils;

/**
 * Created by jackthebones on 14/06/15.
 */
public class Utils {

    public static float GetRandomYOpening(){

        return MathUtils.random(RideGame.HEIGHT * 15f, RideGame.HEIGHT * .85f);

    }
}
