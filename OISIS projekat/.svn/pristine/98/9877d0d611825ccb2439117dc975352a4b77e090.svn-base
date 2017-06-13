package paneliZaUserMode;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ListPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private String nazivParametra;
	private String sviParametri;
	
	
public String[] razdeli(String s){
	
	String podeljeno[]=s.split(",");
	
	return podeljeno;
}
	
	public ListPanel(String s1, String s2) {
		sviParametri=s2;
		nazivParametra=s1;
	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.5; //Procenat ekrana
		height=height*0.15; 
		
		setBounds(100, 100, (int)width, (int)height); 
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{60, 0, 80, 0};
		gridBagLayout.rowHeights = new int[]{20, 40, 60, 20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblInstalacionaLokacija = new JLabel(s1);
		GridBagConstraints gbc_lblInstalacionaLokacija = new GridBagConstraints();
		gbc_lblInstalacionaLokacija.insets = new Insets(0, 0, 5, 5);
		gbc_lblInstalacionaLokacija.gridx = 1;
		gbc_lblInstalacionaLokacija.gridy = 1;
		add(lblInstalacionaLokacija, gbc_lblInstalacionaLokacija);
		
		JComboBox comboBox = new JComboBox(razdeli(sviParametri));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		add(comboBox, gbc_comboBox);
		

		}
		
	
	}


