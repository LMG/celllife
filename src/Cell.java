import java.util.ArrayList;

/**
 * Constructeur d'une cellule
 */
public class Cell 
{
	public final int x;
	public final int y;
	
	private int vegetables;
	private int meat;
	
	private ArrayList<Subject> subjects;
	
	
	public Cell(int x , int y)
	{
		super();	
		this.x = x;
		this.y= y;
		this.vegetables=0;
		this.meat=0;
	}
	
	public Cell(int x , int y , int meat , int vegetagles)
	{
		super();
		this.x = x;
		this.y= y;
		this.vegetables=vegetagles;
		this.meat=meat;
	}
	
	public ArrayList<Subject> getSubjects()
	{
		return subjects;
	}
	
	public void setSubjects(ArrayList<Subject> subj)
	{
		subjects=subj;
	}
	
	public int getVegetables()
	{
		return vegetables;
	}
	
	public void setVegetables(int value)
	{
		this.vegetables = value;
	}
	
	public int getMeat()
	{
		return meat;
	}
	
	public void setMeat(int value)
	{
		this.meat = value;
	}
	
	
}
