package bangbangproject;

import devintAPI.MenuAbstrait;
import jeu.FenetreSimple;
import jeu.Jeu;


public class MenuPrincipal extends MenuAbstrait{

	/** constructeur
	 * @param title : le nom du jeu 
	 */
	public MenuPrincipal() {
		super("Bang Bang Project");
	}

	/** renvoie le nom des options du menu
     * vous pouvez définir autant d'options que vous voulez
     **/
	protected String[] nomOptions() {
		String[] noms = {"Duel","Sound Shot","Crédits","Aide","Quitter"};
		return noms;
	}

	/** lance l'action associée au bouton
	 * la numérotation est celle du tableau renvoyé par nomOption
	 */
	protected void lancerOption(int i) {
		switch (i){  
		case 0 : new OptionDuel();break;
		case 1 : new OptionSoundShot();break;
		case 2 : new Credits("Bang Bang Project Crédits");break;
		case 3 : new Aide("Bang Bang Project Aide");break;
		case 5 : System.exit(0);
		default: System.err.println("action non définie");
		}
	} 

	// renvoie le fichier wave contenant le message d'accueil
	// ces fichiers doivent être placés dans ressources/sons/
	protected  String wavAccueil() {
		return "../ressources/sons/accueil.wav";
	}

	// renvoie le fichier wave contenant la règle du jeu
	protected  String wavRegleJeu() {
		return "../ressources/sons/accueil.wav";
	}

}
