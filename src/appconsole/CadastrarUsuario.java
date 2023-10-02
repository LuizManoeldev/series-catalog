package appconsole;

import regras_negocio.Fachada;

public class CadastrarUsuario {
	public CadastrarUsuario() {
		try {
			Fachada.inicializar();

			System.out.println("cadastrando usuario...");
			Fachada.cadastrarUsuario("admin", "123");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}



	public static void main(String[] args) {
		new CadastrarUsuario();
	}
}
