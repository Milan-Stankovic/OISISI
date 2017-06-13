package paneliZaUserMode;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import user_mode_gui.InstallFrame;
import warnings.Neuspesno;

@SuppressWarnings("serial")
public class ExePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private String vrednostPar;
	private String nazivPar;
	private boolean izvrseno=false;
	private int i=0;
	private ActionListener al;
	private JProgressBar progressBar;
	private int progress=0;
	private InstallFrame insF;
	private JButton btnInstall;
	private Properties jezik;
	public void setInst(boolean b){
		btnInstall.setEnabled(b);
	}
	
	public void setParVal(String s){
		vrednostPar=s;
	}
	public void setParName(String s){
		nazivPar=s;
	}
	public String getNaziv(){
		return nazivPar;
	}
	public String getVrednost(){
		return vrednostPar;
	}
	
	public boolean getIzvrseno(){
		return izvrseno;
	}
	
	
	private void izvrsenje(String command){
		
		Runtime rt = Runtime.getRuntime();
		
		try {
			@SuppressWarnings("unused")
			Process pr = rt.exec(command);
			
	
		} catch (IOException e) {
			Neuspesno poruka=new Neuspesno(jezik);
			poruka.showMess(jezik);
			return;
		}
		
		
	}
	

	
	
	public ExePanel(String s1, String s2, boolean b,InstallFrame ins, Properties j) {
		nazivPar=s1;
		vrednostPar=s2;
		insF=ins;
		jezik=j;
	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.5; //Procenat ekrana
		height=height*0.15; 
		
		setBounds(100, 100, (int)width, (int)height); 
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{60, 0, 80, 0};
		gridBagLayout.rowHeights = new int[]{20, 40, 60, 20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

		btnInstall = new JButton(nazivPar);
		btnInstall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				izvrsenje(vrednostPar);
				progress=0;
				if(insF!=null){
					
					insF.setSledece(false);	
					insF.setZavrsi(false);
				}
				ses.scheduleAtFixedRate(new Runnable() {
				    @Override
				    public void run() {
				    	btnInstall.setEnabled(false);
				    	int pomeri=1;
				    	progress+= pomeri;
				    	
						if(progress>=100){
							progress=100;
							insF.setSledece(true);	
							insF.setZavrsi(true);
							insF.repaint();
							insF.revalidate();
							
						    progressBar.setValue(progress);  
						    progressBar.repaint();
						    progressBar.revalidate();
							ses.shutdown(); 
							return;
						}
						
				        progressBar.setValue(progress);  
				        progressBar.repaint();
				        progressBar.revalidate();
						
				    }
				}, 0, 10, TimeUnit.MILLISECONDS);
				

				  
			}
				
				
		
		});
		GridBagConstraints gbc_btnInstall = new GridBagConstraints();
		gbc_btnInstall.insets = new Insets(0, 0, 5, 5);
		gbc_btnInstall.gridx = 1;
		gbc_btnInstall.gridy = 1;
		add(btnInstall, gbc_btnInstall);
		
		if(b){
		progressBar = new JProgressBar(0, 20);
		progressBar.setStringPainted(true);
		
		
		
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 0, 5, 5);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 2;
		add(progressBar, gbc_progressBar);
		
		}

			
		
	
	
	}

}
