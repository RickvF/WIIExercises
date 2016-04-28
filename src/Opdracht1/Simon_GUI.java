package Opdracht1;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * Simon Says (Final Version)
 * @author Sascha Worms
 * @author Rick van Fessem
 * @version Final 22-04-2016
 * @category games
 * Description : Simple Simon Says game that uses four colored fields which will light up. 
 * The user then has to mimick these moves using the buttons on the wiiRemote.
 * 
 * This Class the User Interface
 */
public class Simon_GUI extends JFrame
{
	//Creating a new Instance of the game
	public static void main(String[] args)
	{
		new Simon_GUI();
	}
	//Constructor of Simon_GUI() Creating GUI and setting default size
	public Simon_GUI()
	{
		super("Simon");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setContentPane(new Panel());
		setVisible(true);
		setSize(500, 500);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	//JPanel Class
	class Panel extends JPanel
	{
		WiiMoteTracker track; //remote with game commands
		Timer time;           //timer for updating GUI
		
		public Panel()
		{
			super();			
			track = new WiiMoteTracker();
			//repainting the user interface each timer tick
			ActionListener update = new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					repaint();
				}
				
			};
			
			//setting refreshrate at 60fps
			time = new Timer(1000/60, update);
			time.start();
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			//painting rectangles whith colors from WiiMoteTracker track
			g2d.setColor(track.getColors().get(0));
			g2d.fillRect(125, 0, 125, 125);
			g2d.setColor(track.getColors().get(1));
			g2d.fillRect(0, 125, 125, 125);
			g2d.setColor(track.getColors().get(3));
			g2d.fillRect(250, 125, 125, 125);
			g2d.setColor(track.getColors().get(2));
			g2d.fillRect(125, 250, 125,125);
			g2d.setColor(Color.BLACK);
			g2d.drawString("Player 1: "+ track.getPlayer1(), 10, 10);
			g2d.drawString("Player 2: "+ track.getPlayer2(), 10, 400);
			g2d.drawString("Player 3: "+ track.getPlayer3(), 400, 10);
			g2d.drawString("Player 4: "+ track.getPlayer4(), 400, 400);
			g2d.drawString("Total Score: " + track.getScore(), 150, 200);
			g2d.drawString("Press 1 to start a round", 150, 400);
			
		}
	}

}
