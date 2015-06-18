package com.infiniteloop.ride;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

/**
 * Created by jackthebones on 18/06/15.
 */
public class Alien extends Actor{

    public static final int AlienWidth = 64;
    public static final int AlienHeight = 64;

    public static float GRAVITY = 400f;

    //Velocidad y aceleracion posiciones X y Y
    private Vector2 Velocity;

    private TextureRegion textureRegion;


    //Estado del personaje
    private State state;
    //Posibles estados de personaje durante el juego
    private enum State {alive, dead}

    private Rectangle AlienPerimeter;


    public Alien() {
        textureRegion = new TextureRegion(Assets.alien);
        setWidth(AlienWidth);
        setHeight(AlienHeight);


        //Inicializa el personaje como "alive"
        state = State.alive;

        //Variables de movimiento del personaje
        //Velocidad del personaje
        Velocity = new Vector2(0, 0);
        //Peso o gravedad del personaje
        AlienPerimeter = new Rectangle(0, 0, AlienWidth,AlienHeight);

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

        AlienPerimeter.x = getX();
        AlienPerimeter.y = getY();

    }

    public Rectangle getAlienPerimeter() {
        return AlienPerimeter;
    }

    public void setAlienPerimeter(Rectangle alienPerimeter) {
        AlienPerimeter = alienPerimeter;
    }

    private void ActAlive(float delta) {
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
    }

    private boolean IsBelowGround(){
        return getY(Align.bottom) < 0;
    }


    private void UpdatePosition(float delta) {

        //Mueve el personaje, utilizando el metodo setX y setY
        //pasandole la ubicacion en el momento + añadiendole las variables del
        //cambio de posicion.
        setX(getX() + Velocity.x * delta);
        setY(getY() + Velocity.y * delta);

    }
}
