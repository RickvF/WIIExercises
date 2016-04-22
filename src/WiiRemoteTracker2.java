import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.utils.WiimoteListener;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.DisconnectionEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.StatusEvent;

public class WiiRemoteTracker2 implements WiimoteListener
{
	public static void main(String[] args)
	{
		new WiiRemoteTracker2();
	}
	
	Wiimote wiimote;
	public WiiRemoteTracker2()
	{
			
		Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(1, true);
		if(wiimotes != null)
		{
			wiimote = wiimotes[0];
			wiimote.setLeds(false, true, true, true);
			wiimote.activateIRTRacking();
			wiimote.addWiiMoteEventListeners(this);
			wiimote.activateMotionSensing();
			wiimote.activateContinuous();
			// Set IR sensivity stuff
			wiimote.setIrSensitivity(0);
			wiimote.setIrSensitivity(3);
		}
	}	
	
	@Override
	public void onButtonsEvent(WiimoteButtonsEvent arg0)
	{
	
	}

	@Override
	public void onClassicControllerInsertedEvent(ClassicControllerInsertedEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClassicControllerRemovedEvent(ClassicControllerRemovedEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisconnectionEvent(DisconnectionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExpansionEvent(ExpansionEvent arg0)
	{		
		
		
	}

	@Override
	public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onIrEvent(IREvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent arg0)
	{
		System.out.println(arg0.getGforce()) ;
		System.out.println(arg0.getOrientation()) ;
		System.out.println(arg0.getRawAcceleration()) ;
	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent arg0)
	{
		System.out.println("Nunchuck insert");

	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent arg0)
	{
		System.out.println("Nunchuck removed");
	}

	@Override
	public void onStatusEvent(StatusEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
