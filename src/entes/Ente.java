package entes;

import mapa.Mapa;

public abstract class Ente {
    protected int x;
    protected int y;
    private boolean eliminado = false;
    protected Mapa mapa;

    public void actualizar(){

    }

    public void mostrar(){

    }

    public void eliminar(){
        eliminado = true;
    }

    public int getX() {
        return x;
    }

    public void setX(int desplazamientoX) {
        this.x += desplazamientoX;
    }

    public int getY() {
        return y;
    }

    public void setY(int desplazamientoY) {
        this.y += desplazamientoY;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
}
