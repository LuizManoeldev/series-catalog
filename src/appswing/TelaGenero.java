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
import modelo.Episodio;
import modelo.Genero;
import regras_negocio.Fachada;

public class TelaGenero {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField novoGenero;
	private JTextField generoAtual;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JLabel label;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JTextField serieModificada;
	private JLabel label_1;
	private JButton button_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGenero tela = new TelaGenero();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaGenero() {
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
		frame.setTitle("Genero");
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

		label_2 = new JLabel("nome do genero:");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(143, 252, 104, 14);
		frame.getContentPane().add(label_2);

		novoGenero = new JTextField();
		novoGenero.setFont(new Font("Dialog", Font.PLAIN, 12));
		novoGenero.setColumns(10);
		novoGenero.setBounds(257, 249, 195, 20);
		frame.getContentPane().add(novoGenero);

		button_1 = new JButton("criar novo genero");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(novoGenero.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String gen = novoGenero.getText();
					Fachada.cadastrarGenero(gen);
					label.setText("genero criado: "+ gen);
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(473, 248, 153, 23);
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

		label_3 = new JLabel("novo genero:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(10, 280, 78, 14);
		frame.getContentPane().add(label_3);

		generoAtual = new JTextField();
		generoAtual.setFont(new Font("Dialog", Font.PLAIN, 12));
		generoAtual.setColumns(10);
		generoAtual.setBounds(98, 277, 171, 20);
		frame.getContentPane().add(generoAtual);

		button_2 = new JButton("Apagar selecionado");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){	
						label.setText("nao implementado " );
						String gen = (String) table.getValueAt( table.getSelectedRow(), 0);
						Fachada.excluirGenero(gen);
						label.setText("genero apagado" );
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
		button_2.setBounds(270, 209, 171, 23);
		frame.getContentPane().add(button_2);

		serieModificada = new JTextField();
		serieModificada.setBounds(388, 278, 152, 20);
		frame.getContentPane().add(serieModificada);
		serieModificada.setColumns(10);

		label_1 = new JLabel("     serie para mudar:    ");
		label_1.setBounds(267, 280, 130, 14);
		frame.getContentPane().add(label_1);

		button_4 = new JButton("atualizar serie");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(generoAtual.getText().isEmpty() || serieModificada.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String genAtual = generoAtual.getText();
					String serie = serieModificada.getText();
					Fachada.atualizarGeneroDeSerie(genAtual, serie);
					label.setText("genero modificado na serie: "+ serie);
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});	
		button_4.setBounds(550, 277, 153, 23);
		frame.getContentPane().add(button_4);
	}

	public void listagem() {
		try{
			List<Genero> lista = Fachada.listarGenero();

			// model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("nome do genero");
			model.addColumn("series");

			//adicionar linhas no model
			int contagem = 0;
			String text = "";
			for(Genero gen : lista) {
				text = "";
				for(Serie ser : gen.getSeries()) {
					if(contagem == 0) {
						text = ser.getNome();
						contagem ++;

					} else {
						text += " - " + ser.getNome();

					}


				}
				model.addRow(new Object[]{gen.getNome(), text} );

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
