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
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import model.Verzija;
import user_mode_gui.InstallFrame;
import warnings.Neuspesno;


@SuppressWarnings("serial")
public class InstalPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private String vrednostPar;
	private String nazivPar;
	private boolean izvrseno=false;
	private JProgressBar progressBar;
	private int progress;
	private InstallFrame insF;
	private JCheckBox cb;
	private JButton btnInstall;
	private Properties jezik;
	
	public void setIns(boolean b){
		btnInstall.setEnabled(b);
	}
	
	public JCheckBox getCB(){
		return cb;
	}
	public void	setInstallFrame(InstallFrame i){
		
		insF=i;
		
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
	
	
		private void izvrsenje(String copyLoc, String lokacija){
		/*String copyLoc=nazivPar;
		//String myLoc= (System.getProperty("user.dir")+"/src/dev_mode_gui/");
		//String command="cmd.exe /c copy "+"\""+copyLoc+"\""+" "+"\""+ lokacija +"\"";  proveriti sta se desava
		/*nazivPar=s1;lokInstalacije
		vrednostPar=s2; loksoftvera
		
		
		String lokacija= vrednostPar;*/
		int last= lokacija.lastIndexOf("/");
		String end= lokacija.substring(last+1);
		copyLoc=copyLoc+ "\\" + end;
		lokacija=lokacija.replaceAll("/", "\\\\");
		
		
		String command="cmd.exe /c copy "+"\""+lokacija+"\""+" "+"\""+copyLoc+"\"";
		//String lokacija= vrednostPar;
	
		
		//String command="cmd.exe /c copy "+lokacija+"\\"+ copyLoc +"\\";
		
		
		Runtime rt = Runtime.getRuntime();
		
		try {
			@SuppressWarnings("unused")
			Process pr = rt.exec(command);
			
	
		} catch (IOException e) {
		
		}
		
			// TODO Auto-generated constructor stub
		}
	public InstalPanel(Verzija v, String inst, InstallFrame ins, Properties j, String dugme) {
		jezik=j;
		insF=ins;
	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Odredjivanje velicine ekrana 
		double width =  screenSize.getWidth(); //da bi dialog izgledao normalno na svim ekranima
		double height =  screenSize.getHeight();
		width=width*0.5; //Procenat ekrana
		height=height*0.15; 
		
		setBounds(100, 100, 683, 178); 
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{60, 0, 80, 0};
		gridBagLayout.rowHeights = new int[]{40, 60, 20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		
		
		progressBar = new JProgressBar(0, 20);
		progressBar.setStringPainted(true);
		
		
		
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 0, 5, 5);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 1;
		add(progressBar, gbc_progressBar);
		
		
		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

		
		
		btnInstall = new JButton(inst);
		btnInstall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				izvrsenje(v.getLokInstalacije(), v.getLokSoftvera());
				progress=0;
				if(insF!=null){
					
					insF.setSledece(false);	
					insF.setZavrsi(false);
					insF.repaint();
					insF.revalidate();
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
		gbc_btnInstall.gridy = 0;
		add(btnInstall, gbc_btnInstall);
		
		
		if(dugme==""){
			dugme=jezik.getProperty("hocePrecicu");
		}
		
		cb = new JCheckBox(dugme);
		GridBagConstraints gbc_cb = new GridBagConstraints();
		gbc_cb.insets = new Insets(0, 0, 0, 5);
		gbc_cb.gridx = 1;
		gbc_cb.gridy = 2;
		add(cb, gbc_cb);
		

		
	
		//NE ZNAM DA LI RADI 
		
			
		
	}

	
	
	}

