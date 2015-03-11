package bangbangproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;

import org.eclipse.swt.layout.FormLayout;

import devintAPI.FenetreAbstraite;
import devintAPI.Preferences;

public class OptionDuel extends FenetreAbstraite {
	private int nbRounds = 0;
	private JTextField tRounds;
	private JLabel labelRound;
	private String titre;

	public OptionDuel() {
		super("Bang Bang Project Duel");
		init();
		titre = "Bang Bang Project Duel";
	}

	@Override
	protected void init() {

		

		JPanel ti = new JPanel();
		JLabel title = new JLabel("Bang Bang Project Duel");
		ti.add(title);
		ti.setBackground(Color.red);
		nbRounds = 0;
		JPanel rounds = new JPanel();
		GridLayout sLayout = new GridLayout(5,2);
		rounds.setLayout(sLayout);

		for(int i=0;i<4;i++){
			rounds.add(new JPanel());
		}
		

		labelRound = new JLabel("Nombre de Rounds :");
		labelRound.setFont(new Font("Arial", Font.BOLD, 50));
		title.setFont(new Font("Arial", Font.BOLD, Preferences.LARGE_SIZE));
		tRounds = new JTextField(nbRounds);
		tRounds.setFont(new Font("Arial", Font.BOLD, 50));
		
		rounds.add(labelRound);
		rounds.add(tRounds);
		for(int i=0;i<4;i++){
			rounds.add(new JPanel());
		}
		JButton letsGo= new JButton("Let's Go!!!");
		letsGo.setBackground(Color.GREEN);
		letsGo.setFont(new Font("Arial", Font.BOLD, Preferences.LARGE_SIZE));
		letsGo.addActionListener(new letsGoActionListener());
		this.add(rounds, BorderLayout.CENTER);
		this.add(ti, BorderLayout.NORTH);
		this.add(letsGo,BorderLayout.SOUTH);
		

	}

	/**
	 * Pour modifier les couleurs de fond et de premier plan Cette fonction est
	 * appelée par la fonction "changeColor" de la classe
	 * "devintAPI.Preferences" à chaque fois que l'on presse F3
	 * 
	 * ici on décide que le changement des couleurs s'applique sur le label lbl1
	 **/
	public void changeColor() {
		// on récupère les couleurs de base dans la classe Preferences
		Preferences pref = Preferences.getData();
		labelRound.setBackground(pref.getCurrentBackgroundColor());
		labelRound.setForeground(pref.getCurrentForegroundColor());

		tRounds.setBackground(pref.getCurrentBackgroundColor());
		tRounds.setForeground(pref.getCurrentForegroundColor());

	}

	/**
	 * Pour modifier la police des textes à chaque fois que l'on presse F4
	 */
	public void changeSize() {
		Font f = Preferences.getData().getCurrentFont();
		labelRound.setFont(f);
		tRounds.setFont(f);
	}

	@Override
	protected String wavAide() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String wavAccueil() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String wavRegleJeu() {
		// TODO Auto-generated method stub
		return null;
	}

}

class letsGoActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		new GameWindow(null);
	}
	
}