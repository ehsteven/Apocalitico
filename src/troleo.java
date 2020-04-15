import java.util.Random;

public class troleo {
    private static Random hola = new Random();

    public static void main(String args[]) throws InterruptedException {
        int num = hola.nextInt(103);
        for (int i = 0; i < 11; i++) {
            Thread.sleep(1000);
            System.out.println(i);
        }
        System.out.println("El numero es: "+ num);
    }
}
