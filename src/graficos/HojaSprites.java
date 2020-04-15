package graficos;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HojaSprites {
    private final int ancho;
    private final int alto;
    public final int[] pixeles;

    public static HojaSprites mapa = new HojaSprites("/texturas/HojaMejorada.png",
            160, 192);
    public static HojaSprites jugador = new HojaSprites("/personajes/pelon.png",
            64, 128);

    public HojaSprites(final String ruta, final int ancho, final int alto){
        this.alto = alto;
        this.ancho = ancho;
        pixeles = new int[ancho * alto];

        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(HojaSprites.class.getResource(ruta));
            imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public int getAncho() {
        return ancho;
    }
}
