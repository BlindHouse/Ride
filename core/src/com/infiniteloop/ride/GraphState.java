package com.infiniteloop.ride;

import Calses3.Gnodo;
import Calses3.GraphMap;
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

import static com.infiniteloop.ride.RideGame.Map;

import java.util.ArrayList;

/**
 * Created by jackthebones on 20/06/15.
 */
public class GraphState extends ScreenAdapter {

    public static RideGame game;
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
    public static ArrayList<Label> Labels = new ArrayList();
    //////////////////////////////////////////////////////////////////////

    //Incialización del Mapa//////////////////


    public GraphState(RideGame game){
        this.game = game;


        camera = new OrthographicCamera();
        camera.setToOrtho(false, RideGame.WIDHT, RideGame.HEIGHT);

        GraphStage = new Stage(new StretchViewport(RideGame.WIDHT, RideGame.HEIGHT));

        spacebackground = new SpaceBackground();
        spacebackground.setPosition(0,0);

        graph = new com.infiniteloop.ride.Graph();
        graph.setPosition(0,0);
        ////////////////////
        //DIBUJO DENTRO DE NODOS DEL GRAFO

        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        NodoInicio = new Label("I", new Label.LabelStyle(font, Color.WHITE));
        NodoInicio.setPosition(141, 226 + 30, Align.center);

        Nodo1 = new Label(GraphMap.A.Accesible() + GraphMap.A.foes, new Label.LabelStyle(font, Color.WHITE));
        Nodo1.setPosition(150, RideGame.HEIGHT - 54, Align.center);

        Nodo2 = new Label(GraphMap.B.Accesible() + GraphMap.B.foes, new Label.LabelStyle(font, Color.WHITE));
        Nodo2.setPosition(268, RideGame.HEIGHT - 105, Align.center);

        Nodo3 = new Label(GraphMap.C.Accesible() + GraphMap.C.foes, new Label.LabelStyle(font, Color.WHITE));
        Nodo3.setPosition(277, RideGame.HEIGHT - 231, Align.center);

        Nodo4 = new Label(GraphMap.D.Accesible() + GraphMap.D.foes, new Label.LabelStyle(font, Color.WHITE));
        Nodo4.setPosition(248, RideGame.HEIGHT - 336, Align.center);

        Nodo5 = new Label(GraphMap.E.Accesible() + GraphMap.E.foes, new Label.LabelStyle(font, Color.WHITE));
        Nodo5.setPosition(148, RideGame.HEIGHT - 389, Align.center);

        Nodo6 = new Label(GraphMap.F.Accesible() + GraphMap.F.foes, new Label.LabelStyle(font, Color.WHITE));
        Nodo6.setPosition(52, RideGame.HEIGHT - 346, Align.center);

        Nodo7 = new Label(GraphMap.G.Accesible() + GraphMap.G.foes, new Label.LabelStyle(font, Color.WHITE));
        Nodo7.setPosition(19, RideGame.HEIGHT - 229, Align.center);

        Nodo8 = new Label(GraphMap.H.Accesible() + GraphMap.H.foes, new Label.LabelStyle(font, Color.WHITE));
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

    public static void UpdateMovement(){
        //Primero LLamar Movement
        Labels.clear();
        //SetFoes
        Nodo1.setText(GraphMap.A.Accesible() + GraphMap.A.foes);
        Nodo2.setText(GraphMap.B.Accesible() + GraphMap.B.foes);
        Nodo3.setText(GraphMap.C.Accesible() + GraphMap.C.foes);
        Nodo4.setText(GraphMap.D.Accesible() + GraphMap.D.foes);
        Nodo5.setText(GraphMap.E.Accesible() + GraphMap.E.foes);
        Nodo6.setText(GraphMap.F.Accesible() + GraphMap.F.foes);
        Nodo7.setText(GraphMap.G.Accesible() + GraphMap.G.foes);
        Nodo8.setText(GraphMap.H.Accesible() + GraphMap.H.foes);
        Labels.add(Nodo1);
        Labels.add(Nodo2);
        Labels.add(Nodo3);
        Labels.add(Nodo4);
        Labels.add(Nodo5);
        Labels.add(Nodo6);
        Labels.add(Nodo7);
        Labels.add(Nodo8);
    }

    private boolean CheckWin() {
        for (int i = 0;i < Labels.size();i++){
            System.out.println(Labels.get(i).getText().toString());
            if (Labels.get(i).getText().toString().startsWith("A")){
                return false;
            }else{}
        }
        return true;
    }

    private void InitInputProcessor() {
        Nodo1.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(Nodo1.getText().toString().startsWith("A")){
                    Map.MoveFoes();
                    GraphMap.A.visited = true;
                    System.out.println( " Nodo A :: " + GraphMap.A.visited);
                    GraphMap.B.visited = false;
                    System.out.println( " Nodo B :: " + GraphMap.B.visited);
                    GraphMap.C.visited = true;
                    System.out.println( " Nodo C :: " + GraphMap.C.visited);
                    GraphMap.D.visited = false;
                    System.out.println( " Nodo D :: " + GraphMap.D.visited);
                    GraphMap.E.visited = true;
                    System.out.println( " Nodo E :: " + GraphMap.E.visited);
                    GraphMap.F.visited = true;
                    System.out.println( " Nodo F :: " + GraphMap.F.visited);
                    GraphMap.G.visited = false;
                    System.out.println( " Nodo G :: " + GraphMap.G.visited);
                    GraphMap.H.visited = true;
                    System.out.println( " Nodo H :: " + GraphMap.H.visited);
                    try {
                        game.setScreen(new GameplayState(game, GraphMap.A.foes));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GraphMap.A.completed = true;
                }return super.touchDown(event, x, y, pointer, button);
            }
        });

