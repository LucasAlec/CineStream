package util;

public class ValidacaoUtil {

    public static boolean validarEntrada(String entrada, String nomeConteudo) {
        return nomeConteudo.toLowerCase().startsWith(entrada.toLowerCase());
    }
}
