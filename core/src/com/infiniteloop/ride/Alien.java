package com.infiniteloop.ride;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

/**
 * Created by jackthebones on 18/06/15.
 *
 * Clase que crea la nave enemiga Alien
 * y la hace actuar en pantalla.
 *
 */
public class Alien extends Actor{

    //Dimensiones del Actor
    public static final int AlienWidth = 64;
    public static final int AlienHeight = 64;

    //Velocidad constante posicion Y
    private Vector2 Velocity;
    //Texture Region de donde se obtiene la imagen del Alien del spritesheet generado en assets.
    private TextureRegion textureRegion;
    //Inicializacion de puntos de vida del Alien [Varia de 1 a 4 puntos de vida]
    public static int AlienLifePoints = MathUtils.random(1,4);
    //Cantidad de Aliens por nivel.
    public int ALIENAMOUNT;
    //Estado del personaje [Puede ser Dead o Alive]
    public State state;
    //Posibles estados de personaje durante el juego
    public enum State {alive, dead}
    //Rectangulo que encierra al alien. Utilizado para determinar si hay colisiones.
    private Rectangle AlienPerimeter;
    //Inicializa un AlienShot con una velocidad constante.
    public static AlienShot alienShot = new AlienShot(-250f);

    /**
     * Inicializa a el Alien con un parametro entero
     * que sera la cantidad de aliens que saldran
     * en dicho nivel, asi como los algoritmos de
     * renderizado y movimiento, y acciones en caso
     * de que el alien sea eliminado.
     * @param AlienAmount
     */
    public Alien(int AlienAmount) {

        ALIENAMOUNT = AlienAmount;

        textureRegion = new TextureRegion(Assets.alien);
        setWidth(AlienWidth);
        setHeight(AlienHeight);


        //Inicializa el personaje como "alive"
        state = State.alive;

        //Variables de movimiento del personaje
        //Velocidad del personaje
        Velocity = new Vector2(0, -75f);
        //Peso o gravedad del personaje
        AlienPerimeter = new Rectangle(0, 0, AlienWidth,AlienHeight);

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
                DeadRise();
                break;
        }

        UpdatePerimeter();
    }

    /**
     * Funcion encargada de eliminar de pantalla
     * al alien una vez que este muere
     * y reiniciar a otro alien en caso de que
     * la cantidad de aliens restantes
     * en el nivel sea distinta de cero.
     */
    public void DeadRise(){
        if (ALIENAMOUNT != 0){
            AlienLifePoints = MathUtils.random(1,4);
            setPosition(MathUtils.random(32, RideGame.WIDHT),
                    RideGame.HEIGHT + MathUtils.random(35,170), Align.center);
            state = State.alive;
        }
        else{
            remove();
            clear();
            setAlienPerimeter(new Rectangle(0, 0, -30, -30));
        }
    }

    /**
     *Funcion que registra cuando un disparo
     * es recibido por el alien y baja sus
     * puntos de vida, cuando los puntos de
     * vida llegan a cero, el alien pasa
     * a estar de Alive a Dead.
     * Ademas, reduce la cantidad de aliens
     * que hay registrados en el nivel.
     *
     */
    public void HitTaken() {

        AlienLifePoints--;
        if(ALIENAMOUNT != 0){
            if(AlienLifePoints == 0){
                state = State.dead;
                ALIENAMOUNT--;
            }
        }
    }

    /**
     * Actualiza la posicion del rectangulo
     * que registra las colisiones.
     */
    private void UpdatePerimeter() {

        AlienPerimeter.x = getX();
        AlienPerimeter.y = getY();

    }

    /**
     * Retorna el rectangulo con su posicion
     * y dimensiones para ser utilizado como
     * evaluaciion de colisiones llamando
     * al metodo de rectangulos .Overlaps()
     * @return Rectangle
     */
    public Rectangle getAlienPerimeter() {
        return AlienPerimeter;
    }

    /**
     * Hace un set del perimetro del rectangulo
     * a un rectangulo dado.
     * @param alienPerimeter
     */
    public void setAlienPerimeter(Rectangle alienPerimeter) {
        AlienPerimeter = alienPerimeter;
    }

    /**
     * Encargado de ejecutarse mientras el actor
     * se encuentre vivo, actualizando la posicion
     * y ademas verificando en que lugar de la pantalla
     * se encuentra por si se sale de la misma y
     * llamar el metodo de reubicacion.
     *
     * Ademas, ejecuta la funcion de disparar,
     * y actualiza los valores de la vida, combustible
     * y puntaje en pantalla.
     * @param delta
     */
    private void ActAlive(float delta) {
        UpdatePosition(delta);

        if (IsBelowGround()){
            ResetAlien();
        }

        if(IsInShootingPosition()){
            AlienFire();
            Ship.CurrentGas = Ship.CurrentGas - 3;
            GameplayState.label.setText("Life : " + Ship.CurrentLife + "  " + "Gas : " + Ship.CurrentGas + "  "
                    + "Score : " + Ship.CurrentScore);
        }
    }

    /**
     * Metodo que hace que se cree
     * un disparo de clase AlienShot.
     */
    private void AlienFire(){
        alienShot.setPosition(getX(Align.center), getY(Align.bottom), Align.top);
        GameplayState.GameplayStage.addActor(alienShot);
    }

    /**
     * Evalua si el alien esta en una
     * posicion apta para disparar.
     * @return boolean.
     */
    private boolean IsInShootingPosition() {
        if(getY(Align.bottom) < RideGame.HEIGHT - 150 && getY(Align.bottom) > RideGame.HEIGHT - 155){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Evalua si la posicion del actor
     * esta por debajo de la linea final
     * de la pantalla.
     * @return boolean
     */
    private boolean IsBelowGround(){
        return getY(Align.top) < 0;
    }

    /**
     * Metodo de reposicionamiento del alien
     * en caso de que sea eliminado,
     * o se salga de la pantalla de juego.
     */
    private void ResetAlien(){
        setPosition(MathUtils.random(32, RideGame.WIDHT),
                RideGame.HEIGHT + MathUtils.random(35,170), Align.center);
    }

    /**
     * Metodo que mueve al actor en pantalla.
     */
    private void UpdatePosition(float delta) {

        //Mueve el personaje, utilizando el metodo setX y setY
        //pasandole la ubicacion en el momento + añadiendole las variables del
        //cambio de posicion.
        setX(getX() + Velocity.x * delta);
        setY(getY() + Velocity.y * delta);

    }
}