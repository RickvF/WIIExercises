import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

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

public class WiiMoteTracker implements WiimoteListener
{
	private ArrayList<Integer> simon = new ArrayList<>();
	private ArrayList<Color> colors = new ArrayList<>();
	private short lastPressed;
	private int counter = 0 ;
	Simon_GUI s;
	
	Timer timer;
	
	Wiimote wiimote;
	Wiimote wiimote2;
	
	boolean changeColor = false;
	
	public WiiMoteTracker(Simon_GUI s)
	{
		colors.add(Color.RED);
		colors.add(Color.BLUE);
		colors.add(Color.YELLOW);
		colors.add(Color.GREEN);
		
		this.s = s;
		
		Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(2, true);
//		if(wiimotes != null)
//		{
//			wiimote = wiimotes[0];
//			wiimote.setLeds(false, true, true, true);
//			wiimote.activateIRTRacking();
//			wiimote.addWiiMoteEventListeners(this);
//	
//			// Set IR sensivity stuff
//			wiimote.setIrSensitivity(0);
//			wiimote.setIrSensitivity(3);
//		}
		if(wiimotes.length > 1)
		{
			wiimote = wiimotes[0];
			wiimote.setLeds(false, true, true, true);
			wiimote.activateIRTRacking();
			wiimote.addWiiMoteEventListeners(this);
	
			// Set IR sensivity stuff
			wiimote.setIrSensitivity(0);
			wiimote.setIrSensitivity(3);
			
				
			wiimote2 = wiimotes[1];
			wiimote2.setLeds(true, true, true, true);
			wiimote2.activateIRTRacking();
			wiimote2.addWiiMoteEventListeners(this);
	
			// Set IR sensivity stuff
			wiimote2.setIrSensitivity(0);
			wiimote2.setIrSensitivity(3);
		}
		
		ActionListener update = new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				changeColor = true;
			}
			
		};
		
		timer = new Timer(1000, update);
	}
	public ArrayList<Color> getColors()
	{
		return colors;
	}
	//Starts the color sequence which has to be repeated 
	public void startSimon()
	{
		
		for(int i = 0 ; i<simon.size(); i++)
		{
			System.out.println(simon.get(i));
			int q = simon.get(i) %4;
			Color c = colors.get(q);
			colors.set(q, Color.BLACK);
			//s.repaint();
			timer.start();
			while(!changeColor)
			{
				System.out.print(changeColor);
				
			}
			timer.stop();			
			changeColor = false;
			colors.set(q,c);
			//s.repaint();
		}
	}
	
	public int getScore()
	{
		if(simon.size() > 0)
		{
			return simon.size() -1;
		}
		return 0;
	}
	
	public void checkSimon(int button)
	{
		if(button == simon.get(counter))
		{
			
			int q = simon.get(counter) % 4;
			Color c = colors.get(q);
			colors.set(q, Color.PINK);
			timer.start();
			while(!changeColor)
			{
				System.out.print(changeColor);
				
			}
			timer.stop();			
			changeColor = false;
			colors.set(q,c);
			
			counter++;
		}
		else
		{
			wiimote.activateRumble();
			wiimote2.activateRumble();
			timer.start();
			while(!changeColor)
			{
				System.out.print(changeColor);
				
			}
			timer.stop();			
			changeColor = false;
			wiimote.deactivateRumble();
			wiimote2.deactivateRumble();
			
			simon.clear();
			counter = 0;
		}
	}
	@Override
	public void onButtonsEvent(WiimoteButtonsEvent arg0)
	{
	

			if(arg0.isButtonOnePressed()&& lastPressed != arg0.getButtonsJustPressed())
			{ 
				if(counter != simon.size() )
				{
					wiimote.activateRumble();
					wiimote2.activateRumble();
					timer.start();
					while(!changeColor)
					{
						System.out.print(changeColor);
						
					}
					timer.stop();			
					changeColor = false;
					wiimote.deactivateRumble();
					wiimote2.deactivateRumble();
					
					simon.clear();
				}
				else{
				simon.add((int)(Math.random()*4));
				startSimon();}
				counter = 0;
			}
	
		if(arg0.isButtonUpPressed())
		{
			checkSimon(0);
		}
		if(arg0.isButtonDownPressed())
		{
			checkSimon(2);
		}
		if(arg0.isButtonLeftPressed())
		{
			checkSimon(1);
		}
		if(arg0.isButtonRightPressed())
		{
			checkSimon(3);
		}
		if(arg0.isButtonAPressed() && arg0.isButtonBPressed())
		{
			System.exit(0);
		}
		lastPressed = arg0.getButtonsJustPressed();	

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
		if(arg0 instanceof NunchukEvent)
		{
			NunchukEvent nc = (NunchukEvent) arg0;
		//	System.out.println(nc);
			if(nc.getButtonsEvent().isButtonCPressed())
			{
				System.out.println("C pressed");
			}
			if(nc.getButtonsEvent().isButtonZJustPressed())
			{
				System.out.println("hallo malou");
			}
		}
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent arg0)
	{
		System.out.println("Insert Nunchuk");
		
	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent arg0)
	{
		System.out.println("Removed Nunchuk");
		
	}

	@Override
	public void onStatusEvent(StatusEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
