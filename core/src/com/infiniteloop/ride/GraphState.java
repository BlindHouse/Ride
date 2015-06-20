package com.infiniteloop.ride;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by jackthebones on 20/06/15.
 */
public class GraphState extends ScreenAdapter {

    public RideGame game;
    public OrthographicCamera camera;

    public static Stage GraphStage;

    private SpaceBackground spacebackground;

    public GraphState(RideGame game){
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, RideGame.WIDHT, RideGame.HEIGHT);

        GraphStage = new Stage(new StretchViewport(RideGame.WIDHT, RideGame.HEIGHT));

        spacebackground = new SpaceBackground();
        spacebackground.setPosition(0,0);

        GraphStage.addActor(spacebackground);
    }

    @Override
    //RENDER DE PANTALLA --- LOOP DE RENDERIZADO POR FPS.
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        GraphStage.act();
        //Revisa si hay colisiones por cada vez que se refresca la pantalla.
        GraphStage.draw();

    }

}
