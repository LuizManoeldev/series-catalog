
package aplicacao;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import daodb4o.Util;
import modelo.Genero;
import modelo.Serie;


public class Alterar {
	protected ObjectContainer manager;
	
	public Alterar() {
		manager = Util.conectarBanco();
		
		// Adicionar uma nova serie
		
		// Obtendo Generos
		Query q = manager.query();
		q.constrain(Genero.class);  				
		List<Genero> generos = q.execute();
		
		Serie novaSerie = new Serie("Stranger Things", "2016", generos.get(0) ,"NETFLIX");
		
		manager.store(novaSerie);
		manager.commit();
		
		Util.desconectar();
		System.out.println("\nAlteração Realizada!");
	}

	public static void main(String[] args) {
		new Alterar();
	}
}
