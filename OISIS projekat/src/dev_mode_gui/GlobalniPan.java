package dev_mode_gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GlobalniPan extends JDialog {
	private JTextField txtLokacija;
	private JTextField textField;
	private String softLok="";
	private String instalLok="";
	private String logo="";
	

	/**
	 * Create the frame.
	 */
	
	public void CloseFrame(){
	    super.dispose();
	}
	
	public void setSoftLok(String s){
		softLok=s;
		
	}
	
	public void setInstalLok(String s){
		instalLok=s;
		
	}
	
	public void setLogo(String s){
		logo=s;
		
	}
	
	public GlobalniPan(String s1, String s2, String s3, Properties jezik) {
		String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/");
		ImageIcon icon = new ImageIcon(myLoc+"/LOGO.jpg");
		setIconImage(icon.getImage());
		
		softLok=s2;
		instalLok=s1;
		logo=s3;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.6; //Procenat ekrana
		height=height*0.5; 
		setBounds(100, 100, (int)width, (int)height);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{250, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel label = new JLabel(jezik.getProperty("DefaultInstallLocation"));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		getContentPane().add(label, gbc_label);
		
		txtLokacija = new JTextField();
		txtLokacija.setText(instalLok);
		GridBagConstraints gbc_txtLokacija = new GridBagConstraints();
		gbc_txtLokacija.insets = new Insets(0, 0, 5, 5);
		gbc_txtLokacija.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLokacija.gridx = 1;
		gbc_txtLokacija.gridy = 0;
		getContentPane().add(txtLokacija, gbc_txtLokacija);
		txtLokacija.setColumns(10);
		
		JLabel lblSoftvare = new JLabel(jezik.getProperty("LokacijaSoftwvra"));
		GridBagConstraints gbc_lblSoftvare = new GridBagConstraints();
		gbc_lblSoftvare.insets = new Insets(0, 0, 5, 5);
		gbc_lblSoftvare.gridx = 0;
		gbc_lblSoftvare.gridy = 1;
		getContentPane().add(lblSoftvare, gbc_lblSoftvare);
		
		textField = new JTextField();
		textField.setText(softLok);
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		getContentPane().add(textField, gbc_textField);
		
		JLabel lblLogo = new JLabel("Logo : ");
		GridBagConstraints gbc_lblLogo = new GridBagConstraints();
		gbc_lblLogo.fill = GridBagConstraints.VERTICAL;
		gbc_lblLogo.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogo.gridx = 0;
		gbc_lblLogo.gridy = 2;
		getContentPane().add(lblLogo, gbc_lblLogo);
		
		JLabel lblNewLabel = new JLabel();
		if(logo!=""){
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(logo).getImage().getScaledInstance((int)width/2, (int)height/2, Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(imageIcon);
		}
		
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		
		
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseFrame();
			}
		});
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.gridwidth = 3;
		gbc_btnOk.insets = new Insets(0, 0, 0, 5);
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 3;
		getContentPane().add(btnOk, gbc_btnOk);

	}

}
