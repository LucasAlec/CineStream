package database;

import model.Filme;
import model.Serie;

import java.util.List;

public interface CarregarDados {
    List<Filme> carregarFilmes();
    List<Serie> carregarSeries();
}
