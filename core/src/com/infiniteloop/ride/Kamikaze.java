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
public class Kamikaze extends Actor{
    public static final int KamikazeWidth = 32;
    public static final int KamikazeHeight = 32;

    //Velocidad y aceleracion posiciones X y Y
    private Vector2 Velocity;

    private TextureRegion textureRegion;

    public static int KamikazeLifePoints = MathUtils.random(1, 2);
    public int KAMIKAZEAMOUNT;


    //Estado del personaje
    public State state;

    //Posibles estados de personaje durante el juego
    public enum State {alive, dead}

    private Rectangle KamikazePerimeter;

    public Kamikaze(int KamikazeAmount) {

        KAMIKAZEAMOUNT = KamikazeAmount;

        textureRegion = new TextureRegion(Assets.kamikaze);
        setWidth(KamikazeWidth);
        setHeight(KamikazeHeight);


        //Inicializa el personaje como "alive"
        state = State.alive;

        //Variables de movimiento del personaje
        //Velocidad del personaje
        Velocity = new Vector2(0, -45f);
        //Peso o gravedad del personaje
        KamikazePerimeter = new Rectangle(0, 0, KamikazeWidth, KamikazeHeight);

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
        if (KAMIKAZEAMOUNT != 0){
            KamikazeLifePoints = MathUtils.random(1,2);
            setPosition(MathUtils.random(32, RideGame.WIDHT),
                    RideGame.HEIGHT + MathUtils.random(700,1700), Align.center);
            state = State.alive;
        }
        else{
            remove();
            clear();
            setKamikazePerimeter(new Rectangle(0, 0, -30, -30));
        }
    }

    public void HitTaken() {

        KamikazeLifePoints--;
        if(KAMIKAZEAMOUNT != 0){
            if(KamikazeLifePoints == 0){
                state = State.dead;
                KAMIKAZEAMOUNT--;
            }
        }
    }

    private void UpdatePerimeter() {

        KamikazePerimeter.x = getX();
        KamikazePerimeter.y = getY();

    }

    public Rectangle getKamikazePerimeter() {
        return KamikazePerimeter;
    }

    public void setKamikazePerimeter(Rectangle kamikazePerimeter) {
        KamikazePerimeter = kamikazePerimeter;
    }

    private void ActAlive(float delta) {
        UpdatePosition(delta);

        if (IsBelowGround()){
            ResetKamikaze();
        }
    }


    private boolean IsBelowGround(){
        return getY(Align.top) < 0;
    }


    private void ResetKamikaze(){
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

