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
	private int currentSimon = 0;
	private Color changedColor;
	private Simon_GUI s;	
	private Timer timer;	
	private Wiimote wiimote;	
	boolean changeColor = false;
	private int q;
	
	public WiiMoteTracker(Simon_GUI s)
	{
		colors.add(Color.RED);
		colors.add(Color.BLUE);
		colors.add(Color.YELLOW);
		colors.add(Color.GREEN);
		
		this.s = s;
		
		Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(2, true);
		if(wiimotes != null)
		{
			wiimote = wiimotes[0];
			wiimote.setLeds(false, true, true, true);
			wiimote.addWiiMoteEventListeners(this);
		}	
		ActionListener update = new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				timer.stop();
				System.out.println("Timer Tick");

				changeColorBack();
			}
			
		};
		
		timer = new Timer(500, update);
	}
	public ArrayList<Color> getColors()
	{
		return colors;
	}
	 
	public void startSimon()
	{
		System.out.println("C"+currentSimon);
		System.out.println("S"+ simon.size());
		System.out.println("CS "+ simon.get(currentSimon));
		 q = simon.get(currentSimon)%4 ;
		changedColor = colors.get(q);
		colors.set(q, Color.BLACK);
		timer.start();
	}
	
	public int getScore()
	{
		if(simon.size() > 0)
		{
			return simon.size() -1;
		}
		return 0;
	}
	public void changeColorBack()
	{
		System.out.println("CB");
		wiimote.deactivateRumble();
		colors.set(q,changedColor);
		if( simon.size()-1 > currentSimon)
		{
			
				currentSimon++;
				startSimon();
			
			
		}
	}
	
	public void checkSimon(int button)
	{
		try{
		if(button == simon.get(counter))
		{
			
			q = simon.get(counter) % 4;
			changedColor = colors.get(q);
			colors.set(q, Color.PINK);
			timer.start();
			counter++;
		}
		
		
		else
		{
			wiimote.activateRumble();
			timer.start();
			simon.clear();
			counter = 0;
			currentSimon =0;
		}}
		catch(Exception e)
		{
			wiimote.activateRumble();
			timer.start();
			simon.clear();
			counter = 0;
			currentSimon =0;
			
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
					timer.start();
					simon.clear();
				}
				else{
				simon.add((int)(Math.random()*4));
				currentSimon =0;
				startSimon();
				}
				
				counter = 0;
			}
			
	if(!timer.isRunning()){
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
