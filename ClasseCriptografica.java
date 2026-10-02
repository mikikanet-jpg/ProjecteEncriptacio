import java.util.Base64;

public class ClasseCriptografica {

    public static String encripta(String missatge, String clau) {

        if (clau.isEmpty()) {
            throw new IllegalArgumentException("La clau no pot estar buida");
        }

        byte[] missatgeBytes = missatge.getBytes();
        byte[] clauBytes = clau.getBytes();

        byte[] resultat = new byte[missatgeBytes.length];

        for (int i = 0; i < missatgeBytes.length; i++) {

            // Repetim la clau si és més curta que el missatge
            int posicioClau = i % clauBytes.length;

            // Convertim els bytes a valors entre 0 i 255
            int caracterMissatge = missatgeBytes[i] & 0xFF;
            int caracterClau = clauBytes[posicioClau] & 0xFF;

            // Fem XOR
            int valor = caracterMissatge ^ caracterClau;

            // Sumem la posició i fem que el resultat quedi entre 0 i 255
            valor = (valor + i) % 256;

            resultat[i] = (byte) valor;
        }

        // Convertim el resultat a Base64
        return Base64.getEncoder().encodeToString(resultat);
    }


    public static String desencripta(String missatgeXifrat, String clau) {

        if (clau.isEmpty()) {
            throw new IllegalArgumentException("La clau no pot estar buida");
        }

        // Recuperem els bytes originals des de Base64
        byte[] missatgeBytes =
                Base64.getDecoder().decode(missatgeXifrat);

        byte[] clauBytes = clau.getBytes();

        byte[] resultat = new byte[missatgeBytes.length];

        for (int i = 0; i < missatgeBytes.length; i++) {

            // Repetim la clau
            int posicioClau = i % clauBytes.length;

            // Convertim el byte a un valor entre 0 i 255
            int valor = missatgeBytes[i] & 0xFF;

            // Desfem la suma de la posició
            valor = (valor - i + 256) % 256;

            // Desfem el XOR
            int caracterClau = clauBytes[posicioClau] & 0xFF;

            valor = valor ^ caracterClau;

            resultat[i] = (byte) valor;
        }

        return new String(resultat);
    }
}