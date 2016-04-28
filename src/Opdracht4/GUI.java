package Opdracht4;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
public class GUI extends JFrame
{
	//Creating a new Instance of the game
	public static void main(String[] args)
	{
		new GUI();
	}
	//Constructor of Simon_GUI() Creating GUI and setting default size
	public GUI()
	{
		super("GUI");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setContentPane(new Panel());
		setVisible(true);
		setSize(800, 800);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	//JPanel Class
	class Panel extends JPanel
	{
		WiiTracker track; //remote with game commands
		Timer time;           //timer for updating GUI
		
		public Panel()
		{
			super();			
			track = new WiiTracker();
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
			ArrayList<Integer> x = new ArrayList<>(track.xCoordinaten());
			ArrayList<Integer> y = new ArrayList<>(track.yCoordinaten());
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.translate(250, 250);
			g2d.scale(1, 1);
			for(int i = 0; i<x.size(); i++)
			{
				g2d.fillOval(x.get(i)-5, y.get(i)-5, 10, 10);
			}
		}
	}

}
