package refencia;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import daodb4o.Util;
import modelo.Episodio;
import modelo.Genero;
import modelo.Serie;


public class Cadastrar {
	protected ObjectContainer manager;

	public Cadastrar(){
		manager = Util.conectarBanco();
		cadastrar();
		Util.desconectar();
		
		System.out.println("fim da aplicacao");
	}


	public void cadastrar(){
		System.out.println("Cadastrando...");
		
		Genero acao = new Genero("acao");
		Genero drama = new Genero("drama");
		Genero terror = new Genero("terror");
		
		Episodio ep1 = new Episodio(1, "Caverna");
		Episodio ep2 = new Episodio(1, "Ultimato");
		Episodio ep3 = new Episodio(1, "Por ordem dos peaky blinders");
		Episodio ep4 = new Episodio(2, "Guerra Infinita");
		Episodio ep5 = new Episodio(1, "Tempo");
		
		Serie casadodragao = new Serie("A Casa do Dragao", "2023", acao, "HBOMAAX" );
		Serie peakyblinders = new Serie("Peaky Blinders", "2023", terror, "NETFLIX");
		Serie vingadores = new Serie("Vingadores", "2019", drama, "DISNEY");
		Serie thewitcher = new Serie("The Witcher", "2019", acao, "NETFLIX");
		
		casadodragao.adicionar(ep1);
		peakyblinders.adicionar(ep3);
		vingadores.adicionar(ep2);
		vingadores.adicionar(ep4);
		thewitcher.adicionar(ep5);
		
		acao.adicionar(thewitcher);
		acao.adicionar(casadodragao);
		drama.adicionar(vingadores);
		terror.adicionar(peakyblinders);
		
		
		manager.store(casadodragao);
		manager.store(peakyblinders);
		manager.store(vingadores);
		manager.store(thewitcher);
		
		manager.commit();

	}

	//=================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
}


