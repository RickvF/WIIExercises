package NetwerkProgrammerenSascha_Rick;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame implements Runnable, Serializable
{
	private String host = "localhost";
	
	private DataInputStream fromServer;
	private DataOutputStream toServer;
	
	JPanel contentPane;
	JPanel pokePane;
	JPanel buttonPane;
	JPanel questionPane;
	
	private String pokemonChoose = "";
	
	private ArrayList<Pokemon> pokemons;
	private ArrayList<String> questions;
	private Set<String> types;
	private Set<String> colors;
	private Set<Integer> generations;
	
	int i = 0;
	
	//setup for questionspane
	JComboBox<String> vragen;
	JTextArea input;
	
	public static void main(String[] args)
	{
		new Client();

	}
	
	public Client()
	{
		//initialisation JFrame
		super("Player");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//setup JPanels
		contentPane = new JPanel(new BorderLayout());
		
		pokePane = new JPanel(new GridLayout(5, 3));
		buttonPane = new JPanel(new FlowLayout());
		questionPane = new JPanel(new GridLayout(2, 1));
		questionPane.setPreferredSize(new Dimension(400, 768));
		
		contentPane.add(pokePane, BorderLayout.CENTER);
		contentPane.add(buttonPane, BorderLayout.SOUTH);
		contentPane.add(questionPane, BorderLayout.EAST);
		
		//method to add the buttons 
		choosePokemon();
		buttons();
		questions();
		
		//initialisation JFrame
		setContentPane(contentPane);
		setVisible(true);
		setResizable(false);
		setSize(1024,768);
		setLocationRelativeTo(null);
		
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		
	}
	
	private void connect()
	{
		Socket socket;
		try
		{
			socket = new Socket(host, 8000);
			
			//setup dataStream to and from server
			fromServer = new DataInputStream(socket.getInputStream());
			toServer = new DataOutputStream(socket.getOutputStream());
			
		} catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void choosePokemon()
	{
		for(int x = 0; x < 3; x++)
		{
			for(int y = 0; y < 5; y++)
			{
				JButton b = new JButton((Icon) pokemons.get(i).getImage());
				pokePane.add(b);
				b.addActionListener(new ActionListener()
				{
					
					@Override
					public void actionPerformed(ActionEvent e)
					{
						pokemonChoose = pokemons.get(i).getName();
					}
				});
				
				i++;
			}
		}
	}
	
	private void buttons()
	{
		//send, quit, new game
		
		JButton send = new JButton("SEND");
		send.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				randomFillCombo();
				
			}
		});
		
		JButton quit = new JButton("QUIT");
		quit.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				
			}
		});
		
		JButton ngame = new JButton("NEW GAME");
		ngame.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				
			}
		});
		
		buttonPane.add(ngame);
		buttonPane.add(quit);
		buttonPane.add(send);
	}
	
	private void questions()
	{
		vragen = new JComboBox<String>();
		vragen.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				
			}
		});
		questionPane.add(vragen);
		
		input = new JTextArea();
		questionPane.add(input);
		
		fillCombobox();
		randomFillCombo();
	}
	
	private void fillCombobox()
	{
		for(String t : types)
		{
			String q = "Is het type van deze pokemon.. " + t + "?";
			questions.add(q);
		}
		for(String t : colors)
		{
			String q = "Is de kleur van deze pokemon.. " + t + "?";
			questions.add(q);
		}
		for(Integer t : generations)
		{
			String q = "Komt deze pokemon uit generations: " + t + "?";
			questions.add(q);
		}
		for(Pokemon p : pokemons)
		{
			String n = p.getName();
			String q = "Is deze pokemon: " + p + "?";
			questions.add(q);
		}
	}
	
	private void randomFillCombo()
	{
		vragen.removeAll();
		for(int i = 0; i<5; i++)
		{
			int g = (int) Math.random() * 27; 
			vragen.addItem(questions.get(g));
		}
	}
}
