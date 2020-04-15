package entes.criatura;

import entes.Ente;
import graficos.Sprite;

public abstract  class Criatura extends Ente {
    protected Sprite sprite;
    protected char direccion = 'a';
    protected boolean movimiento = false;

    public void actualizar(){

    }

    public void mostrar(){

    }

    public void mover(int desplazamientoX, int desplazamientoY){
        if(desplazamientoX > 0){
            direccion = 'e';
        }if (desplazamientoX < 0){
            direccion = 'o';
        }if(desplazamientoY > 0){
            direccion = 's';
        }if(desplazamientoY < 0){
            direccion = 'n';
        }
        if(!getEliminado()){
            if(!enColision(desplazamientoX, 0)){
                setX(desplazamientoX);
            }if(!enColision(0, desplazamientoY)) {
                setY(desplazamientoY);
            }
        }
    }
    public boolean enColision(int desplazamientoX, int desplazamientoY){
        boolean colision = false;
        int posicionX = x + desplazamientoX;
        int posicionY = y + desplazamientoY;
        int margenIzq = -19;
        int margenDer = 25;
        int margenSuperior = -4;
        int margenInferior = 31;
        int bordeIzq = (posicionX + margenDer) / sprite.getLado();
        int bordeDer = (posicionX + margenDer + margenIzq) / sprite.getLado();
        int bordeSuperior = (posicionY + margenInferior) / sprite.getLado();
        int bordeInferior = (posicionY + margenInferior + margenSuperior) / sprite.getLado();

        if(mapa.getCuadrosCatalogo(bordeIzq+bordeSuperior*mapa.getAncho()).getSolido()){
            colision = true;
        }if(mapa.getCuadrosCatalogo(bordeIzq+bordeInferior*mapa.getAncho()).getSolido()){
            colision = true;
        }if(mapa.getCuadrosCatalogo(bordeDer+bordeSuperior*mapa.getAncho()).getSolido()){
            colision = true;
        }if(mapa.getCuadrosCatalogo(bordeDer+bordeInferior*mapa.getAncho()).getSolido()){
            colision = true;
        }
        return colision;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
