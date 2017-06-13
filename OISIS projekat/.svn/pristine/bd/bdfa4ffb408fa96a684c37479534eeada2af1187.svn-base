package paneliZaUserMode;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SlikaPanel extends JPanel {
	private String nazivPar;
	private String vrednostPar;

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
	public SlikaPanel(String n, String lok){
		nazivPar=n;
		vrednostPar=lok;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.5; //Procenat ekrana
		height=height*0.5;
		JLabel label = new JLabel();
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(lok).getImage().getScaledInstance((int)width/2, (int)height/2, Image.SCALE_DEFAULT));
		label.setIcon(imageIcon);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 0};
		gridBagLayout.rowHeights = new int[]{300, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		

						GridBagConstraints gbc_label = new GridBagConstraints();
						gbc_label.fill = GridBagConstraints.VERTICAL;
						gbc_label.gridx = 0;
						gbc_label.gridy = 0;
						add(label, gbc_label );
		
		
}
}

