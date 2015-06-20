package com.infiniteloop.ride;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

/**
 * Created by jackthebones on 17/06/15.
 *
 * Clase encargada de crear los disparos
 * que seran utilizados por los alien.
 *
 */
public class AlienShot extends Actor {
    //Dimensiones de actor
    public static final int ShotWidth = 4;
    public static final int ShotHeight = 15;


    //Velocidad y aceleracion posiciones X y Y
    private Vector2 Velocity;
    //Define el spritesheet a utilizar.
    private TextureRegion textureRegion;
    //Estado del personaje
    private State state;
    //Posibles estados de personaje durante el juego
    private enum State {alive, dead}
    //Rectangulo que encierra al actor. Utilizado para determinar si hay colisiones.
    private Rectangle AlienShotPerimeter;

    /**
     * Inicializa a el Disparo con un parametro entero
     * que sera la velocidad del mismo,
     * asi como los algoritmos de
     * renderizado y movimiento.
     * @param ShotDirection
     */
    public AlienShot(float ShotDirection) {
        if(ShotDirection > 0f){
            textureRegion = new TextureRegion(Assets.shot);
        }
        if(ShotDirection < 0f){
            textureRegion = new TextureRegion(Assets.alienshot);
        }
        setWidth(ShotWidth);
        setHeight(ShotHeight);

        AlienShotPerimeter = new Rectangle(0, 0, ShotWidth, ShotHeight);


        //Inicializa el personaje como "alive"
        state = State.alive;

        //Variables de movimiento del personaje
        //Velocidad del personaje
        Velocity = new Vector2(0, ShotDirection);

        //Centro de rotacion del pj
        setOrigin(Align.center);


    }

    /**
     * Funcion encargada de dibujar al personaje
     * con la imagen correspondiente del TextureRegion.
     * (Es un override de la clase draw de "Actor")
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
                getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    /**
     * Funcion encargada de ejecutar las acciones
     * o hacer que el actor actue dependiendo del
     * estado en el que este.
     * @param delta
     */
    @Override
    public void act(float delta) {
        super.act(delta);

        switch (state){
            case alive:
                ActAlive(delta);
                break;

            case dead:
                break;
        }

        UpdatePerimeter();
    }


    /**
     * Actualiza la posicion del rectangulo
     * que registra las colisiones.
     */
    private void UpdatePerimeter() {

        AlienShotPerimeter.x = getX();
        AlienShotPerimeter.y = getY();

    }

    /**
     * Retorna el rectangulo con su posicion
     * y dimensiones para ser utilizado como
     * evaluaciion de colisiones llamando
     * al metodo de rectangulos .Overlaps()
     * @return Rectangle
     */
    public Rectangle getAlienShotPerimeter() {
        return AlienShotPerimeter;
    }

    /**
     * Hace un set del perimetro del rectangulo
     * a un rectangulo dado.
     * @param alienShotPerimeter
     */
    public void setAlienShotPerimeter(Rectangle alienShotPerimeter) {
        AlienShotPerimeter = alienShotPerimeter;
    }


    /**
     * Encargado de ejecutarse mientras el actor
     * se encuentre vivo, actualizando la posicion
     * y ademas verificando en que lugar de la pantalla
     * se encuentra por si se sale de la misma y
     * llamar el metodo de reubicacion.
     * @param delta
     */
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
    }

    /**
     * Evalua si la posicion del actor
     * esta por debajo de la linea final
     * de la pantalla.
     * @return boolean
     */
    private boolean IsBelowGround(){
        if (getY(Align.top) < 0){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * Metodo que mueve al actor en pantalla.
     */
    private void UpdatePosition(float delta) {

        //Mueve el personaje, utilizando el metodo setX y setY
        //pasandole la ubicacion en el momento + añadiendole las variables del
        //cambio de posicion.
        setY(getY() + Velocity.y * delta);

    }

}