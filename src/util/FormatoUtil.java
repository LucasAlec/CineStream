package util;

public class FormatoUtil {

    // Código ANSI para cor amarela
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static String converterAvaliacaoEmEstrelas(double avaliacao) {
        int estrelas = (int) Math.round(avaliacao / 2); // Converte a escala de 10 para 5
        StringBuilder estrelasAmarelas = new StringBuilder();

        // Adiciona as estrelas preenchidas em amarelo
        for (int i = 0; i < estrelas; i++) {
            estrelasAmarelas.append(ANSI_YELLOW).append("★").append(ANSI_RESET);
        }
        // Adiciona as estrelas vazias em branco
        for (int i = estrelas; i < 5; i++) {
            estrelasAmarelas.append("☆");
        }
        return estrelasAmarelas.toString();
    }
}