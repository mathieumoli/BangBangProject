package jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import t2s.SIVOXDevint;
import devintAPI.FenetreAbstraite;
import devintAPI.Preferences;

import java.awt.event.*;

public class BlahFenetre extends FenetreAbstraite implements KeyListener {

	private JTextArea lb1;
	private JButton jbutton;

	public BlahFenetre(String title) {
		super(title);
	}

	@Override
	protected void init() {
		String text = "FENETRE BLAH\n\n";
		text += "Ceci est une fenêtre BLAH, ne paniquez pas.";
		lb1 = new JTextArea(text);
		lb1.setLineWrap(true);
		lb1.setEditable(false);
		this.add(lb1, BorderLayout.CENTER);
		
		jbutton = new JButton("Lis un texte");
		this.add(jbutton, BorderLayout.SOUTH);
		jbutton.addActionListener(new ButtonActionListener(voix));
		jbutton.setBorder(new LineBorder(Color.BLACK, 10));
		jbutton.setFocusable(false);
		
		changeColor();
		changeSize();
		addKeyListener(this);
	}

	@Override
	public void changeColor() {
		Preferences pref = Preferences.getData();
		lb1.setBackground(pref.getCurrentBackgroundColor());
		lb1.setForeground(pref.getCurrentForegroundColor());
		jbutton.setBackground(pref.getCurrentBackgroundColor());
		jbutton.setForeground(pref.getCurrentForegroundColor());
	}

	@Override
	public void changeSize() {
		Font f = Preferences.getData().getCurrentFont();
		lb1.setFont(f);
		jbutton.setFont(f);
	}

	// renvoie le fichier wave contenant le message d'accueil
	protected String wavAccueil() {
		return "../ressources/sons/accueil.wav";
	}

	// renvoie le fichier wave contenant la règle du jeu
	protected String wavRegleJeu() {
		return "../ressources/sons/aideF1.wav";
	}

	// renvoie le fichier wave contenant la règle du jeu
	protected String wavAide() {
		return "../ressources/sons/aide.wav";
	}

	// évènements clavier
	@Override
	public void keyPressed(KeyEvent e) {
		voix.stop();
		// appel à la méthode mère qui gère les évènements ESC, F1, F3, F4
		super.keyPressed(e);
		// cas particulier pour ce jeu : la touche F5
		if (e.getKeyCode() == KeyEvent.VK_F5) {
			voix.playText("Vé cé esse pret !");
		}
	}

}

class ButtonActionListener implements ActionListener {
	SIVOXDevint voix;

	public ButtonActionListener(SIVOXDevint voix) {
		this.voix = voix;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		voix.stop();
		voix.playText("Ceci est text");
	}
}
