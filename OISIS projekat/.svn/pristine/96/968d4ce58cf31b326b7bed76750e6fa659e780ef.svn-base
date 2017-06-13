package paneliZaUserMode;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Panel;
import model.Parametar;
import model.TipParametra;
import model.Verzija;

@SuppressWarnings("serial")
public class DefLokPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private String vrednostPar;
	private String zadatavrednostPar;
	private JTextField textField;
	private boolean dozvoljeno;
	
	

	public void setParVal(String s){
		vrednostPar=s;
	}
	
	public String getParVal(){
		return vrednostPar;
	}

	public DefLokPanel(){
		
	}
	public DefLokPanel(String s1, boolean b, String instLokLab, String choose, Verzija v) {
		zadatavrednostPar=s1;
		vrednostPar=zadatavrednostPar;
		dozvoljeno=b;
	
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
		
		JLabel lblInstalacionaLokacija = new JLabel(instLokLab);
		GridBagConstraints gbc_lblInstalacionaLokacija = new GridBagConstraints();
		gbc_lblInstalacionaLokacija.insets = new Insets(0, 0, 5, 5);
		gbc_lblInstalacionaLokacija.gridx = 1;
		gbc_lblInstalacionaLokacija.gridy = 1;
		add(lblInstalacionaLokacija, gbc_lblInstalacionaLokacija);
		
		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		add(textField, gbc_textField);
		textField.setColumns(10);
		textField.setText(zadatavrednostPar);
		
		if(dozvoljeno){
		JButton btnOdabir = new JButton(choose);
		btnOdabir.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser locationChooser = new JFileChooser();
			locationChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			locationChooser.setAcceptAllFileFilterUsed(false);
			int response=locationChooser.showOpenDialog(null);

			if( response ==JFileChooser.APPROVE_OPTION){
				File f=locationChooser.getSelectedFile();
				vrednostPar=f.getAbsolutePath();
				v.setLokInstalacije(vrednostPar);
				for(int i=0; i<v.getChildCount(); i++){						
					Panel p = (Panel) v.getChildAt(i);
					for(int j=0; j<p.getChildCount(); j++){
						Parametar par = (Parametar) p.getChildAt(j);
						if(par.getTip()==TipParametra.DEFAULT_LOK){
							par.setVrednost(vrednostPar);
						}
					}
				}
				textField.setText(vrednostPar); // ON SAMO STAVLJA TEKST NE MENJA ZAPRAVO LOKACIJU
			}
			else {
				vrednostPar=zadatavrednostPar;
				textField.setText(vrednostPar);
			}
		}
	});
	
		GridBagConstraints gbc_btnOdabir = new GridBagConstraints();
		gbc_btnOdabir.insets = new Insets(0, 0, 5, 0);
		gbc_btnOdabir.gridx = 2;
		gbc_btnOdabir.gridy = 2;
		add(btnOdabir, gbc_btnOdabir);
		}
		
	
	}

}
