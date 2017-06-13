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
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CheckBox extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String naziv_par;
	private String vrednost_par;
	private boolean zavrseno=false;
	private String tip;
	private boolean prazno=false;
	private JTextField textField;
	private JTextField textField_1;
	private Properties jezik;
	
	private void submitAction() {
        // You can do some validation here before assign the text to the variable 
        naziv_par=textField.getText();
		vrednost_par=textField_1.getText();
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

	public boolean getPrazno(){
		return prazno;
	}
	


	/**
	 * Create the dialog.
	 */
	public CheckBox(Properties j) {
		String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/");
		ImageIcon icon = new ImageIcon(myLoc+"/LOGO.jpg");
		setIconImage(icon.getImage());
		
		jezik = new Properties();
		jezik = j;
		tip="Checkbox";
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.6; //Procenat ekrana
		height=height*0.5; 
		setBounds(100, 100, (int)width, (int)height);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		this.setModal(true);
		
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0};
		gbl_contentPanel.rowHeights = new int[]{50, 0, 0, 0, 0, 50, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
	
			JLabel lblUnesiteNaziv = new JLabel(jezik.getProperty("UnesiteNaziv"));
			GridBagConstraints gbc_lblUnesiteNaziv = new GridBagConstraints();
			gbc_lblUnesiteNaziv.insets = new Insets(0, 0, 5, 0);
			gbc_lblUnesiteNaziv.gridx = 0;
			gbc_lblUnesiteNaziv.gridy = 1;
			contentPanel.add(lblUnesiteNaziv, gbc_lblUnesiteNaziv);
	
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.gridx = 0;
			gbc_textField.gridy = 2;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(30);
			naziv_par=textField.getText();
	
			JLabel lblUnesiteTekst = new JLabel(jezik.getProperty("UnesiteTekst"));
			GridBagConstraints gbc_lblUnesiteTekst = new GridBagConstraints();
			gbc_lblUnesiteTekst.insets = new Insets(0, 0, 5, 0);
			gbc_lblUnesiteTekst.gridx = 0;
			gbc_lblUnesiteTekst.gridy = 3;
			contentPanel.add(lblUnesiteTekst, gbc_lblUnesiteTekst);
	
			textField_1 = new JTextField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 5, 0);
			gbc_textField_1.gridx = 0;
			gbc_textField_1.gridy = 4;
			contentPanel.add(textField_1, gbc_textField_1);
			textField_1.setColumns(30);
			vrednost_par=textField_1.getText();
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				JButton btnSave = new JButton(jezik.getProperty("Sacuvaj"));
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						vrednost_par = textField_1.getText();
						naziv_par=textField.getText();
						zavrseno=true;
						if (naziv_par.trim().equals("") && vrednost_par.trim().equals("")){
							JOptionPane.showMessageDialog(null, jezik.getProperty("NistePopuniliVrednostiParametara"));	
							prazno=true;
							return;
						}
						dispose();
					}
				});

				
				buttonPane.add(btnSave);
			
			
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
