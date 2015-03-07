package bangbangproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import devintAPI.FenetreAbstraite;
import devintAPI.Preferences;

public class Aide extends FenetreAbstraite {
	private JLabel titre;
	private JTextArea desAide;
	private String title;

	public Aide(String title) {
		super(title);
		this.title = title;
		
		init();

	}

	@Override
	protected void init() {
		titre=new JLabel();
		titre.setText(title);
		desAide=new JTextArea();
		desAide.setText("\nExplication du jeu\n\nLe principe du jeu est d'appuyer le plus rapidement sur le bouton B de la Wiimote en la levant après que le son ait retenti. Veuillez ne pas oublier de mettre la dragonne pour votre sécurité et celle des autres.\n\nMode Duel : Le son retentissant est un BONG classique.\n\nMode Sound Shot : Au début du jeu, il vous sera indiqué le son après lequel vous devez dégainer. Vous ne gagnerez le point que si vous dégainez avant l'autre joueur et après le son.\n\nDans les deux modes, si vous tirez avant le son, vous devrez attendre un certains temps , un vrai cowboy doit recharger son revolver. La partie s'arrête après que le nombre de round choisi soit effectué. Puis le vainqueur, celui qui aura atteint le nombre de round correspondant, sera annoncé.");

		desAide.setLineWrap(true);
		desAide.setWrapStyleWord(true);
		desAide.setEditable(false);
		
		//createAideFont();
		// met la police par défaut de DeViNT
    	titre.setFont(new Font("Arial",Font.BOLD,Preferences.LARGE_SIZE));
    	titre.setHorizontalAlignment(0);
    	desAide.setFont(new Font("Arial",Font.BOLD,Preferences.SMALL_SIZE));
    	
    	JPanel milieu=new JPanel();
    	milieu.setBackground(Color.red);
    	BorderLayout border=new BorderLayout();
    	milieu.setLayout(border);
    	
    	//Panel dans milieu
    	JPanel mil=new JPanel();
    	mil.setLayout(new BorderLayout());
    	
    	//Wiimote photos
    	//image
    	ImageIcon imagePict2 = new ImageIcon("../ressources/images/wiimote.jpg");
    	ImageIcon imagePict = new ImageIcon("../ressources/images/wiimote.jpg");
    	JLabel wiimote = new JLabel(imagePict);
    	JLabel wiimote2 = new JLabel(imagePict2);

    	JPanel wii=new JPanel();
    	wii.setBackground(Color.WHITE);
    	wii.setLayout(new GridLayout(1,2));
    	wii.add(wiimote);
    	wii.add(wiimote2);
    	
    	mil.add(desAide,BorderLayout.CENTER);
    	mil.add(wii,BorderLayout.EAST);
		milieu.add(titre,BorderLayout.NORTH);
		milieu.add(mil,BorderLayout.CENTER);
		this.add(milieu,BorderLayout.CENTER);

	}

	/**
	 * Pour modifier les couleurs de fond et de premier plan
	 * Cette fonction est appelée par la fonction "changeColor" de la classe "devintAPI.Preferences"
	 * à chaque fois que l'on presse F3 
	 * 
	 * ici on décide que le changement des couleurs s'applique sur le label lbl1
	 **/
	public  void changeColor() {
    	// on récupère les couleurs de base dans la classe Preferences 
		Preferences pref = Preferences.getData();
		titre.setBackground(pref.getCurrentBackgroundColor());
		titre.setForeground(pref.getCurrentForegroundColor());
		desAide.setBackground(pref.getCurrentBackgroundColor());
		desAide.setForeground(pref.getCurrentForegroundColor());
	}
	
	/**
	 * Pour modifier la police des textes à chaque fois que l'on presse F4 
	 */
	public void changeSize(){
		Font f = Preferences.getData().getCurrentFont();
		titre.setFont(f);
		desAide.setFont(f);
		
	}

	// renvoie le fichier wave contenant le message d'accueil
	protected  String wavAccueil() {
		//TODO ajouter nos sons
		return "../ressources/sons/accueilSimple.wav";
	}
	
	// renvoie le fichier wave contenant la règle du jeu
	protected  String wavRegleJeu() {
		//TODO ajouter nos sons
		return "../ressources/sons/aideF1.wav";
	}
	
	// renvoie le fichier wave contenant la règle du jeu
	protected  String wavAide() {
		//TODO ajouter nos sons
		return "../ressources/sons/aide.wav";
	}
}
