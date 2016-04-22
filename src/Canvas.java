import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Canvas extends JFrame
{

	public static void main(String[] args)
	{
		new Canvas(); 
	}
	
	public Canvas()
	{
		super("Grafiek");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setContentPane(new Panel());
		
		setVisible(true);
		setSize(500, 500);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	class Panel extends JPanel
	{
		WiiRemoteTracker2 track;
		Timer time;
		ArrayList<Short> x;
		
		
		
		
		
		ArrayList<Short> z;
		int tijd = 0;
		
		public Panel()
		{
			super();
			
			track = new WiiRemoteTracker2();
			
			x = new ArrayList<>();
			
			
			
			
			
			z = new ArrayList<>();
			
			ActionListener update = new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					if(x.size() < 300)
					{
						x.add(track.getX());
						
						
						
						
						z.add(track.getZ());
					}
					else
					{
						Iterator<Short> itr = x.iterator();
						while(itr.hasNext())
						{
							if(itr.next().equals(x.get(0)))
							{
								itr.remove();
							}
						}
						
						x.add(track.getX());
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						Iterator<Short> itr2=z.iterator();
						while(itr2.hasNext())
						{
							if(itr2.next().equals(z.get(0)))
									{
								itr2.remove();
									}
						}
						z.add(track.getZ());
					}
					tijd = 0;
					repaint();
				}
				
			};
			
			time = new Timer(1000/100, update);
			time.start();
		}
		
		public void paintComponent(Graphics g)
		{
			
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.BLACK);
			g2d.drawLine(50, 20, 50, 350);
			g2d.drawLine(50, 155, 350, 155);
			Iterator<Short> itr = x.iterator();
			short previousX=0;
			short currentX = 0;
			while(itr.hasNext())
			{
				if(tijd < 1)
				{
					previousX = itr.next();
				}
				else
				{
					previousX=currentX;
				}
				 currentX = itr.next();
				
				g2d.drawLine(tijd+48,(int)previousX+28, tijd+49, (int)currentX+28);
				tijd++;
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			g2d.setColor(Color.GREEN);
			Iterator<Short> itr2 = z.iterator();
			short previousZ=0;
			short currentZ = 0;
			while(itr2.hasNext())
			{
				if(tijd < 1)
				{
					previousZ = itr2.next();
				}
				else
				{
					previousZ=currentZ;
				}
				 currentZ = itr2.next();
				
				g2d.drawLine(tijd,(int)previousZ+28, tijd+1, (int)currentZ+28);
				tijd++;
				
			}
		}
	}

}