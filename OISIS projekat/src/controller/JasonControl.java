package controller;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.swing.tree.DefaultTreeModel;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev_mode_gui.PanelTree;
import model.Instaler;
import model.Verzija;
import warnings.Neuspesno;

public class JasonControl {
	private Properties jezik;
	public JasonControl(Properties j){}

	public void deleteFiles(PanelTree tree, Properties j) {
		jezik=j;
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		Instaler root = (Instaler) model.getRoot();
		for (int i=0; i<root.getChildCount(); i++){
			String fajl = root.getChildAt(i).toString();
			
			fajl = fajl+".json";
			String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/"+fajl);
			try{
	    		File file = new File(myLoc);
	    		file.delete();
	    	}catch(Exception e){
	    		Neuspesno poruka=new Neuspesno(jezik);
				poruka.showMess(jezik);
				return;
	    	}
		}

		
	}
	
	public void saveModelToJSon(PanelTree tree, Properties j){
		jezik=j;
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		Instaler root = (Instaler) model.getRoot();
		
		ObjectMapper mapper = new ObjectMapper();
		deleteFiles(tree, jezik);
		for (int i=0; i<root.getChildCount(); i++){
			String fajl = root.getChildAt(i).toString();
			String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/"+fajl+ ".json");
			try {
				mapper.writeValue(new File(myLoc), root.getChildAt(i));
			} catch (IOException e) {
				Neuspesno poruka=new Neuspesno(jezik);
				poruka.showMess(jezik);
				return;
			}
		}
	}
	public void saveAs(String naziv, String lokacija){
		
	}
	
	public Verzija loadModelFromJSon(String naziv,Verzija instaler, Properties j){
		jezik=j;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.WRAP_EXCEPTIONS, true);
			instaler = new Verzija();
			try {
				//da bi bilo lakse, napravim instaler verzija1 sa 1 panelom i 1 parametrom da bih ovde to testirala
				//zato je ovde zakucana ova lokacija fajla, da vidim da li ce da radi bar za to
				String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/"+naziv+ ".json");
				instaler = mapper.readValue(new File(myLoc), Verzija.class);
		
			} catch (IOException e) {
				Neuspesno poruka=new Neuspesno(jezik);
				poruka.showMess(jezik);
				
			}
			return instaler;
			
	}
			

}
