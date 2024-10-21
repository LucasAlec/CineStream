package view;


import exception.OpcaoInvalidaException;
import service.filme.FilmeService;
import service.serie.SerieService;
import service.serie.SerieServiceImpl;

import java.util.Scanner;

public class Menu {

    private FilmeView filmeView;
    private SerieView serieView;

    public Menu(FilmeService filmeService, SerieServiceImpl serieService) {
        this.filmeView = new FilmeView(filmeService);
        this.serieView = new SerieView(serieService);

    }

    public void exibirMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n+--------------------------------------------------+");
            System.out.println("|          🍿 Bem-vindo(a) ao CineStream! 🍿       |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|                📋 Menu Principal                 |");
            System.out.println("+==================================================+");
            System.out.println("|  1️⃣ → 🎬 Gerenciar Filmes                        |");
            System.out.println("|  2️⃣ → 📺 Gerenciar Séries                        |");
            System.out.println("|  3️⃣ → 🚪 Sair                                    |");
            System.out.println("+==================================================+");
            System.out.print("📝 Escolha uma opção: ");


            try {
                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        filmeView.exibirFilmeView();
                        break;
                    case 2:
                        serieView.exibirSerieView();
                        //System.out.println("Implementar SerieView");
                        break;
                    case 3:
                        continuar = false;
                        System.out.println("👋 Saindo... Até logo!");
                        break;
                    default:
                        throw new OpcaoInvalidaException("Opção inválida. Por favor, escolha uma opção válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Por favor, digite um número.");
            } catch (OpcaoInvalidaException e){
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}
