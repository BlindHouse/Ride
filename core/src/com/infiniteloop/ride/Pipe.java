package com.infiniteloop.ride;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

/**
 * Created by jackthebones on 14/06/15.
 */
public class Pipe extends Actor{

    public static final int BirdWidth = 52;
    public static final int BirdHeight = 320;


    //Velocidad y aceleracion posiciones X y Y
    private Vector2 Velocity;


    private TextureRegion textureRegion;


    //Estado del personaje
    private State state;
    //Posibles estados de personaje durante el juego
    private enum State {alive, dead};


    public Pipe() {
        textureRegion = new TextureRegion(Assets.pipe);
        setWidth(BirdWidth);
        setHeight(BirdHeight);

        //Inicializa el personaje como "alive"
        state = State.alive;

        //Variables de movimiento del personaje
        //Velocidad del personaje
        Velocity = new Vector2(0, 0);
        //Peso o gravedad del personaje

        //Centro de rotacion del pj
        setOrigin(Align.center);


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
                getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        switch (state){
            case alive:
                ActAlive(delta);
                break;

            case dead:
                Velocity = Vector2.Zero;
                break;
        }
    }

    private void ActAlive(float delta) {
        UpdatePosition(delta);



    }

    private void UpdatePosition(float delta) {

        //Mueve el personaje, utilizando el metodo setX y setY
        //pasandole la ubicacion en el momento + añadiendole las variables del
        //cambio de posicion.
        setX(getX() + Velocity.x * delta);
        setY(getY() + Velocity.y * delta);

    }
}

