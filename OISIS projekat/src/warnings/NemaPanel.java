package warnings;

import javax.swing.JOptionPane;
import java.util.Properties;
public class NemaPanel {
	private  Properties jezik;
	
	public void showMess(Properties jezik){
		JOptionPane.showMessageDialog(null, jezik.getProperty("NisteSelektovaliVerziju"));
	}
	
	public NemaPanel(Properties j){
		jezik=j;
		
	}

}
