package appconsole;

import modelo.Serie;
import regras_negocio.Fachada;

public class ConsultarDAO {
	public ConsultarDAO() {
		try {
			Fachada.inicializar();
			// Corrigir bug
			System.out.println("Series com mais de 1 episodio:");
			for(Serie s : Fachada.seriesComMaisDeXEpisodios(1)){
				System.out.println(s);
			}
			
			System.out.println();
			System.out.println("Series de 2019:");
			for(Serie s : Fachada.seriesDoAno("2019")){
				System.out.println(s);
			}
			
			System.out.println();
			System.out.println("Series do genero acao:");
			for(Serie s : Fachada.seriesDoGenero("acao")){
				System.out.println(s);
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}
	
	
	public static void main(String[] args) {
		new ConsultarDAO();
	}
}
