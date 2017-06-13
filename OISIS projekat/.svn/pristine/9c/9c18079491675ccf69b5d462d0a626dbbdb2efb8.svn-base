package user_mode_gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import jdialog.CheckBox;
import model.Panel;
import model.Parametar;
import model.TipParametra;
import model.Verzija;
import paneliZaUserMode.CheckBoxPanel;
import paneliZaUserMode.DefLokPanel;
import paneliZaUserMode.ExePanel;
import paneliZaUserMode.InstalPanel;
import paneliZaUserMode.ListPanel;
import paneliZaUserMode.SlikaPanel;
import paneliZaUserMode.TxtLinePanel;
import paneliZaUserMode.TxtLinesPanel;
import paneliZaUserMode.TxtPanel;

@SuppressWarnings("serial")
public class UserFrame extends JFrame{

	private JPanel contentPane;
	private String imeKomp;
	private ArrayList<Panel> panList;
	private JButton btnSledece;
	private JButton btnNazad;
	private ArrayList<JPanel> JPanlista;
	private int brojac=0;
	private Verzija ver;
	private JPanel panel;
	private JCheckBox cb=null;
	Dimension screenSize;
	private Properties jezik;
	
	public Dimension getScreenSize() {
		return screenSize;
	}

	/**
	 * Create the frame.
	 */
	public JPanel getPanel(int i){
		
		return JPanlista.get(i);
			
	}

	public boolean isComponentInPanel(Component component, int i) {
	    return
	        java.util.Arrays.asList(getPanel(i).getComponents())
	            .contains(component);
	}
	public void setSledece(boolean b){
		btnSledece.setEnabled(b);
	}
	
	
	public UserFrame(Verzija v,Properties j) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(v.getNaziv());
		screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.7; //Procenat ekrana
		height=height*0.6; 
		jezik=j;

