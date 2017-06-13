package jEditDialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class EditInstaler  extends JDialog {
	private final JPanel MainPanel = new JPanel();
	private JTextField textFieldNameVal;
	private String naziv;
	private boolean finished=false;
	private Properties jezik;
	
	public String getNaziv(){
		return naziv;
	}
	
	public boolean getUspesno() {
		return finished;
	}

	public boolean getFinished(){
		return finished;
	}
	
	public void CloseFrame(){
	    super.dispose();
	}
	
	public EditInstaler(String s, Properties j){
		String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/");
		ImageIcon icon = new ImageIcon(myLoc+"/LOGO.jpg");
		setIconImage(icon.getImage());
		
		jezik = new Properties();
		jezik = j;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.4; //Procenat ekrana
		height=height*0.3; 
		

		setBounds(100, 100, (int)width, (int)height); // Mora cast u int jer prima samo intove a screenSize vraca double
		getContentPane().setLayout(new BorderLayout());
		MainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(MainPanel, BorderLayout.CENTER);//Postavljanje 
		this.setModal(true);
		
		GridBagLayout gridBagMain = new GridBagLayout();
		gridBagMain.columnWidths = new int[]{20, 100, 200, 100, 0};
		gridBagMain.rowHeights = new int[]{75, 75, 75, 0};
		gridBagMain.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagMain.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};// Podesavanje gridbaga
		
		
		MainPanel.setLayout(gridBagMain);
		{
			JLabel labParName = new JLabel(jezik.getProperty("NoviNaziv"));
			GridBagConstraints gbc_labParName = new GridBagConstraints();
			gbc_labParName.anchor = GridBagConstraints.WEST;
			gbc_labParName.insets = new Insets(0, 0, 5, 5);
			gbc_labParName.gridx = 1;
			gbc_labParName.gridy = 1;
			MainPanel.add(labParName, gbc_labParName);
		}
		{
			textFieldNameVal = new JTextField();
			GridBagConstraints gbc_textFieldParVal = new GridBagConstraints();
			gbc_textFieldParVal.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldParVal.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldParVal.gridx = 2;
			gbc_textFieldParVal.gridy = 1;
			MainPanel.add(textFieldNameVal, gbc_textFieldParVal);
			textFieldNameVal.setText(s);
			textFieldNameVal.setColumns(10);
		}
		{
			JPanel bottomPanel = new JPanel();
			bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(bottomPanel, BorderLayout.SOUTH);
			{
				JButton btnOK = new JButton("OK");
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						naziv = textFieldNameVal.getText().toString();
						finished=true;
						CloseFrame();
					}
				});
				this.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosing(WindowEvent e) {
						dispose();
						naziv=s;
			        }
				});
				
				
				getRootPane().setDefaultButton(btnOK);
				bottomPanel.add(btnOK);
				
			}
			{
				JButton btnCancel = new JButton(jezik.getProperty("Ponisti"));
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						naziv=s;
						finished=true;
						CloseFrame();
					}
				});
			}
		}
	}
}
