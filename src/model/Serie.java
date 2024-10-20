package model;

public class Serie implements Conteudo {
    private String nome;
    private String genero;
    private double avaliacao;
    private String quantidadeAvaliacoes;
    private String url;
    private int ano;
    private String streamingService;
    private int temporadas;
    private int episodios;

    public Serie(String nome, String genero, double avaliacao, String quantidadeAvaliacoes, String url, int ano, String streamingService, int temporadas, int episodios) {
        this.nome = nome;
        this.genero = genero;
        this.avaliacao = avaliacao;
        this.quantidadeAvaliacoes = quantidadeAvaliacoes;
        this.url = url;
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

    public String getUrl() {return url;}

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
