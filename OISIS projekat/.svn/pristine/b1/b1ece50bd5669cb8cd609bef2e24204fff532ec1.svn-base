package model;

import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Parametar implements TreeNode  {
	private String naziv;
	private String vrednost;
	private TipParametra tip;
	private boolean mora;
	
	public String getNaziv() {
		return naziv;
	}
	public String getVrednost() {
		return vrednost;
	}
	public TipParametra getTip() {
		return tip;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public void setVrednost(String vrednost) {
		this.vrednost = vrednost;
	}
	public void setTip(TipParametra tip) {
		this.tip = tip;
	}
	
	public boolean isCheck() {
		return mora;
	}
	public void setCheck(boolean b) {
		this.mora=b;
	}

	
	public Parametar(String naziv, String vrednost, TipParametra tip) {
		this.naziv = naziv;
		this.vrednost = vrednost;
		this.tip = tip;
	}
	public Parametar(){
		
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		return null;
	}
	@Override
	@JsonIgnore public boolean getAllowsChildren() {
		return false;
	}
	@Override
	@JsonIgnore
	public TreeNode getChildAt(int childIndex) {
		return null;
	}
	@Override
	@JsonIgnore
	public int getChildCount() {
		return 0;
	}
	@Override
	@JsonIgnore
	public int getIndex(TreeNode node) {
		return 0;
	}

	
	@Override
	public String toString(){
		return this.naziv;
	}
	@Override
	@JsonIgnore
	public boolean isLeaf() {
		return true;
	}
	@JsonIgnore
	public void deleteParametar() {
		this.naziv = "";
		this.vrednost = "";
		this.tip = null;
	}
	@Override
	@JsonIgnore
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
