/**********************************
*	Catalogo
*	Luiz Manoel 
*	Maicon Felipe

 **********************************/

package daodb4o;
import java.util.ArrayList;
import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Episodio;
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
		return q.execute();
	}
	
	public List<Serie> seriesDoGenero(String nomeDoGenero) {
		Query g;
		g = manager.query();
		g.constrain(Serie.class);
		g.descend("genero").descend("nome").constrain(nomeDoGenero);
		return g.execute();
	}
	
	public List<Serie> seriesComMaisDeXEpisodios(int numeroDeEpisodios) {
	    Query f = manager.query();
	    f.constrain(Serie.class);
	    f.descend("listaEpisodios");
	    //f.constrain(new filtroEpisodios(numeroDeEpisodios));
	    
	    List<Serie> resultados = f.execute();
	    
	    // Lista para armazenar séries com mais de X episódios
	    List<Serie> seriesComMaisDeXEpisodios = new ArrayList<>();

	    // Filtra séries com mais de X episódios
	    for (Serie serie : resultados) {
	        if ((serie.getEpisodios()).size() > numeroDeEpisodios) {
	            seriesComMaisDeXEpisodios.add(serie);
	        }
	    }
	 
	    
	    return seriesComMaisDeXEpisodios;
	}	
}



class filtroEpisodios implements Evaluation {
    private int numeroDeEpisodios;

    public filtroEpisodios(int numeroDeEpisodios) {
        this.numeroDeEpisodios = numeroDeEpisodios;
    }

    public void evaluate(Candidate candidate) {
        Serie serie = (Serie) candidate.getObject();
        if (serie.getEpisodios().size() > numeroDeEpisodios) {
            candidate.include(true);
        } else {
            candidate.include(false);
        }
    }
}
