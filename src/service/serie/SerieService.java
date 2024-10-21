package service.serie;

import model.Serie;

import java.util.List;
import java.util.Scanner;

public interface SerieService {
    List<Serie> buscarSeriePorNome(String nome);
    List<Serie> buscarSeriesPorAno(int ano);
    List<Serie> sugerirSeriesPorGenero(String genero);
    Serie encontrarMelhorSerie();
    List<Serie> obterTodasSeries();
}
