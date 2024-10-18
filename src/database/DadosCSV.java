package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DadosCSV {

    // Método para ler um arquivo CSV e retornar uma lista de arrays de Strings
    public static List<String[]> lerCSV(String caminho) {
        List<String[]> dados = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            // Lê cada linha do arquivo
            while ((linha = br.readLine()) != null) {
                // Divide a linha pelos separadores de vírgula e adiciona à lista
                String[] valores = linha.split(",");
                dados.add(valores);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
        return dados;
    }
}