		setBounds(100, 100, (int)width, (int)height); // Mora cast u int jer prima samo intove a screenSize vraca double
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{50, 0, 50, 0};
		gbl_contentPane.rowHeights = new int[]{0, 188, 50, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		

	
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		panList=v.getPaneli();
		
		JPanlista=new ArrayList<JPanel>();
		for(int i=0;i<panList.size();i++){
			JPanel pomocni=new JPanel();
			pomocni.setLayout(new BoxLayout(pomocni, BoxLayout.Y_AXIS));
			JPanlista.add(i, pomocni);
			ver=v;
			upis(ver, panList.get(i).getParametri().size(),panList.get(i).getParametri(), JPanlista.get(i));
		}
		scrollPane.setViewportView(JPanlista.get(0));
		

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setSize(screenSize);
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		contentPane.add(panel, gbc_panel);
		
		btnNazad = new JButton(jezik.getProperty("Nazad"));
		if(brojac==0)
			btnNazad.setEnabled(false);
		btnNazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(brojac!=0){	
					brojac--;
					btnSledece.setEnabled(true);
					scrollPane.setViewportView(JPanlista.get(brojac));					
					if(brojac==0)
						btnNazad.setEnabled(false);
			}	
					
				else
					btnNazad.setEnabled(false);
			}
		});
		panel.add(btnNazad);
			
		
		btnSledece = new JButton(jezik.getProperty("Sledece"));
		
		if(brojac==0){
			Component[] components = JPanlista.get(brojac).getComponents(); 
					Component component = null; 
					
					for (int i = 0; i < components.length; i++) 
					{ 
						component = components[i]; 
						
			
						if (component instanceof CheckBoxPanel) 
						{ 
							CheckBoxPanel cbp=(CheckBoxPanel) component;
							cb=cbp.getCB();
							break;
						
						}	
					}
						if(cb!=null){
							btnSledece.setEnabled(false);
							cb.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									if(cb.isSelected())
										btnSledece.setEnabled(true);
									if(!cb.isSelected())
										btnSledece.setEnabled(false);
								
									
								}
							});
							if(cb.isSelected()){
								cb.setEnabled(false);
								btnSledece.setEnabled(true);
							}
						}
						
		}

		if(brojac==JPanlista.size()-1)
			btnSledece.setEnabled(false);

		btnSledece.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cb!=null){
				if(cb.isSelected()){
					cb.setEnabled(false);
					btnSledece.setEnabled(true);
				}
				}
				
				if(brojac!=JPanlista.size()-1){
					brojac++;
					cb=null;
					scrollPane.setViewportView(JPanlista.get(brojac));
					
					Component[] components = JPanlista.get(brojac).getComponents(); 
					Component component = null; 
					
					for (int i = 0; i < components.length; i++) 
					{ 
						component = components[i]; 
						
			
						if (component instanceof CheckBoxPanel) 
						{ 
							CheckBoxPanel cbp=(CheckBoxPanel) component;
							cb=cbp.getCB();
							break;
						
						}	
					}
						if(cb!=null){
							btnSledece.setEnabled(false);
							cb.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									if(cb.isSelected())
										btnSledece.setEnabled(true);
									if(!cb.isSelected())
										btnSledece.setEnabled(false);
								
									
								}
							});
							if(cb.isSelected()){
								cb.setEnabled(false);
								btnSledece.setEnabled(true);
							}
						}
						
					btnNazad.setEnabled(true);
					if(brojac==JPanlista.size()-1)
						btnSledece.setEnabled(false);
				
				}
				else
					btnSledece.setEnabled(false);
			}
		});
		panel.add(btnSledece);
		
		
			
	
	}
	
	public void upis(Verzija v, int duzina_liste, ArrayList<Parametar> l, JPanel pan){
		for(int i=0; i<duzina_liste; i++){
			TipParametra tip=l.get(i).getTip();
			
			switch(tip){
			
			case SLIKA:
				SlikaPanel slikaPan=new SlikaPanel(l.get(i).getNaziv(), l.get(i).getVrednost());
				slikaPan.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5,(int)getScreenSize().getHeight()/3));
				pan.add(slikaPan);
				break;
				
			case TEKST:
				TxtLinesPanel txtLinesPan=new TxtLinesPanel(l.get(i).getNaziv(), l.get(i).getVrednost());

				txtLinesPan.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5,(int)getScreenSize().getHeight()/2));
				pan.add(txtLinesPan);
				break;
				
			case LINIJA_TEKSTA:
				TxtLinePanel txtLinePan=new TxtLinePanel(l.get(i).getNaziv(), l.get(i).getVrednost());

				txtLinePan.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/6));
				pan.add(txtLinePan);
				break;
				
			case TEKSTUALNI_FAJL:
				TxtPanel txtPan=new TxtPanel(l.get(i).getNaziv(), l.get(i).getVrednost(), l.get(i).isCheck()); // OVO IZMENITI DA NIJE TRUE NEGO DA SE DOBIJE VREDNOST

				txtPan.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/2));
				pan.add(txtPan);
				break;
			
			case DEFAULT_LOK:
				DefLokPanel deflokpan=new DefLokPanel(l.get(i).getVrednost(), l.get(i).isCheck(),jezik.getProperty("InstalacionaLokacija2") ,jezik.getProperty("Odabir"),v); // OVO IZMENITI DA NIJE TRUE NEGO DA SE DOBIJE VREDNOST
				deflokpan.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/6));
				pan.add(deflokpan);
				break;

			case EXE:
				ExePanel ex= new ExePanel(l.get(i).getNaziv(), l.get(i).getVrednost(), l.get(i).isCheck(), null, jezik);
				ex.setInst(false);
				ex.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/4));
				pan.add(ex);
				break;
		
			 case INSTALL:
				
				 InstalPanel insPan=new InstalPanel(v,jezik.getProperty("Instaliraj"), null, jezik,"");
				 insPan.setIns(false);
				 insPan.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/6));
					pan.add(insPan);
					break;
					
			 case CHECKBOX:
				 CheckBoxPanel cbPan=new CheckBoxPanel(l.get(i).getNaziv(), l.get(i).getVrednost());
				 cbPan.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/8));
					pan.add(cbPan);
					break;
			 case LIST:
					ListPanel lp= new ListPanel(l.get(i).getNaziv(), l.get(i).getVrednost());
					lp.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/6));
					pan.add(lp);
					break;
					
			 case LOKALIZACIJA:
				 break;
				
		 default:
				JOptionPane.showMessageDialog(null,"Greska!");
				break;
				
			}
		}
	
		
	}

}
