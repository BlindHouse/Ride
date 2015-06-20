package com.infiniteloop.ride;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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

        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        NodoInicio = new Label("P", new Label.LabelStyle(font, Color.WHITE));
        NodoInicio.setPosition(141, 226 + 30, Align.center);

        Nodo1 = new Label("" + 4, new Label.LabelStyle(font, Color.WHITE));
        Nodo1.setPosition(150, RideGame.HEIGHT - 54, Align.center);

        Nodo2 = new Label("" + 4, new Label.LabelStyle(font, Color.WHITE));
        Nodo2.setPosition(268, RideGame.HEIGHT - 105, Align.center);

        Nodo3 = new Label("" + 4, new Label.LabelStyle(font, Color.WHITE));
        Nodo3.setPosition(277, RideGame.HEIGHT - 231, Align.center);

        Nodo4 = new Label("" + 4, new Label.LabelStyle(font, Color.WHITE));
        Nodo4.setPosition(248, RideGame.HEIGHT - 336, Align.center);

        Nodo5 = new Label("" + 4, new Label.LabelStyle(font, Color.WHITE));
        Nodo5.setPosition(148, RideGame.HEIGHT - 389, Align.center);

        Nodo6 = new Label("" + 4, new Label.LabelStyle(font, Color.WHITE));
        Nodo6.setPosition(52, RideGame.HEIGHT - 346, Align.center);

        Nodo7 = new Label("" + 4, new Label.LabelStyle(font, Color.WHITE));
        Nodo7.setPosition(19, RideGame.HEIGHT - 229, Align.center);

        Nodo8 = new Label("" + 4, new Label.LabelStyle(font, Color.WHITE));
        Nodo8.setPosition(50, RideGame.HEIGHT - 105, Align.center);



        /////////////////////////////////////////////////////////////////////////////////
        GraphStage.addActor(spacebackground);
        GraphStage.addActor(graph);

        GraphStage.addActor(NodoInicio);
        GraphStage.addActor(Nodo1);
        GraphStage.addActor(Nodo2);
        GraphStage.addActor(Nodo3);
        GraphStage.addActor(Nodo4);
        GraphStage.addActor(Nodo5);
        GraphStage.addActor(Nodo6);
        GraphStage.addActor(Nodo7);
        GraphStage.addActor(Nodo8);

        InitInputProcessor();
    }

    private void InitInputProcessor() {
        if(NodoInicio.textEquals("h")){
            NodoInicio.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    try {
                        game.setScreen(new GameplayState(game));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        }
    }

    @Override
    //RENDER DE PANTALLA --- LOOP DE RENDERIZADO POR FPS.
    public void render(float delta) {
        Gdx.input.setInputProcessor(GraphStage);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        GraphStage.act();
        //Revisa si hay colisiones por cada vez que se refresca la pantalla.
        GraphStage.draw();

    }

}
