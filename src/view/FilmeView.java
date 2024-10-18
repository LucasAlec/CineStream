package view;

import model.Filme;
import service.filme.FilmeService;
import util.FilmesUtil;

import java.util.List;
import java.util.Scanner;

public class FilmeView {
    private FilmeService filmeService;

    public FilmeView(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    public void exibirFilmeView() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n==== Submenu de Filmes ====");
            System.out.println("1 - Buscar filmes por nome");
            System.out.println("2 - Buscar filmes por ano");
            System.out.println("3 - Sugerir filmes por gênero");
            System.out.println("4 - Mostrar o melhor filme");
            System.out.println("5 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        buscarFilmesPorNome(scanner);
                        break;
                    case 2:
                        buscarFilmesPorAno(scanner);
                        break;
                    case 3:
                        sugerirFilmesPorGenero(scanner);
                        break;
                    case 4:
                        mostrarMelhorFilme();
                        break;
                    case 5:
                        continuar = false;
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }
    }

    private void buscarFilmesPorNome(Scanner scanner) {
        System.out.print("Digite o nome do filme ou parte dele para buscar: ");
        String nome = scanner.nextLine();
        List<Filme> filmes = filmeService.buscarFilmesPorNome(nome);
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme encontrado com esse nome.");
        } else {
            filmes.forEach(FilmesUtil::exibirInfoFilme);
        }
    }

    private void buscarFilmesPorAno(Scanner scanner) {
        System.out.print("Digite o ano para buscar filmes: ");
        try {
            int ano = Integer.parseInt(scanner.nextLine());
            List<Filme> filmes = filmeService.buscarFilmesPorAno(ano);
            if (filmes.isEmpty()) {
                System.out.println("Nenhum filme encontrado para o ano informado.");
            } else {
                filmes.forEach(FilmesUtil::exibirInfoFilme);
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, digite um ano válido.");
        }
    }

    private void sugerirFilmesPorGenero(Scanner scanner) {
        System.out.print("Digite o gênero para sugerir filmes: ");
        String genero = scanner.nextLine();
        List<Filme> filmes = filmeService.sugerirFilmesPorGenero(genero);
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme encontrado para o gênero informado.");
        } else {
            filmes.forEach(FilmesUtil::exibirInfoFilme);
        }
    }

    private void mostrarMelhorFilme() {
        Filme melhorFilme = filmeService.encontrarMelhorFilme();
        if (melhorFilme != null) {
            System.out.println("O melhor filme é:");
            FilmesUtil.exibirInfoFilme(melhorFilme);
        } else {
            System.out.println("Não há filmes disponíveis no momento.");
        }
    }
}
