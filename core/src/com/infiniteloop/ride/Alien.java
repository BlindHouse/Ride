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
 */
public class Alien extends Actor{

    public static final int AlienWidth = 64;
    public static final int AlienHeight = 64;

    public static float GRAVITY = 400f;

    //Velocidad y aceleracion posiciones X y Y
    private Vector2 Velocity;

    private TextureRegion textureRegion;

    public static int AlienLifePoints = MathUtils.random(1,4);
    private int ALIENAMOUNT;


    //Estado del personaje
    public State state;

    //Posibles estados de personaje durante el juego
    public enum State {alive, dead}

    private Rectangle AlienPerimeter;

    public static AlienShot alienShot = new AlienShot(-250f);

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
                DeadRise();
                break;
        }

        UpdatePerimeter();
    }

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

    public void HitTaken() {

        AlienLifePoints--;
        if(ALIENAMOUNT != 0){
            if(AlienLifePoints == 0){
                state = State.dead;
                ALIENAMOUNT--;
            }
        }
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

        if (IsBelowGround()){
            ResetAlien();
        }

        if(IsInShootingPosition()){
            AlienFire();
        }
    }

    private void AlienFire(){
        alienShot.setPosition(getX(Align.center), getY(Align.bottom), Align.top);
        GameplayState.GameplayStage.addActor(alienShot);
    }

    private boolean IsInShootingPosition() {
        if(getY(Align.bottom) < RideGame.HEIGHT - 150 && getY(Align.bottom) > RideGame.HEIGHT - 155){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean IsBelowGround(){
        return getY(Align.top) < 0;
    }



    private void ResetAlien(){
        setPosition(MathUtils.random(32, RideGame.WIDHT),
                RideGame.HEIGHT + MathUtils.random(35,170), Align.center);
    }


    private void UpdatePosition(float delta) {

        //Mueve el personaje, utilizando el metodo setX y setY
        //pasandole la ubicacion en el momento + añadiendole las variables del
        //cambio de posicion.
        setX(getX() + Velocity.x * delta);
        setY(getY() + Velocity.y * delta);

    }
}