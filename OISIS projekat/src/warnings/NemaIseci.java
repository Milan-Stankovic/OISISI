package warnings;

import javax.swing.JOptionPane;
import java.util.Properties;
public class NemaIseci {
	private  Properties jezik;
	
	public void showMess(Properties jezik){
		JOptionPane.showMessageDialog(null, jezik.getProperty("NemaSelektovaneStavkeZaIseci"));
	}
	
	public NemaIseci(Properties j){
		jezik=j;
		
	}

}
