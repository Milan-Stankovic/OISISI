package dev_mode_gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.DialCall;
import controller.JasonControl;
import model.InstalerModel;
import model.Panel;
import model.Verzija;
import user_mode_gui.InstallFrame;
import user_mode_gui.UserFrame;
import warnings.NemaStavke;
import warnings.NeselPanel;
import warnings.NeselVerzija;



@SuppressWarnings("serial")
public class DevFrame extends JFrame {

	private JPanel contentPane;

	private JTextField text_naz_ver;
	private JTextField text_naz_pan;
	private DevMeni meni;
	private PanelTree tree;
	private GlobalniPan gp;
	private JPanel panel_2;
	private Properties jezik;
	private Verzija instaler;
	private UserFrame userFrame;
	private InstallFrame installFrame;

	
	@SuppressWarnings("rawtypes")
	public DevFrame(Properties j) throws IOException {
		setTitle("UIGUI");
		String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/");
		ImageIcon icon = new ImageIcon(myLoc+"/LOGO.jpg");
		setIconImage(icon.getImage());
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.8; //Procenat ekrana
		height=height*0.75; 
//Zakomentarisite ovo ako ne volite distrubed :D
/*		String pesma = (System.getProperty("user.dir")+"/src/dev_mode_gui/Disturbed-Run.wav");
		String command = "\"C:/Program Files (x86)/Windows Media Player/wmplayer.exe\" \\" + pesma;
		try {
		    Process p = Runtime.getRuntime().exec(command);
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
*/		
		
		/**
		 * set default languaga
		 */
		jezik=j;
	
			String []tipovi={jezik.getProperty("Tekst"),jezik.getProperty("Tekstlinija"),jezik.getProperty("Slika"),jezik.getProperty("Lokacijasoftvera"), jezik.getProperty("Tekstualnifajl"), jezik.getProperty("Logokompanije"),jezik.getProperty("Defaultinstalacionalokacija"), jezik.getProperty("Exe"), jezik.getProperty("Instaliraj"), jezik.getProperty("Checkbox"), jezik.getProperty("Lokalizacija"),jezik.getProperty("Lista")}; 
	


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, (int)width, (int)height);
		
		
		
		JasonControl jc=new JasonControl(j);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		JScrollPane jsp = new JScrollPane(panel);
		add(jsp);
		jsp.setVisible(true);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{20, 100, 200, 200, 60, 20, 60, 0};
		gbl_panel_1.rowHeights = new int[]{0, 60, 60, 20, 60, 60, 60, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new BorderLayout(0, 0));
		//panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
	
		
		
		tree=new PanelTree(jezik);
		panel_2.add(tree);
		tree.setEditable(true);
		
		tree.setModel(new InstalerModel());
		JScrollPane scrollPane = new JScrollPane(tree);
		panel_2.add(scrollPane, BorderLayout.NORTH);
		
		
		/* tree.addTreeSelectionListener(new TreeSelectionListener() {
		      public void valueChanged(TreeSelectionEvent evt) {
		  		Object selectedNode = (Object) tree.getLastSelectedPathComponent();
		  		if(selectedNode instanceof Verzija){
		  			changeGui=0;
		  		}
		  		
		  		else if(selectedNode instanceof Panel){
		  			
		  			changeGui=1;
		  		}
		  		else if(selectedNode instanceof Parametar){
		  			
		  			changeGui=2;
		  		}
		  		else{
		  			
		  			changeGui=0;
		  		}

		      }
		    });
		    */
		
		
		
