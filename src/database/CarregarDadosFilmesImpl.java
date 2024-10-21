package database;

import model.Filme;

import java.util.List;
import java.util.stream.Collectors;

public class CarregarDadosFilmesImpl implements CarregarDados<Filme> {
    private String caminhoCsvFilmes;

    public CarregarDadosFilmesImpl(String caminhoCsvFilmes) {
        this.caminhoCsvFilmes = caminhoCsvFilmes;
    }

    @Override
    public List<Filme> carregarDadosDoArquivo() {
        return DadosCSV.lerCSV(caminhoCsvFilmes)
                .stream()
                .skip(1) // Ignora a primeira linha
                .map(this::converterParaFilme)
                .filter(filme -> filme != null) // Filtra filmes inválidosss
                .collect(Collectors.toList());
    }

    private Filme converterParaFilme(String[] dados) {
        if (dados.length < 7) {
            System.out.println("Linha inválida no CSV: dados incompletos. Dados: " + String.join(", ", dados));
            return null; // Retorna nulo para linhas inválidas
        }

        String nome = dados[0];
        int ano;
        String genero = dados[2];
        double imdbRating;
        String quantidadeAvaliacoes;
        String url = dados[4];
        String streamingService = dados[5];
        String duracao = dados[6];

        try {
            ano = Integer.parseInt(dados[1]);
        } catch (NumberFormatException e) {
            System.out.println("Ano inválido para o filme: " + nome);
            ano = 0; // Define um valor padrão
        }

        try {
            String[] partesRating = dados[3].split(" ");
            imdbRating = Double.parseDouble(partesRating[0]);
            quantidadeAvaliacoes = partesRating.length > 1 ? partesRating[1] : "0";
        } catch (Exception e) {
            System.out.println("Erro ao processar avaliações para o filme: " + nome);
            imdbRating = 0.0; // Define um valor padrão
            quantidadeAvaliacoes = "0"; // Define um valor padrão
        }

        return new Filme(nome, ano, genero, imdbRating, quantidadeAvaliacoes, url, streamingService, duracao);
    }
}
