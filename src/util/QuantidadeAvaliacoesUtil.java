package util;

public class QuantidadeAvaliacoesUtil {

    // Método para converter a quantidade de avaliações em um número
    public static int converter(String quantidade) {
        quantidade = quantidade.replace("(", "").replace(")", "").trim(); // Remove parênteses
        if (quantidade.endsWith("k")) {
            // Remove o "k" e multiplica por 1000
            return (int) (Double.parseDouble(quantidade.replace("k", "")) * 1000);
        } else if (quantidade.endsWith("m")) {
            // Remove o "m" e multiplica por 1.000.000
            return (int) (Double.parseDouble(quantidade.replace("m", "")) * 1000000);
        } else {
            // Tenta converter diretamente para inteiro
            return Integer.parseInt(quantidade);
        }
    }
}