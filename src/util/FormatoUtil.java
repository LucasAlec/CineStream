package util;

public class FormatoUtil {

    public static String converterAvaliacaoEmEstrelas(double avaliacao) {
        int estrelas = (int) Math.round(avaliacao / 2); // Converte a escala de 10 para 5
        return "★".repeat(estrelas) + "☆".repeat(5 - estrelas); // Preenche com estrelas e estrelas vazias
    }
}
