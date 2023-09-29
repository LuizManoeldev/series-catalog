/**********************************
*	Catalogo
*	Luiz Manoel 
*	Maicon Felipe

 **********************************/

package regras_negocio;

import java.util.List;

import daodb4o.DAO;
import daodb4o.DAOSerie;
import daodb4o.DAOGenero;
import daodb4o.DAOEpisodio;

import modelo.Serie;
import modelo.Episodio;
import modelo.Genero;
import modelo.Usuario;

public class Fachada {
	private Fachada () {}
	
	private static DAOSerie daoserie = new DAOSerie();
	private static DAOGenero daogenero = new DAOGenero();
	private static DAOEpisodio daoepisodio = new DAOEpisodio();
	
	public static Usuario logado; // Objeto da Interface Grafica
	
	public static void inicializar(){
		DAO.open();
	}
	public static void finalizar(){
		DAO.close();
	}
	
	public static Genero cadastrarGenero() {
		return null;
	}
	
	public static Episodio cadastrarEpisodio() {
		return null;
	}
	
	public static Serie cadastrarSerie() {
		return null;
	}
	
	public static Genero excluirGenero() {
		return null;
	}
	
	public static Episodio excluirEpisodio() {
		return null;
	}
	
	public static Serie excluirSerie() {
		return null;
	}
	
	public static Genero listarGenero() {
		//read.All
		return null;
	}
	
	public static Episodio listarEpisodio() {
		return null;
	}
	
	public static Serie listarSerie() {
		return null;
	}
	
	//consultas especificas
	
	
}
