package wiimote;

import java.util.EventListener;

/**
 * @author Eroyas
 *
 */

public interface ControllerListener extends EventListener {
	
	public void movementConfirmed(MovementEvent event);

	public void getShootEvent(MovementEvent event);
	
	public void buttonAEvent(MovementEvent event);
	
	public void buttonUpEvent(MovementEvent event);
	
	public void buttonDownEvent(MovementEvent event);
	
	public void buttonPlusEvent(MovementEvent event);
	
	public void buttonMinusEvent(MovementEvent event);
	
}