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
import daodb4o.DAOUsuario;

import modelo.Serie;
import modelo.Episodio;
import modelo.Genero;
import modelo.Usuario;

public class Fachada {
	private Fachada () {}
	
	private static DAOUsuario daousuario = new DAOUsuario();
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
	
	public static Genero cadastrarGenero(String nome) throws Exception {
		DAO.open();
		Genero genero = daogenero.read(nome);
		if(genero != null) {
			throw new Exception("Genero ja cadastrado: " + nome);
		}
		
		genero = new Genero(nome);
		daogenero.create(genero);
		DAO.commit();
		
		return genero;
	}
	
	public static Episodio cadastrarEpisodio(int id, String nome) throws Exception {
		DAO.open();
		Episodio episodio = daoepisodio.read(nome);
		if(episodio != null) {
			throw new Exception("Episodio ja cadastrado: " + nome);
		}
		
		episodio = new Episodio(id, nome);
		daoepisodio.create(episodio);
		DAO.commit();
		
		return episodio;
		
	}
	
	public static Serie cadastrarSerie(String nome, String ano, String genero, String canal) throws Exception{
		DAO.begin();
		Serie serie = daoserie.read(nome);
		Genero generoObj = daogenero.read(genero);
		if(serie != null) {
			throw new Exception("Serie ja cadastrada: " + nome);
		}
		
		if(genero == null) {
			throw new Exception("Genero invalido: " + genero);
		}
		
		serie = new Serie(nome, ano, generoObj, canal);
		daoserie.create(serie);
		generoObj.adicionar(serie);
		DAO.commit();
		
		return serie;
	}
	
	public static void adicionarEpisodioNaSerie(String nomeEpisodio, String nomeSerie) throws Exception{
		DAO.begin();
		Serie serie = daoserie.read(nomeSerie);
		Episodio episodio = daoepisodio.read(nomeEpisodio);
		
		if(serie == null) {
			throw new Exception("Serie digitada: "+nomeSerie+" Serie Obtida: " + serie.getNome());
		}
		
		if(episodio == null) {
			throw new Exception("Serie digitada: "+nomeEpisodio + " Episodio obtida: " + episodio.getNome());
		}
		
		serie.adicionar(episodio);
		DAO.commit();
	}
	
	// Aplicar regras de negocio nas operação de delete
	public static void excluirGenero(String nome) throws Exception {
		DAO.begin();
		Genero genero = daogenero.read(nome);
		
		if(genero == null) {
			throw new Exception("Genero inexistente");
		}
		
		daogenero.delete(genero);
		DAO.commit();
		
	}
	
	public static void excluirEpisodio(int id) throws Exception{
		DAO.begin();
		Episodio episodio = daoepisodio.read(id);
		
		if( episodio == null) {
			throw new Exception("Episodio inexistente");
		}
		
		daoepisodio.delete(episodio);
		
		DAO.commit();
	}
	
	public static void excluirSerie(String nome) throws Exception {
		DAO.begin();
		Serie serie = daoserie.read(nome);
		
		if( serie == null) {
			throw new Exception("Serie inexistente");
		}
		
		daoserie.delete(serie);
		
		DAO.commit();
		
	}
	
	public static List<Genero> listarGenero() {
		DAO.begin();
		List<Genero> resultados =  daogenero.readAll();
		DAO.commit();
		return resultados;
	}
	
	public static List<Episodio> listarEpisodio() {
		DAO.begin();
		List<Episodio> resultados =  daoepisodio.readAll();
		DAO.commit();
		return resultados;
	}
	
	public static List<Serie> listarSerie() {
		DAO.begin();
		List<Serie> resultados =  daoserie.readAll();
		DAO.commit();
		return resultados;
	}
	
	
	
	
	
	//consultas especificas
	
	
	public static List<Serie> seriesDoAno(String ano) {
		DAO.begin();
		List<Serie> resultados = daoserie.seriesDoAno(ano);
		DAO.commit();
		return resultados;
	}
	
	public static List<Serie> seriesDoGenero(String nomeDoGenero){
		DAO.begin();
		List<Serie> resultados = daoserie.seriesDoGenero(nomeDoGenero);
		DAO.commit();
		return resultados;
	}
	
	public static List<Serie> seriesComMaisDeXEpisodios(int numeroDeEpisodios) throws Exception{
		DAO.begin();
		List<Serie> resultados = daoserie.seriesComMaisDeXEpisodios(numeroDeEpisodios);
		if (resultados == null) {
			throw new Exception("falha na leitura do banco");
		}
		DAO.commit();
		return resultados;
	}
	
	//Usuario
	public static Usuario cadastrarUsuario(String nome, String senha) throws Exception{
		DAO.begin();
		Usuario usu = daousuario.read(nome);
		if (usu!=null)
			throw new Exception("Usuario ja cadastrado:" + nome);
		usu = new Usuario(nome, senha);

		daousuario.create(usu);
		DAO.commit();
		return usu;
	}
	public static Usuario localizarUsuario(String nome, String senha) {
		Usuario usu = daousuario.read(nome);
		if (usu==null)
			return null;
		if (! usu.getSenha().equals(senha))
			return null;
		return usu;
	}
	
	
}
