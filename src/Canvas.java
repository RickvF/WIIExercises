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

		ArrayList<Short> y;
		ArrayList<Float> gfY;

		int tijd = 0;
		short oldY = 0;
		short previousZ = 0;
		short previousX = 0;
		float oldGf = 0;
		
		public Panel()
		{
			super();
			
			track = new WiiRemoteTracker2();
			
			x = new ArrayList<>();
			
			z = new ArrayList<>();
			
			y = new ArrayList<>();
			
			gfY = new ArrayList<>();
			
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
						Iterator<Short> itrx = x.iterator();
						while(itrx.hasNext())
						{
							if(itrx.next().equals(x.get(0)))
							{
								itrx.remove();
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
						Iterator<Short> itry = y.iterator();
						while(itry.hasNext())
						{
							if(itry.next().equals(y.get(0)))
							{
								itry.remove();
							}
						}
						
						y.add(track.getY());
					}
					
					if(z.size() < 300)
					{
						z.add(track.getZ());
					}
					else
					{
						Iterator<Short> itrz = z.iterator();
						while(itrz.hasNext())
						{
							if(itrz.next().equals(z.get(0)))
							{
								itrz.remove();
							}
						}
						
						z.add(track.getZ());
					}
					
					
					if(gfY.size() < 300)
					{
						gfY.add(track.getgfY());
					}
					else
					{
						Iterator<Float> itrgy = gfY.iterator();
						while(itrgy.hasNext())
						{
							if(itrgy.next().equals(gfY.get(0)))
							{
								itrgy.remove();
							}
						}
						
						gfY.add(track.getgfY());
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
		
			for(short p : x)
			{
				short sx = x.get(tijd);
				g2d.setColor(Color.RED);
				g2d.drawLine(tijd+49,(int)previousX+33, tijd+50, (int)sx+33);
				previousX = sx;
//				
//				short sy = y.get(tijd);
//				g2d.setColor(Color.BLUE);
//				g2d.drawLine(tijd+49, (int)oldY+33, tijd+50, (int)sy+33);
//				oldY = sy;
//				
				short sz = z.get(tijd);
				g2d.setColor(Color.GREEN.darker());
				g2d.drawLine(tijd+49,(int)previousZ+9, tijd+50, (int)sz+9);
				previousZ = sz;
				
//				float gf = gfY.get(tijd);
//				g2d.setColor(Color.PINK);
//				g2d.drawLine(tijd+49, ((int)oldGf*10) + 157, tijd+50,((int)gf*10) +157);
//				oldGf = gf;
//				
				tijd++;
			}
		}
	}

}