package dev_mode_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.xml.ws.Endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.StartProgram;
import jdialog.MeniExit;
import jdialog.NewVerzija;
import jdialog.SaveVerzija;
import model.Instaler;
import model.Panel;
import model.Parametar;
import model.Verzija;
import warnings.NeselVerzija;
import warnings.Neuspesno;

@SuppressWarnings("serial")
public class DevMeni extends JMenuBar {
	protected Instaler rootNode;
	protected DefaultTreeModel treeModel;
	private PanelTree tree;
	private NewVerzija dialNew;
	private SaveVerzija dialSave;
	
	private Properties mjezik;
		
	public void setLang(Properties jezik){
		mjezik = jezik;
	}
	
	public DevMeni(Properties jezik, PanelTree t){
		mjezik = jezik;
		tree=t;
		
		JMenu meni_File = new JMenu(mjezik.getProperty("Fajl"));
		meni_File.setMnemonic('f');
		this.add(meni_File);
		
		JMenuItem file_New = new JMenuItem(mjezik.getProperty("Novo"));
		file_New.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialNew = new NewVerzija(mjezik);
				dialNew.setVisible(true);
				String nova = (String) dialNew.getVrednost();
				if(nova!=null)
				tree.addVerzija(nova);
				return;
			}
		});	
		file_New.setMnemonic('n');
		meni_File.add(file_New);
		
		JMenuItem file_Open = new JMenuItem(mjezik.getProperty("Otvori"));
		file_Open.setMnemonic('o');
		file_Open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String parVal;
				Verzija ver;
				ObjectMapper mapper = new ObjectMapper();
				
				JFileChooser txtChooser = new JFileChooser();
				FileFilter txtfilter =new FileNameExtensionFilter(".json file", "json");
				
				txtChooser.setAcceptAllFileFilterUsed(false);
				txtChooser.setFileFilter(txtfilter);
				int response=txtChooser.showOpenDialog(null);
				if( response ==JFileChooser.APPROVE_OPTION){
					File f=txtChooser.getSelectedFile();
					parVal=f.getAbsolutePath();
				}
				else {
					return;
				}
				
				try {
					ver = mapper.readValue(new File(parVal), Verzija.class);
					tree.addFilledVerzija(ver);
					
					
			
				} catch (IOException ex) {
					Neuspesno poruka=new Neuspesno(jezik);
					poruka.showMess(jezik);
					return;
				}
				
			}
		});
		meni_File.add(file_Open);
		
		JMenuItem file_Save = new JMenuItem(mjezik.getProperty("Sacuvaj"));
		file_Save.setMnemonic('s');
		file_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> tipovi=new ArrayList<>();
				rootNode = (Instaler) tree.getTreeRoot();
				if(rootNode.getChildCount()!=0)
					for (int i=0; i<rootNode.getChildCount(); i++){
						tipovi.add(rootNode.getChildAt(i).toString());
					}
				else{
					NeselVerzija poruka=new NeselVerzija(mjezik);
					poruka.showMess(mjezik);
					
					return;
				}
				dialSave = new SaveVerzija(tipovi, mjezik, false);
				dialSave.setVisible(true);
				if(dialSave.getUspesno()){
					String naziv = (String) dialSave.getNaziv();
					String lokacija = (String) dialSave.getLokacija();
					tree.saveAs(naziv, lokacija);
				}
				return;
			}
		});
		meni_File.add(file_Save);
		
		JMenuItem file_Import = new JMenuItem(mjezik.getProperty("Import"));
		file_Import.setMnemonic('i');
		file_Import.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ObjectMapper mapper = new ObjectMapper();
				
				JFileChooser Chooser = new JFileChooser();
				Chooser.setMultiSelectionEnabled(true);
				FileFilter filter =new FileNameExtensionFilter(".json file", "json");				
				Chooser.setAcceptAllFileFilterUsed(false);
				Chooser.setFileFilter(filter);
				int response=Chooser.showOpenDialog(null);
				
				File[] files;
				ArrayList<String> parValues=new ArrayList<String>();
				if( response ==JFileChooser.APPROVE_OPTION){
					files = Chooser.getSelectedFiles();
					for(int i=0; i<files.length; i++){
						parValues.add(files[i].getAbsolutePath());					
					}
				}
				else {
					return;
				}
				
				try {
					ArrayList<Verzija> versions=new ArrayList<Verzija>();
					for(int i=0; i<files.length; i++){
						
					versions.add(mapper.readValue(new File(parValues.get(i)), Verzija.class));
					tree.addFilledVerzija(versions.get(i));
					
					}
			
				} catch (IOException ex) {
					Neuspesno poruka=new Neuspesno(jezik);
					poruka.showMess(jezik);
					return;
				}
				
			}
		});
		meni_File.add(file_Import);
		
		JMenuItem file_Exit = new JMenuItem(mjezik.getProperty("Izadji"));
		file_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MeniExit mExit = new MeniExit(jezik);
				mExit.setVisible(true);
				boolean b=mExit.isSacuvaj();
				if(!b)
					if(mExit.getUspesno())
						System.exit(0);
					else
						return;
				ArrayList<String> tipovi = new ArrayList<>();
				Instaler inst = tree.getTreeRoot();
				for(int i=0; i<inst.getChildCount(); i++){
					tipovi.add(inst.getChildAt(i).toString());
				}
				SaveVerzija sv=new SaveVerzija(tipovi, jezik, true);
				sv.setVisible(true);
				boolean s = sv.isUspesno();
				if(s){
					if(sv.getUspesno()){
					String naziv = (String) sv.getNaziv();
					String lokacija = (String) sv.getLokacija();
					tree.saveAs(naziv, lokacija);
				
					System.exit(0);
					}
				}
				return;
				
			}
		});
		meni_File.add(file_Exit);
		
		JMenu meni_Edit = new JMenu(mjezik.getProperty("Izmeni"));
		meni_Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		meni_Edit.setMnemonic('e');
		this.add(meni_Edit);
		
		JMenuItem edit_Edit = new JMenuItem(mjezik.getProperty("Uredi"));
		edit_Edit.setMnemonic('t');
		edit_Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tree.editNode();
			}
		});
		meni_Edit.add(edit_Edit);
		
		JMenuItem edit_Cut = new JMenuItem(mjezik.getProperty("Iseci"));
		edit_Cut.setMnemonic('x');
		edit_Cut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tree.cutNode();
			}
		});
		meni_Edit.add(edit_Cut);
		
		JMenuItem edit_Copy = new JMenuItem(mjezik.getProperty("Kopiraj"));
		edit_Copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tree.copyNode();
			}
		});
		edit_Copy.setMnemonic('c');
		meni_Edit.add(edit_Copy);
		
		JMenuItem edit_Paste = new JMenuItem(mjezik.getProperty("Nalepi"));
		edit_Paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tree.pasteNode();
			}
		});
		edit_Paste.setMnemonic('v');
		meni_Edit.add(edit_Paste);
		
		JMenuItem meni_Move = new JMenu(mjezik.getProperty("Pomeri"));
		meni_Move.setMnemonic('m');
		meni_Move.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		meni_Edit.add(meni_Move);
	
		JMenuItem meni_Up = new JMenuItem(mjezik.getProperty("Navise"));
		meni_Up.setMnemonic('u');
		meni_Up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object selected = (Object) tree.getLastSelectedPathComponent();
				if(selected==null){
					JOptionPane.showMessageDialog(null, mjezik.getProperty("NemaSelektovaneStavke"));
					return;
				}
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				Instaler root = (Instaler) model.getRoot();
				if(selected instanceof Verzija){
					for(int ind=0;ind<root.getChildCount(); ind++){
						if(root.getChildAt(ind)==selected){
							if(ind==0){
								return;
							}
							int i=ind;
							for(int j=root.getChildCount()-1; j>=0; j--){
								if(i-1==j){
									Verzija moveD = (Verzija) root.getChildAt(j);
									Verzija moveU = (Verzija) selected;
									ArrayList<Verzija> vList = root.getVerzije();
									vList.set(i, moveD);
									vList.set(j, moveU);
									String s= tree.getExpansionState();
									model.reload();
									tree.setExpansionState(s);
									return;
								}
							}
						}
					}
				}
				if(selected instanceof Panel){
					TreePath path = tree.getSelectionPath();
					Verzija selParent = (Verzija) path.getPathComponent(1);
					for(int ind=0;ind<selParent.getChildCount(); ind++){
						if(selParent.getChildAt(ind)==selected){
							if(ind==0){
								return;
							}
							int i=ind;
							for(int j=selParent.getChildCount()-1; j>=0; j--){
								if(i-1==j){
									Panel moveD = (Panel) selParent.getChildAt(j);
									Panel moveU = (Panel) selected;
									ArrayList<Panel> pList = selParent.getPaneli();
									pList.set(i, moveD);
									pList.set(j, moveU);
									String s= tree.getExpansionState();
									model.reload();
									tree.setExpansionState(s);
									return;
								}
							}
						}
					}
				}
				if(selected instanceof Parametar){
					TreePath path = tree.getSelectionPath();
					Panel selParent = (Panel) path.getPathComponent(2);
					for(int ind=0;ind<selParent.getChildCount(); ind++){
						if(selParent.getChildAt(ind)==selected){
							if(ind==0){
								return;
							}
							int i=ind;
							for(int j=selParent.getChildCount()-1; j>=0; j--){
								if(i-1==j){
									Parametar moveD = (Parametar) selParent.getChildAt(j);
									Parametar moveU = (Parametar) selected;
									ArrayList<Parametar> pList = selParent.getParametri();
									pList.set(i, moveD);
									pList.set(j, moveU);
									String s= tree.getExpansionState();
									model.reload();
									tree.setExpansionState(s);
									return;
								}
							}
						}
					}
				}
				JOptionPane.showMessageDialog(null, mjezik.getProperty("NemoguceIzvrsitiNaredbu"));
			}
		});
		meni_Up.setMnemonic('u');
		meni_Move.add(meni_Up);
				
		JMenuItem meni_Down = new JMenuItem(mjezik.getProperty("Nanize"));
		meni_Up.setMnemonic('d');
		meni_Down.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Object selected = (Object) tree.getLastSelectedPathComponent();
			if(selected==null){
				JOptionPane.showMessageDialog(null, mjezik.getProperty("NemaSelektovaneStavke"));
				return;
			}
			DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
			Instaler root = (Instaler) model.getRoot();
			if(selected instanceof Verzija){
				for(int ind=0;ind<root.getChildCount(); ind++){
					if(root.getChildAt(ind)==selected){
						if(ind==root.getChildCount()-1){
							return;
						}
						int i=ind;
						for(int j=root.getChildCount()-1; j>=0; j--){
							if(i+1==j){
								Verzija moveD = (Verzija) root.getChildAt(j);
								Verzija moveU = (Verzija) selected;
								ArrayList<Verzija> vList = root.getVerzije();
								vList.set(i, moveD);
								vList.set(j, moveU);
								String s= tree.getExpansionState();
								model.reload();
								tree.setExpansionState(s);
								return;
							}
						}
					}
				}
			}
		if(selected instanceof Panel){
			TreePath path = tree.getSelectionPath();
			Verzija selParent = (Verzija) path.getPathComponent(1);
				for(int ind=0;ind<selParent.getChildCount(); ind++){
					if(selParent.getChildAt(ind)==selected){
						if(ind==selParent.getChildCount()-1){
							return;
						}
						int i=ind;
						for(int j=selParent.getChildCount()-1; j>=0; j--){
							if(i+1==j){
								Panel moveD = (Panel) selParent.getChildAt(j);
								Panel moveU = (Panel) selected;
								ArrayList<Panel> pList = selParent.getPaneli();
								pList.set(i, moveD);
								pList.set(j, moveU);
								String s= tree.getExpansionState();
								model.reload();
								tree.setExpansionState(s);
								return;
							}
						}
					}
				}
			}
			if(selected instanceof Parametar){
				TreePath path = tree.getSelectionPath();
				Panel selParent = (Panel) path.getPathComponent(2);
				for(int ind=0;ind<selParent.getChildCount(); ind++){
					if(selParent.getChildAt(ind)==selected){
						if(ind==selParent.getChildCount()-1){
							return;
						}
						int i=ind;
						for(int j=selParent.getChildCount()-1; j>=0; j--){
							if(i+1==j){
								Parametar moveD = (Parametar) selParent.getChildAt(j);
								Parametar moveU = (Parametar) selected;
								ArrayList<Parametar> pList = selParent.getParametri();
								pList.set(i, moveD);
								pList.set(j, moveU);
								String s= tree.getExpansionState();
								model.reload();
								tree.setExpansionState(s);
								return;
							}
						}
					}
				}
			}
			JOptionPane.showMessageDialog(null, mjezik.getProperty("NemoguceIzvrsitiNaredbu"));
		}
		});
		meni_Move.add(meni_Down);
		
		JMenu meni_View = new JMenu(mjezik.getProperty("View"));
		meni_View.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		meni_View.setMnemonic('w');
		this.add(meni_View);

		JMenuItem view_Properties = new JMenuItem(mjezik.getProperty("Osobine"));
		view_Properties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tree.showProperties();
			}
		});
		view_Properties.setMnemonic('p');
		meni_View.add(view_Properties);
		
		JMenu meni_Help = new JMenu(mjezik.getProperty("Pomoc"));
		meni_Help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		meni_Help.setMnemonic('h');
		this.add(meni_Help);
		
		JMenuItem view_Help = new JMenuItem(mjezik.getProperty("Pomoc"));
		view_Help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartProgram sp=new StartProgram();
				String odabranJezik=sp.getOdabranJezik();
				if(odabranJezik=="English"){
				try {
						Runtime.getRuntime().exec("hh.exe "+System.getProperty("user.dir")+"/src/help/engHelp/engHelp.chm");
					} catch (IOException e1) {
						Neuspesno poruka=new Neuspesno(jezik);
						poruka.showMess(jezik);
						return;
					}
				}
				if(odabranJezik=="Srpski"){
					try {
							Runtime.getRuntime().exec("hh.exe "+System.getProperty("user.dir")+"/src/help/help/Help.chm");
						} catch (IOException e1) {
							Neuspesno poruka=new Neuspesno(jezik);
							poruka.showMess(jezik);
							return;
						}
					}
				
			}
		});
		view_Help.setMnemonic('l');
		meni_Help.add(view_Help);
		
		
	}
	
	
}
