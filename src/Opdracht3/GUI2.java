package Opdracht3;
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
public class GUI2 extends JFrame
{
	//Creating a new Instance of the game
	public static void main(String[] args)
	{
		new GUI2();
	}
	//Constructor of Simon_GUI() Creating GUI and setting default size
	public GUI2()
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
		WiiMoteTracker2 track; //remote with game commands
		Timer time;           //timer for updating GUI
		
		public Panel()
		{
			super();			
			track = new WiiMoteTracker2();
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
			time = new Timer(1000/30, update);
			time.start();
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawString("Y: " + (track.getNunchukX()-240) + " X: " + (track.getNunchukY()- 240), 10, 10);
			g2d.translate(250, 250);
			g2d.rotate((Math.PI/180)*(-90+track.getNunchukRoll()));
			g2d.translate(-250, -250);
			g2d.drawLine(250, 150, 250,350 );
			g2d.drawLine(150, 250, 350, 250);
			g2d.drawOval(150,150, 200, 200);
			g2d.fillOval(track.getNunchukX(), track.getNunchukY(),20,20);
		}
	}

}
