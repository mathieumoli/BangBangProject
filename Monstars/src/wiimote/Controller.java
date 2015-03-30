package wiimote;

import javax.swing.event.EventListenerList;

public abstract class Controller {
	
	private EventListenerList listeners;
	protected Direction direction;
	private boolean closed;

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

	public void fireMovementConfirmed() {
		ControllerListener[] controllerList = (ControllerListener[]) listeners
				.getListeners(ControllerListener.class);
		
		if (controllerList == null)
			return;

		for (ControllerListener listener : controllerList) {
			listener.movementConfirmed(new MovementEvent(this, direction));
		}
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public abstract void close();
	
}