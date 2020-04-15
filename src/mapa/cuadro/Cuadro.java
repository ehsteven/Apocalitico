package mapa.cuadro;

import graficos.*;
import org.w3c.dom.css.CSS2Properties;

public class Cuadro {
    public int x;
    public int y;
    public Sprite sprite;
    private boolean solido;
    public static final int LADO = 32;
    //coleccion de cuadros
    public static final Cuadro VACIO = new Cuadro(Sprite.VACIO);
    public static final Cuadro FONDO = new Cuadro(Sprite.FONDO, true);
    public static final Cuadro SUELO = new Cuadro(Sprite.SUELO);
    public static final Cuadro ANTORCHA = new Cuadro(Sprite.ANTORCHA, true);
    public static final Cuadro PUERTA_IZQ = new Cuadro(Sprite.PUERTA_IZQ, true);
    public static final Cuadro PUERTA_DER = new Cuadro(Sprite.PUERTA_DER, true);
    public static final Cuadro PUERTA_INVERTIDA_IZQ = new Cuadro(Sprite.PUERTA_INVERTIDA_IZQ, true);
    public static final Cuadro PUERTA_INVERTIDA_DER = new Cuadro(Sprite.PUERTA_INVERTIDA_DER, true);
//    public static final Cuadro PARED_SOLA = new Cuadro(Sprite.PARED);
//    public static final Cuadro ZACATE = new Cuadro(Sprite.ZACATE);
//    public static final Cuadro CALLE = new Cuadro(Sprite.CALLE);
//    public static final Cuadro PUERTA = new Cuadro(Sprite.PUERTA);
//    public static final Cuadro ARBOL = new Cuadro(Sprite.ARBOL);
//    public static final Cuadro INMEDIO = new Cuadro(Sprite.INMEDIO);

    //fin de la coleccion

    public Cuadro(Sprite sprite){
        this.sprite = sprite;
        solido = false;
    }

    public Cuadro(Sprite sprite, boolean solido){
        this.sprite = sprite;
        this.solido = solido;
    }

    public void mostrar(int x, int y, Pantalla pantalla){
        pantalla.mostrarCuadro(x << 5,  y << 5,
                this);
    }

    public boolean getSolido(){
        return solido;
    }
}
