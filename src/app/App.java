package app;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import dao.DAO;

public class App extends DAO {

	private JFrame frame;
	private JTextField textPat;
	private String sistemaSelecionado;
	private static File pacoteModelo;
	private JTextField textBase;

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

		JLabel lblPat = new JLabel("PAT:");
		lblPat.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblPat);

		JLabel lblBase = new JLabel("BASE:");
		lblBase.setBounds(10, 36, 46, 14);
		frame.getContentPane().add(lblBase);

		textPat = new JTextField();
		textPat.setBounds(46, 8, 122, 20);
		frame.getContentPane().add(textPat);
		textPat.setColumns(10);
		
		textBase = new JTextField();
		textBase.setColumns(10);
		textBase.setBounds(46, 36, 122, 20);
		frame.getContentPane().add(textBase);

		JRadioButton rdbtnProducao = new JRadioButton("Produ\u00E7\u00E3o");
		rdbtnProducao.setBounds(174, 8, 89, 14);
		frame.getContentPane().add(rdbtnProducao);

		JRadioButton rdbtnTeste = new JRadioButton("Teste");
		rdbtnTeste.setBounds(174, 42, 109, 14);
		frame.getContentPane().add(rdbtnTeste);

		JRadioButton rdbtnHomologacao = new JRadioButton("Homologa\u00E7\u00E3o");
		rdbtnHomologacao.setBounds(174, 25, 109, 14);
		frame.getContentPane().add(rdbtnHomologacao);

		JButton btnConstruir = new JButton("Construir");
		btnConstruir.setBounds(299, 11, 98, 29);
		frame.getContentPane().add(btnConstruir);

		JButton btnValidar = new JButton("Validar");
		btnValidar.setBounds(407, 11, 98, 29);
		frame.getContentPane().add(btnValidar);

		JButton btnSvn = new JButton("SVN");
		btnSvn.setBounds(515, 11, 98, 29);
		frame.getContentPane().add(btnSvn);

		JComboBox<String> comboBoxInicialManager = new JComboBox<String>();
		comboBoxInicialManager.setBounds(92, 87, 165, 20);
		frame.getContentPane().add(comboBoxInicialManager);
			
		
		
		JComboBox<String> comboBoxFinalManager = new JComboBox<String>();
		comboBoxFinalManager.setBounds(92, 118, 165, 20);
		frame.getContentPane().add(comboBoxFinalManager);

		JLabel lblInicialManager = new JLabel("Vers\u00E3o Inicial");
		lblInicialManager.setBounds(20, 90, 76, 14);
		frame.getContentPane().add(lblInicialManager);

		JLabel lblFinalManager = new JLabel("Vers\u00E3o Final");
		lblFinalManager.setBounds(20, 121, 76, 14);
		frame.getContentPane().add(lblFinalManager);

		JCheckBox checkBoxManager = new JCheckBox();
		checkBoxManager.setBounds(272, 101, 21, 23);
		frame.getContentPane().add(checkBoxManager);

		JLabel Manager = new JLabel("Manager");
		Manager.setFont(new Font("Tahoma", Font.BOLD, 11));
		Manager.setBounds(20, 62, 114, 14);
		frame.getContentPane().add(Manager);
		
		JComboBox<String> comboBoxInicialSPED = new JComboBox<String>();
		comboBoxInicialSPED.setBounds(92, 188, 165, 20);
		frame.getContentPane().add(comboBoxInicialSPED);
		
		JComboBox<String> comboBoxFinalSPED = new JComboBox<String>();
		comboBoxFinalSPED.setBounds(92, 219, 165, 20);
		frame.getContentPane().add(comboBoxFinalSPED);
		
		JLabel lblInicialSPED = new JLabel("Vers\u00E3o Inicial");
		lblInicialSPED.setBounds(20, 191, 76, 14);
		frame.getContentPane().add(lblInicialSPED);
		
		JLabel lnlFinalSPED = new JLabel("Vers\u00E3o Final");
		lnlFinalSPED.setBounds(20, 222, 76, 14);
		frame.getContentPane().add(lnlFinalSPED);
		
		JCheckBox checkBoxSPED = new JCheckBox();
		checkBoxSPED.setBounds(272, 202, 21, 23);
		frame.getContentPane().add(checkBoxSPED);
		
		JLabel SPED = new JLabel("SPED");
		SPED.setFont(new Font("Tahoma", Font.BOLD, 11));
		SPED.setBounds(20, 163, 114, 14);
		frame.getContentPane().add(SPED);
		
		JComboBox<String> comboBoxInicialECD = new JComboBox<String>();
		comboBoxInicialECD.setBounds(92, 282, 165, 20);
		frame.getContentPane().add(comboBoxInicialECD);
		
		JComboBox<String> comboBoxFinalECD = new JComboBox<String>();
		comboBoxFinalECD.setBounds(92, 313, 165, 20);
		frame.getContentPane().add(comboBoxFinalECD);
		
		JLabel lblInicialECD = new JLabel("Vers\u00E3o Inicial");
		lblInicialECD.setBounds(20, 285, 76, 14);
		frame.getContentPane().add(lblInicialECD);
		
		JLabel lblFinalECD = new JLabel("Vers\u00E3o Final");
		lblFinalECD.setBounds(20, 316, 76, 14);
		frame.getContentPane().add(lblFinalECD);
		
		JCheckBox checkBoxECD = new JCheckBox();
		checkBoxECD.setBounds(272, 296, 21, 23);
		frame.getContentPane().add(checkBoxECD);
		
		JLabel ECD = new JLabel("ECD");
		ECD.setFont(new Font("Tahoma", Font.BOLD, 11));
		ECD.setBounds(20, 257, 114, 14);
		frame.getContentPane().add(ECD);
		
		JComboBox<String> comboBoxInicialNFE = new JComboBox<String>();
		comboBoxInicialNFE.setBounds(454, 87, 165, 20);
		frame.getContentPane().add(comboBoxInicialNFE);
		
		JComboBox<String> comboBoxFinalNFE = new JComboBox<String>();
		comboBoxFinalNFE.setBounds(454, 118, 165, 20);
		frame.getContentPane().add(comboBoxFinalNFE);
		
		JLabel lblInicialNFE = new JLabel("Vers\u00E3o Inicial");
		lblInicialNFE.setBounds(382, 90, 76, 14);
		frame.getContentPane().add(lblInicialNFE);
		
		JLabel lblFinalNFE = new JLabel("Vers\u00E3o Final");
		lblFinalNFE.setBounds(382, 121, 76, 14);
		frame.getContentPane().add(lblFinalNFE);
		
		JCheckBox checkBoxNFE = new JCheckBox();
		checkBoxNFE.setBounds(634, 101, 21, 23);
		frame.getContentPane().add(checkBoxNFE);
		
		JLabel NFE = new JLabel("NFE");
		NFE.setFont(new Font("Tahoma", Font.BOLD, 11));
		NFE.setBounds(382, 62, 114, 14);
		frame.getContentPane().add(NFE);
		
		JComboBox<String> comboBoxInicialWManager = new JComboBox<String>();
		comboBoxInicialWManager.setBounds(454, 188, 165, 20);
		frame.getContentPane().add(comboBoxInicialWManager);
		
		JComboBox<String> comboBoxFinalWManager = new JComboBox<String>();
		comboBoxFinalWManager.setBounds(454, 219, 165, 20);
		frame.getContentPane().add(comboBoxFinalWManager);
		
		JLabel lblInicialWMenage = new JLabel("Vers\u00E3o Inicial");
		lblInicialWMenage.setBounds(382, 191, 76, 14);
		frame.getContentPane().add(lblInicialWMenage);
		
		JLabel lblFinalWManager = new JLabel("Vers\u00E3o Final");
		lblFinalWManager.setBounds(382, 222, 76, 14);
		frame.getContentPane().add(lblFinalWManager);
		
		JCheckBox checkBoxWManager = new JCheckBox();
		checkBoxWManager.setBounds(634, 202, 21, 23);
		frame.getContentPane().add(checkBoxWManager);
		
		JLabel WManager = new JLabel("WebManager");
		WManager.setFont(new Font("Tahoma", Font.BOLD, 11));
		WManager.setBounds(382, 163, 114, 14);
		frame.getContentPane().add(WManager);
		
		JComboBox<String> comboBoxInicialEFD = new JComboBox<String>();
		comboBoxInicialEFD.setBounds(454, 282, 165, 20);
		frame.getContentPane().add(comboBoxInicialEFD);
		
		JComboBox<String> comboBoxFinalEFD = new JComboBox<String>();
		comboBoxFinalEFD.setBounds(454, 313, 165, 20);
		frame.getContentPane().add(comboBoxFinalEFD);
		
		JLabel lblInicialEFD = new JLabel("Vers\u00E3o Inicial");
		lblInicialEFD.setBounds(382, 285, 76, 14);
		frame.getContentPane().add(lblInicialEFD);
		
		JLabel lblFinalEFD = new JLabel("Vers\u00E3o Final");
		lblFinalEFD.setBounds(382, 316, 76, 14);
		frame.getContentPane().add(lblFinalEFD);
		
		JCheckBox checkBoxEFD = new JCheckBox();
		checkBoxEFD.setBounds(634, 296, 21, 23);
		frame.getContentPane().add(checkBoxEFD);
		
		JLabel EFD = new JLabel("EFD");
		EFD.setFont(new Font("Tahoma", Font.BOLD, 11));
		EFD.setBounds(382, 257, 114, 14);
		frame.getContentPane().add(EFD);
		
		JComboBox<String> comboBoxInicialECF = new JComboBox<String>();
		comboBoxInicialECF.setBounds(92, 377, 165, 20);
		frame.getContentPane().add(comboBoxInicialECF);
		
		JComboBox<String> comboBoxFinalECF = new JComboBox<String>();
		comboBoxFinalECF.setBounds(92, 408, 165, 20);
		frame.getContentPane().add(comboBoxFinalECF);
		
		JLabel lblInicialECF = new JLabel("Vers\u00E3o Inicial");
		lblInicialECF.setBounds(20, 380, 76, 14);
		frame.getContentPane().add(lblInicialECF);
		
		JLabel lblFinalECF = new JLabel("Vers\u00E3o Final");
		lblFinalECF.setBounds(20, 411, 76, 14);
		frame.getContentPane().add(lblFinalECF);
		
		JCheckBox checkBoxECF = new JCheckBox();
		checkBoxECF.setBounds(272, 391, 21, 23);
		frame.getContentPane().add(checkBoxECF);
		
		JLabel lblEcf = new JLabel("ECF");
		lblEcf.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEcf.setBounds(20, 352, 26, 14);
		frame.getContentPane().add(lblEcf);
		
		JCheckBox checkBoxREINF = new JCheckBox();
		checkBoxREINF.setBounds(634, 391, 21, 23);
		frame.getContentPane().add(checkBoxREINF);
		
		JLabel REINF = new JLabel("REINF");
		REINF.setFont(new Font("Tahoma", Font.BOLD, 11));
		REINF.setBounds(382, 352, 114, 14);
		frame.getContentPane().add(REINF);
		
		JRadioButton rdbtnV8 = new JRadioButton("v8");
		rdbtnV8.setBounds(46, 347, 37, 23);
		frame.getContentPane().add(rdbtnV8);
		
		JRadioButton rdbtnV9 = new JRadioButton("v9");
		rdbtnV9.setBounds(86, 347, 109, 23);
		frame.getContentPane().add(rdbtnV9);
		
		JRadioButton rdbtnD = new JRadioButton("820D_029");
		rdbtnD.setBounds(416, 376, 89, 23);
		frame.getContentPane().add(rdbtnD);
		
		JRadioButton rdbtnD_E = new JRadioButton("821D_E");
		rdbtnD_E.setBounds(524, 376, 89, 23);
		frame.getContentPane().add(rdbtnD_E);
		
		JRadioButton rdbtnA = new JRadioButton("821A");
		rdbtnA.setBounds(416, 407, 89, 23);
		frame.getContentPane().add(rdbtnA);
		
		JRadioButton rdbtnB = new JRadioButton("821B");
		rdbtnB.setBounds(524, 407, 89, 23);
		frame.getContentPane().add(rdbtnB);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		JMenuItem mntmWManager = new JMenuItem("WEBMANAGER");
		mnCadastro.add(mntmWManager);
		
		JMenuItem mntmManager = new JMenuItem("MANAGER");
		mnCadastro.add(mntmManager);
		
		JMenuItem mntmSped = new JMenuItem("SPED");
		mnCadastro.add(mntmSped);
		
		JMenuItem mntmNfe = new JMenuItem("NFE");
		mnCadastro.add(mntmNfe);
		
		JMenu mnProcessos = new JMenu("Processos");
		menuBar.add(mnProcessos);
		
		JMenuItem mntmManutenoDoArquivo = new JMenuItem("Manuten\u00E7\u00E3o do arquivo .INI");
		mnProcessos.add(mntmManutenoDoArquivo);

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
				pacoteModelo = new File("C:\\Admdados\\Atualizacao\\[MXM]-Pacote_Atualizacao");

				if (sistemaSelecionado.equalsIgnoreCase("MANAGER")) {

					try {
						CopiaDiretorio.copiarDiretorios(pacoteModelo, pacoteDoCliente);
						
						File managerInicial = new File("C:\\Projetos\\SISTEMAS\\MANAGER\\" + comboBoxInicialManager.getSelectedItem().toString());
						File managerFinal = new File("C:\\Projetos\\SISTEMAS\\MANAGER\\" + comboBoxFinalManager.getSelectedItem().toString());
						
						File managerDestino = new File(pacoteDoCliente + "\\Scripts\\SISTEMAS\\MANAGER\\" + comboBoxInicialManager.getSelectedItem().toString());

						System.out.println(managerInicial);
						System.out.println(managerDestino);
						
						
						
						int size = comboBoxInicialManager.getItemCount();
						for (int i = 0; i < size; i++) {
							
							String item = (String) comboBoxInicialManager.getItemAt(i);
							CopiaDiretorio.copiarDiretorios(managerInicial,managerDestino);
							
						}
						
						

					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}

			}
		});
	}
}