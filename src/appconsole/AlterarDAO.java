package appconsole;

import regras_negocio.Fachada;

public class AlterarDAO {
	public AlterarDAO(){
		try {
			Fachada.inicializar();
			
			Fachada.atualizarGeneroDeSerie("terror", "Casa do Dragao");
	
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new AlterarDAO();
	}
}
