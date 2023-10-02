package refencia;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import daodb4o.Util;
import modelo.Episodio;
import modelo.Genero;
import modelo.Serie;

public class Deletar {
	protected ObjectContainer manager;

	public Deletar() {
		try {
			manager = Util.conectarBanco();
			System.out.println("excluindo");

			//apagar serie
			Query q = manager.query();
			q.constrain(Serie.class);
			q.descend("nome").constrain("Vingadores");
			List<Serie> resultados = q.execute();

			
			if(resultados.size()>0) {
				Serie serie = resultados.get(0);
				for(Episodio e : serie.getEpisodios()) {
					manager.delete(e);
				}
				Genero genero = serie.getGenero();
				genero.remover(serie);
				manager.delete(serie);
				manager.commit();

		
}

				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Util.desconectar();
		System.out.println("\nfim do programa !");

	}
	
	public static void main(String[] args) {
		new Deletar();
	}
	
}