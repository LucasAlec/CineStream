package model;

public class Serie implements Conteudo {
    private String nome;
    private String genero;
    private double avaliacao;
    private String quantidadeAvaliacoes;
    private int ano;
    private String streamingService;
    private int temporadas;
    private int episodios;

    public Serie(String nome, String genero, double avaliacao, String quantidadeAvaliacoes, int ano, String streamingService, int temporadas, int episodios) {
        this.nome = nome;
        this.genero = genero;
        this.avaliacao = avaliacao;
        this.quantidadeAvaliacoes = quantidadeAvaliacoes;
        this.ano = ano;
        this.streamingService = streamingService;
        this.temporadas = temporadas;
        this.episodios = episodios;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getGenero() {
        return genero;
    }

    @Override
    public double getAvaliacao() {
        return avaliacao;
    }

    @Override
    public String getQuantidadeAvaliacoes() {
        return quantidadeAvaliacoes;
    }

    @Override
    public int getAno() {
        return ano;
    }

    @Override
    public String getStreamingService() {
        return streamingService;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public int getEpisodios() {
        return episodios;
    }
}
