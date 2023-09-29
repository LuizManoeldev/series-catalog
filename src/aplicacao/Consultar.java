package aplicacao;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import daodb4o.Util;
import modelo.Serie;
import modelo.Episodio;
import modelo.Genero;


public class Consultar {
	protected ObjectContainer manager;
	
	public Consultar() {
		manager = Util.conectarBanco();
		//Quais as series do ano X
		System.out.println("Series do ano 2019:");
		
		Query q;
		q = manager.query();
		q.constrain(Serie.class);
		q.descend("ano").constrain("2019");
		List<Serie> resultados = q.execute();
		for(Serie s : resultados) {
			System.out.println(s);
		}
		
		//Quais as Series do genero acao
		System.out.println();
		System.out.println("Series do Genero Acao:");
		
		Query g;
		g = manager.query();
		g.constrain(Serie.class);
		g.descend("genero").descend("nome").constrain("acao");
		List<Serie> resultados2 = g.execute();
		for(Serie s : resultados2) {
			System.out.println(s);
		}
		
		
		//Quais as Series que tem mais de N episodios
		System.out.println();
		System.out.println("Series com mais de 1 episodio:");
		
		Query f;
		f = manager.query();
		f.constrain(Serie.class);
		f.constrain(new Filtro1());
		List<Serie> resultados3 = f.execute();
		for(Serie s : resultados3) {
			System.out.println(s);
		}
		
		
		
		Util.desconectar();
		System.out.println("\nfim do programa !");
	}
	public static void main(String[] args) {
		new Consultar();
	}

}

//classe interna
	class Filtro1 implements Evaluation {
		public void evaluate(Candidate candidate) {
			Serie serie= (Serie) candidate.getObject();
			if(serie.getEpisodios().size() > 1) 
				candidate.include(true); 
			else		
				candidate.include(false);
		}
	}
