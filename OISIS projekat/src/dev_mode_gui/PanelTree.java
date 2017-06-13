package dev_mode_gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.fasterxml.jackson.databind.ObjectMapper;

import jEditDialog.EditChBox;
import jEditDialog.EditDefLok;
import jEditDialog.EditExe;
import jEditDialog.EditInstaler;
import jEditDialog.EditLinija;
import jEditDialog.EditList;
import jEditDialog.EditLokalizacija;
import jEditDialog.EditPanel;
import jEditDialog.EditSlika;
import jEditDialog.EditTFajl;
import jEditDialog.EditTekst;
import jEditDialog.EditVerzija;
import jdialog.ShowProperties;
import model.Instaler;
import model.Panel;
import model.Parametar;
import model.TipParametra;
import model.Verzija;
import warnings.NemaIseci;
import warnings.NemaKopiranje;
import warnings.NemaNalepi;
import warnings.NemaStavke;
import warnings.NemaVerzije;
import warnings.NemoguceIzvrsiti;
import warnings.NeselPanel;
import warnings.NeselVerzija;
import warnings.Neuspesno;
import warnings.NijeKopirano;
@SuppressWarnings("serial")
public class PanelTree extends JTree{
	 protected DefaultMutableTreeNode rootNode;
	  protected DefaultTreeModel treeModel;
	  protected Object copySelected = null;
	  protected Object pasteSelected = null;
	  protected ArrayList<Parametar> copyPanel=null;
	  protected ArrayList<Panel> copyVerzija=null;
	  protected boolean cuted = false;
	  private EditInstaler dialEinst;
	  private EditVerzija dialEverz;
	  private EditPanel dialEpan;
	  private EditTFajl dialEparTF;
	  private EditTekst dialEparT;
	  private EditLinija dialEparLT;
	  private EditDefLok dialEparDL;
	  private EditSlika dialEparS;
	  private EditExe dialExe;
	  private EditChBox dialEparCB;
	  private EditList dialEparL;
	  private EditLokalizacija dialElokal;
	  private ShowProperties dialProp;
	  private Properties tjezik;
	
	public PanelTree(Properties jezik){
		tjezik = new Properties();
		tjezik = jezik;
	}

    public void addToVerzija(String vrednost, int i, boolean b) {
		DefaultTreeModel model=(DefaultTreeModel) this.getModel();
		Object selectedNode = (Object)this.getLastSelectedPathComponent(); 
		Instaler root = (Instaler) model.getRoot();
		if (selectedNode == null){
			Verzija poslV = (Verzija) root.getChildAt(root.getChildCount()-1);
			ubaci(vrednost,i, poslV,  b); 
			
		}
		else if(selectedNode instanceof Verzija){
			Verzija poslV = (Verzija) selectedNode;
			ubaci(vrednost,i, poslV,b);
		}
		else if(selectedNode instanceof Panel){
			Panel selPan = (Panel) selectedNode;
			Verzija poslV = new Verzija();
			for(int ind1=root.getChildCount()-1; ind1>=0; ind1--){
				poslV = (Verzija) root.getChildAt(ind1);
				for(int ind2=poslV.getChildCount()-1; ind2>=0; ind2--){
					if(selPan == poslV.getChildAt(ind2))
						break;
				}
			}
			ubaci(vrednost,i, poslV,b);
			
		}
		else{
			//warning
		}
		
	}

	public void ubaci(String vrednost, int i, Verzija poslV, boolean b) {
		switch (i){
	  	case 1:
	  		poslV.setLokLogo(vrednost);
	  		break;
	  	case 2:
	  		poslV.setLokInstalacije(vrednost);
	  		poslV.setCheck(b);
	  		break;
	  	case 3:
	  		poslV.setLokSoftvera(vrednost);
	  		break;					  						  		
	  }  
	  
	}

