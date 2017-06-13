package controller;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import dev_mode_gui.DevFrame;

public class StartProgram {
	private static JFrame frame;
	private static Properties jezik;
	private static FileInputStream fs;
	private static String odabranJezik;
	
	private static void setUIFont(javax.swing.plaf.FontUIResource f)
	{
	    java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements())
	    {
	        Object key = keys.nextElement();
	        Object value = UIManager.get(key);
	        if (value instanceof javax.swing.plaf.FontUIResource)
	        {
	            UIManager.put(key, f);
	        }
	    }
	}
	public String getOdabranJezik(){
		return odabranJezik;
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException {
		Properties jezik=new Properties();
		OdabirModa om= new OdabirModa();
		if(om.iscitaj()==0){
			
			Object[] possibilities = {"Srpski", "English"};
			Icon icon = null;
			
			JOptionPane jp = new JOptionPane();
			String s= new String();
			s = (String)jp.showInputDialog(
			                    frame,
			                    null,
			                    "Jezik/Language",
			                    JOptionPane.PLAIN_MESSAGE,
			                    icon, possibilities, null);
			
			//If a string was returned, say so.
		if(s!= "Srpski" && s!="English"){
			System.exit(0);
		}
				if(s == "Srpski"){
					odabranJezik=s;
					fs = new FileInputStream(System.getProperty("user.dir")+"/src/dev_mode_gui/srp.properties");
					jezik.load(fs);
				}
				else if(s == "English"){
					odabranJezik=s;
					fs = new FileInputStream(System.getProperty("user.dir")+"/src/dev_mode_gui/eng.properties");
					jezik.load(fs);
				}
				
				
			
			
			
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					setUIFont (new javax.swing.plaf.FontUIResource(new Font("Calibri",Font.PLAIN, 14)));
					

					    } 
					    catch(Exception e){ 
					    }
				
				DevFrame frame=null;
				try {
					frame = new DevFrame(jezik);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.setVisible(true);
	
				}
		});

	
		
		}else{
			
			//DRUGI MOD
		}
	
	}


	private static void setLabel(String string) {
		// TODO Auto-generated method stub
		
	}	
}
