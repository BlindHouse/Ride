package com.infiniteloop.ride;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;


/**
 * Created by jackthebones on 12/06/15.
 */
public class GameplayState extends ScreenAdapter {

    public RideGame game;
    public OrthographicCamera camera;

    private Stage GameplayStage;

    private Ship ship;
    private SpaceBackground spacebackground;
    private Alien alien;
    private Shot shot;

    private boolean JustTouched;

    public GameplayState(RideGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, RideGame.WIDHT, RideGame.HEIGHT);

        GameplayStage = new Stage(new StretchViewport(RideGame.WIDHT, RideGame.HEIGHT));

        ship = new Ship();
        ship.setPosition(RideGame.WIDHT / 2, RideGame.HEIGHT / 2, Align.center);

        spacebackground = new SpaceBackground();
        spacebackground.setPosition(0,0);

        alien = new Alien();
        alien.setPosition(150, 240, Align.center);

        GameplayStage.addActor(spacebackground);
        GameplayStage.addActor(alien);
        GameplayStage.addActor(ship);

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

            if (shot.getShotPerimeter().overlaps(alien.getAlienPerimeter())) {
                {
                    alien.remove();
                    alien.setAlienPerimeter( new Rectangle(-30, -30, 1, 1));
                    shot.remove();
                    shot.setShotPerimeter( new Rectangle(-30, -30, 1, 1));
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
