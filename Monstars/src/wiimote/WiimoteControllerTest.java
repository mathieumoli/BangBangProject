package wiimote;

import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;

/**
 * @author Eroyas
 *
 */

public class WiimoteControllerTest implements ControllerListener {
	
	private Wiimote[] wiimotes;
	private Controller wiimote1;
	private Controller wiimote2;
	
	public WiimoteControllerTest() {
		
		// Wiimotes connection :
		// - 2 : is the number of wiimotes to connect.
		// - True : make it rumble the first time you get the wiimotes.
		wiimotes = WiiUseApiManager.getWiimotes(2, true);
		wiimote1 = new WiimoteController(wiimotes[0]);
		wiimote2 = new WiimoteController(wiimotes[1]);

		wiimote1.addControllerListener(this);
		wiimote2.addControllerListener(this);
	}
	
	/**
	 * Return the direction of the wiimotes
	 */
	@Override
	public void movementConfirmed(MovementEvent event) {
		
		if (event.getSource() == wiimote1) {
			// System.out.println("Wiimote 1 : " + event.getDirection());
		}

		if (event.getSource() == wiimote2) {
			// System.out.println("Wiimote 2 : " + event.getDirection());
		}
	}
	
	/**
	 * Return the shoot event of the wiimotes
	 */
	@Override
	public void getShootEvent(MovementEvent event) {
		
		if (event.getSource() == wiimote1) {
			System.out.println("Shoot wiimote 1 !");
		}
		
		if (event.getSource() == wiimote2) {
			System.out.println("Shoot wiimote 2 !");
		}
	}
	
	/**
	 * Return the ready event of the wiimotes
	 */
	@Override
	public void buttonAEvent(MovementEvent event) {

		if (event.getSource() == wiimote1) {
			System.out.println("Wiimote 1 ready !");
		}
		
		if (event.getSource() == wiimote2) {
			System.out.println("Wiimote 2 ready !");
		}
	}

	/**
	 * Return the up event of the wiimotes
	 */
	@Override
	public void buttonUpEvent(MovementEvent event) {
		
		if (event.getSource() == wiimote1) {
			System.out.println("Wiimote 1 : up event !");
		}
		
		if (event.getSource() == wiimote2) {
			System.out.println("Wiimote : up event !");
		}
	}
	
	/**
	 * Return the down event of the wiimotes
	 */
	@Override
	public void buttonDownEvent(MovementEvent event) {
		
		if (event.getSource() == wiimote1) {
			System.out.println("Wiimote 1 : down event !");
		}
		
		if (event.getSource() == wiimote2) {
			System.out.println("Wiimote : down event !");
		}
	}

	/**
	 * Return the plus event of the wiimotes
	 */
	@Override
	public void buttonPlusEvent(MovementEvent event) {

		if (event.getSource() == wiimote1) {
			System.out.println("Wiimote 1 : plus event !");
		}
		
		if (event.getSource() == wiimote2) {
			System.out.println("Wiimote : plus event !");
		}
	}

	/**
	 * Return the minus event of the wiimotes
	 */
	@Override
	public void buttonMinusEvent(MovementEvent event) {

		if (event.getSource() == wiimote1) {
			System.out.println("Wiimote 1 : minus event !");
		}
		
		if (event.getSource() == wiimote2) {
			System.out.println("Wiimote : minus event !");
		}
	}
	
	public static void main(String[] args) {
		
		new WiimoteControllerTest();
	}

}

/*
 * Dans le cas d'une erreur :
 * Vérifier que vous avez bien inclue le .jar pour les wiimotes :
 *  - clic droit sur votre projet puis "Build Path" puis "Add External Archives..."
 *    et ajouter le wiiusej.jar
 * Ensuite pour le bon fonctionnement des wiimotes :
 * 	- ajouter à la racine de votre projet, s'il ne sont pas déjà présent, les 4 fichiers suivant :
 * 	  - libwiiuse.so
 *    - libWiiuseJ.so
 *    - wiiuse.dll
 *    - WiiuseJ.dll
 * Tous les éléments donc vous avez besoin se trouve dans le dossier Wiimote de notre repository
 * Normalement ces deux manipulations devraient résoudre vos problèmes. Si ce n'est pas le cas :
 *  - contacter le support Monstars afin qu'ils puissent vous éclaircir !
 */