package com.infiniteloop.ride;

import com.badlogic.gdx.Gdx;
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

    private Bird bird;
    private Image background;
    private Image ground;
    private Image Pipe;

    private boolean JustTouched;

    public GameplayState(RideGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, RideGame.WIDHT, RideGame.HEIGHT);

        GameplayStage = new Stage(new StretchViewport(RideGame.WIDHT, RideGame.HEIGHT));

        bird = new Bird();
        background = new Image(Assets.background);
        ground = new Image(Assets.ground);


        Pipe topPipe = new Pipe();
        topPipe.setRotation(180);
        topPipe.setY(RideGame.HEIGHT / 3);

        Pipe bottomPipe = new Pipe();

        PipePair pair = new PipePair(topPipe, bottomPipe);

        bird.setPosition(RideGame.WIDHT / 4, RideGame.HEIGHT / 2, Align.center);

        GameplayStage.addActor(background);
        GameplayStage.addActor(ground);
        GameplayStage.addActor(bird);
        GameplayStage.addActor(topPipe);
        GameplayStage.addActor(bottomPipe);

        InitInputProcessor();

    }

    private void InitInputProcessor() {
        Gdx.input.setInputProcessor(new InputAdapter(){

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                JustTouched = true;
                bird.Jump();
                return true;
            }
        });

    }

    @Override
    public void render(float delta) {
        GameplayStage.act();
        GameplayStage.draw();
    }

    public void resize(int widht, int height){
        camera.setToOrtho(false, widht, height);
        //Assets.batch.setProjectionMatrix(camera.combined);
    }
}
