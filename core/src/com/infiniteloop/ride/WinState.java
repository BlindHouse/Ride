package com.infiniteloop.ride;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by jackthebones on 21/06/15.
 */
public class WinState extends ScreenAdapter {

    public RideGame game;
    public OrthographicCamera camera;

    public static Stage WinStage;
    private StaticBackground staticBackground;
    private ImageTextButton imageTextButton;


    public WinState(RideGame game){
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, RideGame.WIDHT, RideGame.HEIGHT);
        WinStage = new Stage(new StretchViewport(RideGame.WIDHT, RideGame.HEIGHT));

        staticBackground = new StaticBackground(3);
        staticBackground.setPosition(0,0);
        imageTextButton = new ImageTextButton(2);
        imageTextButton.setPosition(0,230);


        WinStage.addActor(staticBackground);
        WinStage.addActor(imageTextButton);

        InitInputProcessor();

    }
    private void InitInputProcessor() {
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                game.setScreen(new MenuState(game));
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(WinStage);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        WinStage.act();
        //Revisa si hay colisiones por cada vez que se refresca la pantalla.
        WinStage.draw();
    }

}
