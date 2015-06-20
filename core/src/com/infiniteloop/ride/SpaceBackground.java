package com.infiniteloop.ride;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

/**
 * Created by jackthebones on 17/06/15.
 *
 * Clase encargada de hacer
 * scrolling y dibujar el fondo
 * del juego.
 *
 */
public class SpaceBackground extends Actor{


    //Velocidad Y
    private Vector2 Velocity;

    private TextureRegion textureRegion;

    /**
     * Metodo encargado de inicializar
     * el fondo, y asignarle las dimensiones
     * para que encaje correctamente
     * en la pantalla, ademas de brindarle
     * una velocidad de scrolling.
     *
     */
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

    /**
     * Metodo que vuelve a dibujar la imagen una y otra
     * vez para que el scrolling sea infinito.
     * @param delta
     */
    private void Scroll(float delta) {
        UpdatePosition(delta);

        if (NeedsToReset()){
            //Mueve la posicion al nivel del piso y hace la colision con los pixeles
            //"botom" de la imagen
            setY(0);
        }
    }

    /**
     * Metodo encargado de verificar
     * cuando el fondo debido al scrolling
     * llega a un punto de la pantalla en donde
     * debe volver a dibujarse.
     * @return boolean.
     */
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
