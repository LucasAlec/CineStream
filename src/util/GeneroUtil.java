package util;

import model.Filme;

import java.util.List;
import java.util.Scanner;

public class GeneroUtil {

    public static String obterGeneroComEmoji(String genero) {
        switch (genero) {
            case "Action": return "Action 🔥";
            case "Adventure": return "Adventure 🧭";
            case "Comedy": return "Comedy 😂";
            case "Drama": return "Drama 🎭";
            case "Horror": return "Horror 👻";
            case "Mystery": return "Mystery 🕵️‍♂️";
            case "Thriller": return "Thriller 🔪";
            case "Sci-Fi": return "Sci-Fi 🚀";
            case "Fantasy": return "Fantasy 🧙";
            case "Romance": return "Romance ❤️";
            case "Crime": return "Crime 🚨";
            default: return genero + " 🎬";
        }
    }

    public static void exibirGeneros(List<String> generosDisponiveis) {
        System.out.println("+------------------------------------------------------+");
        System.out.println("|                Gêneros Disponíveis                   |");
        System.out.println("+------------------------------------------------------+");

        // Exibe dois gêneros por linha
        for (int i = 0; i < generosDisponiveis.size(); i += 2) {
            String genero1 = obterGeneroComEmoji(generosDisponiveis.get(i));
            String linha = String.format("%2d - %-20s", i + 1, genero1);

            if (i + 1 < generosDisponiveis.size()) {
                String genero2 = obterGeneroComEmoji(generosDisponiveis.get(i + 1));
                linha += String.format("%2d - %-22s", i + 2, genero2);
            }

            System.out.println("| " + linha + " |");
        }

        System.out.println("+------------------------------------------------------+");
    }

}