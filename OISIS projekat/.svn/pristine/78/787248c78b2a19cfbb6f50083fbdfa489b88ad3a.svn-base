package paneliZaUserMode;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
@SuppressWarnings("serial")
public class TxtPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private String nazivPar;
	private String vrednostPar;
	private boolean mora=true;
	
	public void setParVal(String s){
		vrednostPar=s;
	}
	public void setParName(String s){
		nazivPar=s;
	}
	
	public boolean getCheckbox(){
		if(!mora){
			return true;
			
		}
		
		return true;
	}
	

	public String readTxt(String txt){
		
		
		String everything= new String();
		try(BufferedReader br = new BufferedReader(new FileReader(txt))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		  everything = sb.toString();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return everything;
		
		
	}
	
	public TxtPanel(String s1, String s2, boolean b) {
		nazivPar=s1;
		vrednostPar=s2;
		mora=b;// moze se ovaj deo izbaciti
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 0, 50, 0};
		gridBagLayout.rowHeights = new int[]{15, 40, 0, 30, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel label = new JLabel(s1);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		add(label, gbc_label);
		String tekst="";
		if(vrednostPar!=""){
			tekst=readTxt(vrednostPar);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMaximumSize(new Dimension(500, 10000));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setText(tekst);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		GridBagConstraints gbc_txtrOvdeIdeRead = new GridBagConstraints();
		gbc_txtrOvdeIdeRead.insets = new Insets(0, 0, 5, 5);
		gbc_txtrOvdeIdeRead.fill = GridBagConstraints.BOTH;
		gbc_txtrOvdeIdeRead.gridx = 1;
		gbc_txtrOvdeIdeRead.gridy = 2;

		scrollPane.setViewportView(textArea);
		
		
	}
}
