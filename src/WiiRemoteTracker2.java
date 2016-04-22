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
<<<<<<< HEAD
	Wiimote wiimote;
	
=======
	public static void main(String[] args)
	{
		new WiiRemoteTracker2();
	}
	
	Wiimote wiimote;
>>>>>>> ccef0f718227f9427b5845e626004ab9489c5f2d
	public WiiRemoteTracker2()
	{
			
		Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(2, true);
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
<<<<<<< HEAD
//		System.out.println("X: " + arg0.getRawAcceleration().getX());
//		System.out.println("Y: " + arg0.getRawAcceleration().getY());
//		System.out.println("Z: " + arg0.getRawAcceleration().getZ());
//		System.out.println(arg0.toString());
		
=======
		System.out.println(arg0.getGforce()) ;
		System.out.println(arg0.getOrientation()) ;
		System.out.println(arg0.getRawAcceleration()) ;
>>>>>>> ccef0f718227f9427b5845e626004ab9489c5f2d
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
