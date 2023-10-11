package appswing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import modelo.Usuario;
import regras_negocio.Fachada;

public class TelaLogin {

	private JFrame frame;
	private JLabel label;
	private JLabel label_1;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel label_2;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin window = new TelaLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Login");
		frame.setBounds(100, 100, 455, 292);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
				if(Fachada.listarUsuarios().isEmpty()) {
					try {
						Fachada.cadastrarUsuario("luiz", "123");
						label_2.setText("usuario 'luiz' cadastrado");
					}
					catch(Exception ex) {
						label_2.setText("nao conseguiu criar usuario");
					}
				}
			}
			@Override
			public void windowClosed(WindowEvent e) {
				Fachada.finalizar();
			}
		});

		label = new JLabel("usuario");
		label.setBounds(104, 54, 46, 23);
		frame.getContentPane().add(label);

		label_1 = new JLabel("senha");
		label_1.setBounds(104, 107, 46, 14);
		frame.getContentPane().add(label_1);

		textField = new JTextField();
		textField.setBounds(160, 54, 106, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(160, 103, 106, 23);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		label_2 = new JLabel("");
		label_2.setBounds(100, 219, 246, 23);
		frame.getContentPane().add(label_2);

		button = new JButton("entrar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textField.getText();
				String senha = textField_1.getText();

				Usuario usu = Fachada.localizarUsuario(nome,senha);

				if(usu!=null) {
					Fachada.logado = usu;
					TelaPrincipal tela = new TelaPrincipal();
					frame.dispose();
				}
				else
					label_2.setText("usuario ou senha incorreto");
			}
		});
		button.setBounds(160, 152, 106, 32);
		frame.getContentPane().add(button);
	}
}
