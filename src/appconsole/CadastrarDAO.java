/**********************************
*	Catalogo
*	Luiz Manoel 
*	Maicon Felipe

 **********************************/

package appconsole;


import modelo.Serie;
import regras_negocio.Fachada;

public class CadastrarDAO {
	public CadastrarDAO() {
		//Cadastro de Generos
		try {
			Fachada.inicializar();
			System.out.println("Cadastrando Genero...");
			
			Fachada.cadastrarGenero("acao");
			Fachada.cadastrarGenero("drama");
			Fachada.cadastrarGenero("terror");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//Cadastro de Episodios
		try {
			System.out.println("Cadastrando Episodios...");
			
			Fachada.cadastrarEpisodio(1, "Caverna");
			Fachada.cadastrarEpisodio(1, "Ultimato");
			Fachada.cadastrarEpisodio(1, "Por ordem dos peaky blinders");
			Fachada.cadastrarEpisodio(2, "Guerra Infinita");
			Fachada.cadastrarEpisodio(1, "Tempo");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		// Cadastro de Series
		try {
			System.out.println("Cadastrando Series...");
			
			Fachada.cadastrarSerie("Casa do Dragao", "2023", "acao", "HBOMAAX" );
			Fachada.cadastrarSerie("Peaky Blinders", "2023", "terror", "NETFLIX");
			Fachada.cadastrarSerie("Vingadores", "2019", "drama", "DISNEY");
			Fachada.cadastrarSerie("The Witcher", "2019", "acao", "NETFLIX");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//Adicionando Episodios nas series
		try {
			System.out.println("Cadastrando Episodios nas series");
			
			Fachada.adicionarEpisodioNaSerie("Caverna", "Casa do Dragao");
			Fachada.adicionarEpisodioNaSerie("Ultimato", "Vingadores");
			Fachada.adicionarEpisodioNaSerie("Guerra Infinita", "Vingadores");
			Fachada.adicionarEpisodioNaSerie("Por ordem dos peaky blinders", "Peaky Blinders");
			Fachada.adicionarEpisodioNaSerie("Tempo", "The Witcher");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Fachada.finalizar();
		System.out.println("\nfim do programa !");
		
		
	}
	
	public static void main(String[] args) {
		new CadastrarDAO();
	}
}
