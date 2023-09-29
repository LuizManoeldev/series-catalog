package aplicacao;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import daodb4o.Util;
import modelo.Episodio;
import modelo.Genero;
import modelo.Serie;

public class Listar {
	protected ObjectContainer manager;

	public Listar() {
		try {
			manager = Util.conectarBanco();
			listarSeries();
			listarEpisodios();
			listarGeneros();
			Util.desconectar();

		}catch (Exception e){
			System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplica��o");

		}
	}
	public void listarSeries(){
		System.out.println("\n---listagem das series:");

		Query q = manager.query();
		q.constrain(Serie.class);  				
		List<Serie> resultados = q.execute();
		for(Serie s : resultados)
			System.out.println(s);
			
			
	}
	public void listarEpisodios(){
		System.out.println("\n---listagem dos episodios:");

		Query q = manager.query();
		q.constrain(Episodio.class);  				
		List<Episodio> resultados = q.execute();
		for(Episodio e : resultados)
			System.out.println(e);
	}

	public void listarGeneros(){
		System.out.println("\n---listagem dos generos:");

		Query q = manager.query();
		q.constrain(Genero.class);  				
		List<Genero> resultados = q.execute();
		for(Genero g : resultados)
			System.out.println(g);
	}

	//=================================================
	public static void main(String[] args) {
		new Listar();
	}
}