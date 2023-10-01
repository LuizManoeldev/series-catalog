package appconsole;

import modelo.Episodio;
import modelo.Genero;
import modelo.Serie;
import regras_negocio.Fachada;

public class ListarDAO {
	public ListarDAO(){
		try {
			Fachada.inicializar();
			
			
			System.out.println("Listagem de Episodios");
			for(Episodio e : Fachada.listarEpisodio()) {
				System.out.println(e);
			}
			
			System.out.println();
			System.out.println("Listagem de Generos");
			for(Genero g : Fachada.listarGenero()) {
				System.out.println(g);
			}
			
			
			System.out.println();
			System.out.println("Listagem de Series");
			for(Serie s : Fachada.listarSerie()) {
				System.out.println(s);
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		Fachada.finalizar();
		
	}
	
	public static void main(String[] args) {
		new ListarDAO();
	}
}
