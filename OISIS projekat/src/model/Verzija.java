package model;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Verzija implements TreeNode  {
	private String naziv;
	private String lokSoftvera;
	private String lokInstalacije;
	private String lokLogo;
	private boolean check=false;
	private ArrayList<Panel> paneli;
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getLokSoftvera() {
		return lokSoftvera;
	}
	public String getLokInstalacije() {
		return lokInstalacije;
	}
	public String getLokLogo() {
		return lokLogo;
	}
	public ArrayList<Panel> getPaneli() {
		return paneli;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	public void setLokSoftvera(String lokSoftvera) {
		this.lokSoftvera = lokSoftvera;
	}
	public void setLokInstalacije(String lokInstalacije) {
		this.lokInstalacije = lokInstalacije;
	}
	public void setLokLogo(String lokLogo) {
		this.lokLogo = lokLogo;
	}
	public void setPaneli(ArrayList<Panel> paneli) {
		this.paneli = paneli;
	}

	
	public Verzija(String naziv, String lokSoftvera, String lokInstalacije, String lokLogo, ArrayList<Panel> paneli, boolean b) {
		this.naziv = naziv;
		this.lokSoftvera = lokSoftvera;
		this.lokInstalacije = lokInstalacije;
		this.lokLogo = lokLogo;
		this.paneli = paneli;
		this.check=b;
	}
	public Verzija(){
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@JsonIgnore
	public Enumeration children() {
		return (Enumeration<Panel>) paneli;
	}
	@Override
	@JsonIgnore
	public boolean getAllowsChildren() {
		return true;
	}
	@Override
	@JsonIgnore
	public TreeNode getChildAt(int childIndex) {
		return this.paneli.get(childIndex);
	}
	@Override
	@JsonIgnore public int getChildCount() {
		return this.paneli.size();
	}
	@Override
	@JsonIgnore
	public int getIndex(TreeNode node) {
		return this.paneli.indexOf(node);
	}
	@Override
	@JsonIgnore
	public boolean isLeaf() {
		return false;
	}
	@Override 
	@JsonIgnore
	public String toString(){
		return this.naziv;
	}
	@SuppressWarnings("unused")
	
	public void addVerzija(String naziv, Instaler instaler){
		ArrayList<Panel> paneli = new ArrayList<>();
		Verzija verzija = new Verzija(naziv, "", "", "", paneli, false);	
	}
	@JsonIgnore
	public void deleteVerzija(){
		this.naziv="";
		this.lokInstalacije="";
		this.lokLogo="";
		this.lokSoftvera="";
	}
	@JsonIgnore
	public void setParent(TreeNode tn) {
		
	}
	
	@Override
	@JsonIgnore
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

