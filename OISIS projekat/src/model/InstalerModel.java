package model;

import java.util.ArrayList;

import javax.swing.tree.DefaultTreeModel;

@SuppressWarnings("serial")
public class InstalerModel extends DefaultTreeModel {

	public InstalerModel() {
		super(new Instaler("Installer", new ArrayList<Verzija>()));
	}
 
}