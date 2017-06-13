package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.tree.TreePath;

import dev_mode_gui.PanelTree;
import jdialog.CheckBox;
import jdialog.DefaultLokacija;
import jdialog.ExePar;
import jdialog.ListPar;
import jdialog.LogoKompanije;
import jdialog.LokacijaSoft;
import jdialog.LokalizacijaDial;
import jdialog.Slika;
import jdialog.Tekst;
import jdialog.TekstFajl;
import jdialog.TekstLinija;
import model.Parametar;
import model.TipParametra;
import model.Verzija;
import warnings.Neuspesno;

public class DialCall {
	
	private TekstFajl dial;
	private PanelTree tree;
	private LogoKompanije dialLogo;
	private Tekst dialTxt;
	private TekstLinija dialTxtLinija;
	private Slika dialSlika;
	private LokacijaSoft dialLokacijaSoft;
	private DefaultLokacija dialDefaultLokacija;
	private ExePar dialExe;
	private CheckBox dialcb;
	private LokalizacijaDial dialLok;
	private ListPar dialList;
	private Properties jezik;
	

 public String  sacuvaj(String s){
	 	String copyLoc=s;
		String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/");
		
		
		int last= copyLoc.lastIndexOf("\\");
		String end= copyLoc.substring(last+1);
		String lokacija= myLoc+end;
		String command="cmd.exe /c copy "+"\""+copyLoc+"\""+" "+"\""+ lokacija +"\"";
		
		Runtime rt = Runtime.getRuntime();
		
		try {
			
		
			@SuppressWarnings("unused")
			Process pr = rt.exec(command);
	
			
		} catch (IOException e) {
			Neuspesno poruka=new Neuspesno(jezik);
			poruka.showMess(jezik);
			
		
		}
		return lokacija;
	 
	 
 }
 
 
 public void lokalizuj(String s, String s2){
	 
	 String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/");
	 String[] odvojeno= s.split("NEGLEDAJMIUKOD");// objasnjenje u LokalizacijaDial klasi
	 String osnovni=odvojeno[0];
	 String novi=odvojeno[1];
	 String u= myLoc+"VIRUSEXE"+osnovni+".properties"; // Da ne bi ljudi dirali
	 String u1= myLoc+"VIRUSEXE"+novi+".properties"; 
	 File file = new File(u);
	 File f1= new File(u1);
	 file.delete();
	 f1.delete();
	 try {
		
		file.createNewFile();
		f1.createNewFile();
	} catch (IOException e) {
		Neuspesno poruka=new Neuspesno(jezik);
		poruka.showMess(jezik);
		return;
		}
		
		Properties properties0 = new Properties();
		Properties properties1 = new Properties();
		String tempAll=s2;
		
		int strDo=0;
		int key=0;
		int t=0;
		String prviDeo="";
		String drugiDeo="";
		while((strDo=tempAll.indexOf("="))!=-1){
			
			if(t==0){
				prviDeo= tempAll.substring(0, strDo-1);
				prviDeo = prviDeo.trim();
				properties0.setProperty(Integer.toString(key), prviDeo);
				t++;
				tempAll=tempAll.substring(strDo+1);
				continue;
			}

			prviDeo= tempAll.substring(0, strDo-1);// ovde treba odvojiti po prvom new line-u
			prviDeo=prviDeo.trim();
			int pomoc= prviDeo.lastIndexOf('\n');
			drugiDeo=prviDeo.substring(pomoc);
			int pomoc2= prviDeo.indexOf('\n');
			prviDeo= prviDeo.substring(0, pomoc2);
			prviDeo= prviDeo.trim();
			drugiDeo=drugiDeo.trim();
				properties1.setProperty(Integer.toString(key), prviDeo);
				key++;
				properties0.setProperty(Integer.toString(key), drugiDeo);
			tempAll=tempAll.substring(strDo+1);
		}
		properties1.setProperty(Integer.toString(key), tempAll); // poslednji kljuc jer onda iskoci iz while-a
		
		FileOutputStream fileOut = null;
		FileOutputStream fileOut2 = null;
	
		try {
			fileOut = new FileOutputStream(file);
			fileOut2 = new FileOutputStream(f1);
		} catch (FileNotFoundException e1) {
			Neuspesno poruka=new Neuspesno(jezik);
			poruka.showMess(jezik);
			return;
		}
		try {
			properties0.store(fileOut,"Mora_da_ti_je_jako_dosadno"); // Verovatno ovo treba promeniti 
			properties1.store(fileOut2,"Ili_da_mi_jako_ne_verujes"); // Neprofesonalno ali svaki projekat ima neki Easter Egg
		} catch (IOException e1) {
			Neuspesno poruka=new Neuspesno(jezik);
			poruka.showMess(jezik);
			return;
		}
		
		
		

	 
	 
	
	 
 }

	
	public DialCall(Properties j){
		jezik = new Properties();
		jezik = j;
	}
	
	
	public void chooseDial(String s, PanelTree t){
		tree=t;
		
		String []srp={"Tekst","Tekst linija","Slika","Lokacija softvera", "Tekstualni fajl", "Logo kompanije","Default instalaciona lokacija", "Exe", "Instaliraj", "Checkbox", "Lokalizacija", "Lista"}; 

		String []eng={"Text","One line text","Picture","Software location","Text file","Logo","Default install location", "Exe", "Install", "Checkbox", "Localisation", "List"}; 

		boolean promenjen=false;
		int tacka=-1;
		for(int i=0; i<eng.length;i++){
			if(s.equals(eng[i])){
				promenjen =true;
				tacka=i;
			}	
		}
		if(promenjen){
			s=srp[tacka]; //Da ne bih morao pisati ceo case
		}
		
		
		switch (s) {
        case "Tekstualni fajl": 
        		dial=new TekstFajl(jezik);
				dial.setVisible(true);
				String ntf = dial.getNaziv();
        		String vtf = dial.getVrednost();
        		boolean b1= dial.getCheck();
        		if(dial.getPrazno())
    				break;
        		String novaVrednost=sacuvaj(vtf);
        		Parametar ptf = new Parametar(ntf, novaVrednost, TipParametra.TEKSTUALNI_FAJL);
        		ptf.setCheck(b1);
        		tree.addParametarToTree(ptf);
                break;
        case "Logo kompanije":
    		dialLogo = new LogoKompanije(jezik);
    		dialLogo.setVisible(true);
    		String vlk = dialLogo.getVrednost();
			if(dialLogo.getPrazno())
				break;
			String novaVrednost2=sacuvaj(vlk);
    		tree.addToVerzija(novaVrednost2, 1,false);
    		break;
        case "Tekst":
    		dialTxt = new Tekst(jezik);
    		dialTxt.setVisible(true);
    		String nt = dialTxt.getNaziv();
    		String vt = dialTxt.getVrednost();
    		if(dialTxt.getPrazno())
				break;
    		Parametar pt = new Parametar(nt, vt, TipParametra.TEKST);
    		tree.addParametarToTree(pt);
    		break;
        case "Tekst linija":
    		dialTxtLinija = new TekstLinija(jezik);
    		dialTxtLinija.setVisible(true);
    		String ntl = dialTxtLinija.getNaziv();
    		String vtl = dialTxtLinija.getVrednost();
    		if(dialTxtLinija.getPrazno())
				break;
    		Parametar ptl = new Parametar(ntl, vtl, TipParametra.LINIJA_TEKSTA);
    		tree.addParametarToTree(ptl);
    		break;
        case "Slika":
    		dialSlika = new Slika(jezik);
    		dialSlika.setVisible(true);
    		String ns = dialSlika.getNaziv();
    		String vs = dialSlika.getVrednost();
    		if(dialSlika.getPrazno())
				break;
    		String novaVrednost3=sacuvaj(vs);
    		Parametar ps = new Parametar(ns, novaVrednost3, TipParametra.SLIKA);
    		tree.addParametarToTree(ps);
    		break;
        case "Lokacija softvera":
    		dialLokacijaSoft = new LokacijaSoft(jezik);
    		dialLokacijaSoft.setVisible(true);
    		String vls = dialLokacijaSoft.getVrednost();
    		if(dialLokacijaSoft.getPrazno())
				break;
    		String novaVrednost4=sacuvaj(vls);
    		tree.addToVerzija(novaVrednost4, 3,false);
    		break;
       
        case "Lokalizacija":
        	dialLok = new LokalizacijaDial(tree, jezik);
        	dialLok.setVisible(true);
        	if(dialLok.getPrazno())
				break;
        	
    		String nlok = dialLok.getNaziv();
    		String vlok = dialLok.getVrednost();
    		lokalizuj(nlok,vlok);
    		
    		Parametar pl = new Parametar(nlok, vlok, TipParametra.LOKALIZACIJA);
    		tree.addParametarToTree(pl);
    		break;
    		
    		
        case "Exe":
    		dialExe = new ExePar(jezik);
    		dialExe.setVisible(true);
    		String ne = dialExe.getNaziv();
    		String ve = dialExe.getVrednost();
    		boolean be=dialExe.getCheck();
    		if(dialExe.getPrazno())
				break;
    		Parametar pe = new Parametar(ne, ve, TipParametra.EXE); //TREBA BOOL
    		pe.setCheck(be);
    		tree.addParametarToTree(pe);
    		break;
    		
        case "Instaliraj":
    		   TreePath selectedPath = tree.getSelectionPath();
    		   selectedPath.getPathComponent(1);
    		   Object selectedNode = selectedPath.getPathComponent(1); 
				Verzija vers = (Verzija) selectedNode; 
				String lokI= (String) vers.getLokInstalacije();
				String lokS= (String) vers.getLokSoftvera();
				if (lokI.trim().equals("") || lokS.trim().equals("")){
					JOptionPane.showMessageDialog(null,jezik.getProperty("PotrebnoJeJosParametaraZaInstalaciju"));	
					break;
				}
    		Parametar pi = new Parametar(lokS, lokI, TipParametra.INSTALL);
    		tree.addParametarToTree(pi);
    		break;
    		
    		
        case "Default instalaciona lokacija":
    		dialDefaultLokacija = new DefaultLokacija(jezik);
    		dialDefaultLokacija.setVisible(true);
    		String vdil = dialDefaultLokacija.getVrednost();
    		String ndil = dialDefaultLokacija.getNaziv();
    		if(dialDefaultLokacija.getPrazno())
				break;
    		boolean b= dialDefaultLokacija.getCheck();
    		tree.addToVerzija(vdil, 2, b);
    		Parametar dil = new Parametar(ndil, vdil, TipParametra.DEFAULT_LOK);
    		dil.setCheck(b);
    		tree.addParametarToTree(dil);
    		break;
    		
        case "Checkbox":
    		dialcb = new CheckBox(jezik);
    		dialcb.setVisible(true);
    		String vcb = dialcb.getVrednost();
    		String ncb = dialcb.getNaziv();
    		if(dialcb.getPrazno())
				break;
    		Parametar cb = new Parametar(ncb, vcb, TipParametra.CHECKBOX);
    		tree.addParametarToTree(cb);
    		break;
    		
        case "Lista":
        	dialList = new ListPar(jezik);
        	dialList.setVisible(true);
    		String vl = dialList.getVrednost();
    		String nl = dialList.getNaziv();
    		if(dialList.getPrazno())
				break;
    		Parametar li = new Parametar(nl, vl, TipParametra.LIST);
    		tree.addParametarToTree(li);
    		break;

    }
		
		
		
	}
	
}
