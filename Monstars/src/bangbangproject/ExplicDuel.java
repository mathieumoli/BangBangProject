package bangbangproject;

import javax.swing.JTextArea;

public class ExplicDuel extends Aide {

	public ExplicDuel() {
		super("Bang Bang Explication Duel");
		desAide = new JTextArea();
		desAide.setText("\nExplication du jeu\n\nLe principe du jeu est d'appuyer le plus rapidement sur le bouton B de la Wiimote en la levant après que le son ait retenti."
				+ " Veuillez ne pas oublier de mettre la dragonne pour votre sécurité et celle des autres.\n\n"
				+ "Mode Duel : Le son retentissant est un BONG classique.\n\n"
				+ "Si vous tirez avant le son, vous devrez attendre un certains temps , un vrai cowboy doit recharger son revolver. La partie s'arrête après que le nombre de round choisi soit effectué. "
				+ "Puis le vainqueur, celui qui aura atteint le nombre de round correspondant, sera annoncé."
				+ "Lorsque vous êtes prêt à jouer, appuyez sur le bouton A de la Wiimote"
				+ "La prochaine fois pour ne pas écouter ce message et passer directement au jeu appuyez sur le bouton 1");

		init();
	}
}
