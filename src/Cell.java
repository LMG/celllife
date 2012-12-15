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
	
	private ArrayList<Subject> subjects = new ArrayList<Subject>();
	
	
	public Cell(int x , int y)
	{
		this.x = x;
		this.y= y;
		this.vegetables=0;
		this.meat=0;
	}
	
	public Cell(int x , int y , int meat , int vegetagles)
	{
		this(x, y);
		this.vegetables=vegetagles;
		this.meat=meat;
	}
	
	//return if the cell contain Rabbit
	public boolean containRabbit()
	{
		int index = 0;
		boolean lapinFound = false;
		while(subjects.get(index) != null)
		{
			if( subjects.get(index) instanceof Cannibal)
				lapinFound = true;
			index++;
		}
		return lapinFound;
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
