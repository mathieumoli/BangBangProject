package bangbangproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import devintAPI.FenetreAbstraite;
import devintAPI.Preferences;

public class Credits extends FenetreAbstraite {
	private JLabel titre;
	private JTextArea desCred;
	private String title;
	
	public Credits(String title) {
		super(title);
		this.title = title;
		
		init();

	}

	@Override
	protected void init() {
		JPanel ti= new JPanel();
		titre=new JLabel();
		titre.setText(title);
		ti.add(titre);
		ti.setBackground(Color.red);
		desCred=new JTextArea();
		desCred.setText("Ce jeu a été développé dans le cadre des projets DeViNT en SI3 à Polytech'Nice Sophia Antipolis par Yasin EROGLU, Mathieu MOLINENGO, Simon PARIS et Fabien VICENTE.\n"
				+ "\nLe projet a été encadré par Monsieur Jean Paul Stromboni");

		desCred.setLineWrap(true);
		desCred.setWrapStyleWord(true);
		desCred.setEditable(false);
		desCred.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		// met la police par défaut de DeViNT
    	titre.setFont(new Font("Arial",Font.BOLD,Preferences.LARGE_SIZE));
    	titre.setHorizontalAlignment(0);
    	desCred.setFont(new Font("Arial",Font.BOLD,Preferences.SMALL_SIZE));
    	
    	JPanel milieu=new JPanel();
    	GridLayout gridL=new GridLayout(2,2);
    	milieu.setLayout(gridL);
    	
    	//LOGOS
    	ImageIcon imageUns =new ImageIcon (new ImageIcon("../ressources/images/logo_uns.png").getImage().getScaledInstance(350, 235, Image.SCALE_DEFAULT));
    	ImageIcon imageDevint = new ImageIcon("../ressources/images/logo_devint.gif");
    	ImageIcon imagePoly = new ImageIcon (new ImageIcon("../ressources/images/logo_polytech.jpeg").getImage().getScaledInstance(650, 200, Image.SCALE_DEFAULT));
    	JLabel uns = new JLabel(imageUns);
    	JLabel devint = new JLabel(imageDevint);
    	JLabel poly = new JLabel(imagePoly);

    	milieu.add(desCred);
    	milieu.add(uns);
    	milieu.add(poly);
    	milieu.add(devint);
    	milieu.setBackground(Color.WHITE);
    	this.add(ti,BorderLayout.NORTH);
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
		desCred.setBackground(pref.getCurrentBackgroundColor());
		desCred.setForeground(pref.getCurrentForegroundColor());
	}
	
	/**
	 * Pour modifier la police des textes à chaque fois que l'on presse F4 
	 */
	public void changeSize(){
		Font f = Preferences.getData().getCurrentFont();
		titre.setFont(f);
		desCred.setFont(f);
		
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
