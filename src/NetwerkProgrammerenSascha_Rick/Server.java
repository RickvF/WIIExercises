package NetwerkProgrammerenSascha_Rick;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{

	public static void main(String[] args)
	{
		new Server();
	}
	
	public Server()
	{
		try
		{
			ServerSocket server = new ServerSocket(8000);
			
			while(true)
			{
				Socket player1 = server.accept();
				
				Socket player2 = server.accept();
				
				HandleAGame game = new HandleAGame(player1, player2);
				
				new Thread(game).start();
			}
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	class HandleAGame implements Runnable
	{
		private Socket player1;
		private Socket player2;
		
		private DataInputStream fromPlayer1;
		private DataOutputStream toPlayer1;
		private DataInputStream fromPlayer2;
		private DataOutputStream toPlayer2;
		
		public HandleAGame(Socket player1, Socket player2)
		{
			this.player1 = player1;
			this.player2 = player2;
		}

		@Override
		public void run()
		{
			try
			{
				fromPlayer1 = new DataInputStream(player1.getInputStream());
				toPlayer1 = new DataOutputStream(player1.getOutputStream());
				
				fromPlayer2 = new DataInputStream(player2.getInputStream());
				toPlayer2 = new DataOutputStream(player2.getOutputStream());
				
				while(true)
				{
					//TODO afhandeling van het spel
					
				}
				
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