	public void addParametarToTree(Parametar p) {
		DefaultTreeModel model=(DefaultTreeModel) this.getModel();
		Object selectedNode = (Object)this.getLastSelectedPathComponent(); 
		if(!(selectedNode instanceof Panel)){ 
			NeselPanel poruka=new NeselPanel(tjezik);
			poruka.showMess(tjezik);
			return;
		}
		else{
			Panel selPan = (Panel) selectedNode;
			  selPan.getParametri().add(p);
			  
			 String s= this.getExpansionState();
				  model.reload();
				  this.setExpansionState(s);
		}
	}
	public void addVerzija(String s){
		DefaultTreeModel model=(DefaultTreeModel) this.getModel();
		
		Instaler root= (Instaler) model.getRoot();
		if( !s.trim().equals("")) {
			ArrayList<Panel> li = new ArrayList<Panel>();
			Verzija verz = new Verzija(s,"", "", "", li, false);
			root.getVerzije().add(verz);
			 String s1= this.getExpansionState();
			  model.reload();
			  this.setExpansionState(s1);
		}
		
	}
	
	public void addFilledVerzija(Verzija v){
		DefaultTreeModel model=(DefaultTreeModel) this.getModel();
		
		Instaler root= (Instaler) model.getRoot();
		if( !v.getNaziv().trim().equals("")) {
			Verzija verz = new Verzija(v.getNaziv(),v.getLokSoftvera(), v.getLokInstalacije(), v.getLokLogo(), v.getPaneli(), v.isCheck());
			root.getVerzije().add(verz);
			 String s1= this.getExpansionState();
			  model.reload();
			  this.setExpansionState(s1);
			
		}
		
	}

	
	public void addPanel(String s){
		
		DefaultTreeModel model=(DefaultTreeModel) this.getModel();
		
		Instaler inst= (Instaler) model.getRoot();
		if(inst.getChildCount()==0){
			  NemaVerzije poruka=new NemaVerzije(tjezik);
			  poruka.showMess(tjezik);
			// 	DODATI WARNING CETKOV
			// 
			return;
		}
		
		  Object selectedNode = (Object)this.getLastSelectedPathComponent(); 
		  if(selectedNode instanceof Verzija){
			  Verzija vers = (Verzija) this.getLastSelectedPathComponent();
			  if( !s.trim().equals("") ) {	
				  ArrayList<Parametar> pa = new ArrayList<Parametar>();
				  Panel panel = new Panel(s, pa);
				  vers.getPaneli().add(panel);
				  String s11= this.getExpansionState();
				  model.reload();
				  this.setExpansionState(s11);
				  return;
			  	}
		  }
		  else{
			 NeselVerzija poruka=new NeselVerzija(tjezik);
			 poruka.showMess(tjezik);
			 // DODATI CETKOV WARNING
			  return;
		  }
		
	}
	
	
	
	public void delete(){
		DefaultTreeModel model=(DefaultTreeModel) this.getModel();
		Object selectedNode = (Object) this.getLastSelectedPathComponent();
		Instaler root = (Instaler) model.getRoot();
		if(selectedNode instanceof Verzija){
			root.getVerzije().remove(selectedNode);	
			 String s= this.getExpansionState();
			  model.reload();
			  this.setExpansionState(s);
			return;
		}
		if(selectedNode instanceof Panel){
			Panel zaBrisanje = (Panel) selectedNode;
			for(int i=root.getChildCount()-1; i>=0; i--){
				Verzija potVer = (Verzija) root.getChildAt(i); 
				for(int j=potVer.getChildCount()-1; j>=0; j--){
					Panel potencijalni = (Panel) potVer.getChildAt(j);
					if(zaBrisanje==potencijalni){
						potVer.getPaneli().remove(zaBrisanje);
						 String s= this.getExpansionState();
						  model.reload();
						  this.setExpansionState(s);
						return;
					}
				}
			}
		}
		if(selectedNode instanceof Parametar){
			Parametar zaBrisanje = (Parametar) selectedNode;
			for(int i=root.getChildCount()-1; i>=0; i--){
				Verzija potVer = (Verzija) root.getChildAt(i); 
				for(int j=potVer.getChildCount()-1; j>=0; j--){
					Panel potPan = (Panel) potVer.getChildAt(j);
					for(int k=potPan.getChildCount()-1; k>=0; k--){
						Parametar potencijalni = (Parametar) potPan.getChildAt(k);
						if(zaBrisanje==potencijalni){
							potPan.getParametri().remove(zaBrisanje);
							 String s= this.getExpansionState();
							  model.reload();
							  this.setExpansionState(s);
							return;
						}
					}
				}
			}
		}
		
		
		
	}
	
	
	

