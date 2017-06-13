package jdialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultTreeModel;

import dev_mode_gui.PanelTree;
import model.Instaler;
import model.Panel;
import model.Parametar;
import model.Verzija;

public class ShowProperties extends JDialog {
	
	private final JPanel MainPanel = new JPanel();
	private boolean prazno=false;
	
	public boolean getPrazno(){
		return prazno;
	}

	
	public ShowProperties(PanelTree tree) {
		String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/");
		ImageIcon icon = new ImageIcon(myLoc+"/LOGO.jpg");
		setIconImage(icon.getImage());
		
		String s = createTxt(tree);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.5; //Procenat ekrana
		height=height*0.6; 
		

		setBounds(100, 100, (int)width, (int)height); // Mora cast u int jer prima samo intove a screenSize vraca double
		getContentPane().setLayout(new BorderLayout());
		MainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(MainPanel, BorderLayout.CENTER);//Postavljanje 
		this.setModal(true);
		
		GridBagLayout gridBagMain = new GridBagLayout();
		gridBagMain.columnWidths = new int[]{20, 400, 0};
		gridBagMain.rowHeights = new int[]{10, 500, 100};
		gridBagMain.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagMain.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};// Podesavanje gridbaga
		MainPanel.setLayout(gridBagMain);
	
		
		
		JTextArea textArea = new JTextArea(s);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		
		
		GridBagConstraints gbc_txtrOvdeIdeRead = new GridBagConstraints();
		gbc_txtrOvdeIdeRead.insets = new Insets(0, 0, 5, 5);
		gbc_txtrOvdeIdeRead.fill = GridBagConstraints.BOTH;
		gbc_txtrOvdeIdeRead.gridx = 1;
		gbc_txtrOvdeIdeRead.gridy = 1;
		JScrollPane areaScrollPane = new JScrollPane(textArea);
		areaScrollPane.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		MainPanel.add(areaScrollPane, gbc_txtrOvdeIdeRead);
		
		
		
	}

	private String createTxt(PanelTree tree){
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		Instaler root = (Instaler) model.getRoot();
		
		String s ="";
		String s0 = "";
		String s1 = "";
		String s2 = "";
		String s3 = "";
		for(int i=0; i<root.getChildCount();i++){
			Verzija v = (Verzija) root.getChildAt(i);
			s0=" VERZIJA/VERSION -  "+v.getNaziv()+"\n";
			s1="    lokacija instalacije/default location: "+v.getLokInstalacije()+"\n";
			s2="    lokacija softvera/software location: "+v.getLokSoftvera()+"\n";
			s3="    lokacija loga/location of the logo: "+v.getLokSoftvera()+"\n";
			s=s+s0+s1+s2+s3;
			for(int j=0; j<v.getChildCount(); j++){
				Panel p = (Panel) v.getChildAt(j);
				s0="              PANEL/PANEL -  "+p.getNaziv()+"\n";
				s=s+s0;
				for(int k=0; k<p.getChildCount(); k++){
					Parametar param = (Parametar) p.getChildAt(k);
					s0="\t        "+" PARAMERAR/PARAMETER -  "+param.getTip().toString()+"\n";
					s1="\t             "+" naziv/name: "+param.getNaziv()+"\n";
					s2="\t             "+" vrednost/value: "+param.getVrednost()+"\n";
					s=s+s0+s1+s2;
				}
			}
		}
		return s;
		
	}
}


