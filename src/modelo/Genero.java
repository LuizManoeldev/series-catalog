package modelo;

import java.util.ArrayList;

public class Genero {
	

	private String nome;
	private ArrayList<Serie> listaSeries = new ArrayList<>();
	
	public Genero(String nome) {
		super();
		this.nome = nome;
	}
	
	public void adicionar(Serie ser){
		listaSeries.add(ser);
	}
	public void remover(Serie ser){
		listaSeries.remove(ser);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ArrayList<Serie> getSeries() {
		return this.listaSeries;
	}
	
	@Override
	public String toString() {
		return "Genero [nome = " + nome + " ]" ;
	}
	
}
