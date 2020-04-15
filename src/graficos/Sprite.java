package graficos;

public class Sprite {
    private final int lado;
    private int x;
    private int y;
    public int[] pixeles;
    private HojaSprites hoja;

    //coleccion de sprites del personaje
    public static final Sprite ESPALDA_1 = new Sprite(32, 0, 0, 0, HojaSprites.jugador);
    public static final Sprite ESPALDA_2 = new Sprite(32, 1, 0, 0, HojaSprites.jugador);
    public static final Sprite FRENTE_1 = new Sprite(32, 0, 1, 0, HojaSprites.jugador);
    public static final Sprite FRENTE_2 = new Sprite(32, 1, 1, 0, HojaSprites.jugador);
    public static final Sprite CAMINAR_IZQ_1 = new Sprite(32, 0, 2, 0, HojaSprites.jugador);
    public static final Sprite CAMINAR_IZQ_2 = new Sprite(32, 1, 2, 0, HojaSprites.jugador);
    public static final Sprite CAMINAR_DER_1 = new Sprite(32, 0, 3, 0, HojaSprites.jugador);
    public static final Sprite CAMINAR_DER_2 = new Sprite(32, 1, 3, 0, HojaSprites.jugador);

    //fin de la coleccion

    //coleccion de sprites del mapa sub
    public static Sprite FONDO = new Sprite(32, 2, 1,0, HojaSprites.mapa);
    public static Sprite SUELO = new Sprite(32, 3, 1,0, HojaSprites.mapa);
    public static Sprite ANTORCHA = new Sprite(32, 1, 2,0, HojaSprites.mapa);
    public static Sprite PUERTA_IZQ = new Sprite(32, 4, 1,0, HojaSprites.mapa);
    public static Sprite PUERTA_DER = new Sprite(32, 4, 1,1, HojaSprites.mapa);
    public static Sprite PUERTA_INVERTIDA_IZQ = new Sprite(32, 4, 1,3, HojaSprites.mapa);
    public static Sprite PUERTA_INVERTIDA_DER = new Sprite(32, 4, 1,2, HojaSprites.mapa);
    //fin de la coleccion

    //coleccion de sprites del mapa primario;
//    public static Sprite PARED = new Sprite(32, 0, 0,0, HojaSprites.mapa);
//    public static Sprite ZACATE = new Sprite(32, 1, 0,0, HojaSprites.mapa);
//    public static Sprite CALLE = new Sprite(32, 0, 1,0, HojaSprites.mapa);
//    public static Sprite PUERTA = new Sprite(32, 4, 0,0, HojaSprites.mapa);
//    public static Sprite ARBOL = new Sprite(32, 1, 1,0, HojaSprites.mapa);
//    public static Sprite INMEDIO = new Sprite(32, 2, 0,0, HojaSprites.mapa);
    public static Sprite VACIO = new Sprite(32, 0);
    //fin de la coleccion de sprites

    public Sprite(final int lado, final int columna, final int fila, final int version, final HojaSprites hoja){
        this.lado = lado;
        pixeles = new int[lado *lado];
        this.x = columna * lado;
        this.y = fila * lado;
        this.hoja = hoja;
        cargarSprite(version);
    }

    public  Sprite(final int lado, final int color){
        this.lado = lado;
        pixeles = new int[lado * lado];
        for(int i = 0; i < pixeles.length; i++){
            pixeles[i] = color;
        }
    }

    public int getLado() {
        return lado;
    }

    public void cargarSprite(int version){
        if(version == 0){
            cargaNormal();
        }else{
            cargaManipulada(version);
        }
    }

    public void cargaNormal(){
        for(int y = 0; y < lado; y++){
            for(int x = 0; x < lado; x++){
                pixeles[x + y * lado] = hoja.pixeles[(x + this.x) +
                        (y + this.y) * hoja.getAncho()];
            }
        }
    }

    private void cargaManipulada(int version){
        int[] pixelesTemporales = iniciarPixelesTemporales();
        switch (version) {
            case 1:
                invertirX(pixelesTemporales);
                break;
            case 2:
                invertirY(pixelesTemporales);
                break;
            case 3:
                invertirXY(pixelesTemporales);
                break;
            case 4:
                rotar90Izq(pixelesTemporales);
                break;
            case 5:
                rotar90Der(pixelesTemporales);
                break;
            case 6:
                rotarI90InvertirY(pixelesTemporales);
                break;
            case 7:
                rotarD90InvertirY(pixelesTemporales);
                break;
            default:
                cargaNormal();

        }
    }

    private int[] iniciarPixelesTemporales(){
        int[] pixelesTemporales = new int[lado*lado];
        for(int y = 0; y < lado; y++) {
            for (int x = 0; x < lado; x++) {
                pixelesTemporales[x + y * lado] = hoja.pixeles[(x + this.x) +
                        (y + this.y) * hoja.getAncho()];
            }
        }
        return pixelesTemporales;
    }

    private void invertirXY(int[] pixelesTemporales){
        for(int i = 0; i < pixeles.length; i++){
            pixeles[i] = pixelesTemporales[pixelesTemporales.length - 1 - i];
        }
    }

    private void invertirX(int[] pixelesTemporales){
        int i = 0;
        for(y = 0; y < lado; y++){
            for(x = lado-1; x > -1; x--){
                pixeles[i] = pixelesTemporales[x + y * lado];
                i++;
            }
        }
    }

    private void invertirY(int[] pixelesTemporales){
        int i = 0;
        for(y = lado-1; y > -1; y--){
            for(x = 0; x < lado; x++){
                pixeles[i] = pixelesTemporales[x + y * lado];
                i++;
            }
        }
    }

    private void rotar90Izq(int[] pixelesTemporales){
        int i = 0;
        for(x = lado-1; x > -1; x--){
            for(y = 0; y < lado; y++){
                pixeles[i] = pixelesTemporales[x + y * lado];
                i++;
            }
        }
    }

    private void rotar90Der(int[] pixelesTemporales){
        int i = 0;
        for(x = 0; x < lado; x++){
            for(y = lado -1; y > -1; y--){
                pixeles[i] = pixelesTemporales[x + y * lado];
                i++;
            }
        }
    }

    private void rotarI90InvertirY(int[] pixelesTemporales){
        int i = 0;
        for(x = 0; x < lado; x++){
            for(y = 0; y < lado; y++){
                pixeles[i] = pixelesTemporales[x + y * lado];
                i++;
            }
        }
    }

    private void rotarD90InvertirY(int[] pixelesTemporales){
        int i = 0;
        for(x = lado - 1; x > -1; x--){
            for(y = lado -1 ; y > -1; y--){
                pixeles[i] = pixelesTemporales[x + y * lado];
                i++;
            }
        }
    }
}