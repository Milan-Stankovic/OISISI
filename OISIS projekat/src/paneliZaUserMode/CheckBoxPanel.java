package paneliZaUserMode;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheckBoxPanel extends JPanel {
	private String vrednostPar;
	private String nazivPar;
	private JCheckBox chckbxNewCheckBox;
	

	public void setParVal(String s){
		vrednostPar=s;
	}
	public void setParName(String s){
		nazivPar=s;
	}
	public String getNaziv(){
		return nazivPar;
	}
	public String getVrednost(){
		return vrednostPar;
	}
	
	public JCheckBox getCB(){
		return chckbxNewCheckBox;
	}
	
	/**
	 * Create the panel.
	 */
	public CheckBoxPanel(String naziv, String vrednost) {
		vrednostPar=vrednost;
		nazivPar=naziv; 
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{150, 150, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTekst = new JLabel(nazivPar);
		GridBagConstraints gbc_lblTekst = new GridBagConstraints();
		gbc_lblTekst.anchor = GridBagConstraints.EAST;
		gbc_lblTekst.insets = new Insets(0, 0, 0, 5);
		gbc_lblTekst.gridx = 0;
		gbc_lblTekst.gridy = 0;
		add(lblTekst, gbc_lblTekst);
		
		chckbxNewCheckBox = new JCheckBox(vrednostPar);
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.anchor = GridBagConstraints.WEST;
		gbc_chckbxNewCheckBox.fill = GridBagConstraints.VERTICAL;
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 0;
		add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);

	}

}
