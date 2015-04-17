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
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import devintAPI.FenetreAbstraite;
import devintAPI.Preferences;

public class OptionDuel extends FenetreAbstraite {
	protected int nbRounds = 0;
	protected JSpinner tRounds;
	protected JLabel labelRound;
	protected JTextArea title;
	protected String titre = "tghghg";
	protected JPanel rounds;
	protected GridLayout sLayout;
	protected JButton letsGo;

	public OptionDuel() {
		super("Bang Bang Project Duel");
		titre = "Bang Bang Project Duel";
		init();
	}

	public OptionDuel(String nom) {
		super(nom);
		titre = nom;
		init();

	}

	@Override
	protected void init() {

		JPanel ti = new JPanel();
		title = new JTextArea(titre);
		title.setEnabled(false);
		title.setBackground(Color.RED);
		ti.add(title);
		ti.setBackground(Color.red);
		nbRounds = 0;
		rounds = new JPanel();
		sLayout = new GridLayout(5, 2);
		rounds.setLayout(sLayout);

		for (int i = 0; i < 4; i++) {
			rounds.add(new JPanel());
		}

		labelRound = new JLabel("Nombre de Rounds :");
		labelRound.setFont(new Font("Arial", Font.BOLD, 50));
		title.setFont(new Font("Arial", Font.BOLD, Preferences.LARGE_SIZE));
		title.setDisabledTextColor(Color.BLACK);
		tRounds = new JSpinner();
		tRounds.setValue(1);
		tRounds.setFont(new Font("Arial", Font.BOLD, 50));
		tRounds.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if ((int) tRounds.getValue()<1) {
					tRounds.setValue(1);

				} else
					voix.playText("Nombre de tours gagnant "
							+ tRounds.getValue());

			}
		});

		rounds.add(labelRound);
		rounds.add(tRounds);
		for (int i = 0; i < 4; i++) {
			rounds.add(new JPanel());
		}
		letsGo = new JButton("Let's Go!!!");
		letsGo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				voix.forceStop();
				new GameWindow((int)tRounds.getValue(),true,0);
				letsGo.removeActionListener(this);
				
			}

		});
		letsGo.setBackground(Color.GREEN);
		letsGo.setFont(new Font("Arial", Font.BOLD, Preferences.LARGE_SIZE));
		this.add(rounds, BorderLayout.CENTER);
		this.add(ti, BorderLayout.NORTH);
		this.add(letsGo, BorderLayout.SOUTH);

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

	// renvoie le fichier wave contenant le message d'accueil
	protected String wavAccueil() {
		return "../ressources/sons/duel.wav";
	}

	// renvoie le fichier wave contenant la règle du jeu
	protected String wavRegleJeu() {
		return "../ressources/sons/duel.wav";
	}

	// renvoie le fichier wave contenant la règle du jeu
	protected String wavAide() {
		return "../ressources/sons/explication.wav";
	}

}
