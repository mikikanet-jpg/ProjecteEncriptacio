import java.util.Scanner;

public class ProgramaPrincipal {

    // Scanner para poder escribir datos por el teclado
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Pedimos el mensaje que queremos encriptar
        System.out.print("Introdueix el missatge: ");
        String missatge = scanner.nextLine();

        // Pedimos la clave que utilizaremos para encriptar
        System.out.print("Introdueix la clau: ");
        String clau = scanner.nextLine();

        // Comprobamos que la clave no esté vacía
        if (clau.isEmpty()) {
            System.out.println("ERROR: La clau no pot estar buida.");
            return;
        }

        // Llamamos al método encripta de ClasseCriptografica
        // y guardamos el resultado en missatgeXifrat
        String missatgeXifrat =
                ClasseCriptografica.encripta(missatge, clau);

        // Mostramos el mensaje encriptado
        System.out.println();
        System.out.println("Missatge encriptat:");
        System.out.println(missatgeXifrat);


        // Ahora pedimos el mensaje encriptado
        // para poder hacer la desencriptación
        System.out.println();
        System.out.print("Introdueix el missatge encriptat: ");
        String missatgeIntroduit = scanner.nextLine();

        // Pedimos la clave que se quiere utilizar
        // para desencriptar
        System.out.print("Introdueix la clau per desencriptar: ");
        String clauDesencriptacio = scanner.nextLine();

        // Llamamos al método que realiza la desencriptación
        desencriptarMissatge(
                missatgeIntroduit,
                clauDesencriptacio
        );
    }


    // Método encargado de desencriptar el mensaje
    public static void desencriptarMissatge(
            String missatgeXifrat,
            String clau) {

        // Comprobamos que la clave no esté vacía
        if (clau.isEmpty()) {
            System.out.println(
                    "ERROR: La clau no pot estar buida."
            );
            return;
        }

        // Intentamos desencriptar el mensaje utilizando
        // la clave que ha introducido el usuario
        String missatgeDesencriptat =
                ClasseCriptografica.desencripta(
                        missatgeXifrat,
                        clau
                );

        // Mostramos el resultado
        System.out.println();
        System.out.println("Missatge desencriptat:");
        System.out.println(missatgeDesencriptat);
    }
}