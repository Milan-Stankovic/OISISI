package jdialog;



import java.awt.BorderLayout;
import warnings.Uspesno;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

public class LogoKompanije extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	
	private String odabran_par;
	private boolean zavrseno=false;
	private String tip;
	private boolean prazno=false;
	private Properties jezik;

	public boolean getPrazno(){
		return prazno;
	}
	
	public String getNaziv(){
		return "";
	}
	
	public String getVrednost() {
		return odabran_par;
	}
	
	public boolean getUspesno() {
		return zavrseno;
	}
	
	public String getTip() {
		return tip;
	}
	
	public boolean getCheck(){
		
		return false;
	};
	
	
	/*public List<String> getAll(){
		
		List<String> l = new ArrayList<String>();
		
		l.add(getTip());
		l.add(getOdabran_par());
		
		return l;
		} */
	
	
	public void CloseFrame(){
	    super.dispose();
	}
	public LogoKompanije(Properties j){
		String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/");
		ImageIcon icon = new ImageIcon(myLoc+"/LOGO.jpg");
		setIconImage(icon.getImage());
		
		jezik = new Properties();
		jezik = j;
		
		tip="Logo";
		setBounds(100, 100, 800, 450);
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
			JLabel lblNazivParametra = new JLabel(jezik.getProperty("LogoVaseKompanije"));
			GridBagConstraints gbc_lblNazivParametra = new GridBagConstraints();
			gbc_lblNazivParametra.anchor = GridBagConstraints.NORTH;
			gbc_lblNazivParametra.insets = new Insets(0, 0, 5, 5);
			gbc_lblNazivParametra.gridx = 2;
			gbc_lblNazivParametra.gridy = 0;
			contentPanel.add(lblNazivParametra, gbc_lblNazivParametra);
		}
		{
			JLabel lblOdabirLokacije = new JLabel(jezik.getProperty("OdabirLokacije"));
			GridBagConstraints gbc_lblOdabirLokacije = new GridBagConstraints();
			gbc_lblOdabirLokacije.anchor = GridBagConstraints.WEST;
			gbc_lblOdabirLokacije.insets = new Insets(0, 0, 5, 5);
			gbc_lblOdabirLokacije.gridx = 1;
			gbc_lblOdabirLokacije.gridy = 1;
			contentPanel.add(lblOdabirLokacije, gbc_lblOdabirLokacije);
		}
		{
			textField_1 = new JTextField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 5, 5);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 2;
			gbc_textField_1.gridy = 1;
			contentPanel.add(textField_1, gbc_textField_1);
			textField_1.setColumns(10);
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
						File f=chooser.getSelectedFile();
						odabran_par=f.getAbsolutePath();
						textField_1.setText(odabran_par);
					}
					else {
						odabran_par="Otkazan odabir";
						textField_1.setText(odabran_par);
					}
				}
			});
			GridBagConstraints gbc_btnPretraga = new GridBagConstraints();
			gbc_btnPretraga.anchor = GridBagConstraints.EAST;
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
						odabran_par = textField_1.getText();
						zavrseno=true;
						
						 if (odabran_par.trim().equals("")){
								JOptionPane.showMessageDialog(null,jezik.getProperty("NistePopuniliVrednostiParametara"));	
								prazno=true;
								return;
							}

						CloseFrame();
						 Uspesno poruka=new Uspesno(jezik);
						 poruka.showMess(jezik);
					}
				});
	
				getRootPane().setDefaultButton(okButton);
				buttonPane.add(okButton);
				
			}
			
			this.addWindowListener(new WindowAdapter() {
		        @Override
		        public void windowClosing(WindowEvent e) {
		        	odabran_par="";
					prazno=true;
					zavrseno=false;
					CloseFrame();
		        }
		});
			
			
			{
				JButton cancelButton = new JButton(jezik.getProperty("Izadji"));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						odabran_par="";
						zavrseno=true;
						CloseFrame();
					}
				});
				cancelButton.setActionCommand(jezik.getProperty("Ponisti"));
				buttonPane.add(cancelButton);
			}
		}
	}	
}
