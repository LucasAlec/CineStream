package view;


import service.filme.FilmeService;

import java.util.Scanner;

public class Menu {

    private FilmeView filmeView;

    public Menu(FilmeService filmeService) {
        this.filmeView = new FilmeView(filmeService);
        //this.serieView = new SerieView(new SerieServiceImpl(carregarDados));
    }

    public void exibirMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nBem-vindo(a) ao CineStream");
            System.out.println("\n==== Menu Principal ====");
            System.out.println("1 - Gerenciar Filmes");
            System.out.println("2 - Gerenciar Séries");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        filmeView.exibirFilmeView();
                        break;
                    case 2:
                        System.out.println("Implementar SerieView");
                        break;
                    case 3:
                        continuar = false;
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }

        scanner.close();
    }
}
