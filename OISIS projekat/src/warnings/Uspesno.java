package warnings;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Uspesno {
	private  Properties jezik;
	
	
	public void showMess(Properties jezik){
		JOptionPane.showMessageDialog(null, jezik.getProperty("Uspesnoizvrsenaoperacija"));
	}
	
	public Uspesno(Properties j){
		jezik=j;
		
	}


}
