package model;

public class Filme implements Conteudo {
    private String nome;
    private int ano;
    private String genero;
    private double avaliacao;
    private String quantidadeAvaliacoes;
    private String url;
    private String streamingService;
    private String duracao;

    public Filme(String nome, int ano, String genero, double avaliacao, String quantidadeAvaliacoes, String url, String streamingService, String duracao) {
        this.nome = nome;
        this.ano = ano;
        this.genero = genero;
        this.avaliacao = avaliacao;
        this.quantidadeAvaliacoes = quantidadeAvaliacoes;
        this.url = url;
        this.streamingService = streamingService;
        this.duracao = duracao;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getAno() {
        return ano;
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
    public String getStreamingService() {
        return streamingService;
    }

    public String getDuracao() {
        return duracao;
    }

    public String getUrl() {
        return url;
    }
}