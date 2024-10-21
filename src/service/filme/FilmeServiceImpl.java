package service.filme;

import database.CarregarDados;
import database.CarregarDadosFilmesImpl;
import model.Filme;
import util.QuantidadeAvaliacoesUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FilmeServiceImpl implements FilmeService {

    private List<Filme> filmes;

    public FilmeServiceImpl(String caminhoCsvFilmes) {
        CarregarDados carregarDados = new CarregarDadosFilmesImpl(caminhoCsvFilmes);
        this.filmes = carregarDados.carregarDadosDoArquivo();
    }

    @Override
    public List<Filme> buscarFilmesPorNome(String nome) {
        return filmes.stream()
                .filter(filme -> filme.getNome().toLowerCase().startsWith(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Filme> buscarFilmesPorAno(int ano) {
        return filmes.stream()
                .filter(filme -> filme.getAno() == ano)
                .collect(Collectors.toList());
    }

    @Override
    public List<Filme> sugerirFilmesPorGenero(String genero) {
        List<Filme> filmesDoGenero = filmes.stream()
                .filter(filme -> {
                    String[] generosFilme = filme.getGenero().split(",\\s*");
                    return List.of(generosFilme).stream()
                            .anyMatch(generoFilme -> generoFilme.equalsIgnoreCase(genero));
                })
                .collect(Collectors.toList());

        Collections.shuffle(filmesDoGenero);
        return filmesDoGenero.stream().limit(5).collect(Collectors.toList());
    }

    @Override
    public Filme encontrarMelhorFilme() {
        return filmes.stream()
                .reduce((f1, f2) -> {
                    // Usar a utilidade para converter a quantidade de avaliações
                    double score1 = f1.getAvaliacao() * QuantidadeAvaliacoesUtil.converter(f1.getQuantidadeAvaliacoes());
                    double score2 = f2.getAvaliacao() * QuantidadeAvaliacoesUtil.converter(f2.getQuantidadeAvaliacoes());
                    return score1 > score2 ? f1 : f2;
                }).orElse(null);
    }

    @Override
    public List<Filme> obterTodosFilmes() {
        return new ArrayList<>(filmes);
    }
}