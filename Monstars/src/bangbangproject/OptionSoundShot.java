package bangbangproject;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import devintAPI.Preferences;

public class OptionSoundShot extends OptionDuel {
	JLabel lDifficulty;

	public OptionSoundShot() {
		super("Bang Bang Project\n      Sound Shot");
		init();

	}

	@Override
	public void init() {
		super.init();

		title.setFont(new Font("Arial", Font.BOLD, Preferences.MEDIUM_SIZE));
		lDifficulty = new JLabel("Difficulté");
		lDifficulty.setFont(new Font("Arial", Font.BOLD, 50));

		JSlider difficulty = new JSlider(1, 3, 1);
		rounds = new JPanel();
		rounds.setLayout(sLayout);
		difficulty.setMinimum(1);
		difficulty.setMinorTickSpacing(1);
		difficulty.setMajorTickSpacing(1);
		difficulty.setPaintTicks(true);
		difficulty.setValue(1);
		difficulty.setPaintLabels(true);
		difficulty.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println(difficulty.getValue());
				if (!difficulty.getValueIsAdjusting())

					voix.playText("Difficulté niveau " + difficulty.getValue());

			}
		});
		letsGo.removeActionListener(letsGoListener);
		letsGoListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new GameWindow( (int) tRounds.getValue(), true,
						(int) difficulty.getValue());
				letsGo.removeActionListener(this);

			}

		};
		letsGo.addActionListener(letsGoListener);
		rounds.add(lDifficulty);
		rounds.add(difficulty);
		rounds.add(labelRound);
		rounds.add(tRounds);
		this.add(rounds, BorderLayout.CENTER);

	}	

	/**
	 * Pour modifier la police des textes à chaque fois que l'on presse F4
	 */
	public void changeSize() {
		super.changeSize();
		lDifficulty.setFont(Preferences.getData().getCurrentFont());
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
		super.changeColor();
		lDifficulty.setBackground(pref.getCurrentBackgroundColor());
		lDifficulty.setBackground(pref.getCurrentBackgroundColor());
	}

	// renvoie le fichier wave contenant le message d'accueil
	protected String wavAccueil() {
		return "../ressources/sons/soundshot.wav";
	}

	// renvoie le fichier wave contenant la règle du jeu
	protected String wavRegleJeu() {
		return "../ressources/sons/soundshot.wav";
	}

}