	public String getExpansionState(){

		StringBuilder sb = new StringBuilder();

		for ( int i = 0; i < this.getRowCount(); i++ ){

			if ( this.isExpanded(i) ){

				sb.append(i).append(",");

			}

		}

		return sb.toString();

	}
	
	
	public void setExpansionState(String s){

		String[] indexes = s.split(",");

		for ( String st : indexes ){

			int row = Integer.parseInt(st);

			this.expandRow(row);
			this.setSelectionRow(row);

		}

	}

	public void saveAs(String naziv, String lokacija) {
		DefaultTreeModel model = (DefaultTreeModel) this.getModel();
		Instaler root = (Instaler) model.getRoot();
		
		ObjectMapper mapper = new ObjectMapper();
		String fajl = lokacija+"/"+naziv+".json";
		int index=0;
		for (int i=0; i<root.getChildCount(); i++){
			if(naziv == root.getChildAt(i).toString()){
				index=i;
				break;
			}
		}
		try {
			mapper.writeValue(new File(fajl), root.getChildAt(index));
		} catch (IOException e) {
			Neuspesno poruka=new Neuspesno(tjezik);
			poruka.showMess(tjezik);
			return;
		}
		
	}
	
	public Instaler getTreeRoot(){
		DefaultTreeModel model=(DefaultTreeModel) this.getModel();
		Instaler root = (Instaler) model.getRoot();
		return root;
	}

	public void copyNode() {
		copySelected = (Object) this.getLastSelectedPathComponent();
		if(copySelected==null){
			NemaKopiranje poruka=new NemaKopiranje(tjezik);
			poruka.showMess(tjezik);
			return;
		}	
		
		if(copySelected instanceof Instaler){
			NemoguceIzvrsiti poruka=new NemoguceIzvrsiti(tjezik);
			poruka.showMess(tjezik);
			return;
		}
		if(copySelected instanceof Panel){
			ArrayList<Parametar> param = new ArrayList<>();
			for(int i=0; i<((Panel)copySelected).getChildCount(); i++){
				Parametar p = (Parametar) ((Panel)copySelected).getChildAt(i);
				param.add(p);
			}
			copyPanel = param;
		}
		if(copySelected instanceof Verzija){
			ArrayList<Panel> pan = new ArrayList<>();
			for(int i=0; i<((Verzija)copySelected).getChildCount(); i++){
				Panel p = (Panel) ((Verzija)copySelected).getChildAt(i);
				pan.add(p);
			}
			copyVerzija = pan;
		}
	}

	public void pasteNode() {
		pasteSelected =(Object) this.getLastSelectedPathComponent();
		if(copySelected==null){
			NijeKopirano poruka=new NijeKopirano(tjezik);
			poruka.showMess(tjezik);
			return;
		}
		if((copySelected instanceof Verzija) && (pasteSelected instanceof Instaler)){
			pasteNodeRealization(1);
			return;
		}
		if((copySelected instanceof Panel) && (pasteSelected instanceof Verzija)){
			pasteNodeRealization(2);
			return;
		}
		if((copySelected instanceof Parametar) && (pasteSelected instanceof Panel)){
			pasteNodeRealization(3);
			return;
		}
		if(pasteSelected==null){
			NemaNalepi poruka=new NemaNalepi(tjezik);
			poruka.showMess(tjezik);
			return;
		}
		NemoguceIzvrsiti poruka=new NemoguceIzvrsiti(tjezik);
		poruka.showMess(tjezik);	
	}

