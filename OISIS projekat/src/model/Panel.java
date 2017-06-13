package model;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Panel implements TreeNode  {
	private String naziv;
	private ArrayList<Parametar> parametri = new ArrayList<>();
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public ArrayList<Parametar> getParametri() {
		return parametri;
	}
	public void setParametri(ArrayList<Parametar> parametri) {
		this.parametri = parametri;
	}


	public Panel(String naziv, ArrayList<Parametar> parametri) {
		this.naziv = naziv;
		this.parametri = parametri;
	}
	public Panel(){
		
	}
			
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Enumeration children() {
		return (Enumeration<Parametar>) parametri;
	}
	@Override
	@JsonIgnore
	public boolean getAllowsChildren() {
		return true;
	}
	@Override
	@JsonIgnore
	public TreeNode getChildAt(int childIndex) {
		return this.parametri.get(childIndex);
	}
	@Override
	@JsonIgnore
	public int getChildCount() {
		return this.parametri.size();
	}
	@Override
	@JsonIgnore
	public int getIndex(TreeNode node) {
		return this.parametri.indexOf(node);
	}
	@Override
	@JsonIgnore
	public boolean isLeaf() {
		return false;
	}
	public String toString(){
		return this.naziv;
	}
	@JsonIgnore
	public void deletePanel() {
		this.naziv = "";
		this.parametri = null;
	}
	@Override
	@JsonIgnore
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
