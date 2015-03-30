package wiimote;

import java.util.EventListener;

public interface ControllerListener extends EventListener {
	public void movementConfirmed(MovementEvent event);
}
