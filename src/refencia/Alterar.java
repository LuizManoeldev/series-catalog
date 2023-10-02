
package refencia;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import daodb4o.Util;
import modelo.Genero;
import modelo.Serie;


public class Alterar {
	protected ObjectContainer manager;

	public Alterar(){
		manager = Util.conectarBanco();
		atualizar();
		Util.desconectar();
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplica��o");
	}

	public void atualizar(){

		//consulta
		Query q1 = manager.query();
		q1.constrain(Serie.class);  				
		q1.descend("nome").constrain("The Witcher");		 
		List<Serie> resultados1 = q1.execute(); 

		if(resultados1.size()>0) {
		/* atualizar genero de determinada serie
		*  - crio um novo genero para a aplicacao
		*  - armazeno o genero da sehrie que vai ser alterado
		*  - usamos o setGenero para alterar o genero da serie correspondente
		*  - no novo genero nohs adicionamos a sehrie correspondente
		*  - no antigo genero nohs retiramos o relacionamento com a serie, removendo a serie do genero antigo
		*  - atualizamos o genero antigo, o genero novo, e a serie no banco de dados
		*  - o genero antigo nao some do banco de dados, apenas pode ficar orfao no banco
		*/
			Serie serie = resultados1.get(0);
			Genero genero_novo = new Genero("romance");
			Genero genero_antigo = serie.getGenero();
			serie.setGenero(genero_novo);
			genero_novo.adicionar(serie);
			genero_antigo.remover(serie);			
			manager.store(genero_novo);
			manager.store(genero_antigo);
			manager.store(serie);
			manager.commit();
		}

	}

	//=================================================
	public static void main(String[] args) {
		new Alterar();
	}


}
