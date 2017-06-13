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
import java.io.File;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import dev_mode_gui.PanelTree;
import model.Verzija;

public class EditVerzija  extends JDialog {
	private final JPanel MainPanel = new JPanel();
	private JTextField textFieldNameVal;
	private JTextField textFieldLokS;
	private JTextField textFieldLokI;
	private JTextField textFieldLokL;
	private JCheckBox cb;
	private String naziv;
	private String lokSoftvera;
	private String lokInstalacije;
	private String lokLogo;
	private boolean check=false;
	private boolean finished=false;
	private boolean uspesno=false;
	private Properties jezik;
	
	public String getNaziv(){
		return naziv;
	}
	public String getLokSoftvera() {
		return lokSoftvera;
	}
	public String getLokInstalacije() {
		return lokInstalacije;
	}
	public String getLokLogo() {
		return lokLogo;
	}
	public boolean isCheck() {
		return check;
	}
	public boolean getUspesno() {
		return uspesno;
	}

	public boolean getFinished(){
		return finished;
	}
	
	public void CloseFrame(){
	    super.dispose();
	}
	
	public EditVerzija(PanelTree t, Properties j){
		String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/");
		ImageIcon icon = new ImageIcon(myLoc+"/LOGO.jpg");
		setIconImage(icon.getImage());
		
		jezik = new Properties();
		jezik = j;
		
		Verzija v = (Verzija) t.getLastSelectedPathComponent();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.6; //Procenat ekrana
		height=height*0.6; 
		

		setBounds(100, 100, (int)width, (int)height); // Mora cast u int jer prima samo intove a screenSize vraca double
		getContentPane().setLayout(new BorderLayout());
		MainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(MainPanel, BorderLayout.CENTER);//Postavljanje 
		this.setModal(true);
		
		GridBagLayout gridBagMain = new GridBagLayout();
		gridBagMain.columnWidths = new int[]{20, 100, 200, 100, 0};
		gridBagMain.rowHeights = new int[]{70, 70, 30, 40, 70, 100};
		gridBagMain.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagMain.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};// Podesavanje gridbaga
		
		
		MainPanel.setLayout(gridBagMain);
		{
			JLabel labParName = new JLabel(jezik.getProperty("NazivVerzije"));
			GridBagConstraints gbc_labParName = new GridBagConstraints();
			gbc_labParName.anchor = GridBagConstraints.WEST;
			gbc_labParName.insets = new Insets(0, 0, 5, 5);
			gbc_labParName.gridx = 1;
			gbc_labParName.gridy = 0;
			MainPanel.add(labParName, gbc_labParName);
		}
		{
			textFieldNameVal = new JTextField();
			GridBagConstraints gbc_textFieldParVal = new GridBagConstraints();
			gbc_textFieldParVal.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldParVal.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldParVal.gridx = 2;
			gbc_textFieldParVal.gridy = 0;
			MainPanel.add(textFieldNameVal, gbc_textFieldParVal);
			textFieldNameVal.setText(v.getNaziv().toString());
			textFieldNameVal.setColumns(10);
		}
		{
			JLabel labParLokI = new JLabel(jezik.getProperty("LokacijaInstalacije"));
			GridBagConstraints gbc_labParLokI  = new GridBagConstraints();
			gbc_labParLokI.anchor = GridBagConstraints.WEST;
			gbc_labParLokI.insets = new Insets(0, 0, 5, 5);
			gbc_labParLokI.gridx = 1;
			gbc_labParLokI.gridy = 1;
			MainPanel.add(labParLokI, gbc_labParLokI);
		}
		{
			textFieldLokI = new JTextField();
			GridBagConstraints gbc_textFieldLokI = new GridBagConstraints();
			gbc_textFieldLokI.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldLokI.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldLokI.gridx = 2;
			gbc_textFieldLokI.gridy = 1;
			MainPanel.add(textFieldLokI, gbc_textFieldLokI);
			textFieldLokI.setText(v.getLokInstalacije().toString());
			textFieldLokI.setColumns(10);
		}
		{
			JButton btnPretraga = new JButton(jezik.getProperty("Pretraga"));
			btnPretraga.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser locationChooser = new JFileChooser();
					locationChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					locationChooser.setAcceptAllFileFilterUsed(false);
					int response=locationChooser.showOpenDialog(null);
					if( response ==JFileChooser.APPROVE_OPTION){
						File f=locationChooser.getSelectedFile();
						lokInstalacije=f.getAbsolutePath();
						textFieldLokI.setText(lokInstalacije);
					}
				}
			});
			GridBagConstraints gbc_btnPretraga = new GridBagConstraints();
			gbc_btnPretraga.anchor = GridBagConstraints.EAST;
			gbc_btnPretraga.insets = new Insets(0, 0, 5, 0);
			gbc_btnPretraga.gridx = 3;
			gbc_btnPretraga.gridy = 1;
			MainPanel.add(btnPretraga, gbc_btnPretraga);
		}{
			JLabel lblDozvoljenaPromenaLokacije = new JLabel(jezik.getProperty("DozvoljenaPromenaLokacije"));
			GridBagConstraints gbc_lblDozvoljenaPromenaLokacije = new GridBagConstraints();
			gbc_lblDozvoljenaPromenaLokacije.anchor = GridBagConstraints.WEST;
			gbc_lblDozvoljenaPromenaLokacije.insets = new Insets(0, 0, 0, 5);
			gbc_lblDozvoljenaPromenaLokacije.gridx = 1;
			gbc_lblDozvoljenaPromenaLokacije.gridy = 2;
			MainPanel.add(lblDozvoljenaPromenaLokacije, gbc_lblDozvoljenaPromenaLokacije);
		}
		{
			cb = new JCheckBox(jezik.getProperty("Da"));
			GridBagConstraints gbc_cb = new GridBagConstraints();
			gbc_cb.insets = new Insets(0, 0, 0, 5);
			gbc_cb.gridx = 2;
			gbc_cb.gridy = 2;
			cb.setSelected(v.isCheck());
			MainPanel.add(cb, gbc_cb);
		}
		{
			JLabel labParLokS = new JLabel(jezik.getProperty("LokacijaSofrveraKojiCeBitiInstaliran"));
			GridBagConstraints gbc_labParLokS = new GridBagConstraints();
			gbc_labParLokS.anchor = GridBagConstraints.WEST;
			gbc_labParLokS.insets = new Insets(0, 0, 5, 5);
			gbc_labParLokS.gridx = 1;
			gbc_labParLokS.gridy = 4;
			MainPanel.add(labParLokS, gbc_labParLokS);
		}
		{
			textFieldLokS = new JTextField();
			GridBagConstraints gbc_textFieldLokS = new GridBagConstraints();
			gbc_textFieldLokS.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldLokS.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldLokS.gridx = 2;
			gbc_textFieldLokS.gridy = 4;
			MainPanel.add(textFieldLokS, gbc_textFieldLokS);
			textFieldLokS.setText(v.getLokSoftvera().toString());
			textFieldLokS.setColumns(10);
		}
		{
			JButton btnPretraga = new JButton(jezik.getProperty("Pretraga"));
			btnPretraga.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser locationChooser = new JFileChooser();
					locationChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					locationChooser.setAcceptAllFileFilterUsed(false);
					int response=locationChooser.showOpenDialog(null);
					if( response ==JFileChooser.APPROVE_OPTION){
						File f=locationChooser.getSelectedFile();
						lokSoftvera=f.getAbsolutePath();
						textFieldLokS.setText(lokSoftvera);
					}
				}
			});
			GridBagConstraints gbc_btnPretraga = new GridBagConstraints();
			gbc_btnPretraga.anchor = GridBagConstraints.EAST;
			gbc_btnPretraga.insets = new Insets(0, 0, 5, 0);
			gbc_btnPretraga.gridx = 3;
			gbc_btnPretraga.gridy = 4;
			MainPanel.add(btnPretraga, gbc_btnPretraga);
		}
		{
			JLabel labParLokL = new JLabel(jezik.getProperty("LokacijaLogaKompanije"));
			GridBagConstraints gbc_labParLokL = new GridBagConstraints();
			gbc_labParLokL.anchor = GridBagConstraints.WEST;
			gbc_labParLokL.insets = new Insets(0, 0, 5, 5);
			gbc_labParLokL.gridx = 1;
			gbc_labParLokL.gridy = 5;
			MainPanel.add(labParLokL, gbc_labParLokL);
		}
		{
			textFieldLokL = new JTextField();
			GridBagConstraints gbc_textFieldLokL = new GridBagConstraints();
			gbc_textFieldLokL.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldLokL.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldLokL.gridx = 2;
			gbc_textFieldLokL.gridy = 5;
			MainPanel.add(textFieldLokL, gbc_textFieldLokL);
			textFieldLokL.setText(v.getLokLogo().toString());
			textFieldLokL.setColumns(10);
		}
		{
			JButton btnPretraga = new JButton(jezik.getProperty("Pretraga"));
			btnPretraga.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser locationChooser = new JFileChooser();
					FileFilter filter =new FileNameExtensionFilter("JPEG", "jpeg", "JPG", "jpg", "PNG", "png", "BMP", "bmp");
					locationChooser.setFileFilter(filter);
					locationChooser.setAcceptAllFileFilterUsed(false);
					int response=locationChooser.showOpenDialog(null);
					if( response ==JFileChooser.APPROVE_OPTION){
						File f=locationChooser.getSelectedFile();
						lokLogo=f.getAbsolutePath();
						textFieldLokL.setText(lokLogo);
					}
				}
			});
			GridBagConstraints gbc_btnPretraga = new GridBagConstraints();
			gbc_btnPretraga.anchor = GridBagConstraints.EAST;
			gbc_btnPretraga.insets = new Insets(0, 0, 5, 0);
			gbc_btnPretraga.gridx = 3;
			gbc_btnPretraga.gridy = 5;
			MainPanel.add(btnPretraga, gbc_btnPretraga);
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
						lokSoftvera = textFieldLokS.getText().toString();
						lokInstalacije = textFieldLokI.getText().toString();
						lokLogo = textFieldLokL.getText().toString();
						check = cb.isSelected();
						finished=true;
						uspesno=true;
						CloseFrame();
					}
				});
				this.addWindowListener(new WindowAdapter() {
			        @Override
			        public void windowClosing(WindowEvent e) {
						naziv=v.getNaziv();
						uspesno=false;
						dispose();
			        }
				});
				
				
				getRootPane().setDefaultButton(btnOK);
				bottomPanel.add(btnOK);
				
			}
			{
				JButton btnCancel = new JButton(jezik.getProperty("Ponisti"));
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						naziv=v.getNaziv();
						uspesno=false;
						finished=true;
						CloseFrame();
					}
				});
			}
		}
	}
	public boolean isUspesno() {
		return uspesno;
	}
	public void setUspesno(boolean uspesno) {
		this.uspesno = uspesno;
	}
}

