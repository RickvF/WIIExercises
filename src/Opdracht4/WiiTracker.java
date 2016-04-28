package Opdracht4;
import java.util.ArrayList;

import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;
import wiiusej.values.IRSource;
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
/**
 *
 */
public class WiiTracker implements WiimoteListener
{
	private Wiimote[] wiimotes;
	private ArrayList<Integer> xCoordinaten;
	private ArrayList<Integer> yCoordinaten;
	public WiiTracker()
	{
		//Searching for WiiRemotes
		 wiimotes = WiiUseApiManager.getWiimotes(4, true);
		//checking if there are wii remotes connected. If so set led 2,3,4 on
		//else exit program
		int wiimoteNumber = 0;
		xCoordinaten = new ArrayList<Integer>();
		yCoordinaten = new ArrayList<Integer>();
		for(Wiimote wiimote: wiimotes)
		{
			switch (wiimoteNumber) {
			case 0:
				wiimote.setLeds(true, false, false, false);
				wiimote.activateIRTRacking();
				wiimote.setSensorBarBelowScreen();
				wiimote.setVirtualResolution(500, 500);
				wiimote.setIrSensitivity(2);
				
				break;
			case 1:
				wiimote.setLeds(false, true,  false, false);
				wiimote.activateIRTRacking();
				wiimote.setSensorBarBelowScreen();
				wiimote.setVirtualResolution(500, 500);
				wiimote.setIrSensitivity(2);
				
				break;
			case 2:
				wiimote.setLeds(false, false, true, false);
				wiimote.activateIRTRacking();
				wiimote.setSensorBarBelowScreen();
				wiimote.setVirtualResolution(500, 500);
				wiimote.setIrSensitivity(2);
				
				break;
			case 3:
				wiimote.setLeds( false, false, false, true);
				wiimote.activateIRTRacking();
				wiimote.setSensorBarBelowScreen();
				wiimote.setVirtualResolution(500, 500);
				wiimote.setIrSensitivity(2);
				
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
	public ArrayList xCoordinaten()
	{
		return xCoordinaten;
	}
	
	public ArrayList yCoordinaten()
	{
		return yCoordinaten;
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
		
		
	}

	@Override
	public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent arg0)
	{
		

		
	}

	@Override
	public void onIrEvent(IREvent arg0)
	{
		xCoordinaten.clear();
		yCoordinaten.clear();
		if(arg0.getIRPoints().length>0){
			for(IRSource point : arg0.getIRPoints()){
				xCoordinaten.add((int)(point.getRx()-523)/2);
				yCoordinaten.add((int)(point.getRy()-523)/2);
			}
		}
		
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent arg0)
	{
	
	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent arg0)
	{		
	
	}

	@Override
	public void onStatusEvent(StatusEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
