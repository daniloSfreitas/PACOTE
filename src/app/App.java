package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import dao.DAO;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JList;

public class App extends DAO {

	private JFrame frame;
	private JTextField textPat;
	private JTextField textBase;
	private String sistemaSelecionado;
	private static File pacoteModelo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public App() throws SQLException {
		initialize();
	}

	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblPat = new JLabel("PAT");
		lblPat.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblPat);

		JLabel lblBase = new JLabel("Base");
		lblBase.setBounds(10, 63, 46, 14);
		frame.getContentPane().add(lblBase);

		textPat = new JTextField();
		textPat.setBounds(66, 8, 122, 20);
		frame.getContentPane().add(textPat);
		textPat.setColumns(10);

		textBase = new JTextField();
		textBase.setColumns(10);
		textBase.setBounds(65, 60, 122, 20);
		frame.getContentPane().add(textBase);

		JRadioButton rdbtnProducao = new JRadioButton("Produ\u00E7\u00E3o");
		rdbtnProducao.setBounds(206, 7, 109, 23);
		frame.getContentPane().add(rdbtnProducao);

		JRadioButton rdbtnTeste = new JRadioButton("Teste");
		rdbtnTeste.setBounds(206, 59, 109, 23);
		frame.getContentPane().add(rdbtnTeste);

		JRadioButton rdbtnHomologacao = new JRadioButton("Homologa\u00E7\u00E3o");
		rdbtnHomologacao.setBounds(206, 33, 109, 23);
		frame.getContentPane().add(rdbtnHomologacao);

		JButton btnConstruir = new JButton("Construir");
		btnConstruir.setBounds(335, 33, 89, 23);
		frame.getContentPane().add(btnConstruir);

		JButton btnValidar = new JButton("Validar");
		btnValidar.setBounds(434, 33, 89, 23);
		frame.getContentPane().add(btnValidar);

		JButton btnSvn = new JButton("SVN");
		btnSvn.setBounds(533, 33, 89, 23);
		frame.getContentPane().add(btnSvn);

		JComboBox<String> comboBoxInicialManager = new JComboBox<String>();
		comboBoxInicialManager.setBounds(66, 141, 165, 20);
		frame.getContentPane().add(comboBoxInicialManager);
			
		
		
		JComboBox<String> comboBoxFinalManager = new JComboBox<String>();
		comboBoxFinalManager.setBounds(66, 176, 165, 20);
		frame.getContentPane().add(comboBoxFinalManager);

		JLabel lblInicialManager = new JLabel("Inicial");
		lblInicialManager.setBounds(10, 144, 46, 14);
		frame.getContentPane().add(lblInicialManager);

		JLabel lblFinalManager = new JLabel("Final");
		lblFinalManager.setBounds(10, 179, 46, 14);
		frame.getContentPane().add(lblFinalManager);

		JCheckBox checkBoxManager = new JCheckBox();
		checkBoxManager.setBounds(237, 156, 21, 23);
		frame.getContentPane().add(checkBoxManager);

		JLabel lblWebManager = new JLabel("WebManager");
		lblWebManager.setBounds(10, 111, 46, 14);
		frame.getContentPane().add(lblWebManager);
		
		JList list = new JList();
		
		list.setEnabled(false);
		list.setBounds(389, 110, 200, 212);
		frame.getContentPane().add(list);

		checkBoxManager.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					sistemaSelecionado = "MANAGER";
					try {
						Connection conexao = getConexao();
						PreparedStatement pstmt = conexao.prepareStatement(
								"SELECT MXR_VERSAO FROM MXS_RELEASES_MXR WHERE MXR_SISTEMA='MANAGER' ORDER BY MXR_ORDEM");
						
						ResultSet rs = pstmt.executeQuery();

						while (rs.next()) {
							comboBoxInicialManager.addItem(rs.getString("MXR_VERSAO"));
							comboBoxFinalManager.addItem(rs.getString("MXR_VERSAO"));
							
							

						}
						pstmt.close();
						conexao.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					comboBoxInicialManager.removeAllItems();
					comboBoxFinalManager.removeAllItems();
				}
			}
		});

		btnConstruir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				
				String pat = textPat.getText();
				String base = textBase.getText();
				File pacoteDoCliente = new File("C:\\[MXM-" + pat + "]-" + pat + "_" + base);
				pacoteModelo = new File("C:\\ADMDADOS\\Atualizacao\\[MXM]-Pacote_Atualizacao");

				if (sistemaSelecionado.equalsIgnoreCase("MANAGER")) {

					try {
						CopiaDiretorio.copiarDiretorios(pacoteModelo, pacoteDoCliente);
						
						File managerInicial = new File("C:\\SVN-PROJETOS\\SISTEMAS\\MANAGER\\" + comboBoxInicialManager.getSelectedItem().toString());
						File managerFinal = new File("C:\\SVN-PROJETOS\\SISTEMAS\\MANAGER\\" + comboBoxFinalManager.getSelectedItem().toString());
						
						File managerDestino = new File(pacoteDoCliente + "\\Scripts\\SISTEMAS\\MANAGER\\" + comboBoxInicialManager.getSelectedItem().toString());

						System.out.println(managerInicial);
						System.out.println(managerDestino);
						
						
						
						int size = comboBoxInicialManager.getItemCount();
						for (int i = 0; i < size; i++) {
							
							String item = (String) comboBoxInicialManager.getItemAt(i);
							CopiaDiretorio.copiarDiretorios(managerInicial, managerDestino);
							
						}
						
						

					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}

			}
		});
	}
}