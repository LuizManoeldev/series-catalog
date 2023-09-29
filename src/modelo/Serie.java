package modelo;

import java.util.ArrayList;

public class Serie {
	private String nome;
	private String ano;
	private ArrayList<Episodio> listaEpisodios = new ArrayList<>();
	private Genero genero;
	private String canal;
	
	public Serie(String nome, String ano, Genero genero, String canal) {
		super();
		this.nome = nome;
		this.ano = ano;
		this.genero = genero;
		this.canal = canal;
	}
	
	public void adicionar(Episodio ep){
		listaEpisodios.add(ep);
	}
	public void remover(Episodio ep){
		listaEpisodios.remove(ep);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}
	
	public ArrayList<Episodio> getEpisodios() {
		return this.listaEpisodios;
	}

	@Override
	public String toString() {
		return "Serie [nome = " + nome + " ] [Genero = " + this.genero.getNome() + 
				"] [ Ano = " + ano + " ] [ Canal = " + canal + " ] [Qtd de Eps = " + this.listaEpisodios.size() + " ]" ;
	}
	
	
	
}
