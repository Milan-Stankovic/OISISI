package model;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public class Instaler implements TreeNode {
	private String naziv;
	private ArrayList<Verzija> verzije;
	

	public String getNaziv() {
		return naziv;
	}
	
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public ArrayList<Verzija> getVerzije() {
		return verzije;
	}
	
	public void setVerzije(ArrayList<Verzija> verzije) {
		this.verzije = verzije;
	}
	
	public Instaler() {
		
	}
	
	public Instaler(String n, ArrayList<Verzija> v) {
		this.naziv=n;
		this.verzije=v;
	}
	/*
	public static Instaler getInstance() {
		if (instance == null) {
			instance = new Instaler("Naziv softverske kompanije", null);
			ArrayList<Verzija> pan = new ArrayList<Verzija>();
			instance.setVerzije(pan);
		}
		return instance;
	}*/
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Enumeration children() {
		return (Enumeration<Verzija>) verzije;
	}
	@Override
	public boolean getAllowsChildren() {
		return true;
	}
	@Override
	public TreeNode getChildAt(int childIndex) {
		return this.verzije.get(childIndex);
	}
	@Override
	public int getChildCount() {
		if (this.verzije == null)
			return 0;
		return this.verzije.size();
	}
	@Override
	public int getIndex(TreeNode node) {
		return this.verzije.indexOf(node);
	}
	@Override
	public TreeNode getParent() {
		return null;
	}
	@Override
	public boolean isLeaf() {
		return false;
	}
	@Override
	public String toString(){
		return this.naziv;
	}
	
}
