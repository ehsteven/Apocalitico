package mapa;

import mapa.cuadro.Cuadro;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Map;

public class MapaCargado extends Mapa{
    private int[] pixeles;

    public MapaCargado(String ruta) {
        super(ruta);

    }

    protected void cargarMapa(String ruta){
        try {
            BufferedImage imagen = ImageIO.read(Mapa.class.getResource(ruta));
            ancho = imagen.getWidth();
            alto = imagen.getHeight();
            cuadrosCatalogo = new Cuadro[ancho * alto];
            pixeles = new int[ancho * alto];
            imagen.getRGB(0,0, ancho, alto, pixeles, 0, ancho);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //15369
    //61758
    protected void generarMapa(){
        for(int i = 0; i < pixeles.length; i++){
            switch (pixeles[i]){
                case 0xff404b1b:
                    cuadrosCatalogo[i] = Cuadro.FONDO;
                    continue;
                case 0xff40c91b:
                    cuadrosCatalogo[i] = Cuadro.PUERTA_DER;
                    continue;
                case 0xff409c1b:
                    cuadrosCatalogo[i] = Cuadro.PUERTA_IZQ;
                    continue;
                case 0xff40b0a8:
                    cuadrosCatalogo[i] = Cuadro.SUELO;
                    continue;
                case 0xff40379d:
                    cuadrosCatalogo[i] = Cuadro.ANTORCHA;
                    continue;
                case 0xfff24b1b:
                    cuadrosCatalogo[i] = Cuadro.PUERTA_INVERTIDA_IZQ;
                    continue;
                case 0xff407e1b:
                    cuadrosCatalogo[i] = Cuadro.PUERTA_INVERTIDA_DER;;
                    continue;
//                case 0xffff0000:
//                    cuadrosCatalogo[i] = Cuadro.PARED_SOLA;
//                    continue;
//                case 0xffffff00:
//                    cuadrosCatalogo[i] = Cuadro.ZACATE;
//                    continue;
//                case 0xff7d8d62:
//                    cuadrosCatalogo[i] = Cuadro.CALLE;
//                    continue;
//                case 0xff0000ff:
//                    cuadrosCatalogo[i] = Cuadro.INMEDIO;
//                    continue;
//                case 0xff61e49a:
//                    cuadrosCatalogo[i] = Cuadro.ARBOL;
//                    continue;
//                case 0xffb49e36:
//                    cuadrosCatalogo[i] = Cuadro.PUERTA;
//                    continue;
                default:
                    cuadrosCatalogo[i] = Cuadro.VACIO;
                    continue;
            }
        }
    }
}
