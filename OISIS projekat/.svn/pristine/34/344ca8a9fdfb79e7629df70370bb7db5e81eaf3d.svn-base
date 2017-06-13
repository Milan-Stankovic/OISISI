package dev_mode_gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class UserFrame extends JFrame {

	private JPanel contentPane;
	private JPanel mid;
	private JPanel bot;
	private List<JPanel> trenutna;
	private int brojac=-1;
	private List<List<JPanel>> svi;


	public void load(){
		
		brojac++;
		trenutna=svi.get(brojac);
		for(int i=0; i<trenutna.size();i++){
			mid.add(trenutna.get(i));
		}
		setBot();
		
	}
	public void setBot(){
		
		//Ovde treba da izabere dugmad koja idu dole
	}
	
	public UserFrame(List<List<JPanel>> j) {
		svi=j;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		mid = new JPanel();
		contentPane.add(mid, BorderLayout.CENTER);
		mid.setLayout(new BoxLayout(mid, BoxLayout.X_AXIS));
		
		bot = new JPanel();
		contentPane.add(bot, BorderLayout.SOUTH);
		bot.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
	}

}
