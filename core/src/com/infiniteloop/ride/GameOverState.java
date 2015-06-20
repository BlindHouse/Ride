package com.infiniteloop.ride;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by jackthebones on 20/06/15.
 */
public class GameOverState extends ScreenAdapter {

    public RideGame game;
    public OrthographicCamera camera;

    public static Stage GameOverStage;
    private StaticBackground staticBackground;
    private ImageTextButton imageTextButton;


    public GameOverState(RideGame game){
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, RideGame.WIDHT, RideGame.HEIGHT);
        GameOverStage = new Stage(new StretchViewport(RideGame.WIDHT, RideGame.HEIGHT));

        staticBackground = new StaticBackground(2);
        staticBackground.setPosition(0,0);
        imageTextButton = new ImageTextButton(2);
        imageTextButton.setPosition(0,230);


        GameOverStage.addActor(staticBackground);
        GameOverStage.addActor(imageTextButton);

        InitInputProcessor();

    }
    private void InitInputProcessor() {
        imageTextButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MenuState(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(GameOverStage);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        GameOverStage.act();
        //Revisa si hay colisiones por cada vez que se refresca la pantalla.
        GameOverStage.draw();
    }

}
