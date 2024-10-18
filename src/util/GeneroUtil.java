package util;

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
}