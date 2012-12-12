import java.util.ArrayList;

/**
 * Constructeur d'une cellule
 */
public class Cell 
{
	public final int x= 30;
	public final int y= 30;
	
	private int vegetables;
	private int meat;
	
	private ArrayList<Subject> subjects;
	public Cell()
	{
		super();	
		this.vegetables=0;
		this.meat=0;
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
