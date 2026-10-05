// Classes de l'API de criptografia de Java que necessitem (apartat 6.1)
import java.nio.charset.StandardCharsets;                 // Realitza el xifrat i el desxifrat
import java.util.Base64;     // Converteix els bytes de la clau en una clau AES
import javax.crypto.Cipher;   // Per fixar la codificació del text (UTF-8)
import javax.crypto.spec.SecretKeySpec;                    // Per passar de bytes a text (i al revés)

/**
 * ClasseAES
 *
 * Classe independent i reutilitzable que xifra i desxifra missatges
 * amb l'algoritme estàndard AES (Advanced Encryption Standard).
 *
 * No conté el programa principal: qui l'utilitza és ProgramaPrincipalAES.
 *
 * Esquema del procés:
 *   MISSATGE + CLAU           -> AES -> MISSATGE XIFRAT (Base64)
 *   MISSATGE XIFRAT + CLAU    -> AES -> MISSATGE ORIGINAL
 *
 * Important: la clau s'ha de passar tal qual, sense transformar-la.
 * Per tant, ha de tenir exactament 16, 24 o 32 caràcters (1 byte per
 * caràcter si només s'utilitzen caràcters ASCII), que equivalen a claus
 * AES de 128, 192 o 256 bits.
 */
public class ClasseAES {

    // Transformació que utilitzarem: "algoritme/mode/farciment".
    //  - AES        : l'algoritme de xifrat.
    //  - ECB        : mode de xifrat (cada bloc de 16 bytes es xifra per separat).
    //  - PKCS5Padding: farciment per completar l'últim bloc fins a 16 bytes.
    // ECB no necessita IV (vector d'inicialització), per això és el mode
    // més senzill per a aquesta pràctica.
    private static final String TRANSFORMACIO = "AES/ECB/PKCS5Padding";

    // Nom de l'algoritme que s'indica a SecretKeySpec.
    private static final String ALGORITME = "AES";

    /**
     * Encripta un missatge amb AES.
     *
     * @param missatge text en clar que volem protegir
     * @param clau     clau secreta (16, 24 o 32 caràcters)
     * @return el missatge xifrat, codificat en Base64
     * @throws IllegalArgumentException si la clau no és vàlida per a AES
     */
    public static String encripta(String missatge, String clau) {
        try {
            // 1. Preparem la clau: la convertim a bytes i creem una clau AES.
            //    Si la mida no és 16, 24 o 32 bytes, el xifrat fallarà més avall.
            byte[] clauBytes = clau.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec clauAES = new SecretKeySpec(clauBytes, ALGORITME);

            // 2. Creem i configurem el sistema de xifrat en mode ENCRIPTACIÓ.
            Cipher cipher = Cipher.getInstance(TRANSFORMACIO);
            cipher.init(Cipher.ENCRYPT_MODE, clauAES);

            // 3. Convertim el missatge a bytes (AES treballa amb bytes, no amb text).
            byte[] missatgeBytes = missatge.getBytes(StandardCharsets.UTF_8);

            // 4. Xifrem el missatge: doFinal() processa tots els bytes
            //    i afegeix el farciment necessari al final.
            byte[] xifrat = cipher.doFinal(missatgeBytes);

            // 5. Els bytes xifrats no són text llegible, així que els
            //    convertim a Base64 per poder retornar-los com a String.
            return Base64.getEncoder().encodeToString(xifrat);

        } catch (Exception e) {
            // Cas típic: InvalidKeyException quan la clau no fa 16, 24 o 32 bytes.
            throw new IllegalArgumentException(
                    "No s'ha pogut encriptar: " + e.getMessage(), e);
        }
    }

    /**
     * Desencripta un missatge xifrat amb AES.
     *
     * @param missatgeXifrat text xifrat en Base64 (resultat d'encripta)
     * @param clau           la mateixa clau que es va utilitzar per encriptar
     * @return el missatge original
     * @throws IllegalArgumentException si la clau no és vàlida, si és
     *         incorrecta o si el text xifrat no és vàlid
     */
    public static String desencripta(String missatgeXifrat, String clau) {
        try {
            // 1. Preparem la clau exactament igual que en encriptar.
            byte[] clauBytes = clau.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec clauAES = new SecretKeySpec(clauBytes, ALGORITME);

            // 2. Creem i configurem el sistema de xifrat en mode DESENCRIPTACIÓ.
            Cipher cipher = Cipher.getInstance(TRANSFORMACIO);
            cipher.init(Cipher.DECRYPT_MODE, clauAES);

            // 3. Recuperem els bytes xifrats a partir del text en Base64.
            byte[] xifrat = Base64.getDecoder().decode(missatgeXifrat);

            // 4. Desxifrem els bytes. doFinal() també treu el farciment.
            //    Amb una clau incorrecta, normalment el farciment no
            //    quedarà bé i es llançarà una BadPaddingException.
            byte[] missatgeBytes = cipher.doFinal(xifrat);

            // 5. Tornem a convertir els bytes en text.
            return new String(missatgeBytes, StandardCharsets.UTF_8);

        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "No s'ha pogut desencriptar: " + e.getMessage(), e);
        }
    }
}