		JButton btnGlobali = new JButton(jezik.getProperty("GlobalniParametri"));
		btnGlobali.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Object selectedNode = (Object)tree.getLastSelectedPathComponent(); 
				  if(selectedNode instanceof Verzija){
				Verzija vers = (Verzija) tree.getLastSelectedPathComponent();
				String lokI= (String) vers.getLokInstalacije();
				String lokS= (String) vers.getLokSoftvera();
				String lokL= (String) vers.getLokLogo();
				gp=new GlobalniPan(lokI , lokS, lokL, jezik);
				gp.setVisible(true);
				  
				  }else{
					  NeselVerzija poruka=new NeselVerzija(jezik);
					  poruka.showMess(jezik);
				  }
				
			}
		});
		panel_2.add(btnGlobali, BorderLayout.SOUTH);
		
		meni=new DevMeni(jezik, tree);
		setJMenuBar(meni);
		
		
		JLabel lblDodavanjeNoveVerzije = new JLabel(jezik.getProperty("Dnv"));
		GridBagConstraints gbc_lblDodavanjeNoveVerzije = new GridBagConstraints();
		gbc_lblDodavanjeNoveVerzije.gridwidth = 2;
		gbc_lblDodavanjeNoveVerzije.insets = new Insets(0, 0, 5, 5);
		gbc_lblDodavanjeNoveVerzije.gridx = 2;
		gbc_lblDodavanjeNoveVerzije.gridy = 1;
		panel_1.add(lblDodavanjeNoveVerzije, gbc_lblDodavanjeNoveVerzije);
		
		JLabel lblDodavanjeVerzije = new JLabel(jezik.getProperty("NazivVerzije"));
		GridBagConstraints gbc_lblDodavanjeVerzije = new GridBagConstraints();
		gbc_lblDodavanjeVerzije.insets = new Insets(0, 0, 5, 5);
		gbc_lblDodavanjeVerzije.gridx = 1;
		gbc_lblDodavanjeVerzije.gridy = 2;
		panel_1.add(lblDodavanjeVerzije, gbc_lblDodavanjeVerzije);
		
		
		text_naz_ver = new JTextField();
		GridBagConstraints gbc_text_naz_ver = new GridBagConstraints();
		gbc_text_naz_ver.gridwidth = 2;
		gbc_text_naz_ver.insets = new Insets(0, 0, 5, 5);
		gbc_text_naz_ver.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_naz_ver.gridx = 2;
		gbc_text_naz_ver.gridy = 2;
		panel_1.add(text_naz_ver, gbc_text_naz_ver);
		text_naz_ver.setColumns(10);

		
		JButton btnDodajVerziju = new JButton(jezik.getProperty("DodajVerziju")); //ISKORISTITI UGRADJENE PanelTree i TreeLisener da bi ispravno radilo potrebno kopirati ovaj kod u PanelTree
		
		btnDodajVerziju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String s=text_naz_ver.getText();
				tree.addVerzija(s);
				
				
			}
		});
		GridBagConstraints gbc_btnDodajVerziju = new GridBagConstraints();
		gbc_btnDodajVerziju.gridwidth = 3;
		gbc_btnDodajVerziju.anchor = GridBagConstraints.WEST;
		gbc_btnDodajVerziju.insets = new Insets(0, 0, 5, 0);
		gbc_btnDodajVerziju.gridx = 4;
		gbc_btnDodajVerziju.gridy = 2;
		panel_1.add(btnDodajVerziju, gbc_btnDodajVerziju);

		
		
		
		
		
		JLabel lblDodavanjeNovogPanela = new JLabel(jezik.getProperty("DodavanjeNovogPanela"));
		GridBagConstraints gbc_lblDodavanjeNovogPanela = new GridBagConstraints();
		gbc_lblDodavanjeNovogPanela.gridwidth = 2;
		gbc_lblDodavanjeNovogPanela.insets = new Insets(0, 0, 5, 5);
		gbc_lblDodavanjeNovogPanela.gridx = 2;
		gbc_lblDodavanjeNovogPanela.gridy = 3;
		panel_1.add(lblDodavanjeNovogPanela, gbc_lblDodavanjeNovogPanela);
		
		JLabel lblDodavanjePanela = new JLabel(jezik.getProperty("NazivPanela"));
		GridBagConstraints gbc_lblDodavanjePanela = new GridBagConstraints();
		gbc_lblDodavanjePanela.insets = new Insets(0, 0, 5, 5);
		gbc_lblDodavanjePanela.gridx = 1;
		gbc_lblDodavanjePanela.gridy = 4;
		panel_1.add(lblDodavanjePanela, gbc_lblDodavanjePanela);
		
		
		text_naz_pan = new JTextField();
		GridBagConstraints gbc_text_naz_pan = new GridBagConstraints();
		gbc_text_naz_pan.gridwidth = 2;
		gbc_text_naz_pan.insets = new Insets(0, 0, 5, 5);
		gbc_text_naz_pan.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_naz_pan.gridx = 2;
		gbc_text_naz_pan.gridy = 4;
		panel_1.add(text_naz_pan, gbc_text_naz_pan);
		text_naz_pan.setColumns(10);

		
		JButton btnDodajPanel = new JButton(jezik.getProperty("DodajPanel")); //ISKORISTITI UGRADJENE PanelTree i TreeLisener da bi ispravno radilo potrebno kopirati ovaj kod u PanelTree
		btnDodajPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=text_naz_pan.getText();
				tree.addPanel(s);
			}
		});
		
		GridBagConstraints gbc_btnDodajPanel = new GridBagConstraints();
		gbc_btnDodajPanel.gridwidth = 3;
		gbc_btnDodajPanel.anchor = GridBagConstraints.WEST;
		gbc_btnDodajPanel.insets = new Insets(0, 0, 5, 0);
		gbc_btnDodajPanel.gridx = 4;
		gbc_btnDodajPanel.gridy = 4;
		panel_1.add(btnDodajPanel, gbc_btnDodajPanel);
		
		
		
		
		
		JLabel lblDodavanjeNovogParametra = new JLabel(jezik.getProperty("DodavanjeNovogParametraUOdabraniPanel"));
		GridBagConstraints gbc_lblDodavanjeNovogParametra = new GridBagConstraints();
		gbc_lblDodavanjeNovogParametra.gridwidth = 2;
		gbc_lblDodavanjeNovogParametra.insets = new Insets(0, 0, 5, 5);
		gbc_lblDodavanjeNovogParametra.gridx = 2;
		gbc_lblDodavanjeNovogParametra.gridy = 4;
		panel_1.add(lblDodavanjeNovogParametra, gbc_lblDodavanjeNovogParametra);
		
		JLabel lblDodavanjeNovogParametra_1 = new JLabel(jezik.getProperty("DodavanjeNovogParametra"));
		GridBagConstraints gbc_lblDodavanjeNovogParametra_1 = new GridBagConstraints();
		gbc_lblDodavanjeNovogParametra_1.gridwidth = 2;
		gbc_lblDodavanjeNovogParametra_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblDodavanjeNovogParametra_1.gridx = 2;
		gbc_lblDodavanjeNovogParametra_1.gridy = 5;
		panel_1.add(lblDodavanjeNovogParametra_1, gbc_lblDodavanjeNovogParametra_1);
		
		JTextPane txtpnOdabirTipa = new JTextPane();
		txtpnOdabirTipa.setBackground(UIManager.getColor("Button.background"));
		txtpnOdabirTipa.setText(jezik.getProperty("OdabirTipa"));
		GridBagConstraints gbc_txtpnOdabirTipa = new GridBagConstraints();
		gbc_txtpnOdabirTipa.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnOdabirTipa.fill = GridBagConstraints.VERTICAL;
		gbc_txtpnOdabirTipa.gridx = 1;
		gbc_txtpnOdabirTipa.gridy = 6;
		panel_1.add(txtpnOdabirTipa, gbc_txtpnOdabirTipa);
		
		
		
		@SuppressWarnings("unchecked") // POGLEDATI STA JE OVO
		JComboBox combo_tipovi = new JComboBox(tipovi);
		GridBagConstraints gbc_combo_tipovi = new GridBagConstraints();
		gbc_combo_tipovi.gridwidth = 2;
		gbc_combo_tipovi.fill = GridBagConstraints.HORIZONTAL;
		gbc_combo_tipovi.insets = new Insets(0, 0, 5, 5);
		gbc_combo_tipovi.anchor = GridBagConstraints.NORTH;
		gbc_combo_tipovi.gridx = 2;
		gbc_combo_tipovi.gridy = 6;
		panel_1.add(combo_tipovi, gbc_combo_tipovi);
		combo_tipovi.setAlignmentY(Component.TOP_ALIGNMENT);
		combo_tipovi.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		combo_tipovi.setSelectedIndex(4);
				DialCall d= new DialCall(jezik);
				JButton btnAdd = new JButton(jezik.getProperty("DodajParam"));
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						Object selectedNode = (Object)tree.getLastSelectedPathComponent(); 
						if(!(selectedNode instanceof Panel)){ 
							NeselPanel poruka=new NeselPanel(jezik);
							poruka.showMess(jezik);
							return;
						}
						else{
						String odabrani = combo_tipovi.getSelectedItem().toString();
						d.chooseDial(odabrani, tree);
				}
					}
				});
				
				
				GridBagConstraints gbc_btnAdd = new GridBagConstraints();
				gbc_btnAdd.gridwidth = 3;
				gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
				gbc_btnAdd.anchor = GridBagConstraints.NORTHWEST;
				gbc_btnAdd.gridx = 4;
				gbc_btnAdd.gridy = 6;
				panel_1.add(btnAdd, gbc_btnAdd);
		
		
		
		JButton btnObrisi = new JButton(jezik.getProperty("Obrisi"));
		
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object selectedNode = (Object)tree.getLastSelectedPathComponent(); 
				if((selectedNode==null)){ 
					NemaStavke poruka=new NemaStavke(jezik);
					poruka.showMess(jezik);
					return;
				}
				tree.delete();
				
				
			}
		});
		
		JButton btnPregled = new JButton(jezik.getProperty("Pregled"));
		GridBagConstraints gbc_btnPregled = new GridBagConstraints();
		gbc_btnPregled.insets = new Insets(0, 0, 5, 5);
		gbc_btnPregled.gridx = 2;
		gbc_btnPregled.gridy = 7;
		panel_1.add(btnPregled, gbc_btnPregled);
		btnPregled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jc.saveModelToJSon(tree, jezik);
				Object selectedNode = (Object) tree.getLastSelectedPathComponent();
				if(selectedNode instanceof Verzija){
					String nazivVer= ((Verzija) selectedNode).getNaziv();
					jc.saveModelToJSon(tree, jezik);
					instaler = jc.loadModelFromJSon(nazivVer,instaler, jezik);
					userFrame = new UserFrame(instaler, jezik);
					if (instaler.getLokLogo()!=null){
					ImageIcon icon = new ImageIcon(instaler.getLokLogo());
					userFrame.setIconImage(icon.getImage());
					}
					userFrame.setVisible(true);
				}else{
					NeselVerzija poruka=new NeselVerzija(jezik);
					poruka.showMess(jezik);
					return;
				}
					
				}
				
		});
		//uarditi properties za ovo
		JButton btnInstaliraj = new JButton(jezik.getProperty("Pokreni"));
		GridBagConstraints gbc_btnInstaliraj = new GridBagConstraints();
		gbc_btnInstaliraj.insets = new Insets(0, 0, 5, 5);
		gbc_btnInstaliraj.gridx = 3;
		gbc_btnInstaliraj.gridy = 7;
		panel_1.add(btnInstaliraj, gbc_btnInstaliraj);
		
		btnInstaliraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jc.saveModelToJSon(tree, jezik);
				Object selectedNode = (Object) tree.getLastSelectedPathComponent();
				if(selectedNode instanceof Verzija){
					String nazivVer= ((Verzija) selectedNode).getNaziv();
					instaler = jc.loadModelFromJSon(nazivVer,instaler, jezik);
					installFrame = new InstallFrame(instaler, jezik);
					if (instaler.getLokLogo()!=null){
					ImageIcon icon = new ImageIcon(instaler.getLokLogo());
					installFrame.setIconImage(icon.getImage());
					}
					installFrame.setVisible(true);
				}else{
					NeselVerzija poruka=new NeselVerzija(jezik);
					poruka.showMess(jezik);
					return;
				}
					
				}
				
		});
		
		
		GridBagConstraints gbc_btnObrisi = new GridBagConstraints();
		gbc_btnObrisi.insets = new Insets(0, 0, 0, 5);
		gbc_btnObrisi.gridx = 4;
		gbc_btnObrisi.gridy = 9;
		panel_1.add(btnObrisi, gbc_btnObrisi);
		
		JButton btnSacuvaj = new JButton(jezik.getProperty("Sacuvaj"));
		GridBagConstraints gbc_btnSacuvaj = new GridBagConstraints();
		gbc_btnSacuvaj.gridx = 6;
		gbc_btnSacuvaj.gridy = 9;
		panel_1.add(btnSacuvaj, gbc_btnSacuvaj);
		btnSacuvaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jc.saveModelToJSon(tree, jezik);
				
			}
		});
				
	}
 		
	
}
	


