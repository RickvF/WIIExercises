package Opdracht3;
import javax.swing.JOptionPane;

import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.NunchukEvent;
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
/**
 *
 */
public class WiiMoteTracker2 implements WiimoteListener
{
	private Wiimote[] wiimotes;
	private int nunchukX;
	private int nunchukY;
	private int nunchukRoll;
	private boolean nunchukConnected = false;
	public WiiMoteTracker2()
	{
		//Searching for WiiRemotes
		 wiimotes = WiiUseApiManager.getWiimotes(1, true);
		//checking if there are wii remotes connected. If so set led 2,3,4 on
		//else exit program
		 int wiimoteNumber = 0;
		for(Wiimote wiimote: wiimotes)
		{
			switch (wiimoteNumber) {
			case 0:
				wiimote.setLeds(true, false, false, false);
				break;
			case 1:
				wiimote.setLeds(false, true,  false, false);
				break;
			case 2:
				wiimote.setLeds(false, false, true, false);
				break;
			case 3:
				wiimote.setLeds( false, false, false, true);
				break;
			}
			wiimoteNumber++;
			wiimote.addWiiMoteEventListeners(this);
		}
		
		if (wiimotes == null)
		{
			System.out.println("No Wii Remotes found");
			System.exit(0);
		}
		
		
	}
	public int getNunchukX()
	{
		return nunchukX;
	}
	public int getNunchukY()
	{
		return nunchukY;
	}
	
	public int getNunchukRoll()
	{
		return nunchukRoll;
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
		if(nunchukConnected){
		if(arg0 instanceof NunchukEvent)
		{
			NunchukEvent nc = (NunchukEvent) arg0;
			if(nc.isThereNunchukJoystickEvent())
			{
				double cos = Math.cos(Math.toRadians(nc.getNunchukJoystickEvent().getAngle()));
				double sin = Math.sin(Math.toRadians(nc.getNunchukJoystickEvent().getAngle()));
				double mag = nc.getNunchukJoystickEvent().getMagnitude();
				cos = Math.round((cos*mag)*100+240);
				sin = Math.round((sin*mag)*100+240);
				nunchukX = (int)(cos);
				nunchukY = (int)(sin);
			}
			if(nc.isThereMotionSensingEvent())
			{
			nunchukRoll = (int) nc.getNunchukMotionSensingEvent().getOrientation().getARoll();
			}
			if(nc.getButtonsEvent().isButtonCJustPressed())
			{
				JOptionPane.showMessageDialog(null, "Button C pressed");	

			}
			if(nc.getButtonsEvent().isButtonZJustPressed())
			{
				JOptionPane.showMessageDialog(null, "Button Z pressed");
			}
		}
		}
		
	}

	@Override
	public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent arg0)
	{
		
		
	}

	@Override
	public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent arg0)
	{
		

		
	}

	@Override
	public void onIrEvent(IREvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent arg0)
	{
		nunchukConnected = true;
	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent arg0)
	{		
		nunchukConnected = false;
	}

	@Override
	public void onStatusEvent(StatusEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
