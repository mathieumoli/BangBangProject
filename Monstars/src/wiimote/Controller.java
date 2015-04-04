package wiimote;

import javax.swing.event.EventListenerList;

/**
 * @author Eroyas
 *
 */

public abstract class Controller {
	
	private EventListenerList listeners;
	protected Direction direction;

	public Controller() {
		listeners = new EventListenerList();
		this.direction = Direction.NONE;
	}
	
	public void addControllerListener(ControllerListener listener) {
		listeners.add(ControllerListener.class, listener);
	}

	public void removeControllerListerner(ControllerListener listener) {
		listeners.remove(ControllerListener.class, listener);
	}

	// Orientation event
	public void fireMovementConfirmed() {
		ControllerListener[] controllerList = (ControllerListener[]) listeners
				.getListeners(ControllerListener.class);
		
		if (controllerList == null)
			return;

		for (ControllerListener listener : controllerList) {
			listener.movementConfirmed(new MovementEvent(this, direction));
		}
	}
	
	// Shoot event
	public void fireShootEvent() {
		ControllerListener[] controllerList = (ControllerListener[]) listeners
				.getListeners(ControllerListener.class);
		
		if (controllerList == null)
			return;

		for (ControllerListener listener : controllerList) {
			listener.getShootEvent(new MovementEvent(this, direction));
		}
	}
	
	// Ready event
	public void fireButtonAEvent() {
		ControllerListener[] controllerList = (ControllerListener[]) listeners
				.getListeners(ControllerListener.class);
		
		if (controllerList == null)
			return;

		for (ControllerListener listener : controllerList) {
			listener.buttonAEvent(new MovementEvent(this, direction));
		}
	}
	
	// Menu up event
	public void fireButtonDownEvent() {
		ControllerListener[] controllerList = (ControllerListener[]) listeners
				.getListeners(ControllerListener.class);
		
		if (controllerList == null)
			return;

		for (ControllerListener listener : controllerList) {
			listener.buttonDownEvent(new MovementEvent(this, direction));
		}
	}
	
	// Menu down event
	public void fireButtonUpEvent() {
		ControllerListener[] controllerList = (ControllerListener[]) listeners
				.getListeners(ControllerListener.class);
		
		if (controllerList == null)
			return;

		for (ControllerListener listener : controllerList) {
			listener.buttonUpEvent(new MovementEvent(this, direction));
		}
	}
	
	// Menu plus event
	public void fireButtonPlusEvent() {
		ControllerListener[] controllerList = (ControllerListener[]) listeners
				.getListeners(ControllerListener.class);
		
		if (controllerList == null)
			return;

		for (ControllerListener listener : controllerList) {
			listener.buttonPlusEvent(new MovementEvent(this, direction));
		}
	}
	
	// Menu minus event
	public void fireButtonMinusEvent() {
		ControllerListener[] controllerList = (ControllerListener[]) listeners
				.getListeners(ControllerListener.class);
		
		if (controllerList == null)
			return;

		for (ControllerListener listener : controllerList) {
			listener.buttonMinusEvent(new MovementEvent(this, direction));
		}
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public abstract void close();
	
}