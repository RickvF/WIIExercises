
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.*;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.strategies.QuadSpaceStrategy;

public class Box2D_Example4 {
	
	public static void main(String s[])
	{
		JFrame frame = new JFrame("Box2d Example 4 - Bier.png");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new Ex4Panel();
		
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}


class Ex4Panel extends JPanel implements ActionListener, MouseListener
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Add World object
	private World myWorld = new World( 
				new Vector2f(0.0f, 10.0f), 
				50,
				new QuadSpaceStrategy(20,5));
	
	private ArrayList<Body> bodies = new ArrayList<Body>();
	
	private BufferedImage bier;
	
	/* Constructor */
	public Ex4Panel()
	{
		setPreferredSize( new Dimension(640,480));
	
		try {
			bier = ImageIO.read(new File("src/bier.png"));
		} catch(IOException e) {
			System.out.println(e);
		}
		
		myWorld.clear();
		myWorld.setGravity(0, 10);
		
		// Create Static body
		Body b = new StaticBody("Static", new Box(450,5));
		b.setPosition(300f,300f);
		b.setRotation((float) (Math.PI/60f));
		bodies.add(b);
		
		for(int idx = 0; idx < 4; idx++)
		{
			b = new Body("b"+idx, new Box(100f,197f), 10f);
			b.setPosition(210f + idx*130f, 100f);
			b.setFriction(0.01f);
			bodies.add(b);
		}
		
		for(Body body:bodies)
			myWorld.add(body);
		
		Timer timer = new Timer(1000/30, this);
		timer.start();
		
		addMouseListener(this);
	}

	// Timer, action performed. 
	public void actionPerformed(ActionEvent arg0)
	{	
		for(int i=0; i<5; i++) {
			myWorld.step();
		}
		repaint();
	}
	
	public void drawBox(Graphics2D g2, Body b)
	{
		Box box = (Box) b.getShape();
		Vector2f[] pts = box.getPoints(b.getPosition(), b.getRotation());

		Vector2f p1 = pts[0];
		Vector2f p2 = pts[1];
		Vector2f p3 = pts[2];
		Vector2f p4 = pts[3];
		
		BufferedImage img = bier;
		AffineTransform tr = new AffineTransform();

		tr.rotate(b.getRotation(), img.getWidth()/2, img.getHeight()/2);

		AffineTransformOp op = new AffineTransformOp(tr, AffineTransformOp.TYPE_BILINEAR);
		img = op.filter(img, null);
		
		g2.drawImage( img, (int)p1.x, (int)p1.y, 100, 197, null);
		

//		g2.setColor(Color.BLACK);
//		g2.drawLine((int) p1.x,(int) p1.y,(int) p2.x,(int) p2.y);
//		g2.drawLine((int) p2.x,(int) p2.y,(int) p3.x,(int) p3.y);
//		g2.drawLine((int) p3.x,(int) p3.y,(int) p4.x,(int) p4.y);
//		g2.drawLine((int) p4.x,(int) p4.y,(int) p1.x,(int) p1.y);
	}
	
	// Hier alleen tekenen ! Nooit een repaint() !!
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// Dikke lijn, beter zichtbaar
		g2.setStroke(new BasicStroke(2));
		
		// Draw body 1 en body 2
		for(Body body:bodies)
		{
			drawBox(g2,body);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		bodies.get(1).setForce(5000f, 0f);
		
		System.out.println(bodies.get(1));
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

