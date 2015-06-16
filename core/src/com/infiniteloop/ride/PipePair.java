package com.infiniteloop.ride;

import com.badlogic.gdx.utils.Align;

/**
 * Created by jackthebones on 14/06/15.
 */
public class PipePair {

    public static final float STARTING_X_POSITION = 400f;
    public static final float GAP_SIZE = 130f;


    private Pipe TopPipe;
    private Pipe BottomPipe;

    public PipePair(Pipe topPipe, Pipe bottomPipe) {
        TopPipe = topPipe;
        BottomPipe = bottomPipe;
    }

    public void init(){


        float y = Utils.GetRandomYOpening();
        TopPipe.setPosition(0, y - GAP_SIZE/2, Align.topLeft);
        BottomPipe.setPosition(0, y + GAP_SIZE/2, Align.bottomLeft);
    }

    public Pipe getTopPipe() {
        return TopPipe;
    }

    public void setTopPipe(Pipe topPipe) {
        TopPipe = topPipe;
    }

    public Pipe getBottomPipe() {
        return BottomPipe;
    }

    public void setBottomPipe(Pipe bottomPipe) {
        BottomPipe = bottomPipe;
    }
}