	private void pasteNodeRealization(int i) {
		DefaultTreeModel model=(DefaultTreeModel) this.getModel();
		Instaler root = this.getTreeRoot();
		switch (i){
		case 1:
			String nV= new String();
			nV=((Verzija)copySelected).getNaziv();
			this.addVerzijaCopy(nV);
			if (cuted==true){
				for(int ind=0;ind<root.getChildCount(); ind++){
					if(root.getChildAt(ind)==copySelected){
						((Instaler)root).getVerzije().remove(ind);
						cuted=false;
						model.reload();
						return;
					}
				}
			}
			break;
		case 2:
			String nP = ((Panel)copySelected).getNaziv();
			if (cuted==false)
				nP=nP+"-copy";
			Panel pan = this.addPanelCopy(nP);
			((Verzija)pasteSelected).getPaneli().add(pan);
			if (cuted==true){
				for(int ind=0;ind<root.getChildCount(); ind++){
					Verzija v = (Verzija) root.getChildAt(ind);
					for(int jot=0; jot<v.getChildCount(); jot++){
						if(v.getChildAt(jot)==copySelected){
							v.getPaneli().remove(jot);
							cuted=false;
							model.reload();
							return;
						}
					}
				}
			}
			break;
		case 3:
			Parametar toBeCopied3 = (Parametar) copySelected;
			((Panel)pasteSelected).getParametri().add(toBeCopied3);
			if (cuted==true){
				for(int ind=0;ind<root.getChildCount(); ind++){
					Verzija v = (Verzija) root.getChildAt(ind);
					for(int jot=0; jot<v.getChildCount(); jot++){
						Panel p = (Panel) v.getChildAt(jot);
						for(int ka=0; ka<p.getChildCount();ka++){
							if(p.getChildAt(ka)==copySelected){
								p.getParametri().remove(ka);
								cuted=false;
								model.reload();
								return;
							}
						}
					}
				}
			}
			break;
		}
		model.reload();
		
	}

	private void addVerzijaCopy(String nV) {
		DefaultTreeModel model=(DefaultTreeModel) this.getModel();
		Instaler root= (Instaler) model.getRoot();
		
		String naziv = nV;
		if(cuted==false)
			nV=nV+"-copy";
		Verzija v = addVerzija1(naziv);
		Verzija vC=new Verzija();
		for(int i=root.getChildCount()-1; i>=0;i--){
			vC = (Verzija) root.getChildAt(i);
			if(vC.getNaziv()==nV && vC!=v)
				break;
		}
		String lokI= (String) vC.getLokInstalacije();
		String lokS= (String) vC.getLokSoftvera();
		String lokL= (String) vC.getLokLogo();
		ArrayList<Panel> panC = new ArrayList<>();
		Panel p = new Panel();
		String pN = new String();
		for(int i=0; i<vC.getChildCount(); i++){
			p = (Panel) vC.getChildAt(i);
			pN = p.getNaziv();
			panC.add(addPanelCopy(pN));
			
		}
		Boolean b = vC.isCheck();
		v.setLokInstalacije(lokI);
		v.setLokLogo(lokL);
		v.setLokSoftvera(lokS);
		if (copyVerzija!=null){
			v.setPaneli(copyVerzija);
			copyVerzija=null;
		}
		else
			v.setPaneli(panC);
		v.setCheck(b);
	}
	private Panel addPanelCopy(String pN) {
		ArrayList<Parametar> par = new ArrayList<>();
		Panel pan = new Panel(pN, par);
		DefaultTreeModel model=(DefaultTreeModel) this.getModel();
		Instaler root= (Instaler) model.getRoot();
		
		Verzija vC=new Verzija();
		Panel pC = new Panel();
		for(int i=0; i<root.getChildCount();i++){
			vC = (Verzija) root.getChildAt(i);
			for(int j=0; j<vC.getChildCount(); j++){
				pC = (Panel) vC.getChildAt(j);
				if(pC.getNaziv()==pN && pC!=pan)
					break;
			}
			if(pC.getNaziv()==pN && pC!=pan)
				break;
		}
		Parametar param = new Parametar();
		if (copyPanel!=null){
			par=copyPanel;
			copyPanel=null;
		}
		else{
			for(int i=0; i<pC.getChildCount(); i++){
				param = (Parametar)pC.getChildAt(i);
				par.add(param);
			}
		}
		pan = new Panel(pN,par);
		return pan;
	}

