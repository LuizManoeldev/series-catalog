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


import modelo.Episodio;


public class DAOEpisodio extends DAO<Episodio>{

	@Override
	public Episodio read(Object chave) {
		int id = (int) chave;
		Query q = manager.query();
		q.constrain(Episodio.class);
		q.descend("id").constrain(id);
		List<Episodio> resultados = q.execute();
		if (resultados.size() > 0 ) {
			return resultados.get(0);
		}else {
			return null;
		}
	}
	
	// Consultas
	
	

	
}
