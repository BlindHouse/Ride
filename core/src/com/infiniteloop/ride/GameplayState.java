package com.infiniteloop.ride;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;


/**
 * Created by jackthebones on 12/06/15.
 */
public class GameplayState extends ScreenAdapter {

    public RideGame game;
    public OrthographicCamera camera;

    public static Stage GameplayStage;

    private Ship ship;
    private SpaceBackground spacebackground;
    private Shot shot;
    private Alien alien;


    private boolean JustTouched;

    public GameplayState(RideGame game) throws InterruptedException {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, RideGame.WIDHT, RideGame.HEIGHT);

        GameplayStage = new Stage(new StretchViewport(RideGame.WIDHT, RideGame.HEIGHT));

        ship = new Ship();
        ship.setPosition(RideGame.WIDHT / 2, RideGame.HEIGHT / 2, Align.center);

        alien = new Alien(5);
        alien.setPosition(MathUtils.random(32, RideGame.WIDHT - 32), RideGame.HEIGHT, Align.center);

        spacebackground = new SpaceBackground();
        spacebackground.setPosition(0,0);



        GameplayStage.addActor(spacebackground);
        GameplayStage.addActor(ship);
        GameplayStage.addActor(alien);

        InitInputProcessor();
    }


    private void InitInputProcessor() {
        Gdx.input.setInputProcessor(new InputAdapter(){

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                //ship.Jump();
                shot = new Shot(200f);
                shot.setPosition(ship.getX(Align.center), ship.getY(Align.top), Align.bottom);
                GameplayStage.addActor(shot);
                return true;
            }
        });
    }



    @Override
    //RENDER DE PANTALLA --- LOOP DE RENDERIZADO POR FPS.
    public void render(float delta) {

        GameplayStage.act();
        //Revisa si hay colisiones por cada vez que se refresca la pantalla.
        CheckColisions();
        GameplayStage.draw();

        KeyMovementControls();
        AccelerometerMovementControls();
    }

    private void CheckColisions(){
        if(shot != null) {
            if(alien != null){
                if(shot.getShotPerimeter().overlaps(alien.getAlienPerimeter())){
                    shot.remove();
                    shot.clear();
                    shot.setShotPerimeter(new Rectangle(0, 0, -30, -30));
                    alien.HitTaken();
                }
            }
        }
    }


    private void KeyMovementControls() {
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            ship.MoveLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            ship.MoveRight();
        }
    }

    private void AccelerometerMovementControls(){
        float acceleration=Gdx.input.getAccelerometerX();

        if (Math.abs(acceleration) > 0.5f){
            if (acceleration < 0){
                ship.MoveRight();
            }
            else{
                ship.MoveLeft();
            }
        }
    }

    public void resize(int widht, int height){
        camera.setToOrtho(false, widht, height);
        //Assets.batch.setProjectionMatrix(camera.combined);
    }
}
