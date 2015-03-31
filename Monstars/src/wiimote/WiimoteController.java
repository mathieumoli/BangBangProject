package wiimote;

import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.*;
import wiiusej.wiiusejevents.wiiuseapievents.*;
import wiiusej.wiiusejevents.utils.WiimoteListener;

public class WiimoteController extends Controller implements WiimoteListener {

	private Wiimote wiimote;
	
	public WiimoteController(Wiimote wiimote) {
		this.wiimote = wiimote;
		wiimote.activateMotionSensing();
		wiimote.addWiiMoteEventListeners(this);
		direction = Direction.NONE;
	}
	
	@Override
	public void onButtonsEvent(WiimoteButtonsEvent arg0) {
		
		if (arg0.isButtonBPressed()) {
			
			if (this.direction == Direction.ASIDE) {
				
				System.out.println(arg0.getButtonsJustPressed());
				fireShootEvent();
			}
			
		}
		if( arg0.isButtonAPressed()){
			fireButtonAEvent();
		}

	}

	@Override
	public void onClassicControllerInsertedEvent(
			ClassicControllerInsertedEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onClassicControllerRemovedEvent(
			ClassicControllerRemovedEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onDisconnectionEvent(DisconnectionEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onExpansionEvent(ExpansionEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onIrEvent(IREvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent arg0) {
		if (arg0.getOrientation().getAPitch() > 15 && arg0.getOrientation().getAPitch() < 120) {
			direction = Direction.DOWN;
			fireMovementConfirmed();
		} else if (arg0.getOrientation().getAPitch() < -15 && arg0.getOrientation().getAPitch() > -140) {
			direction = Direction.UP;
			fireMovementConfirmed();
		} else {
			direction = Direction.ASIDE;
			fireMovementConfirmed();
		}
	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusEvent(StatusEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	/*
	 * For disconnect the wiimote
	 */
	public void close() {
		wiimote.disconnect();
	}
	
}