        Nodo2.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(Nodo2.getText().toString().startsWith("A")){


                    Map.MoveFoes();
                    GraphMap.A.visited = true;
                    System.out.println( " Nodo A :: " + GraphMap.A.visited);
                    GraphMap.B.visited = true;
                    System.out.println( " Nodo B :: " + GraphMap.B.visited);
                    GraphMap.C.visited = false;
                    System.out.println( " Nodo C :: " + GraphMap.C.visited);
                    GraphMap.D.visited = true;
                    System.out.println( " Nodo D :: " + GraphMap.D.visited);
                    GraphMap.E.visited = true;
                    System.out.println( " Nodo E :: " + GraphMap.E.visited);
                    GraphMap.F.visited = true;
                    System.out.println( " Nodo F :: " + GraphMap.F.visited);
                    GraphMap.G.visited = true;
                    System.out.println( " Nodo G :: " + GraphMap.G.visited);
                    GraphMap.H.visited = false;
                    System.out.println( " Nodo H :: " + GraphMap.H.visited);
                    UpdateMovement();
                    System.out.println(Nodo1.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo2.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo3.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo4.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo5.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo6.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo7.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo8.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println("Click en B");
                    try {
                        game.setScreen(new GameplayState(game, GraphMap.B.foes));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GraphMap.B.completed = true;
                }return super.touchDown(event, x, y, pointer, button);
            }
        });
        Nodo3.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(Nodo3.getText().toString().startsWith("A")){

                    Map.MoveFoes();
                    GraphMap.A.visited = true;
                    System.out.println( " Nodo A :: " + GraphMap.A.visited);
                    GraphMap.B.visited = true;
                    System.out.println( " Nodo B :: " + GraphMap.B.visited);
                    GraphMap.C.visited = true;
                    System.out.println( " Nodo C :: " + GraphMap.C.visited);
                    GraphMap.D.visited = true;
                    System.out.println( " Nodo D :: " + GraphMap.D.visited);
                    GraphMap.E.visited = false;
                    System.out.println( " Nodo E :: " + GraphMap.E.visited);
                    GraphMap.F.visited = true;
                    System.out.println( " Nodo F :: " + GraphMap.F.visited);
                    GraphMap.G.visited = false;
                    System.out.println( " Nodo G :: " + GraphMap.G.visited);
                    GraphMap.H.visited = true;
                    System.out.println( " Nodo H :: " + GraphMap.H.visited);
                    UpdateMovement();
                    System.out.println(Nodo1.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo2.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo3.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo4.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo5.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo6.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo7.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo8.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println("Click en C");
                    try {
                        game.setScreen(new GameplayState(game, GraphMap.C.foes));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GraphMap.C.completed = true;

                }return super.touchDown(event, x, y, pointer, button);
            }
        });
        Nodo4.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(Nodo4.getText().toString().startsWith("A")){

                    Map.MoveFoes();
                    GraphMap.A.visited = true;
                    System.out.println( " Nodo A :: " + GraphMap.A.visited);
                    GraphMap.B.visited = false;
                    System.out.println( " Nodo B :: " + GraphMap.B.visited);
                    GraphMap.C.visited = false;
                    System.out.println( " Nodo C :: " + GraphMap.C.visited);
                    GraphMap.D.visited = true;
                    System.out.println( " Nodo D :: " + GraphMap.D.visited);
                    GraphMap.E.visited = true;
                    System.out.println( " Nodo E :: " + GraphMap.E.visited);
                    GraphMap.F.visited = true;
                    System.out.println( " Nodo F :: " + GraphMap.F.visited);
                    GraphMap.G.visited = false;
                    System.out.println( " Nodo G :: " + GraphMap.G.visited);
                    GraphMap.H.visited = true;
                    System.out.println( " Nodo H :: " + GraphMap.H.visited);
                    UpdateMovement();
                    System.out.println(Nodo1.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo2.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo3.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo4.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo5.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo6.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo7.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo8.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println("Click en D");
                    try {
                        game.setScreen(new GameplayState(game, GraphMap.D.foes));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GraphMap.D.completed = true;

                }return super.touchDown(event, x, y, pointer, button);
            }
        });
        Nodo5.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(Nodo5.getText().toString().startsWith("A")){

                    Map.MoveFoes();
                    GraphMap.A.visited = true;
                    System.out.println( " Nodo A :: " + GraphMap.A.visited);
                    GraphMap.B.visited = true;
                    System.out.println( " Nodo B :: " + GraphMap.B.visited);
                    GraphMap.C.visited = true;
                    System.out.println( " Nodo C :: " + GraphMap.C.visited);
                    GraphMap.D.visited = false;
                    System.out.println( " Nodo D :: " + GraphMap.D.visited);
                    GraphMap.E.visited = true;
                    System.out.println( " Nodo E :: " + GraphMap.E.visited);
                    GraphMap.F.visited = false;
                    System.out.println( " Nodo F :: " + GraphMap.F.visited);
                    GraphMap.G.visited = true;
                    System.out.println( " Nodo G :: " + GraphMap.G.visited);
                    GraphMap.H.visited = true;
                    System.out.println( " Nodo H :: " + GraphMap.H.visited);
                    UpdateMovement();
                    System.out.println(Nodo1.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo2.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo3.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo4.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo5.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo6.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo7.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo8.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println("Click en E");
                    try {
                        game.setScreen(new GameplayState(game, GraphMap.E.foes));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GraphMap.E.completed = true;

                }return super.touchDown(event, x, y, pointer, button);
            }
        });
        Nodo6.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(Nodo6.getText().toString().startsWith("A")){
                    Map.MoveFoes();

                    GraphMap.A.visited = false;
                    System.out.println( " Nodo A :: " + GraphMap.A.visited);
                    GraphMap.B.visited = true;
                    System.out.println( " Nodo B :: " + GraphMap.B.visited);
                    GraphMap.C.visited = true;
                    System.out.println( " Nodo C :: " + GraphMap.C.visited);
                    GraphMap.D.visited = false;
                    System.out.println( " Nodo D :: " + GraphMap.D.visited);
                    GraphMap.E.visited = false;
                    System.out.println( " Nodo E :: " + GraphMap.E.visited);
                    GraphMap.F.visited = true;
                    System.out.println( " Nodo F :: " + GraphMap.F.visited);
                    GraphMap.G.visited = true;
                    System.out.println( " Nodo G :: " + GraphMap.G.visited);
                    GraphMap.H.visited = true;
                    System.out.println( " Nodo H :: " + GraphMap.H.visited);
                    UpdateMovement();
                    System.out.println(Nodo1.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo2.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo3.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo4.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo5.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo6.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo7.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo8.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println("Click en F");
                    try {
                        game.setScreen(new GameplayState(game, GraphMap.F.foes));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GraphMap.F.completed = true;

                }return super.touchDown(event, x, y, pointer, button);
            }
        });
        Nodo7.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(Nodo7.getText().toString().startsWith("A")){

                    Map.MoveFoes();
                    GraphMap.A.visited = true;
                    System.out.println( " Nodo A :: " + GraphMap.A.visited);
                    GraphMap.B.visited = false;
                    System.out.println( " Nodo B :: " + GraphMap.B.visited);
                    GraphMap.C.visited = true;
                    System.out.println( " Nodo C :: " + GraphMap.C.visited);
                    GraphMap.D.visited = true;
                    System.out.println( " Nodo D :: " + GraphMap.D.visited);
                    GraphMap.E.visited = true;
                    System.out.println( " Nodo E :: " + GraphMap.E.visited);
                    GraphMap.F.visited = false;
                    System.out.println( " Nodo F :: " + GraphMap.F.visited);
                    GraphMap.G.visited = true;
                    System.out.println( " Nodo G :: " + GraphMap.G.visited);
                    GraphMap.H.visited = false;
                    System.out.println( " Nodo H :: " + GraphMap.H.visited);
                    UpdateMovement();
                    System.out.println(Nodo1.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo2.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo3.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo4.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo5.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo6.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo7.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo8.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println("Click en G");
                    try {
                        game.setScreen(new GameplayState(game, GraphMap.G.foes));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GraphMap.G.completed = true;

                }return super.touchDown(event, x, y, pointer, button);
            }
        });
        Nodo8.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(Nodo8.getText().toString().startsWith("A")){

                    Map.MoveFoes();
                    GraphMap.A.visited = false;
                    System.out.println( " Nodo A :: " + GraphMap.A.visited);
                    GraphMap.B.visited = true;
                    System.out.println( " Nodo B :: " + GraphMap.B.visited);
                    GraphMap.C.visited = true;
                    System.out.println( " Nodo C :: " + GraphMap.C.visited);
                    GraphMap.D.visited = true;
                    System.out.println( " Nodo D :: " + GraphMap.D.visited);
                    GraphMap.E.visited = false;
                    System.out.println( " Nodo E :: " + GraphMap.E.visited);
                    GraphMap.F.visited = false;
                    System.out.println( " Nodo F :: " + GraphMap.F.visited);
                    GraphMap.G.visited = true;
                    System.out.println( " Nodo G :: " + GraphMap.G.visited);
                    GraphMap.H.visited = true;
                    System.out.println( " Nodo H :: " + GraphMap.H.visited);
                    UpdateMovement();
                    System.out.println(Nodo1.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo2.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo3.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo4.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo5.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo6.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo7.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println(Nodo8.getText().toString() + "::::::::::::::::::::::::::::");
                    System.out.println("Click en H");
                    try {
                        game.setScreen(new GameplayState(game, GraphMap.H.foes));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GraphMap.H.completed = true;

                }return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void CheckIfGraphDone(){
        if(CheckWin()){
            GraphStage.clear();
            GraphStage.dispose();
            dispose();
            game.setScreen(new WinState(game));
        }
    }

    @Override
    //RENDER DE PANTALLA --- LOOP DE RENDERIZADO POR FPS.
    public void render(float delta) {
        Gdx.input.setInputProcessor(GraphStage);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        GraphStage.act();

        CheckIfGraphDone();

        //Revisa si hay colisiones por cada vez que se refresca la pantalla.
        GraphStage.draw();

    }

}
