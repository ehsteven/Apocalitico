package juego;

import control.Teclado;
import entes.criatura.Jugador;
import graficos.Pantalla;
import graficos.Sprite;
import mapa.*;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;

public class Juego extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;

    private static final int ANCHO = 800;
    private static final int ALTO = 600;
    private static volatile boolean enfuncionamiento = false;

    private static String CONTADOR_APS = "";
    private static String CONTADOR_FPS = "";

    private  static final String NOMBRE = "Juego";

    private static int aps = 0;
    private static int fps = 0;

    private static JFrame ventana;
    private  static Thread thread;
    private static Teclado teclado;
    private static Pantalla pantalla;
    private static Mapa mapa;
    private static Jugador jugador;

    private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);

    private static int[]  pixeles = ((DataBufferInt) imagen.getRaster()
            .getDataBuffer()).getData();

    public Juego(){
        setPreferredSize(new Dimension(ANCHO, ALTO));

        pantalla = new Pantalla(ANCHO, ALTO);
        //mapa = new MapaGenerado(124, 124);
        teclado = new Teclado();
        addKeyListener(teclado);

        mapa = new MapaCargado("/Mapas/mapaSub.png");
        jugador = new Jugador(mapa, teclado, Sprite.CAMINAR_DER_2, 48, 48);

        ventana = new JFrame(NOMBRE);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLayout(new BorderLayout());
        ventana.add(this, BorderLayout.CENTER);
        ventana.setUndecorated(true);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.iniciar();
    }

    private synchronized void iniciar(){
        enfuncionamiento = true;
        thread = new Thread(this, "Graficos");
        thread.start();
    }

    private synchronized void detener() {
        enfuncionamiento = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void actualizar(){
        teclado.actualizar();
        jugador.actualizar();
        if(teclado.salir){
            System.exit(0);
        }
        aps++;
    }

    private void mostrar() {
        BufferStrategy estrategia = getBufferStrategy();
        if (estrategia == null) {
            createBufferStrategy(3);
            return;
        }
        pantalla.limpiar();
        mapa.mostrar(jugador.getX() - pantalla.getAncho()/2 + jugador.getSprite().getLado()/2,
                jugador.getY() - pantalla.getAlto()/2 + jugador.getSprite().getLado()/2, pantalla);
        jugador.mostrar(pantalla);

        System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);
        Graphics g = estrategia.getDrawGraphics();
        g.drawImage(imagen, 0,0, getWidth(), getHeight(), null);
        g.setColor(Color.RED);
        g.drawString(CONTADOR_APS, 10,20);
        g.drawString(CONTADOR_FPS, 10,35);
        g.drawString("X: "+jugador.getX(), 10, 50);
        g.drawString("Y: "+jugador.getY(), 10, 65);
        g.dispose();

        estrategia.show();
        fps++;
    }

    @Override
    public void run() {
        final int NS_POR_SEGUNDO = 1000000000;
        final byte APS_OJETIVO = 60;
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO/APS_OJETIVO;
        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();
        double tiempoTranscurrido;
        double delta = 0;
        requestFocus();

        while(enfuncionamiento){
            final long inicioBucle = System.nanoTime();
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;
            delta += tiempoTranscurrido/NS_POR_ACTUALIZACION;

            while(delta >= 1){
                actualizar();
                delta--;
            }
            mostrar();
            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO){
                CONTADOR_APS = "APS: " + aps;
                CONTADOR_FPS = "FPS: " + fps;
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
            }
        }
    }
}
