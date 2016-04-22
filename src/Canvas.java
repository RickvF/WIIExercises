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
		ArrayList<Short> y;
		int tijd = 0;
		short oldY = 0;
		
		public Panel()
		{
			super();
			
			track = new WiiRemoteTracker2();
			
			x = new ArrayList<>();
			
			ActionListener update = new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					if(x.size() < 300)
					{
						x.add(track.getX());
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
					}
					if(y.size() < 300)
					{
						y.add(track.getY());
					}
					else
					{
						Iterator<Short> itr = y.iterator();
						while(itr.hasNext())
						{
							if(itr.next().equals(y.get(0)))
							{
								itr.remove();
							}
						}
						
						y.add(track.getY());
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
				previousX=currentX;
				
				currentX = itr.next();
				g2d.setColor(Color.RED);
				g2d.drawLine(tijd+49,(int)previousX+33, tijd+50, (int)currentX+33);
				tijd++;
			}
			
			Iterator<Short> itY = y.iterator();
			while(itY.hasNext())
			{
				short s = itY.next();
				g2d.setColor(Color.BLUE);
				g2d.drawLine(tijd+49, (int)oldY+33, tijd+50, (int)s+33);
				tijd++;
				oldY = s;
			}
			
			
		}
	}

}