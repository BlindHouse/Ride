package com.infiniteloop.ride;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
    private Image spacebackground;

    private boolean JustTouched;

    public GameplayState(RideGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, RideGame.WIDHT, RideGame.HEIGHT);

        GameplayStage = new Stage(new StretchViewport(RideGame.WIDHT, RideGame.HEIGHT));

        ship = new Ship();
        ship.setPosition(RideGame.WIDHT / 4, RideGame.HEIGHT / 2, Align.center);

        spacebackground = new Image(Assets.spacebackground);

        GameplayStage.addActor(spacebackground);
        GameplayStage.addActor(ship);


        InitInputProcessor();

    }

    private void InitInputProcessor() {
        Gdx.input.setInputProcessor(new InputAdapter(){

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                JustTouched = true;
                ship.Jump();
                return true;
            }
        });



    }

    @Override
    public void render(float delta) {


        GameplayStage.act();
        GameplayStage.draw();

        KeyMovementControls();
        AccelerometerMovementControls();
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
