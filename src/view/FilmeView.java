package view;

import model.Filme;
import service.filme.FilmeService;
import util.FilmesUtil;
import util.GeneroUtil;
import util.PaginacaoUtil;

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

            System.out.println("\n+--------------------------------------------------+");
            System.out.println("|                 🎬   Filmes   🎬                 |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|  1️⃣ → 🔎 Buscar filmes por nome                  |");
            System.out.println("|  2️⃣ → 📅 Buscar por ano de estréia               |");
            System.out.println("|  3️⃣ → 🎭 Sugerir por gênero                      |");
            System.out.println("|  4️⃣ → 🏆 Melhor filme                            |");
            System.out.println("|  5️⃣ → 🔄 Voltar ao menu principal                |");
            System.out.println("+--------------------------------------------------+");
            System.out.print("📝 Escolha uma opção: ");

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
                System.out.println(" ❌ Entrada inválida. Por favor, digite um número.");
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
        // Lista de gêneros disponíveis (pode ser gerada dinamicamente a partir dos filmes)
        List<String> generosDisponiveis = List.of("Action", "Adventure", "Comedy", "Drama",
                "Horror", "Mystery", "Sci-Fi", "Fantasy",
                "Romance", "Crime");

        // Usa a utilidade para exibir os gêneros disponíveis em uma caixa formatada
        GeneroUtil.exibirGeneros(generosDisponiveis);

        System.out.print("Digite o número do gênero para sugerir filmes: ");
        int escolha = Integer.parseInt(scanner.nextLine());

        // Valida se a escolha é válida
        if (escolha > 0 && escolha <= generosDisponiveis.size()) {
            String generoSelecionado = generosDisponiveis.get(escolha - 1);

            // Sugerir filmes com base no gênero selecionado
            List<Filme> filmes = filmeService.sugerirFilmesPorGenero(generoSelecionado);

            if (!filmes.isEmpty()) {
                // Usa a utilidade de paginação para exibir os filmes
                PaginacaoUtil.exibirFilmesPaginados(filmes, scanner);
            } else {
                System.out.println("⚠️ Nenhum filme encontrado para o gênero selecionado.");
            }
        } else {
            System.out.println("⚠️ Opção inválida. Por favor, escolha um número da lista.");
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
