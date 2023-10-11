package appswing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Episodio;
import modelo.Serie;
import modelo.Genero;
import regras_negocio.Fachada;

public class TelaEpisodio {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField numEpisodio;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JLabel label;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;

	private JButton serieAndGenero;
	private JLabel label_1;
	private JTextField nomeEpisodio;
	private JTextField nomeEpisodio2;
	private JLabel label_5;
	private JTextField nomeSerie;
	private JButton adicionarEpisodio;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEpisodio tela = new TelaEpisodio();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaEpisodio() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setModal(true);

		frame.setResizable(false);
		frame.setTitle("Episodio");
		frame.setBounds(100, 100, 729, 385);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
				listagem();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 43, 674, 148);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label_4.setText("selecionado="+ (int) table.getValueAt( table.getSelectedRow(), 0));
			}
		});
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.ORANGE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");		//label de mensagem
		label.setForeground(Color.BLUE);
		label.setBounds(10, 321, 688, 14);
		frame.getContentPane().add(label);

		label_4 = new JLabel("resultados:");
		label_4.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(label_4);

		label_2 = new JLabel("numero do episodio:");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(10, 242, 134, 14);
		frame.getContentPane().add(label_2);

		numEpisodio = new JTextField();
		numEpisodio.setFont(new Font("Dialog", Font.PLAIN, 12));
		numEpisodio.setColumns(10);
		numEpisodio.setBounds(130, 239, 65, 20);
		frame.getContentPane().add(numEpisodio);

		button_1 = new JButton("cadastrar novo episodio");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(numEpisodio.getText().isEmpty() || nomeEpisodio.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					int num = Integer.parseInt(numEpisodio.getText());
					String nome = nomeEpisodio.getText();
					Fachada.cadastrarEpisodio(num, nome);
					label.setText("cliente criado: "+ nome);
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(502, 238, 193, 23);
		frame.getContentPane().add(button_1);

		button = new JButton("Listar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button.setBounds(308, 11, 89, 23);
		frame.getContentPane().add(button);

		label_3 = new JLabel("     nome do episodio:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(205, 242, 138, 14);
		frame.getContentPane().add(label_3);

		nomeEpisodio = new JTextField();
		nomeEpisodio.setFont(new Font("Dialog", Font.PLAIN, 12));
		nomeEpisodio.setColumns(10);
		nomeEpisodio.setBounds(342, 239, 142, 20);
		frame.getContentPane().add(nomeEpisodio);

		button_2 = new JButton("Apagar selecionado");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){	
						label.setText("nao implementado " );
						String nome = (String) table.getValueAt( table.getSelectedRow(), 1);
						Fachada.excluirEpisodio(nome);
						label.setText("Episodio apagado" );
						listagem();
					}
					else
						label.setText("nao selecionado");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(281, 202, 171, 23);
		frame.getContentPane().add(button_2);

		label_1 = new JLabel("    nome do episodio:  ");
		label_1.setBounds(0, 282, 123, 14);
		frame.getContentPane().add(label_1);

		label_5 = new JLabel("     nome da serie:");
		label_5.setBounds(237, 282, 106, 14);
		frame.getContentPane().add(label_5);

		nomeSerie = new JTextField();
		nomeSerie.setBounds(342, 279, 142, 20);
		frame.getContentPane().add(nomeSerie);
		nomeSerie.setColumns(10);

		adicionarEpisodio = new JButton("adicionar episodio na serie");
		adicionarEpisodio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(nomeEpisodio2.getText().isEmpty() || nomeSerie.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String nome = nomeEpisodio2.getText();
					String serie = nomeSerie.getText();
					Fachada.adicionarEpisodioNaSerie(nome, serie);
					label.setText("episodio adicionado na serie: "+ serie);
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		adicionarEpisodio.setBounds(502, 278, 193, 23);
		frame.getContentPane().add(adicionarEpisodio);

		nomeEpisodio2 = new JTextField();
		nomeEpisodio2.setBounds(121, 279, 106, 20);
		frame.getContentPane().add(nomeEpisodio2);
		nomeEpisodio2.setColumns(10);

	}

	public void listagem() {
		try{
			List<Episodio> lista = Fachada.listarEpisodio();
			List<Serie> series = Fachada.listarSerie();

			// model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("id");
			model.addColumn("nome");
			model.addColumn("serie");

			//adicionar linhas no model

			int contagem = 0;
			for(Episodio ep : lista) {
				String text = "";
				contagem = 0;
				if(series == null) {
					model.addRow(new Object[]{ep.getId(), ep.getNome(), text} );
				}
				else {
					for(Serie ser : series) {

						if((ser.getEpisodios()).contains(ep)) {
							if(contagem == 0) {
								text = ser.getNome();
								contagem ++;
							}
							else {
								text += " - " + ser.getNome();
							}


						}

					}
					model.addRow(new Object[]{ep.getId(), ep.getNome(), text} );

				}


			}

			//atualizar model no table (visualizacao)
			table.setModel(model);

			label_4.setText("resultados: "+lista.size()+ " objetos");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
}

