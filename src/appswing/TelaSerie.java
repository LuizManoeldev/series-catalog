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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.db4o.ObjectContainer;

import modelo.Serie;
import modelo.Genero;
import modelo.Episodio;
import regras_negocio.Fachada;

public class TelaSerie {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField nomeSerie;
	private JTextField anoSerie;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JLabel label;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;

	private JButton button_3;
	private JLabel label_1;
	private JTextField generoSerie;
	private JLabel label_5;
	private JTextField canalSerie;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSerie tela = new TelaSerie();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaSerie() {
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
		frame.setTitle("Serie");
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
				label_4.setText("selecionado="+ (String) table.getValueAt( table.getSelectedRow(), 0));
			}
		});
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.YELLOW);
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
		label.setBounds(21, 321, 688, 14);
		frame.getContentPane().add(label);

		label_4 = new JLabel("resultados:");
		label_4.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(label_4);

		label_2 = new JLabel("nome da serie:");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(21, 249, 116, 14);
		frame.getContentPane().add(label_2);

		nomeSerie = new JTextField();
		nomeSerie.setFont(new Font("Dialog", Font.PLAIN, 12));
		nomeSerie.setColumns(10);
		nomeSerie.setBounds(138, 246, 102, 20);
		frame.getContentPane().add(nomeSerie);

		button_1 = new JButton("criar nova serie");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(nomeSerie.getText().isEmpty() || anoSerie.getText().isEmpty() || generoSerie.getText().isEmpty() || canalSerie.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}

					String nome = nomeSerie.getText();
					String ano = anoSerie.getText();
					String genero = generoSerie.getText();
					String canal = canalSerie.getText();

					Fachada.cadastrarSerie(nome, ano, genero, canal);
					label.setText("serie criada: " + nome);
					listagem();
				}


				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(540, 259, 153, 20);
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

		label_3 = new JLabel("ano de lancamento:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(21, 274, 134, 14);
		frame.getContentPane().add(label_3);

		anoSerie = new JTextField();
		anoSerie.setFont(new Font("Dialog", Font.PLAIN, 12));
		anoSerie.setColumns(10);
		anoSerie.setBounds(137, 271, 103, 20);
		frame.getContentPane().add(anoSerie);

		button_2 = new JButton("Apagar selecionado");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){	
						label.setText("nao implementado " );
						String nome = (String) table.getValueAt( table.getSelectedRow(), 0);

						Fachada.excluirSerie(nome);
						label.setText("serie apagado" );
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
		button_2.setBounds(281, 213, 171, 23);
		frame.getContentPane().add(button_2);

		button_3 = new JButton("exibir episodios");
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto="";
				try{
					if (table.getSelectedRow() >= 0){	
						String nome = (String) table.getValueAt( table.getSelectedRow(), 0);
						Serie ser = Fachada.localizarSerie(nome);

						if((ser.getEpisodios()).size() != 0) {
							for (Episodio ep : ser.getEpisodios()) {
								texto = texto + ep.getId() + "-" + ep.getNome() + "\n";
							}
						}else {
							texto = "sem episodios cadastrados";

						}

					}

					JOptionPane.showMessageDialog(frame, texto, "episodios", 1);
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_3.setBounds(48, 215, 134, 23);
		frame.getContentPane().add(button_3);

		label_1 = new JLabel("           genero da serie:");
		label_1.setBounds(250, 250, 142, 14);
		frame.getContentPane().add(label_1);

		generoSerie = new JTextField();
		generoSerie.setBounds(396, 247, 134, 20);
		frame.getContentPane().add(generoSerie);
		generoSerie.setColumns(10);

		label_5 = new JLabel("        canal de trasmissao:   ");
		label_5.setBounds(244, 275, 153, 14);
		frame.getContentPane().add(label_5);

		canalSerie = new JTextField();
		canalSerie.setBounds(396, 272, 134, 20);
		frame.getContentPane().add(canalSerie);
		canalSerie.setColumns(10);
	}

	public void listagem() {

		try{
			List<Serie> lista = Fachada.listarSerie();

			// model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("nome");
			model.addColumn("ano");
			model.addColumn("genero");
			model.addColumn("canal");
			model.addColumn("quant. de episodios");

			//adicionar linhas no model
			for(Serie ser : lista) {
				String genero = "";
				if(ser.getGenero() != null) {
					genero = ser.getGenero().getNome();
				}
				int eps = 0;
				for(Episodio ep : ser.getEpisodios()) {
					if(ep != null) {
						eps ++;
					}

				}

				model.addRow(new Object[]{ser.getNome(), ser.getAno(), genero, ser.getCanal(), eps} );
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
