package com.infiniteloop.ride;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by jackthebones on 19/06/15.
 */
public class MenuState extends ScreenAdapter {

    public RideGame game;
    public OrthographicCamera camera;

    public static Stage MenuStage;

    public static Label label;

    private BitmapFont font;

    private MenuBackground menuBackground;
    private PlayButton playButton;

    public MenuState(RideGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, RideGame.WIDHT, RideGame.HEIGHT);

        MenuStage = new Stage(new StretchViewport(RideGame.WIDHT, RideGame.HEIGHT));

        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        label = new Label("MENU", new Label.LabelStyle(font, Color.WHITE));
        label.setPosition(10,10);

        menuBackground = new MenuBackground();
        menuBackground.setPosition(0,0);

        playButton = new PlayButton();
        playButton.setPosition(0,230);



        MenuStage.addActor(label);
        MenuStage.addActor(menuBackground);
        MenuStage.addActor(playButton);



        InitInputProcessor();

    }
    private void InitInputProcessor() {
        playButton.addListener(new InputListener() {
            public boolean isTouched(InputEvent event, float x, float y, int pointer, int button) {
                if (touchDown(event, x, y, pointer, button)){
                    System.out.println("Example" + " touch started at (" + x + ", " + y + ")");
                    return true;
                }
                return false;
            }
        });
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        MenuStage.act();
        //Revisa si hay colisiones por cada vez que se refresca la pantalla.
        MenuStage.draw();
    }
}
