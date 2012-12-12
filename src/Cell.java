

/**
 * Constructeur d'une cellule
 */
public class Cell 
{
	public final int x= 30;
	public final int y= 30;
	
	private int vegetables;
	private int meat;
	
	public Cell()
	{
		super();	
		this.vegetables=0;
		this.meat=0;
	}
	
	int getVegetables()
	{
		return vegetables;
	}
	
	void setVegetables(int value)
	{
		this.vegetables = value;
	}
	
	int getMeat()
	{
		return meat;
	}
	
	void setMeat(int value)
	{
		this.meat = value;
	}
	
}
