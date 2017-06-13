package jEditDialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;

import dev_mode_gui.PanelTree;
import model.Panel;
import model.Parametar;
import model.TipParametra;
import model.Verzija;

public class EditLokalizacija extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField naziv;
	private JTextArea vrednost;
	private String naziv_par;
	private String vrednost_par;
	private String tip="Lokalizacija";
	private boolean zavrseno;
	private JScrollPane scrollPane;
	private boolean prazno=false;
	private JTextField textField;
	private PanelTree tree;
	private Properties jezik;
	
	public boolean getPrazno(){
		return prazno;
	}
	public void setTree(PanelTree t){
		tree=t;
		
	}
	
	private void submitAction() {
        // You can do some validation here before assign the text to the variable
		
		String s1=naziv.getText();
		String s2= textField.getText();
		
		
        naziv_par=s1+ "NEGLEDAJMIUKOD" + s2; //Morao sam nekako odvojiti... Nadam se neko nece bas gledati u kod da bi nasao nacin da polomi lokalizaciju
		vrednost_par=vrednost.getText(); // Veoma neprofesionalno, znam
	}									// Ali zanimljiv Easter egg

	/**
	 * Create the dialog.
	 */
	public EditLokalizacija(Properties j, PanelTree tree) {
		String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/");
		ImageIcon icon = new ImageIcon(myLoc+"/LOGO.jpg");
		setIconImage(icon.getImage());
		
		tip="Lokalizacija";
		jezik = new Properties();
		jezik = j;
		Parametar p=(Parametar) tree.getLastSelectedPathComponent();
		
		
		String s1= new String();
		String s2 = new String();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.6; //Procenat ekrana
		height=height*0.5; 
		setBounds(100, 100, (int)width, (int)height);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{50, 696, 50, 0};
		gbl_contentPanel.rowHeights = new int[]{50, 50, 50, 50, 50, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		this.setModal(true);
		contentPanel.setLayout(gbl_contentPanel);
		{
			
			
			JLabel lblPrviJezik = new JLabel(jezik.getProperty("Osnovnijezik"));
			GridBagConstraints gbc_lblPrviJezik = new GridBagConstraints();
			gbc_lblPrviJezik.insets = new Insets(0, 0, 5, 5);
			gbc_lblPrviJezik.gridx = 1;
			gbc_lblPrviJezik.gridy = 0;
			contentPanel.add(lblPrviJezik, gbc_lblPrviJezik);
		}
		{
			naziv = new JTextField();
			GridBagConstraints gbc_naziv = new GridBagConstraints();
			gbc_naziv.insets = new Insets(0, 0, 5, 5);
			gbc_naziv.fill = GridBagConstraints.HORIZONTAL;
			gbc_naziv.gridx = 1;
			gbc_naziv.gridy = 1;
			contentPanel.add(naziv, gbc_naziv);
			naziv.setText(p.getNaziv());
			naziv.setColumns(10);
		}
		{
			JLabel lblJezikNaKoji = new JLabel(jezik.getProperty("JezikNaKojiPrevodite"));
			GridBagConstraints gbc_lblJezikNaKoji = new GridBagConstraints();
			gbc_lblJezikNaKoji.insets = new Insets(0, 0, 5, 5);
			gbc_lblJezikNaKoji.gridx = 1;
			gbc_lblJezikNaKoji.gridy = 2;
			contentPanel.add(lblJezikNaKoji, gbc_lblJezikNaKoji);
		}
		{
			textField = new JTextField();
			textField.setColumns(10);
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 3;
			contentPanel.add(textField, gbc_textField);
		}
		{
			JLabel lblUnesiteVrednostParametra = new JLabel(jezik.getProperty("UnesiteVrednostPrevoda"));
			GridBagConstraints gbc_lblUnesiteVrednostParametra = new GridBagConstraints();
			gbc_lblUnesiteVrednostParametra.insets = new Insets(0, 0, 5, 5);
			gbc_lblUnesiteVrednostParametra.gridx = 1;
			gbc_lblUnesiteVrednostParametra.gridy = 4;
			contentPanel.add(lblUnesiteVrednostParametra, gbc_lblUnesiteVrednostParametra);
		}
		{
			scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 5;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				vrednost = new JTextArea();
				vrednost.setWrapStyleWord(true);
				vrednost.setLineWrap(true);
				vrednost.setText(p.getVrednost());
				scrollPane.setViewportView(vrednost);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(jezik.getProperty("Sacuvaj"));
				okButton.setActionCommand("Sacuvaj");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						submitAction();
						zavrseno=true;
			      		 if (naziv_par.trim().equals("") && vrednost_par.trim().equals("")){
			  				JOptionPane.showMessageDialog(null,jezik.getProperty("NistePopuniliVrednostParametara"));
			  				prazno=true;
			  				return;
			  			}
						dispose();
					}
				});
				
				
				this.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosing(WindowEvent e) {
			        	vrednost_par="";
						naziv_par="";
						prazno=true;
						zavrseno=false;
						dispose();
			        }
			});
				
				
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton(jezik.getProperty("Izadji"));
				cancelButton.setActionCommand("Izlazak");
				cancelButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0){
						vrednost_par="";
						naziv_par="";
						prazno=true;
						zavrseno=false;
						dispose();
				        
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private String createTxt(PanelTree tree){
		  TreePath selectedPath = tree.getSelectionPath();
		   Object selectedNode = selectedPath.getPathComponent(1); 
		   Verzija vers = (Verzija) selectedNode; 
		String s ="";
		String s1 = "";
		String s2 = "";

			for(int j=0; j<vers.getChildCount(); j++){
				Panel p = (Panel) vers.getChildAt(j);
				for(int k=0; k<p.getChildCount(); k++){
					Parametar param = (Parametar) p.getChildAt(k);
					TipParametra tp= param.getTip();
					if(tp == TipParametra.DEFAULT_LOK){
						continue;
					}
					else if(tp == TipParametra.INSTALL){
						continue;
					}
					else if(tp == TipParametra.LOKALIZACIJA){
						continue;
					}
					else if(tp == TipParametra.EXE){
						s1=param.getNaziv() + "=" + "\n";
						s=s+s1;
						continue;
					}else if(tp == TipParametra.SLIKA){
						s1=param.getNaziv() + "=" + "\n";
						s=s+s1;
						continue;
					}
					else if(tp == TipParametra.TEKSTUALNI_FAJL){
						s1=param.getNaziv() +"=" + "\n";
						s=s+s1;
						continue;
					}else{
						s1=param.getNaziv() +"=" + "\n";
						s2=param.getVrednost() +"=" + "\n";
						s=s+s1+s2;
					}
					
					
			}
		}
			//OVO TREBA PROMENITI AKO JE IZABRANI JEZIK ENGLESKI
			
			
			s=s+"Instaliraj" +"=" + "\n"; //6 od nazad
			s=s+"Instalaciona lokacija :"+"=" + "\n";  //5 od nazad
			s=s+"Odabir"+"=" + "\n"; //4 od nazad
			s=s+"Sledece"+"=" + "\n"; // 3 od nazad
			s=s+"Nazad"+"=" + "\n"; //2 od nazad
			s=s+"Zavrsi"+"=" + "\n"; // poslednje
			
			
		return s;
		
	}
	
	
	public String getNaziv(){
		return naziv_par;
	}
	
	public String getVrednost(){
		return vrednost_par;
	}
	public String getTip(){
		
		return tip;
	}
	
	public boolean getUspesno() {
		return zavrseno;
	}
	
	public boolean getCheck(){
		
		return false;
	}


}

