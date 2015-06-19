package com.infiniteloop.ride;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

/**
 * Created by jackthebones on 19/06/15.
 */
public class Coins extends Actor {

    public static final int CoinWidth = 25;
    public static final int CoinHeight = 25;

    //Velocidad y aceleracion posiciones X y Y
    private Vector2 Velocity;

    private TextureRegion textureRegion;


    //Estado del personaje
    public State state;

    //Posibles estados de personaje durante el juego
    public enum State {alive, dead}

    private Rectangle CoinPerimeter;

    public Coins() {

        textureRegion = new TextureRegion(Assets.coin);
        setWidth(CoinWidth);
        setHeight(CoinHeight);


        //Inicializa el personaje como "alive"
        state = State.alive;

        //Variables de movimiento del personaje
        //Velocidad del personaje
        Velocity = new Vector2(0, -150f);
        //Peso o gravedad del personaje
        CoinPerimeter = new Rectangle(0, 0, CoinWidth, CoinHeight);

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

        setPosition(MathUtils.random(32, RideGame.WIDHT),
                    RideGame.HEIGHT + MathUtils.random(50,100), Align.center);
        state = State.alive;
    }

    public void HitTaken() {
        Ship.CurrentScore = Ship.CurrentScore + MathUtils.random(1,3);
        GameplayState.label.setText("Life : " + Ship.CurrentLife + "  " + "Score : " + Ship.CurrentScore);
        state = State.dead;
    }



    private void UpdatePerimeter() {

        CoinPerimeter.x = getX();
        CoinPerimeter.y = getY();

    }

    public Rectangle getCoinPerimeter() {
        return CoinPerimeter;
    }

    public void setCoinPerimeter(Rectangle coinPerimeter) {
        CoinPerimeter = coinPerimeter;
    }

    private void ActAlive(float delta) {
        UpdatePosition(delta);

        if (IsBelowGround()){
            ResetCoin();
        }
    }


    private boolean IsBelowGround(){
        return getY(Align.top) < 0;
    }


    private void ResetCoin(){
        setPosition(MathUtils.random(32, RideGame.WIDHT),
                RideGame.HEIGHT + MathUtils.random(50,100), Align.center);
    }


    private void UpdatePosition(float delta) {

        //Mueve el personaje, utilizando el metodo setX y setY
        //pasandole la ubicacion en el momento + añadiendole las variables del
        //cambio de posicion.
        setY(getY() + Velocity.y * delta);

    }

}
