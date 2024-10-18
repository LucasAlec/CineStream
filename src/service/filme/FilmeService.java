package service.filme;

import model.Filme;

import java.util.List;

public interface FilmeService {
    List<Filme> buscarFilmesPorNome(String nome);
    List<Filme> buscarFilmesPorAno(int ano);
    List<Filme> sugerirFilmesPorGenero(String genero);
    Filme encontrarMelhorFilme();
}
