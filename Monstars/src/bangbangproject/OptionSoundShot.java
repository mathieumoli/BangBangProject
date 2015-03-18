package bangbangproject;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

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
		difficulty.setMinorTickSpacing(1);
		difficulty.setMajorTickSpacing(1);
		difficulty.setPaintTicks(true);
		difficulty.setPaintLabels(true);
		difficulty.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println(difficulty.getValue());
				if (!difficulty.getValueIsAdjusting())
					
					voix.playText("Difficulté niveau " + difficulty.getValue());

			}
		});

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
}
