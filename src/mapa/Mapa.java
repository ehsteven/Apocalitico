package mapa;

import graficos.Pantalla;
import mapa.cuadro.Cuadro;

public abstract class Mapa {
    protected int ancho;
    protected int alto;
    protected int[] cuadros;
    protected Cuadro[] cuadrosCatalogo;

    public Mapa(int ancho, int alto){
        this.ancho = ancho;
        this.alto = alto;
        cuadros = new int [ancho * alto];
        generarMapa();
    }

    public Mapa(String ruta){
        cargarMapa(ruta);
        generarMapa();
    }

    protected void generarMapa(){

    }

    protected void cargarMapa(String ruta){

    }

    public void actualizar(){

    }

    public void mostrar(final int compensacionX, final int compensacionY, final Pantalla pantalla){
        pantalla.setDiferencia(compensacionX, compensacionY);
        int o = compensacionX >> 5 ; //32;
        int e = (compensacionX + pantalla.getAncho() + Cuadro.LADO) >> 5;
        int n = compensacionY >> 5;
        int s = (compensacionY + pantalla.getAlto() + Cuadro.LADO) >> 5;

        for(int y = n; y < s; y++){
            for(int x = o; x < e; x++){
                //getCuadros(x, y).mostrar(x, y, pantalla);
                if(x < 0 || y < 0 || x >= ancho || y >= alto){
                    Cuadro.VACIO.mostrar(x, y, pantalla);
                }else{
                    cuadrosCatalogo[x + y * ancho].mostrar(x, y, pantalla);
                }
            }
        }
    }

//    public Cuadro getCuadros(final int x, final int y) {
//        if(x < 0 || y < 0 || x >= ancho || y >= alto){
//            return Cuadro.VACIO;
//        }
//        switch (cuadros[x + y * alto]) {
//            case 0:
//                return Cuadro.PARED_SOLA;
//            case 1:
//                return Cuadro.ZACATE;
//            case 2:
//                return Cuadro.CALLE;
//            case 3:
//                return Cuadro.INMEDIO;
//            case 4:
//                return Cuadro.ARBOL;
//            case 5:
//                return Cuadro.PUERTA;
//            default:
//                return Cuadro.VACIO;
//        }
//    }


    public Cuadro getCuadrosCatalogo(int posicion) {
        return cuadrosCatalogo[posicion];
    }

    public void setCuadrosCatalogo(Cuadro[] cuadrosCatalogo) {
        this.cuadrosCatalogo = cuadrosCatalogo;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }
}