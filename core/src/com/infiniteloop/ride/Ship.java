package com.infiniteloop.ride;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;


/**
 * Created by jackthebones on 14/06/15.
 *
 * Clase encargada de crear la nave
 * que sera controlada por el usuario
 * ademas de ciertos metodos que la hacen
 * actuar.
 *
 */
public class Ship extends Actor {
    //Definicion de las dimensiones del actor
    public static final int ShipWidth = 64;
    public static final int ShipHeight = 64;
    //Variables de velocidad
    public static float GRAVITY = 400f;
    public static int CurrentScore = 0;

    //Velocidad y aceleracion posiciones X y Y
    private Vector2 Velocity;
    private Vector2 Acceleration;
    //Variables de puntos de vida y de combustible.
    public static int CurrentLife = 100;
    public static int CurrentGas = 100;
    //Variable que sera utilizada para asignar una imagen del sprite a la nave.
    private TextureRegion textureRegion;
    //Estado del personaje
    public static State state;
    //Posibles estados de personaje durante el juego
    public enum State {alive, dead}
    //Rectangulo que encierra a la nave. Utilizado para determinar si hay colisiones.
    private Rectangle ShipPerimeter;

    /**
     * Inicializa a la nave, asi como los algoritmos de
     * renderizado y movimiento, y acciones en caso
     * de que la nave reciba golpes.
     */
    public Ship() {
        //Asignacion de la textura a la nave.
        textureRegion = new TextureRegion(Assets.ship);
        setWidth(ShipWidth);
        setHeight(ShipHeight);

        //Inicializa el personaje como "alive"
        state = State.alive;

        //Variables de movimiento del personaje
        //Velocidad del personaje
        Velocity = new Vector2(0, 0);
        //Peso o gravedad del personaje
        Acceleration = new Vector2(0, -GRAVITY);

        ShipPerimeter = new Rectangle(0, 0, ShipWidth, ShipHeight);

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
                try {
                    ActAlive(delta);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case dead:
                GameOver();
                break;
        }

        UpdatePerimeter();
    }

    private void GameOver() {
        System.out.println("GAME OVER");
    }

    private void UpdatePerimeter() {

        ShipPerimeter.x = getX();
        ShipPerimeter.y = getY();

    }

    /**
     * Hace que la vida de la nave descienda cuando ha sido
     * alcanzada por algun disparo o nave enemiga,
     * ademas actualiza la cantidad de vida en pantalla,
     * y en caso de que la vida sea 0,
     * cambia el estado a muerto, lo que ejecuta la funcion de
     * gameover.
     *
     * Se le ingresa un parametro el cual es un numero entero que
     * sera restado de la vida de la nave.
     *
     * @param Hit
     */
    public static void HitTaken(int Hit) {
        CurrentLife = CurrentLife - Hit;
        GameplayState.label.setText("Life : " + Ship.CurrentLife + "  " + "Gas : " + Ship.CurrentGas + "  "
                + "Score : " + Ship.CurrentScore);
        if(CurrentLife <= 0){
            state = State.dead;
        }

    }

    public Rectangle getShipPerimeter() {
        return ShipPerimeter;
    }

    public void setShipPerimeter(Rectangle shipPerimeter) {
        ShipPerimeter = shipPerimeter;
    }

    private void ActAlive(float delta) throws InterruptedException {
        ApplyAcceleration(delta);
        UpdatePosition(delta);

        //ROTACION

        //setRotation(MathUtils.clamp(Velocity.y / JUMPVELOCITY * 45f, -90, 45));

        if (IsBelowGround()) {
            //Mueve la posicion al nivel del piso y hace la colision con los pixeles
            //"botom" de la imagen
            setY(0);
            //Cambia el estado del personaje a Dead.
            //state = State.dead;
        }
    }

    private boolean IsBelowGround(){
        if (getY(Align.bottom) < 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void MoveLeft(){
        if(getX(Align.left) > 0) {
            setX(getX() - 3);
        }
    }

    public void MoveRight(){
        if(getX(Align.right) < RideGame.WIDHT) {
            setX(getX() + 3);
        }
    }

    private void UpdatePosition(float delta) {

        //Mueve el personaje, utilizando el metodo setX y setY
        //pasandole la ubicacion en el momento + añadiendole las variables del
        //cambio de posicion.
        setX(getX() + Velocity.x * delta);
        setY(getY() + Velocity.y * delta);

    }

    /**
     * Aplica la aceleracion a la velocidad de la nave
     * para la animacion inicial cuando se ingresa a la pantalla
     * de juego.
     * @param delta
     */
    private void ApplyAcceleration(float delta) {

        Velocity.add(Acceleration.x * delta, Acceleration.y * delta);

    }


}