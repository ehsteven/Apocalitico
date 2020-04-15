package entes.criatura;

import control.Teclado;
import graficos.Pantalla;
import graficos.Sprite;
import mapa.Mapa;

import javax.swing.*;
import java.nio.channels.spi.SelectorProvider;

public class Jugador extends Criatura{
    private Teclado teclado;
    private int animacion;

    public Jugador(Mapa mapa, Teclado teclado, Sprite sprite){
        this.mapa = mapa;
        this.teclado = teclado;
        this.sprite = sprite;
    }

    public Jugador(Mapa mapa, Teclado teclado,  Sprite sprite, int posicionX, int posicionY){
        this(mapa, teclado, sprite);
        this.x = posicionX;
        this.y = posicionY;
    }

    public void actualizar() {
        int desplazamientoX = 0;
        int desplazamientoY = 0;
        int velocidadMovimiento = 1;
        if (animacion < 32767)
            animacion++;
        else
            animacion = 0;

        if (teclado.correr) {
            velocidadMovimiento = 2;
        }
        if (teclado.arriba)
            desplazamientoY -= velocidadMovimiento;
        if (teclado.abajo)
            desplazamientoY += velocidadMovimiento;
        if (teclado.izquierda)
            desplazamientoX -= velocidadMovimiento;
        if (teclado.derecha)
            desplazamientoX += velocidadMovimiento;

        if (desplazamientoX != 0 || desplazamientoY != 0) {
            mover(desplazamientoX, desplazamientoY);
            movimiento = true;
        } else
            movimiento = false;
        if(direccion == 'a')
            sprite = Sprite.FRENTE_1;

        if(direccion == 'n'){
            sprite = Sprite.ESPALDA_1;
            if(movimiento){
                if(animacion%30 > 15)
                    sprite = Sprite.ESPALDA_2;
                else{
                    sprite = Sprite.ESPALDA_1;
                }
            }
        }
        if(direccion == 's'){
            sprite = Sprite.FRENTE_1;
            if(movimiento){
                if(animacion%30 > 15)
                    sprite = Sprite.FRENTE_2;
                else{
                    sprite = Sprite.FRENTE_1;
                }
            }
        }
        if(direccion == 'o'){
            sprite = Sprite.CAMINAR_IZQ_1;
            if(movimiento){
                if(animacion%30 > 15)
                    sprite = Sprite.CAMINAR_IZQ_2;
                else{
                    sprite = Sprite.CAMINAR_IZQ_1;
                }
            }
        }
        if(direccion == 'e'){
            sprite = Sprite.CAMINAR_DER_1;
            if(movimiento){
                if(animacion%30 > 15)
                    sprite = Sprite.CAMINAR_DER_2;
                else{
                    sprite = Sprite.CAMINAR_DER_1;
                }
            }
        }
    }

    public void mostrar(Pantalla pantalla){
        pantalla.mostrarJugador(x, y, this);
    }
}