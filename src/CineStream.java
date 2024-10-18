import service.filme.FilmeServiceImpl;
import view.Menu;

public class CineStream {
    public static void main(String[] args) {

        // Caminho dos arquivos
        String caminhoFilmes = "src/database/Movies_Data.csv";
        //String caminhoSeries = "src/database/Tv_Show.csv";

        // Instanciando o servico de filmes e series
        FilmeServiceImpl filmeService = new FilmeServiceImpl(caminhoFilmes);


        // Exibindo menu principal
        Menu menu = new Menu(filmeService);

        menu.exibirMenuPrincipal();
    }
}