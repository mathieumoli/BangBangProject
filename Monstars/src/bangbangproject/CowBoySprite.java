package bangbangproject;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CowBoySprite extends JLabel {
	public static int LEFT = -1;
	public static int RIGHT = 1;
	private ImageIcon icon;
	/**
	 * Initializes the cowboy sprite
	 * @param orientation CowBoySprite.LEFT for left CowBoySprite.RIGHT for right
	 */
	public CowBoySprite(int orientation){
		icon = new ImageIcon("cowboy_idle.png");
		setIcon(icon);
	}
	/**
	 * Sets the cowboy in shoot state
	 */
	public void setShootState(){
		
	}
	/**
	 * Sets the cowboy in dead state
	 */
	public void setDeadState(){
		
	}
	/**
	 * Resets the cowboy for a new round
	 */
	public void resetState(){
		
	}
}
