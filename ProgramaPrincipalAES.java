import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * ProgramaPrincipalAES
 *
 * Programa principal que utilitza ClasseAES.
 * Només s'encarrega de mostrar dades i de cridar els mètodes de la classe;
 * no sap com funciona AES per dins.
 *
 *   Programa principal -> ClasseAES -> encripta / desencripta
 *
 * Apartat 6.3: demostració bàsica amb les dades inicials (fixes).
 * Apartat 6.4: proves en què l'usuari pot escriure la clau o el missatge
 *              que vulgui. Si prem Enter sense escriure res, es fa servir
 *              el valor per defecte que s'indica entre parèntesis.
 */
public class ProgramaPrincipalAES {

    // Scanner per llegir dades per teclat (UTF-8 per no perdre els accents)
    public static Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

    // Llegeix una línia; si l'usuari no escriu res (o no hi ha entrada),
    // retorna el valor per defecte.
    public static String llegeix(String pregunta, String perDefecte) {
        System.out.print(pregunta + " (Enter = \"" + perDefecte + "\"): ");
        String linia = scanner.hasNextLine() ? scanner.nextLine() : "";
        if (linia.isEmpty()) {
            System.out.println(perDefecte);
            return perDefecte;
        }
        return linia;
    }

    public static void main(String[] args) {

        // ---------------------------------------------------------
        // 6.3. Programa principal amb les dades inicials
        // ---------------------------------------------------------
        String missatge = "àëëóóü çñ ï¿S";
        String clau = "1234567890123456"; // 16 caràcters = 16 bytes = AES-128

        System.out.println("=== 6.3. Demostració AES ===");

        // Mostrem el missatge original
        System.out.println("Missatge original : " + missatge);
        System.out.println("Mida de la clau   : " + clau.length() + " bytes");

        // Encriptem amb AES
        String missatgeXifrat = ClasseAES.encripta(missatge, clau);
        System.out.println("Missatge xifrat   : " + missatgeXifrat);

        // Desencriptem amb la mateixa clau
        String missatgeRecuperat = ClasseAES.desencripta(missatgeXifrat, clau);
        System.out.println("Missatge recuperat: " + missatgeRecuperat);


        // Claus de 24 i 32 bytes: també són vàlides (AES-192 i AES-256).
        System.out.println();
        System.out.println("Claus vàlides de 24 i 32 bytes:");
        String clau24 = "123456789012345678901234";
        String clau32 = "12345678901234567890123456789012";
        System.out.println("  24 bytes -> " + ClasseAES.desencripta(
                ClasseAES.encripta(missatge, clau24), clau24));
        System.out.println("  32 bytes -> " + ClasseAES.desencripta(
                ClasseAES.encripta(missatge, clau32), clau32));
    }
}