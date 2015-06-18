package com.infiniteloop.ride;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

/**
 * Created by jackthebones on 17/06/15.
 */
public class Shot extends Actor {

    public static final int ShotWidth = 4;
    public static final int ShotHeight = 15;

    public static float GRAVITY = 400f;

    //Velocidad y aceleracion posiciones X y Y
    private Vector2 Velocity;

    private TextureRegion textureRegion;


    //Estado del personaje
    private State state;
    //Posibles estados de personaje durante el juego
    private enum State {alive, dead};

    private Rectangle ShotPerimeter;

    public Shot(float ShotDirection) {
        if(ShotDirection > 0f){
            textureRegion = new TextureRegion(Assets.shot);
        }
        if(ShotDirection < 0f){
            textureRegion = new TextureRegion(Assets.alienshot);
        }
        setWidth(ShotWidth);
        setHeight(ShotHeight);

        ShotPerimeter = new Rectangle(0, 0, ShotWidth, ShotHeight);


        //Inicializa el personaje como "alive"
        state = State.alive;

        //Variables de movimiento del personaje
        //Velocidad del personaje
        Velocity = new Vector2(0, ShotDirection);

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

        UpdatePerimeter();
    }

    private void UpdatePerimeter() {

        ShotPerimeter.x = getX();
        ShotPerimeter.y = getY();

    }

    public Rectangle getShotPerimeter() {
        return ShotPerimeter;
    }

    public void setShotPerimeter(Rectangle shotPerimeter) {
        ShotPerimeter = shotPerimeter;
    }

    private void ActAlive(float delta) {
        UpdatePosition(delta);

        //setRotation(MathUtils.clamp(Velocity.y / JUMPVELOCITY * 45f, -90, 45));

        if (IsBelowGround()){
            //Mueve la posicion al nivel del piso y hace la colision con los pixeles
            //"botom" de la imagen
            remove();
            //Cambia el estado del personaje a Dead.
            //state = State.dead;
        }
        if (IsAboveTop()){
            //Mueve la posicion al nivel del techo y hace la colision con los pixeles
            //"top" de la imagen
            remove();
            //Cambia el estado del personaje a Dead.
            //state = State.dead;
        }
    }

    private boolean IsAboveTop(){

        return getY(Align.bottom) > RideGame.HEIGHT;
    }

    private boolean IsBelowGround(){
        if (getY(Align.top) < 0){
            return true;
        }
        else{
            return false;
        }
    }



    private void UpdatePosition(float delta) {

        //Mueve el personaje, utilizando el metodo setX y setY
        //pasandole la ubicacion en el momento + añadiendole las variables del
        //cambio de posicion.
        setY(getY() + Velocity.y * delta);

    }

}
