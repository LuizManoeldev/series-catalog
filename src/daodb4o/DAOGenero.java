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


import modelo.Genero;

public class DAOGenero extends DAO<Genero> {

	@Override
	public Genero read(Object chave) {
		String nome = (String) chave; // Casting da chave para o tipo do Atributo
		Query q = manager.query();
		q.constrain(Genero.class);
		q.descend("nome").constrain(nome);
		List<Genero> resultados = q.execute();
		if (resultados.size()>0) {
			return resultados.get(0);
		}else {
			return null;
		}
	}
	
	
}
	
