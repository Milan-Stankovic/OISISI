package user_mode_gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

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
import warnings.Neuspesno;
import mslinks.ShellLink;

@SuppressWarnings("serial")
public class InstallFrame extends JFrame{

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
	private JButton btnZavrsi;
	private FileInputStream fs;
	private boolean bLok=false;
	private String [] imenaLok;
	private String [] vrednostiLok;
	private JFrame frame;
	private boolean uspelo=false;
	private int duzina;
	private int kljuc_lokal;
	private String odabrano;
	private String dugme1="Sledece";
	private String dugme2="Nazad";
	private String dugme3="Zavrsi";
	private boolean shortcut;
	public boolean b;
	private Properties jezik;
	private JCheckBox cb2;
	
	public void KreirajShortcut(boolean b, Verzija v){
		if(b){
		int ext= v.getLokSoftvera().lastIndexOf(".");
		int nazivIext=v.getLokSoftvera().lastIndexOf("/");
		String naziv=v.getLokSoftvera().substring(nazivIext);

		String path=v.getLokInstalacije()+naziv;

		
		String ime="";
		String shortcut="";
		String lok="";
		
			ime= v.getLokSoftvera().substring(nazivIext,ext);
		shortcut=System.getProperty("user.home") + "/Desktop/";
		lok= shortcut + ime +".lnk";
		try {
			//UKOLIKO SE JAVE GRESKE OVDE, POTREBNO JE OTVORITI LIBRARY I BUILDOVATI PATH ZA MSLINKS.JAR 
			//UKOLIKO SE MSLINK.JAR NE NALAZI U REFERENCED LIBRARIES DESAVA SE OVA GRESKA
			//MI SMO BUILDOVALI PATH U PROJEKTU ZA OVAJ JAR ALI IAKO SMO TAKAV PROJEKAT UPLOADOVALI NA SVN DESAVALO SE DA SE OPET JAVE GRESKE
			ShellLink.createLink(path, lok);
			
		} catch (IOException e1) {
			Neuspesno poruka=new Neuspesno(jezik);
			poruka.showMess(jezik);
			return;
			
		}
		}
		
		
	}
	
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
		btnSledece.repaint();
		btnSledece.revalidate();
	}
	
	public void setZavrsi(boolean b){
		btnZavrsi.setEnabled(b);
		btnZavrsi.repaint();
		btnZavrsi.revalidate();
	}
	
	
	
	public void duzina(){
		int k=0;
		for(int i=0;i<panList.size(); i++){
			Panel p= panList.get(i);
			ArrayList<Parametar> sviParam=	p.getParametri();
			for(int j=0;j<sviParam.size(); j++){
				Parametar param= sviParam.get(j);
				if(param.getTip() == TipParametra.LOKALIZACIJA ){
					bLok=true;
					k++;
					duzina=k;
					}
			}
			
		}
	
		
	}
	
	
	
	public void imaLokal(){
		int k=0;
		for(int i=0;i<panList.size(); i++){
			Panel p= panList.get(i);
			ArrayList<Parametar> sviParam=	p.getParametri();
			for(int j=0;j<sviParam.size(); j++){
				Parametar param= sviParam.get(j);
				if(param.getTip() == TipParametra.LOKALIZACIJA ){
					bLok=true;
					String temp=param.getNaziv();
					imenaLok[k]=temp;
					vrednostiLok[k]=param.getVrednost();
					k++;
					}
			}
			
		}
	
		
	}
	
	
	public InstallFrame(Verzija v, Properties j) {
		jezik=j;
		
	/*	Properties ujezik=new Properties();
		
		try {
			fs = new FileInputStream(System.getProperty("user.dir")+"/src/dev_mode_gui/srp.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ujezik.load(fs);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		duzina=0;
		kljuc_lokal=0;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(v.getNaziv());
		screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.7; //Procenat ekrana
		height=height*0.6; 
		

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
		duzina();
		
		imenaLok=new String[duzina];
		vrednostiLok=new String[duzina];
		imaLokal();
		
		if(bLok){
			
			String [] desifrovano= desifruj();
			odabrano = odabirJezika(desifrovano);
			
			
			String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/");
			String lokProp= myLoc+"VIRUSEXE"+odabrano+".properties"; // videti dial call zasto virusexe

			Properties prop=ucitajProp(lokProp);
				
			int duz=prop.size();
			dugme3=prop.getProperty(Integer.toString(duz-1));
			dugme2=prop.getProperty(Integer.toString(duz-2));
			dugme1=prop.getProperty(Integer.toString(duz-3));
		}else{
			
			
			dugme3=jezik.getProperty("Zavrsi");
			dugme2=jezik.getProperty("Nazad");
			dugme1=jezik.getProperty("Sledece");
			
		}
		
	
		
		JPanlista=new ArrayList<JPanel>();
		for(int i=0;i<panList.size();i++){
			JPanel pomocni=new JPanel();
			pomocni.setLayout(new BoxLayout(pomocni, BoxLayout.Y_AXIS));
			JPanlista.add(i, pomocni);
			ver=v;
			if(bLok){
				upisSaLok(ver, panList.get(i).getParametri().size(),panList.get(i).getParametri(), JPanlista.get(i));
			}else{
				upis(ver, panList.get(i).getParametri().size(),panList.get(i).getParametri(), JPanlista.get(i));
				
			}
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
		
		
		
		
		
		
		
		btnZavrsi=new JButton(dugme3); 
		btnZavrsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Component[] components = JPanlista.get(brojac).getComponents(); 
				Component component = null; 
				
				  for (int i = 0; i < components.length; i++) 
					{ 
					component = components[i]; 
									
					if (component instanceof InstalPanel) 
						{ 
						InstalPanel ip=(InstalPanel) component;
						
						cb2=ip.getCB();
					
						break;
								
						}	
					
					}
				
				if(cb2!=null){
					
					if(cb2.isSelected()){
						shortcut=true;
						KreirajShortcut(true, ver);
					}
					
			
			  	}
			if(shortcut)
				KreirajShortcut(true,ver);
			
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
					btnZavrsi.setEnabled(false);
					cb.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(cb.isSelected())
								btnZavrsi.setEnabled(true);
							if(!cb.isSelected())
								btnZavrsi.setEnabled(false);
						}
					});
					if(cb.isSelected()){
						cb.setEnabled(false);
						btnZavrsi.setEnabled(true);
					}
				
				dispose();
			}
				dispose();
			}
			
			});
		
		
		btnSledece = new JButton(dugme1); 

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
		
		
		
		btnNazad = new JButton(dugme2);
		if(brojac==0 && JPanlista.size()==1){
			btnNazad.setEnabled(false);
			panel.remove(btnSledece);
			panel.add(btnZavrsi);
			panel.repaint();
			panel.revalidate();
		}
		else if(brojac==0)
			btnNazad.setEnabled(false);
		
		btnNazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(brojac!=0){	
					brojac--;
					if(JPanlista.size()!=1){
						panel.remove(btnZavrsi);
						panel.add(btnSledece);
						panel.repaint();
						panel.revalidate();
					}
					if(brojac!=JPanlista.size()-1){
						panel.remove(btnZavrsi);
						panel.add(btnNazad);
						btnNazad.setEnabled(true);
						panel.add(btnSledece);
						btnSledece.setEnabled(true);
						panel.repaint();
						panel.revalidate();
						
					}
					scrollPane.setViewportView(JPanlista.get(brojac));
									
					if(brojac==0){
						btnNazad.setEnabled(false);
						btnSledece.setEnabled(true);
					}
					
					
			}	
					
				else{
					btnNazad.setEnabled(false);
					if(JPanlista.size()!=1){
					panel.remove(btnZavrsi);
					panel.add(btnSledece);
					panel.repaint();
					panel.revalidate();
					}else{
						panel.remove(btnSledece);
						panel.add(btnZavrsi);
						panel.repaint();
						panel.revalidate();
					}
				}
					
			}
		});
		panel.add(btnNazad);
	
		
		if(brojac==JPanlista.size()-1){
			panel.remove(btnSledece);
			panel.add(btnZavrsi);
			panel.revalidate();
			panel.repaint();
			
		}
		btnSledece.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cb!=null){
				if(cb.isSelected()){
					cb.setEnabled(false);
					btnSledece.setEnabled(true);
				}
		
				}
				
				if(brojac!=JPanlista.size()-1){
					panel.add(btnSledece);
					brojac++;
					cb=null;
					scrollPane.setViewportView(JPanlista.get(brojac));
					
					
					Component[] components = JPanlista.get(brojac).getComponents(); 
					Component component = null; 
					
					  for (int i = 0; i < components.length; i++) 
						{ 
						component = components[i]; 
										
						if (component instanceof InstalPanel) 
							{ 
							InstalPanel ip=(InstalPanel) component;
							cb2=ip.getCB();
							
							break;
									
							}
						}
						
						if(cb2!= null && cb2.isSelected())
							shortcut=true;
						
					  		
					  		
					  		
					  		
					  
					  
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
					if(brojac==JPanlista.size()-1){
						panel.remove(btnSledece);
						panel.add(btnZavrsi);
						panel.revalidate();
						panel.repaint();
					
					}
				
				}
				else{
					panel.remove(btnSledece);
					panel.add(btnZavrsi);
					panel.revalidate();
					panel.repaint();
					
					}
			}
		});
		if(JPanlista.size()!=1)
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
				TxtPanel txtPan=new TxtPanel(l.get(i).getNaziv(), l.get(i).getVrednost(), l.get(i).isCheck()); 

				txtPan.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/2));
				pan.add(txtPan);
				break;
			
			case DEFAULT_LOK:
				DefLokPanel deflokpan=new DefLokPanel(l.get(i).getVrednost(), l.get(i).isCheck(),jezik.getProperty("InstalacionaLokacija2") ,jezik.getProperty("Odabir"),v); 
				deflokpan.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/6));
				pan.add(deflokpan);
				break;

			case EXE:
				ExePanel ex= new ExePanel(l.get(i).getNaziv(), l.get(i).getVrednost(), l.get(i).isCheck(),this, jezik);
				ex.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/6));
				pan.add(ex);
				break;
				
			case LIST:
				ListPanel lp= new ListPanel(l.get(i).getNaziv(), l.get(i).getVrednost());
				lp.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/6));
				pan.add(lp);
				break;
		
			 case INSTALL:
				 InstalPanel insPan=new InstalPanel(v,jezik.getProperty("Instaliraj"), this, jezik, "");
				 insPan.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/6));
					pan.add(insPan);
					break;
					
			 case LOKALIZACIJA:
				 break;
					
			 case CHECKBOX:
				 CheckBoxPanel cbPan=new CheckBoxPanel(l.get(i).getNaziv(), l.get(i).getVrednost());
				 cbPan.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/8));
					pan.add(cbPan);
					break;
				
		 default:
				JOptionPane.showMessageDialog(null,"Greska!");
				break;
				
			}
		}
		
	}	
	public String odabirJezika(String[] svi){
		
		Object[] possibilities= new Object[svi.length];
		for(int i=0; i<svi.length; i++){
			possibilities[i]= svi[i];
		} // dodati jezike 
		Icon icon = null;
		
		JOptionPane jp = new JOptionPane();
		String odabrano= new String();
		odabrano = (String)jp.showInputDialog( frame, null, "Jezik/Language", JOptionPane.PLAIN_MESSAGE, icon, possibilities, null);
		if(odabrano.equals("")){
			System.exit(0);
		}
		
		return odabrano;
	}
	
	public String[] desifruj(){
		String [] desifrovano= new String[imenaLok.length+1]; // cista imena da bih mogao da ponudim
		for(int i=0; i<imenaLok.length; i++){
			 String[] odvojeno= imenaLok[i].split("NEGLEDAJMIUKOD");// objasnjenje u LokalizacijaDial klasi
			 desifrovano[i]=odvojeno[1]; // ima jedan jedini osnovni jezik a ima vise drugih koji se nalaze na drugom mestu
			 }
			String[] odvojeno= imenaLok[imenaLok.length-1].split("NEGLEDAJMIUKOD");
			 desifrovano[imenaLok.length]=odvojeno[0]; // ubacujem i osnovni naziv
			 return desifrovano;
	}		
		
	public Properties ucitajProp(String s){
		Properties prop = new Properties();
		InputStream input = null;

			try {
				input = new FileInputStream( s);
			} catch (FileNotFoundException e) {
				Neuspesno poruka=new Neuspesno(jezik);
				poruka.showMess(jezik);
				
			}

			// load a properties file
			try {
				prop.load(input);
				uspelo=true;
			} catch (IOException e) {
				Neuspesno poruka=new Neuspesno(jezik);
				poruka.showMess(jezik);
				
			}
			return prop;
		
	}
			
	public void upisSaLok(Verzija v, int duzina_liste, ArrayList<Parametar> l, JPanel pan){
		
		// nalazi se cisto ime
		String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/");
		String lokProp= myLoc+"VIRUSEXE"+odabrano+".properties"; // videti dial call zasto virusexe
		
		
		Properties prop=ucitajProp(lokProp);
			
		int duz=prop.size();
		
		for(int i=0; i<duzina_liste; i++){
			TipParametra tip=l.get(i).getTip();
			
			switch(tip){
			
			case SLIKA:
				
				//l.get(i).setNaziv(prop.getProperty(Integer.toString(kljuc_lokal)));
				//kljuc_lokal++;
				SlikaPanel slikaPan=new SlikaPanel(l.get(i).getNaziv(), l.get(i).getVrednost());
				slikaPan.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5,(int)getScreenSize().getHeight()/3));
				pan.add(slikaPan);
				
				break;
				
			case TEKST:
				
				l.get(i).setNaziv(prop.getProperty(Integer.toString(kljuc_lokal)));
				kljuc_lokal++;
				l.get(i).setVrednost(prop.getProperty(Integer.toString(kljuc_lokal)));
				kljuc_lokal++;
				
				TxtLinesPanel txtLinesPan=new TxtLinesPanel(l.get(i).getNaziv(), l.get(i).getVrednost());

				txtLinesPan.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5,(int)getScreenSize().getHeight()/2));
				pan.add(txtLinesPan);
				break;
				
			case LINIJA_TEKSTA:
				
				l.get(i).setNaziv(prop.getProperty(Integer.toString(kljuc_lokal)));
				kljuc_lokal++;
				l.get(i).setVrednost(prop.getProperty(Integer.toString(kljuc_lokal)));
				kljuc_lokal++;
				
				TxtLinePanel txtLinePan=new TxtLinePanel(l.get(i).getNaziv(), l.get(i).getVrednost());

				txtLinePan.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/6));
				pan.add(txtLinePan);
				break;
				
			case TEKSTUALNI_FAJL:
				
				l.get(i).setNaziv(prop.getProperty(Integer.toString(kljuc_lokal)));
				kljuc_lokal++;
				//TU PUCA
				TxtPanel txtPan=new TxtPanel(l.get(i).getNaziv(), l.get(i).getVrednost(), l.get(i).isCheck()); 

				txtPan.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/2));
				pan.add(txtPan);
				break;
			
			case DEFAULT_LOK:
				
				String s= prop.getProperty(Integer.toString(duz-5));
				String s2= prop.getProperty(Integer.toString(duz-4));
				DefLokPanel deflokpan=new DefLokPanel(l.get(i).getVrednost(), l.get(i).isCheck(),s,s2, v); 
				deflokpan.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/6));
				pan.add(deflokpan);
				break;

			case EXE:
				l.get(i).setNaziv(prop.getProperty(Integer.toString(kljuc_lokal)));
				kljuc_lokal++;
				ExePanel ex= new ExePanel(l.get(i).getNaziv(), l.get(i).getVrednost(), l.get(i).isCheck(), this, prop);
				ex.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/4));
				pan.add(ex);
				break;
				
			case LIST:
				
				l.get(i).setNaziv(prop.getProperty(Integer.toString(kljuc_lokal)));
				kljuc_lokal++;
				l.get(i).setVrednost(prop.getProperty(Integer.toString(kljuc_lokal)));
				kljuc_lokal++;
				
				ListPanel lp= new ListPanel(l.get(i).getNaziv(), l.get(i).getVrednost());
				lp.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/6));
				pan.add(lp);
				break;
		
			 case INSTALL:				 
				 String s3= prop.getProperty(Integer.toString(duz-6));
				 String s4=prop.getProperty(Integer.toString(duz-7));
				 InstalPanel insPan=new InstalPanel(v,s3,this, prop, s4);
				 insPan.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/6));
					pan.add(insPan);
					break;
					
			 case LOKALIZACIJA:
				 break;
					
			 case CHECKBOX:
					l.get(i).setNaziv(prop.getProperty(Integer.toString(kljuc_lokal)));
					kljuc_lokal++;
					l.get(i).setVrednost(prop.getProperty(Integer.toString(kljuc_lokal)));
					kljuc_lokal++;
				 
				 CheckBoxPanel cbPan=new CheckBoxPanel(l.get(i).getNaziv(), l.get(i).getVrednost());
				 cbPan.setPreferredSize(new Dimension((int)getScreenSize().getWidth()/5, (int)getScreenSize().getHeight()/8));
					pan.add(cbPan);
					break;
				
		 default:
				JOptionPane.showMessageDialog(null,"Greska!");
				break;
				
			}
		}
		
	}		

}
