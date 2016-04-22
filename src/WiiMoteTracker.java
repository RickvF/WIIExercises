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
/**
 * Simon Says (Final Version)
 * @author Sascha Worms
 * @author Rick van Fessem
 * @version Final 22-04-2016
 * @category games
 * Description : Simple Simon Says game that uses four colored fields which will light up. 
 * The user then has to mimick these moves using the buttons on the wiiRemote.
 * 
 * This Class Contains the actual Game
 */
public class WiiMoteTracker implements WiimoteListener
{
	private ArrayList<Integer> simon = new ArrayList<>();	//Contains the moves the user has to mimic
	private ArrayList<Color> colors = new ArrayList<>();	//Contains the possible colors for the buttons
	private short lastPressed; 								//The button which was last pressed on the remote. Used to check for double running a round
	private int pressCounter = 0 ;							//Counter for the amount of buttons pressed
	private int currentSimon = 0;							//The number of the current Field that has to light up, ranged 0-3
	private Color changedColor;								//The old color from the changed field
	private Timer timer;									//Timer which contains the amount of time to wait before changing the color back to the original one
	private Wiimote wiimote;								//Wiiremote which is used
	private int changedColorLocation;						//Location of the changedColor in the ArrayList simon.
	
	public WiiMoteTracker()
	{
		//adds button colors to array
		colors.add(Color.RED);
		colors.add(Color.BLUE);
		colors.add(Color.YELLOW);
		colors.add(Color.GREEN);
		
		//Searching for WiiRemotes
		Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(1, true);
		//checking if there are wii remotes connected. If so set led 2,3,4 on
		//else exit program
		if(wiimotes != null)
		{
			wiimote = wiimotes[0];
			wiimote.setLeds(false, true, true, true);
			wiimote.addWiiMoteEventListeners(this);
		}
		else
		{
			System.out.println("No Wii Remotes found");
			System.exit(0);
		}
		//Timer which on tick changes the color of the rectangle back to the original one.
		ActionListener update = new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				timer.stop();
				changeColorBack();
			}
			
		};
		//time the color stays changed
		timer = new Timer(500, update);
	}
	 //return ArrayList<Color> colors with all the current colors.
	public ArrayList<Color> getColors()
	{
		return colors;
	}
	
	//Changing Color of current rectangle 
	public void startSimon()
	{
		changedColorLocation = simon.get(currentSimon)%4 ;
		changedColor = colors.get(changedColorLocation);
		colors.set(changedColorLocation, Color.BLACK);
		timer.start();
	}
	//@returns int score - > amount of rounds completed
	public int getScore()
	{
		if(simon.size() > 0)
		{
			return simon.size() -1;
		}
		return 0;
	}
	//Changes color back to the original one
	//Also stops timer and rumble if necesairy
	public void changeColorBack()
	{
		wiimote.deactivateRumble();
		colors.set(changedColorLocation,changedColor);
		if( simon.size()-1 > currentSimon)
		{
				currentSimon++;
				startSimon();
		}
	}
	//checks if the button pressed is the same as the one in the simon arraylist of the current round
	//rumbles and ends round if wrong
	public void checkSimon(int button)
	{
		try{
		if(button == simon.get(pressCounter))
		{
			changedColorLocation = simon.get(pressCounter) % 4;
			changedColor = colors.get(changedColorLocation);
			colors.set(changedColorLocation, Color.PINK);
			timer.start();
			pressCounter++;
		}
		else
		{
			wiimote.activateRumble();
			timer.start();
			simon.clear();
			pressCounter = 0;
			currentSimon =0;
		}}
		catch(Exception e)
		{
			wiimote.activateRumble();
			timer.start();
			simon.clear();
			pressCounter = 0;
			currentSimon =0;
		}
	}
	//Handles the buttons pressed on the wii remote.
	
	@Override
	public void onButtonsEvent(WiimoteButtonsEvent arg0)
		{
			//Starts a new round if the current round is completed and the last button wasn't 1
			if(arg0.isButtonOnePressed()&& lastPressed != arg0.getButtonsJustPressed())
			{ 
				if(pressCounter != simon.size() )
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
				pressCounter = 0;
			}
	//checks if timer is running. If not running start Comparing of buttons to simon. 
	if(!timer.isRunning()){
		if(arg0.isButtonUpPressed())
		{
			checkSimon(0);
		}
		else if(arg0.isButtonDownPressed())
		{
			checkSimon(2);
		}
		else if(arg0.isButtonLeftPressed())
		{
			checkSimon(1);
		}
		else if(arg0.isButtonRightPressed())
		{
			checkSimon(3);
		}
		else
		{
			checkSimon(-1);			
		}
	}
	//exits game if A&B are pressed together.
	if(arg0.isButtonAPressed() && arg0.isButtonBPressed())
	{
		wiimote.deactivateRumble();
		System.exit(0);
	}
	//registers lastButtonPressed
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
