import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Simon_GUI extends JFrame
{
	Simon_GUI instance;
	
	public static void main(String[] args)
	{
		new Simon_GUI();
	}
	
	public Simon_GUI()
	{
		super("Simon");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setContentPane(new Panel());
		instance = this;
		
		setVisible(true);
		setSize(500, 500);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	class Panel extends JPanel
	{
		WiiMoteTracker track;
		Timer time;
		
		public Panel()
		{
			super();
			
			track = new WiiMoteTracker(instance);
			
			ActionListener update = new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					repaint();
				}
				
			};
			
			time = new Timer(1000/10, update);
			time.start();
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(track.getColors().get(0));

			g2d.fillRect(125, 0, 125, 125);
			g2d.setColor(track.getColors().get(1));
			g2d.fillRect(0, 125, 125, 125);
			g2d.setColor(track.getColors().get(3));
			g2d.fillRect(250, 125, 125, 125);
			g2d.setColor(track.getColors().get(2));
			g2d.fillRect(125, 250, 125,125);
			g2d.setColor(Color.BLACK);
			g2d.drawString("Score: " + track.getScore(), 150, 200);
			g2d.drawString("Press 1 to start a round", 150, 400);
		}
	}

}
