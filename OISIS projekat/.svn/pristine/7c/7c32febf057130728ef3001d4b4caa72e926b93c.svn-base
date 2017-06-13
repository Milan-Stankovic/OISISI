package jdialog;

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
import java.io.File;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class Slika extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField naziv;
	private JTextField vrednost;
	private String naziv_par;
	private String vrednost_par;
	private boolean zavrseno=false;
	private String tip;
	private boolean prazno=false;
	private Properties jezik;

	public boolean getPrazno(){
		return prazno;
	}
	
	private void submitAction() {
        // You can do some validation here before assign the text to the variable 
        naziv_par=naziv.getText();
		vrednost_par=vrednost.getText();
	}

	/**
	 * Create the dialog.
	 */

	public Slika(Properties j) {
		String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/");
		ImageIcon icon = new ImageIcon(myLoc+"/LOGO.jpg");
		setIconImage(icon.getImage());
		
		jezik = new Properties ();
		jezik = j;
		tip="Slika";
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
		gbl_contentPanel.columnWidths = new int[]{20, 100, 200, 100, 0};
		gbl_contentPanel.rowHeights = new int[]{75, 75, 75, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		this.setModal(true);
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNazivParametra = new JLabel(jezik.getProperty("NazivParametra"));
			GridBagConstraints gbc_lblNazivParametra = new GridBagConstraints();
			gbc_lblNazivParametra.insets = new Insets(0, 0, 5, 5);
			gbc_lblNazivParametra.gridx = 1;
			gbc_lblNazivParametra.gridy = 0;
			contentPanel.add(lblNazivParametra, gbc_lblNazivParametra);
		}
		{
			naziv = new JTextField();
			GridBagConstraints gbc_naziv = new GridBagConstraints();
			gbc_naziv.insets = new Insets(0, 0, 5, 5);
			gbc_naziv.fill = GridBagConstraints.HORIZONTAL;
			gbc_naziv.gridx = 2;
			gbc_naziv.gridy = 0;
			contentPanel.add(naziv, gbc_naziv);
			naziv.setColumns(10);
		}
		{
			JLabel lblOdabirLokacije = new JLabel(jezik.getProperty("OdabirLokacije"));
			GridBagConstraints gbc_lblOdabirLokacije = new GridBagConstraints();
			gbc_lblOdabirLokacije.insets = new Insets(0, 0, 5, 5);
			gbc_lblOdabirLokacije.gridx = 1;
			gbc_lblOdabirLokacije.gridy = 1;
			contentPanel.add(lblOdabirLokacije, gbc_lblOdabirLokacije);
		}
		{
			vrednost = new JTextField();
			GridBagConstraints gbc_vrednost = new GridBagConstraints();
			gbc_vrednost.insets = new Insets(0, 0, 5, 5);
			gbc_vrednost.fill = GridBagConstraints.HORIZONTAL;
			gbc_vrednost.gridx = 2;
			gbc_vrednost.gridy = 0;
			contentPanel.add(vrednost,gbc_vrednost);
			vrednost.setColumns(10);
			
		}
		{
			JButton btnPretraga = new JButton(jezik.getProperty("Pretraga"));
			btnPretraga.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser chooser = new JFileChooser();
					FileFilter filter =new FileNameExtensionFilter("JPEG", "jpeg", "JPG", "jpg", "PNG", "png", "BMP", "bmp");
					
					chooser.setAcceptAllFileFilterUsed(false);
					chooser.setFileFilter(filter);
					int response=chooser.showOpenDialog(null);
					if( response ==JFileChooser.APPROVE_OPTION){
						String path=chooser.getSelectedFile().getAbsolutePath();
						File f=chooser.getSelectedFile();
						vrednost_par=f.getAbsolutePath();
						vrednost.setText(vrednost_par);
					}
					else {
						vrednost_par="Otkazan odabir";
						vrednost.setText(vrednost_par);
					}
				}
			});
			{
				vrednost = new JTextField();
				GridBagConstraints gbc_vrednost = new GridBagConstraints();
				gbc_vrednost.insets = new Insets(0, 0, 5, 5);
				gbc_vrednost.fill = GridBagConstraints.HORIZONTAL;
				gbc_vrednost.gridx = 2;
				gbc_vrednost.gridy = 1;
				contentPanel.add(vrednost, gbc_vrednost);
				vrednost.setColumns(10);
			}
			GridBagConstraints gbc_btnPretraga = new GridBagConstraints();
			gbc_btnPretraga.insets = new Insets(0, 0, 5, 0);
			gbc_btnPretraga.gridx = 3;
			gbc_btnPretraga.gridy = 1;
			contentPanel.add(btnPretraga, gbc_btnPretraga);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(jezik.getProperty("Sacuvaj"));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						submitAction();
						zavrseno=true;
						//provera da li su ispravno postavljene vrednosti
						//JOptionPane.showMessageDialog(vrednost, vrednost_par);
						//JOptionPane.showMessageDialog(naziv, naziv_par);
						if (naziv_par.trim().equals("") && vrednost_par.trim().equals("")){
							JOptionPane.showMessageDialog(null,jezik.getProperty("NistePopuniliVrednostiParametara"));	
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
				
				getRootPane().setDefaultButton(okButton);
				buttonPane.add(okButton);
				
			}
			{
				JButton cancelButton = new JButton(jezik.getProperty("Izadji"));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						vrednost_par="";
						naziv_par="";
						prazno=true;
						zavrseno=false;
						dispose();
					}
				});
				cancelButton.setActionCommand(jezik.getProperty("Ponisti"));
				buttonPane.add(cancelButton);
			}
		}
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
	};

}