	public Verzija addVerzija1(String s){
		DefaultTreeModel model=(DefaultTreeModel) this.getModel();
		
		Instaler root= (Instaler) model.getRoot();
		if( !s.trim().equals("")) {
			ArrayList<Panel> li = new ArrayList<Panel>();
			Verzija verz = new Verzija(s,"", "", "", li, false);
			root.getVerzije().add(verz);
			 String s1= this.getExpansionState();
			  model.reload();
			  this.setExpansionState(s1);
			  return verz;
		}
		return null;
		
	}

	public void cutNode() {
		Object toCut = (Object) this.getLastSelectedPathComponent();
		if(toCut==null){
			NemaIseci poruka=new NemaIseci(tjezik);
			poruka.showMess(tjezik);
			return;
		}
		if(toCut instanceof Instaler){
			NemoguceIzvrsiti poruka=new NemoguceIzvrsiti(tjezik);
			poruka.showMess(tjezik);
		}
		copyNode();
		cuted=true;	
	}

	public void editNode() {
		Object selected = (Object) this.getLastSelectedPathComponent();
		DefaultTreeModel model=(DefaultTreeModel) this.getModel();
		PanelTree tree = this;
		if(selected instanceof Instaler){
			dialEinst = new EditInstaler(model.getRoot().toString(), tjezik);
			dialEinst.setVisible(true);
			String naziv = (String) dialEinst.getNaziv();
			((Instaler)selected).setNaziv(naziv);
			model.reload();
			return;
		}
		if(selected instanceof Verzija){
			dialEverz = new EditVerzija(tree, tjezik);
			dialEverz.setVisible(true);
			if(!dialEverz.getUspesno())
				return;
			if(((Verzija)selected).getChildCount()>0){
				((Verzija) selected).setLokInstalacije(dialEverz.getLokInstalacije());
				((Verzija) selected).setLokLogo(dialEverz.getLokLogo());
				((Verzija) selected).setLokSoftvera(dialEverz.getLokSoftvera());
				((Verzija) selected).setNaziv(dialEverz.getNaziv());
				((Verzija) selected).setCheck(dialEverz.isCheck());
				boolean postoji=false;
				for(int i=0; i<((Verzija)selected).getChildCount(); i++){						
					Panel p = (Panel) ((Verzija)selected).getChildAt(i);
					for(int j=0; j<p.getChildCount(); j++){
						Parametar par = (Parametar) p.getChildAt(j);
						if(par.getTip()==TipParametra.DEFAULT_LOK){
							par.setVrednost(dialEverz.getLokInstalacije());
							par.setCheck(dialEverz.isCheck());
							postoji=true;
						}
					}
				}
				{
					if(postoji==false){
						Panel pa = (Panel) ((Verzija)selected).getChildAt(((Verzija)selected).getChildCount()-1);
						Parametar param = new Parametar("(defaultLok)", dialEverz.getLokInstalacije(), TipParametra.DEFAULT_LOK);
						pa.getParametri().add(param);
						param.setCheck(dialEverz.isCheck());
					}
				}
		}
		model.reload();
		return;
		}
		if(selected instanceof Panel){
			dialEpan = new EditPanel(tree, tjezik);
			dialEpan.setVisible(true);
			if(!dialEpan.isUspesno())
				return;
			String naziv = (String) dialEinst.getNaziv();
			((Panel)selected).setNaziv(naziv);
			model.reload();
			return;
		}
		if(selected instanceof Parametar){
			TipParametra tip=((Parametar)selected).getTip();
			switch(tip){
			case TEKSTUALNI_FAJL:
				dialEparTF = new EditTFajl(tree, tjezik);
				dialEparTF.setVisible(true);
				if(!dialEparTF.getUspesno())
					return;
				String naziv = (String) dialEparTF.getNaziv();
				String vrednost = (String) dialEparTF.getVrednost();
				Boolean b = (Boolean) dialEparTF.getCheck();
				((Parametar)selected).setNaziv(naziv);
				((Parametar)selected).setVrednost(vrednost);
				((Parametar)selected).setCheck(b);
				return;
			case TEKST:
				dialEparT = new EditTekst(tree, tjezik);
				dialEparT.setVisible(true);
				if(!dialEparT.getUspesno())
					return;
				String nT = (String) dialEparT.getNaziv();
				String vT = (String) dialEparT.getVrednost();
				((Parametar)selected).setNaziv(nT);
				((Parametar)selected).setVrednost(vT);
				return;
			case LINIJA_TEKSTA:
				dialEparLT = new EditLinija(tree, tjezik);
				dialEparLT.setVisible(true);
				if(!dialEparLT.getUspesno())
					return;
				String nLT = (String) dialEparLT.getNaziv();
				String vLT = (String) dialEparLT.getVrednost();
				((Parametar)selected).setNaziv(nLT);
				((Parametar)selected).setVrednost(vLT);
				return;
			case DEFAULT_LOK:
				dialEparDL = new EditDefLok(tree, tjezik);
				dialEparDL.setVisible(true);
				if(!dialEparDL.getUspesno())
					return;
				String nDL = (String) dialEparDL.getNaziv();
				String vDL = (String) dialEparDL.getVrednost();
				((Parametar)selected).setNaziv(nDL);
				((Parametar)selected).setVrednost(vDL);
				Instaler root = (Instaler) model.getRoot();
				for(int i=root.getChildCount()-1; i>=0; i--){
					Verzija v = (Verzija) root.getChildAt(i);
					for(int j=v.getChildCount()-1;j>=0; j--){
						Panel p = (Panel) v.getChildAt(j);
						for(int k=p.getChildCount()-1; k>=0; k--){
							if(p.getChildAt(k)==selected){
								v.setLokInstalacije(vDL);
								return;
							}
						}
					}
				}
				return;
			case SLIKA:
				dialEparS = new EditSlika(tree, tjezik);
				dialEparS.setVisible(true);
				if(!dialEparS.getUspesno())
					return;
				String nS = (String) dialEparS.getNaziv();
				String vS = (String) dialEparS.getVrednost();
				((Parametar)selected).setNaziv(nS);
				((Parametar)selected).setVrednost(vS);
				return;
			case EXE:
				dialExe = new EditExe(tree, tjezik);
				dialExe.setVisible(true);
				if(!dialExe.getUspesno())
					return;
				String nE = (String) dialExe.getNaziv();
				String vE = (String) dialExe.getVrednost();
				Boolean bE = (Boolean) dialExe.getCheck();
				((Parametar)selected).setNaziv(nE);
				((Parametar)selected).setVrednost(vE);
				((Parametar)selected).setCheck(bE);
				break;
			case CHECKBOX:
				dialEparCB = new EditChBox(tree, tjezik);
				dialEparCB.setVisible(true);
				if(!dialEparCB.getUspesno())
					return;
				String nCB = (String) dialEparCB.getNaziv();
				String vCB = (String) dialEparCB.getVrednost();
				((Parametar)selected).setNaziv(nCB);
				((Parametar)selected).setVrednost(vCB);
				return;
			case LIST:
				dialEparL = new EditList(tjezik,tree);
				dialEparL.setVisible(true);
				if(!dialEparL.getUspesno())
					return;
				String nL = (String) dialEparL.getNaziv();
				String vL = (String) dialEparL.getVrednost();
				((Parametar)selected).setNaziv(nL);
				((Parametar)selected).setVrednost(vL);
				return;
			case LOKALIZACIJA:
				dialElokal = new EditLokalizacija(tjezik,tree);
				dialElokal.setVisible(true);
				if(!dialElokal.getUspesno())
					return;
				String nLo = (String) dialElokal.getNaziv();
				String vLo = (String) dialElokal.getVrednost();
				((Parametar)selected).setNaziv(nLo);
				((Parametar)selected).setVrednost(vLo);
				return;
			default:
				break;
			}
		}
		NemaStavke poruka=new NemaStavke(tjezik);
		poruka.showMess(tjezik);
	}

	public void showProperties() {
		dialProp = new ShowProperties(this);
		dialProp.setVisible(true);
	}

}
