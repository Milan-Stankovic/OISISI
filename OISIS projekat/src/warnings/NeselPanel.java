package warnings;

import javax.swing.JOptionPane;
import java.util.Properties;
public class NeselPanel {
	private  Properties jezik;
	
	public void showMess(Properties jezik){
		JOptionPane.showMessageDialog(null, jezik.getProperty("NisteSelektovaliPanelZaDodavanjeParametara"));
	}
	
	public NeselPanel(Properties j){
		jezik=j;
		
	}

}
