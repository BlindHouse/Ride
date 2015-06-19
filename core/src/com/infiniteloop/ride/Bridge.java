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
public class Bridge extends Actor{
    public static final int BridgeWidth = 32;
    public static final int BridgeHeight = 32;

    //Velocidad y aceleracion posiciones X y Y
    private Vector2 Velocity;

    private TextureRegion textureRegion;

    public static int BridgeLifePoints = MathUtils.random(1, 15);
    public int BRIDGEAMOUNT;


    //Estado del personaje
    public State state;

    //Posibles estados de personaje durante el juego
    public enum State {alive, dead}

    private Rectangle BridgePerimeter;

    public Bridge(int BridgeAmount) {

        BRIDGEAMOUNT = BridgeAmount;

        textureRegion = new TextureRegion(Assets.bridge);
        setWidth(BridgeWidth);
        setHeight(BridgeHeight);


        //Inicializa el personaje como "alive"
        state = State.alive;

        //Variables de movimiento del personaje
        //Velocidad del personaje
        Velocity = new Vector2(0, -10f);
        //Peso o gravedad del personaje
        BridgePerimeter = new Rectangle(0, 0, BridgeWidth, BridgeHeight);

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
        if (BRIDGEAMOUNT != 0){
            BridgeLifePoints = MathUtils.random(1,2);
            setPosition(RideGame.WIDHT / 2, RideGame.HEIGHT + MathUtils.random(1700,5000), Align.center);
            state = State.alive;
        }
        else{
            remove();
            clear();
            setBridgePerimeter(new Rectangle(0, 0, -30, -30));
        }
    }

    public void HitTaken() {

        BridgeLifePoints--;
        if(BRIDGEAMOUNT != 0){
            if(BridgeLifePoints == 0){
                state = State.dead;
                BRIDGEAMOUNT--;
            }
        }
    }

    private void UpdatePerimeter() {

        BridgePerimeter.x = getX();
        BridgePerimeter.y = getY();

    }

    public Rectangle getBridgePerimeter() {
        return BridgePerimeter;
    }

    public void setBridgePerimeter(Rectangle bridgePerimeter) {
        BridgePerimeter = bridgePerimeter;
    }

    private void ActAlive(float delta) {
        UpdatePosition(delta);

        if (IsBelowGround()){
            ResetBridge();
        }
    }


    private boolean IsBelowGround(){
        return getY(Align.top) < 0;
    }


    private void ResetBridge(){
        setPosition(RideGame.WIDHT / 2, RideGame.HEIGHT + MathUtils.random(1700,5000), Align.center);
    }


    private void UpdatePosition(float delta) {

        //Mueve el personaje, utilizando el metodo setX y setY
        //pasandole la ubicacion en el momento + añadiendole las variables del
        //cambio de posicion.
        setY(getY() + Velocity.y * delta);

    }
}
