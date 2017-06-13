package jdialog;

import java.awt.BorderLayout;
import java.awt.Component;
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
import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class SaveVerzija  extends JDialog {
	private final JPanel MainPanel = new JPanel();
	private JTextField textFieldParVal;
	private String lokacija;
	private String naziv;
	private boolean finished=false;
	private boolean uspesno=false;
	private Properties jezik;
	
	public String getLokacija(){
		return lokacija;
	}
	public String getNaziv(){
		return naziv;
	}
	
	public boolean getUspesno() {
		return finished;
	}

	public boolean getFinished(){
		
		return finished;
	}
	
	public void CloseFrame(){
	    super.dispose();
	}
	
	public SaveVerzija(ArrayList<String> tipovi, Properties j, boolean kraj) {
		String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/");
		ImageIcon icon = new ImageIcon(myLoc+"/LOGO.jpg");
		setIconImage(icon.getImage());
		
		jezik = new Properties();
		jezik = j;
		
		//POSTO SAM VIDEO DA RADIS KLASU ZA SVE PARAMETRE MOZES U NJU UBACITI OVAJ DEO ZA GET SCREEN SIZE PA SAMO DA SVI KORISTIMO TO
		// ILI MOZEMO SAMO SVI IMATI U KODU, KAKO GOD ZELITE 
		// SRY ZA CAPS, SAMO DA BI PRIMETILI TEKST OD KODA :D
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.4; //Procenat ekrana
		height=height*0.3; 
		

		setBounds(100, 100, (int)width, (int)height); // Mora cast u int jer prima samo intove a screenSize vraca double
		getContentPane().setLayout(new BorderLayout());
		MainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(MainPanel, BorderLayout.CENTER);//Postavljanje 
		this.setModal(true);
		
		GridBagLayout gridBagMain = new GridBagLayout();
		gridBagMain.columnWidths = new int[]{20, 100, 200, 100, 0};
		gridBagMain.rowHeights = new int[]{75, 75, 75, 0};
		gridBagMain.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagMain.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};// Podesavanje gridbaga
		
		
		MainPanel.setLayout(gridBagMain);
		{
			JLabel labParName = new JLabel(jezik.getProperty("NazivSacuvanogFajla"));
			GridBagConstraints gbc_labParName = new GridBagConstraints();
			gbc_labParName.anchor = GridBagConstraints.WEST;
			gbc_labParName.insets = new Insets(0, 0, 5, 5);
			gbc_labParName.gridx = 1;
			gbc_labParName.gridy = 0;
			MainPanel.add(labParName, gbc_labParName);
		}
		{
		if(tipovi.size()==0 && kraj==true){
			JOptionPane.showMessageDialog(null, jezik.getProperty("NemaVerzijaZaCuvanje"));
			System.exit(0);
			return;
		}
		String[] tipL = new String[tipovi.size()];
		tipL = tipovi.toArray(tipL);
		JComboBox combo_tipovi = new JComboBox(tipL);
		GridBagConstraints gbc_combo_tipovi = new GridBagConstraints();
		gbc_combo_tipovi.fill = GridBagConstraints.HORIZONTAL;
		gbc_combo_tipovi.insets = new Insets(0, 0, 5, 5);
		gbc_combo_tipovi.anchor = GridBagConstraints.NORTH;
		gbc_combo_tipovi.gridx = 2;
		gbc_combo_tipovi.gridy = 0;
		MainPanel.add(combo_tipovi, gbc_combo_tipovi);
		combo_tipovi.setAlignmentY(Component.TOP_ALIGNMENT);
		combo_tipovi.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		combo_tipovi.setSelectedIndex(0);
		{
			JPanel bottomPanel = new JPanel();
			bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(bottomPanel, BorderLayout.SOUTH);
			{
				JButton btnOK = new JButton("OK");
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lokacija = textFieldParVal.getText();
						naziv = combo_tipovi.getSelectedItem().toString();
						finished=true;
						setUspesno(true);
						CloseFrame();
					}
					
				});


				this.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosing(WindowEvent e) {
			        	uspesno=false;
						dispose();
			        }
				});
				
				getRootPane().setDefaultButton(btnOK);
				bottomPanel.add(btnOK);
				
			}
			{
				JButton btnCancel = new JButton(jezik.getProperty("Ponisti"));
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lokacija="";
						finished=true;
						uspesno=false;
						CloseFrame();
					}
				});
				btnCancel.setActionCommand("Cancel");
				bottomPanel.add(btnCancel);
			}
			}
		{
			JLabel labOdabirLokacije = new JLabel(jezik.getProperty("OdabirLokacije"));
			GridBagConstraints gbc_labOdabirLokacije = new GridBagConstraints();
			gbc_labOdabirLokacije.anchor = GridBagConstraints.WEST;
			gbc_labOdabirLokacije.insets = new Insets(0, 0, 5, 5);
			gbc_labOdabirLokacije.gridx = 1;
			gbc_labOdabirLokacije.gridy = 1;
			MainPanel.add(labOdabirLokacije, gbc_labOdabirLokacije);
		}
		{
			textFieldParVal = new JTextField();
			GridBagConstraints gbc_textFieldParVal = new GridBagConstraints();
			gbc_textFieldParVal.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldParVal.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldParVal.gridx = 2;
			gbc_textFieldParVal.gridy = 1;
			MainPanel.add(textFieldParVal, gbc_textFieldParVal);
			textFieldParVal.setColumns(10);
		}
		{
			JButton btnPretraga = new JButton(jezik.getProperty("Pretraga"));
			btnPretraga.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser locationChooser = new JFileChooser();
					locationChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					locationChooser.setAcceptAllFileFilterUsed(false);
					int response=locationChooser.showOpenDialog(null);
					if( response ==JFileChooser.APPROVE_OPTION){
						File f=locationChooser.getSelectedFile();
						lokacija=f.getAbsolutePath();
						textFieldParVal.setText(lokacija);
					}
				}
			});
			GridBagConstraints gbc_btnPretraga = new GridBagConstraints();
			gbc_btnPretraga.anchor = GridBagConstraints.EAST;
			gbc_btnPretraga.insets = new Insets(0, 0, 5, 0);
			gbc_btnPretraga.gridx = 3;
			gbc_btnPretraga.gridy = 1;
			MainPanel.add(btnPretraga, gbc_btnPretraga);
		}
		}
	}
	public boolean isUspesno() {
		return uspesno;
	}
	public void setUspesno(boolean uspesno) {
		this.uspesno = uspesno;
	}
}




