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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MeniExit extends JDialog  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private boolean sacuvaj=false;
	private boolean zavrseno=false;
	private Properties jezik;


	public MeniExit(Properties j) {
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
		setBounds(100, 100, (int)width, (int)height);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{50, 696, 50, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		this.setModal(true);
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel label = new JLabel(jezik.getProperty("ZeliteDaSacuvate?"));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.SOUTH;
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = 1;
			contentPanel.add(label, gbc_label);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton yesButton = new JButton(jezik.getProperty("Da"));
				yesButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							setSacuvaj(true);
							zavrseno=true;
							dispose();
					}
				});
				buttonPane.add(yesButton);
				getRootPane().setDefaultButton(yesButton);
			}
			
			{
				JButton noButton = new JButton(jezik.getProperty("Ne"));
				noButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							setSacuvaj(false);
							zavrseno=true;
							dispose();
					}
				});
				buttonPane.add(noButton);
				getRootPane().setDefaultButton(noButton);
			}
				
			
			
			this.addWindowListener(new WindowAdapter() {
		        @Override
		        public void windowClosing(WindowEvent e) {
		        	sacuvaj=false;
		        	zavrseno=false;
					dispose();
		        }
		});

		}
	}

	public boolean getUspesno() {
		return zavrseno;
	}
	
	public boolean getCheck(){
		
		return false;
	}

	public boolean isSacuvaj() {
		return sacuvaj;
	}

	public void setSacuvaj(boolean sacuvaj) {
		this.sacuvaj = sacuvaj;
	};
}

