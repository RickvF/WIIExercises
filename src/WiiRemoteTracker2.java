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
	private Wiimote wiimote;
	short X;
	short Z;
	short Y;
	float gfY;
	public WiiRemoteTracker2()
	{
			
		Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(2, true);
		if(wiimotes != null)
		{
			wiimote = wiimotes[0];
			wiimote.setLeds(false, true, true, true);
			wiimote.activateIRTRacking();
			wiimote.addWiiMoteEventListeners(this);
	
			// Set IR sensivity stuff
			wiimote.setIrSensitivity(0);
			wiimote.setIrSensitivity(3);
			wiimote.activateMotionSensing();
			
//			wiimote.setAccelerationThreshold(-5);
//			wiimote.activateSmoothing();
//			wiimote.setAlphaSmoothingValue(3.0f);
		}
	}	
	
	@Override
	public void onButtonsEvent(WiimoteButtonsEvent arg0)
	{
	
	}

	@Override
	public void onClassicControllerInsertedEvent(ClassicControllerInsertedEvent arg0)
	{
		
		
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
		
		X = arg0.getRawAcceleration().getX();
		Z = arg0.getRawAcceleration().getZ();
		Y = arg0.getRawAcceleration().getY();
		gfY = arg0.getGforce().getZ();
		
	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent arg0)
	{
		System.out.println("Nunchuck insert!!!!");
	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent arg0)
	{
		System.out.println("Nunchuck removed!!!!!!!!!!");
	}

	@Override
	public void onStatusEvent(StatusEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	public short getX()
	{
		return X;
	}
	
	public short getZ()
	{
		return Z;
	}
	public short getY()
	{
		return Y;
	}
	
	public float getgfY()
	{
		return gfY;
	}
}