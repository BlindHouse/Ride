package com.infiniteloop.ride;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by jackthebones on 20/06/15.
 */
public class GraphState extends ScreenAdapter {

    public RideGame game;
    public OrthographicCamera camera;

    public static Stage GraphStage;

    private SpaceBackground spacebackground;
    private com.infiniteloop.ride.Graph graph;

    private BitmapFont font;
    public static Label NodoInicio;
    public static Label Nodo1;
    public static Label Nodo2;
    public static Label Nodo3;
    public static Label Nodo4;
    public static Label Nodo5;
    public static Label Nodo6;
    public static Label Nodo7;
    public static Label Nodo8;


    public GraphState(RideGame game){
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, RideGame.WIDHT, RideGame.HEIGHT);

        GraphStage = new Stage(new StretchViewport(RideGame.WIDHT, RideGame.HEIGHT));

        spacebackground = new SpaceBackground();
        spacebackground.setPosition(0,0);

        graph = new com.infiniteloop.ride.Graph();
        graph.setPosition(0,0);

        //DIBUJO DENTRO DE NODOS DEL GRAFO
        NodoInicio = new Label("P", new Label.LabelStyle(font, Color.WHITE));
        NodoInicio.setPosition(141, 226, Align.center);

        GraphStage.addActor(spacebackground);
        GraphStage.addActor(graph);

        GraphStage.addActor(NodoInicio);

        InitInputProcessor();
    }

    private void InitInputProcessor() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                System.out.println(" EJE X : " + screenX + " EJE Y : " + screenY);
                return true;
            }
        });
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
