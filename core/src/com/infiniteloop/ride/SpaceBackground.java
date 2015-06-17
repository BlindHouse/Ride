package com.infiniteloop.ride;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

/**
 * Created by jackthebones on 17/06/15.
 */
public class SpaceBackground extends Actor{


    public static float GRAVITY = 800f;
    //Velocidad y aceleracion posiciones X y Y
    private Vector2 Velocity;

    private TextureRegion textureRegion;

    //Estado del personaje

    public SpaceBackground() {
        textureRegion = new TextureRegion(Assets.spacebackground);
        setWidth(320);
        setHeight(960);

        //Variables de movimiento del personaje
        //Velocidad del personaje
        Velocity = new Vector2(0, -100f);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
                getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Scroll(delta);

    }

    private void Scroll(float delta) {
        UpdatePosition(delta);

        if (NeedsToReset()){
            //Mueve la posicion al nivel del piso y hace la colision con los pixeles
            //"botom" de la imagen
            setY(0);
        }
    }

    private boolean NeedsToReset(){
        if (getY(Align.bottom) < -480){
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
        setX(getX() + Velocity.x * delta);
        setY(getY() + Velocity.y * delta);

    }

}
