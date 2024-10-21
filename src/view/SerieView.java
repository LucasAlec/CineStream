package view;

import exception.AnoInvalidoException;
import exception.OpcaoInvalidaException;
import exception.SerieNaoEncontradaException;
import exception.SeriesIndisponiveisException;
import model.Serie;
import service.serie.SerieService;
import util.SeriesUtil;
import util.GeneroUtil;
import util.PaginacaoUtil;

import java.util.List;
import java.util.Scanner;

public class SerieView {
    private SerieService serieService;

    public SerieView(SerieService serieService) {
        this.serieService = serieService;
    }

    public void exibirSerieView() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n+--------------------------------------------------+");
            System.out.println("|                  🎬 Séries 🎬                    |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|  1️⃣ → 🔎 Buscar séries por nome                  |");
            System.out.println("|  2️⃣ →  📅 Buscar por ano de estréia              |");
            System.out.println("|  3️⃣ →  🎭 Sugerir por gênero                     |");
            System.out.println("|  4️⃣ →  🏆 Melhor série                           |");
            System.out.println("|  5️⃣ →  🔄 Voltar ao menu principal               |");
            System.out.println("+--------------------------------------------------+");
            System.out.print("📝 Escolha uma opção: ");
            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        buscarSeriesPorNome(scanner);
                        break;
                    case 2:
                        buscarSeriesPorAno(scanner);
                        break;
                    case 3:
                        sugerirSeriesPorGenero(scanner);
                        break;
                    case 4:
                        mostrarMelhorSerie();
                        break;
                    case 5:
                        continuar = false;
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        throw new OpcaoInvalidaException("Opção inválida. Por favor, escolha uma opção válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println(" ❌ Entrada inválida. Por favor, digite um número.");
            } catch (OpcaoInvalidaException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void buscarSeriesPorNome(Scanner scanner) {
        System.out.print("Digite o nome da série ou parte dele para buscar: ");
        String nome = scanner.nextLine();
        List<Serie> series = serieService.buscarSeriePorNome(nome);
        try {
            if (series.isEmpty()) {
                throw new SerieNaoEncontradaException("Nenhuma série encontrada com esse nome.");
            } else {
                series.forEach(SeriesUtil::exibirInfoSerie);
            }
        } catch (SerieNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarSeriesPorAno(Scanner scanner) {
        System.out.print("Digite o ano para buscar séries: ");
        try {
            int ano = Integer.parseInt(scanner.nextLine());

            // o ano da primeira série lançada foi em 1951
            if (ano < 1951 || ano > 2024){
                throw new AnoInvalidoException("⚠️ Ano inválido. Por favor, digite um ano até 2024.");
            }

            List<Serie> series = serieService.buscarSeriesPorAno(ano);
            if (series.isEmpty()) {
                System.out.println("Nenhuma série encontrada para o ano informado.");
            } else {
                series.forEach(SeriesUtil::exibirInfoSerie);
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, digite um ano válido.");
        } catch (AnoInvalidoException e){
            System.out.println(e.getMessage());
        }
    }

    private void sugerirSeriesPorGenero(Scanner scanner) {
        List<String> generosDisponiveis = List.of("Action", "Adventure", "Comedy", "Drama",
                "Horror", "Mystery", "Sci-Fi", "Fantasy",
                "Romance", "Crime");
        GeneroUtil.exibirGeneros(generosDisponiveis);
        System.out.print("Digite o número do gênero para sugerir séries: ");

        try {
            int escolha = Integer.parseInt(scanner.nextLine());
            if (escolha > 0 && escolha <= generosDisponiveis.size()) {
                String generoSelecionado = generosDisponiveis.get(escolha - 1);
                List<Serie> series = serieService.sugerirSeriesPorGenero(generoSelecionado);
                if (!series.isEmpty()) {
                    PaginacaoUtil.exibirSeriesPaginadas(series, scanner);
                } else {
                    System.out.println("⚠️ Nenhuma série encontrada para o gênero selecionado.");
                }
            } else {
                throw new OpcaoInvalidaException("⚠️ Opção inválida. Por favor, escolha um número da lista.");
            }
        } catch (OpcaoInvalidaException e){
            System.out.println(e.getMessage());
        }
    }

    private void mostrarMelhorSerie() {
        Serie melhorSerie = serieService.encontrarMelhorSerie();

        try {
            if (melhorSerie != null) {
                System.out.println("A melhor série é:");
                SeriesUtil.exibirInfoSerie(melhorSerie);
            } else {
                throw new SeriesIndisponiveisException("Não há séries disponíveis no momento.");
            }
        } catch (SeriesIndisponiveisException e) {
            System.out.println(e.getMessage());
        }
    }
}

