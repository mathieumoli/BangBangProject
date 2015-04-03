package wiimote;

import java.util.EventObject;

/**
 * @author Eroyas
 *
 */

public class MovementEvent extends EventObject {
	private Direction direction;
	
	public MovementEvent(Object source, Direction direction) {
		super(source);
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
}