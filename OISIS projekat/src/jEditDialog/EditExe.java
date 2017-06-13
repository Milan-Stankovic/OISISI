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
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dev_mode_gui.PanelTree;
import model.Parametar;

public class EditExe extends JDialog  {

	private final JPanel MainPanel = new JPanel();
	private JTextField textFieldParName;
	private JTextField textFieldParVal;
	private String parName;
	private String parVal;
	private boolean finished=false;
	private JCheckBox cb;
	private String parType="Exe";
	private boolean prazno=false;
	private Properties jezik;

	public boolean getPrazno(){
		return prazno;
	}
	public String getVrednost(){
		
		return parVal;
	}
	public String getNaziv(){
		
		return parName;
	}
	public String getTip(){
		
		return parType;
	}
	public boolean getCheck(){
		
		return cb.isSelected();
	}
	public boolean getUspesno(){
		
		return finished;
	}	
	public void CloseFrame(){
	    super.dispose();
	}
	
	public EditExe(PanelTree tree, Properties j) {
		String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/");
		ImageIcon icon = new ImageIcon(myLoc+"/LOGO.jpg");
		setIconImage(icon.getImage());
		
		jezik = new Properties();
		jezik = j;
		Parametar p = (Parametar) tree.getLastSelectedPathComponent();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.6; //Procenat ekrana
		height=height*0.5; 
		

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
			JLabel labParName = new JLabel(jezik.getProperty("NazivKomande"));
			GridBagConstraints gbc_labParName = new GridBagConstraints();
			gbc_labParName.anchor = GridBagConstraints.WEST;
			gbc_labParName.insets = new Insets(0, 0, 5, 5);
			gbc_labParName.gridx = 1;
			gbc_labParName.gridy = 0;
			MainPanel.add(labParName, gbc_labParName);
		}
		
		{
			textFieldParName = new JTextField();
			GridBagConstraints gbc_textFieldParName = new GridBagConstraints();
			gbc_textFieldParName.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldParName.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldParName.gridx = 2;
			gbc_textFieldParName.gridy = 0;
			MainPanel.add(textFieldParName, gbc_textFieldParName);
			textFieldParName.setColumns(10);
			textFieldParName.setText(p.getNaziv());
		}
		{
			JLabel labOdabirLokacije = new JLabel(jezik.getProperty("Komanda"));
			GridBagConstraints gbc_labOdabirLokacije = new GridBagConstraints();
			gbc_labOdabirLokacije.anchor = GridBagConstraints.WEST;
			gbc_labOdabirLokacije.insets = new Insets(0, 0, 5, 5);
			gbc_labOdabirLokacije.gridx = 1;
			gbc_labOdabirLokacije.gridy = 1;
			MainPanel.add(labOdabirLokacije, gbc_labOdabirLokacije);
		}
		{
			textFieldParVal = new JTextField();
			GridBagConstraints gbc_textFieldParVal = new GridBagConstraints();
			gbc_textFieldParVal.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldParVal.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldParVal.gridx = 2;
			gbc_textFieldParVal.gridy = 1;
			MainPanel.add(textFieldParVal, gbc_textFieldParVal);
			textFieldParVal.setColumns(10);
			textFieldParVal.setText(p.getVrednost());
		}
		{
			JLabel labObavezna = new JLabel(jezik.getProperty("PrikazatiLinijuNapretka"));
			GridBagConstraints gbc_labObavezna = new GridBagConstraints();
			gbc_labObavezna.anchor = GridBagConstraints.WEST;
			gbc_labObavezna.insets = new Insets(0, 0, 0, 5);
			gbc_labObavezna.gridx = 1;
			gbc_labObavezna.gridy = 2;
			MainPanel.add(labObavezna, gbc_labObavezna);
		}
		{
			cb = new JCheckBox(jezik.getProperty("Da"));
			GridBagConstraints gbc_cb = new GridBagConstraints();
			gbc_cb.insets = new Insets(0, 0, 0, 5);
			gbc_cb.gridx = 2;
			gbc_cb.gridy = 2;
			MainPanel.add(cb, gbc_cb);
			cb.setSelected(p.isCheck());
		}
		{
			JPanel bottomPanel = new JPanel();
			bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(bottomPanel, BorderLayout.SOUTH);
			{
				JButton btnSave = new JButton(jezik.getProperty("Sacuvaj"));
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						parName = textFieldParName.getText();
						parVal=textFieldParVal.getText();
						if (parName.trim().equals("") && parVal.trim().equals("")){
							JOptionPane.showMessageDialog(null, jezik.getProperty("NistePopuniliVrednostiParametara"));	
							return;
						}
						finished=true;
						CloseFrame();
					}
				});
				
				
				this.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosing(WindowEvent e) {
			        	parVal=p.getVrednost();
						parName=p.getNaziv();
						prazno=true;
						finished=false;
						CloseFrame();
			        }
			});
				
				
				getRootPane().setDefaultButton(btnSave);
				bottomPanel.add(btnSave);
				
			}
			{
				JButton btnCancel = new JButton(jezik.getProperty("Izadji"));
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						parVal=p.getVrednost();
						parName=p.getNaziv();
						prazno=true;
						finished=false;
						CloseFrame();
					}
				});
				btnCancel.setActionCommand("Cancel");
				bottomPanel.add(btnCancel);
			}
		}
	}

}

