/**********************************
*	Catalogo
*	Luiz Manoel 
*	Maicon Felipe

 **********************************/

package daodb4o;
import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;


import modelo.Serie;

public class DAOSerie extends DAO<Serie> {

	@Override
	public Serie read(Object chave) {
		String nome = (String) chave;
		Query q = manager.query();
		q.constrain(Serie.class);
		q.descend("nome").constrain(nome);
		List<Serie> resultados = q.execute();
		if (resultados.size() > 0 ) {
			return resultados.get(0);
		}else {
			return null;
		}
	}
	
	public void create(Serie obj) {
		manager.store(obj);
	}
	
	//Consultas
	
	public List<Serie> seriesDoAno(String ano) {
		Query q;
		q = manager.query();
		q.constrain(Serie.class);
		q.descend("ano").constrain(ano);
		List<Serie> resultados = q.execute();
		if (resultados.size() > 0 ) {
			return resultados;
		}else {
			return null;
		}
	}
	
	public List<Serie> seriesDoGenero(String nomeDoGenero) {
		Query g;
		g = manager.query();
		g.constrain(Serie.class);
		g.descend("genero").descend("nome").constrain(nomeDoGenero);
		List<Serie> resultados = g.execute();
		if (resultados.size() > 0 ) {
			return resultados;
		}else {
			return null;
		}
	}
	
	public List<Serie> seriesComMaisDeXEpisodios(int numerdoDeEpisodios) {
		Query f;
		f = manager.query();
		f.constrain(Serie.class);
		f.constrain(new filtroEpisodios());
		List<Serie> resultados = f.execute();
		if (resultados.size() > 0 ) {
			return resultados;
		}else {
			return null;
		}
	}	
}



class filtroEpisodios implements Evaluation {
	public void evaluate(Candidate candidate) {
		Serie serie= (Serie) candidate.getObject();
		if(serie.getEpisodios().size() > 1) 
			candidate.include(true); 
		else		
			candidate.include(false);
	}
}
