package com.infiniteloop.ride;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;

/**
 * Created by jackthebones on 14/06/15.
 */
public class Ship extends Actor {

    public static final int BirdWidth = 64;
    public static final int BirdHeight = 64;

    public static float GRAVITY = 800f;
    public static float JUMPVELOCITY = 450f;

    //Velocidad y aceleracion posiciones X y Y
    private Vector2 Velocity;
    private Vector2 Acceleration;


    private TextureRegion textureRegion;


    //Estado del personaje
    private State state;
    //Posibles estados de personaje durante el juego
    private enum State {alive, dead};


    public Ship() {
        textureRegion = new TextureRegion(Assets.ship);
        setWidth(BirdWidth);
        setHeight(BirdHeight);

        //Inicializa el personaje como "alive"
        state = State.alive;

        //Variables de movimiento del personaje
        //Velocidad del personaje
        Velocity = new Vector2(0, 0);
        //Peso o gravedad del personaje
        Acceleration = new Vector2(0, -GRAVITY);

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
                Acceleration = Vector2.Zero;
                break;
        }
    }

    private void ActAlive(float delta) {
        ApplyAcceleration(delta);
        UpdatePosition(delta);

        //ROTACION

        //setRotation(MathUtils.clamp(Velocity.y / JUMPVELOCITY * 45f, -90, 45));

        if (IsBelowGround()){
            //Mueve la posicion al nivel del piso y hace la colision con los pixeles
            //"botom" de la imagen
            setY(0);
            //Cambia el estado del personaje a Dead.
            //state = State.dead;
        }
        if (IsAboveTop()){
            //Mueve la posicion al nivel del techo y hace la colision con los pixeles
            //"top" de la imagen
            setPosition(getX(), RideGame.TOPSCREENLEVEL, Align.topLeft);

            //Cambia el estado del personaje a Dead.
            //state = State.dead;
        }
    }

    private boolean IsAboveTop(){
        return getY(Align.top) > RideGame.TOPSCREENLEVEL;
    }

    private boolean IsBelowGround(){
        if (getY(Align.bottom) < 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void Jump(){
        Velocity.y = JUMPVELOCITY;

    }

    public void MoveLeft(){

        setX(getX() - 3);

    }

    public void MoveRight(){

        setX(getX() + 3);

    }

    private void UpdatePosition(float delta) {

        //Mueve el personaje, utilizando el metodo setX y setY
        //pasandole la ubicacion en el momento + añadiendole las variables del
        //cambio de posicion.
        setX(getX() + Velocity.x * delta);
        setY(getY() + Velocity.y * delta);

    }

    private void ApplyAcceleration(float delta) {

        Velocity.add(Acceleration.x * delta, Acceleration.y * delta);

    }


}
