package bangbangproject;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;

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

		JSlider difficulty = new JSlider();
		rounds = new JPanel();
		rounds.setLayout(sLayout);
		difficulty.setMinorTickSpacing(1);
		difficulty.setMajorTickSpacing(1);
		difficulty.setPaintTicks(true);
		difficulty.setPaintLabels(true);
		difficulty.setMaximum(3);
		difficulty.setMinimum(1);
		difficulty.setValue(1);

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
}
