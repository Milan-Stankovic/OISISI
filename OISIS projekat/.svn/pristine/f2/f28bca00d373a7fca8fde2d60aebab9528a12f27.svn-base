package paneliZaUserMode;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class TxtLinesPanel extends JPanel  {
	private String nazivPar;
	private String vrednostPar;
	
	public void setParVal(String s){
		vrednostPar=s;
	}
	public void setParName(String s){
		nazivPar=s;
	}	
	public TxtLinesPanel(String n, String s){
		nazivPar=n;
		vrednostPar=s;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.5; //Procenat ekrana
		height=height*0.15; 
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 350, 50, 0};
		gridBagLayout.rowHeights = new int[]{15, 40, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);		
		
		JLabel lblNaziv = new JLabel(nazivPar);
		GridBagConstraints gbc_lblNaziv = new GridBagConstraints();
		gbc_lblNaziv.insets = new Insets(0, 0, 5, 5);
		gbc_lblNaziv.gridx = 1;
		gbc_lblNaziv.gridy = 1;
		add(lblNaziv, gbc_lblNaziv);
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 2;
			add(scrollPane, gbc_scrollPane);
			{
				JTextArea textArea = new JTextArea();
				textArea.setText(vrednostPar);
				textArea.setEditable(false);
				textArea.setWrapStyleWord(true);
				textArea.setLineWrap(true);
				scrollPane.setViewportView(textArea);
			}
		}

	}

}
