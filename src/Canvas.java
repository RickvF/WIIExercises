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
		int piektel = 0;
		long startTijd = 0;
		long eindTijd = 0;
		long snelheid = 0;
		double snelheid2 = 0;
		int snelheid3 = 0;
		
		public Panel()
		{
			super();
			
			track = new WiiRemoteTracker2();
			
			x = new ArrayList<>();
			
			z = new ArrayList<>();
			
			y = new ArrayList<>();
			
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
						if(z.size() > 11)
						{
							if(track.getZ() > 160 && z.get(z.size()-10) < 130)
							{
								piektel ++;
								if(piektel == 1)
								{
									startTijd = System.currentTimeMillis();
								}
								else if(piektel > 2)
								{
									eindTijd = System.currentTimeMillis();
									berekenTijd();
									
									piektel = 0;
								}
							}
							else if(track.getZ() < 130 && z.get(z.size()-10) > 160)
							{
								piektel++;
								
								if(piektel == 1)
								{
									startTijd = System.currentTimeMillis();
								}
								else if(piektel > 2)
								{
									
									eindTijd = System.currentTimeMillis();
									berekenTijd();
									piektel = 0;
								}
							}
							
						}
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
					
					tijd = 0;
					repaint();
				}
				
			};
			
			time = new Timer(1000/60, update);
			time.start();
		}
		
		public void berekenTijd()
		{
			snelheid = eindTijd - startTijd;
			//System.out.println(snelheid);
			if(snelheid > 100)
			{
				snelheid2 = (double) (snelheid / 1000);
				System.out.println(snelheid2);
				snelheid2 = (double) (1/snelheid2);
				
			}
			
		}
		
		
		
		public void paintComponent(Graphics g)
		{
			
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.BLACK);
			g2d.drawLine(50, 20, 50, 350);
			g2d.drawLine(50, 155, 350, 155);
			g2d.drawString("omwent/sec: " + snelheid2, 100, 20);
		
			for(short p : z)
			{
//				short sx = x.get(tijd);
//				g2d.setColor(Color.RED);
//				g2d.drawLine(tijd+49,(int)previousX+33, tijd+50, (int)sx+33);
//				previousX = sx;
				
//				short sy = y.get(tijd);
//				g2d.setColor(Color.BLUE);
//				g2d.drawLine(tijd+49, (int)oldY+33, tijd+50, (int)sy+33);
//				oldY = sy;
				
				short sz = z.get(tijd);
				g2d.setColor(Color.GREEN.darker());
				g2d.drawLine(tijd+49,(int)previousZ+9, tijd+50, (int)sz+9);
				previousZ = sz;
				
				tijd++;
			}
		}
	}

}