package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OdabirModa {

	private FileInputStream fs;
	public OdabirModa(){}
	
	
	public int iscitaj(){
		
		String txt= (System.getProperty("user.dir")+"/src/dev_mode_gui/mod.txt");
		
		String everything= new String();
		try(BufferedReader br = new BufferedReader(new FileReader(txt))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		  everything = sb.toString();
		
		} catch (FileNotFoundException e) {
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i=Integer.parseInt(everything) ;
		
		return i;
		
		
}
	

}
