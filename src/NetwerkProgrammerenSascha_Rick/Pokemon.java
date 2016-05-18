package NetwerkProgrammerenSascha_Rick;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class Pokemon implements Comparable<String>, Serializable
{
	private BufferedImage image;
	private ArrayList<String> types;
	private String name;
	private int generation;
	private String color;
	
	//Constructor of the class Pokemon
	public Pokemon(BufferedImage image, String name, int generation, String color)
	{
		this.image = image;
		this.name = name;
		this.generation = generation;
		this.color = color;
	}
	
	//method to add a type to the list of types
	public void addType(String type)
	{
		types.add(type);
	}
	
	//method which returns the pokemon's image
	public BufferedImage getImage()
	{
		return image;
	}
	
	//method which returns the arraylist with types 
	public ArrayList<String> getTypes()
	{
		return types;
	}
	
	//method which returns the pokemon's name
	public String getName()
	{
		return name;
	}
	
	//method which returns the pokemon's generation
	public int getGeneration()
	{
		return generation;
	}
	
	//method which returns the pokemon's color
	public String getColor()
	{
		return color;
	}

	@Override
	public int compareTo(String t)
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
