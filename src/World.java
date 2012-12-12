
public class World extends Thread{
	
	public final int LENGTHT = 30 ;
	public final int WIDTH= 30 ;
	
	
	private Cell cellTab [][] ;
	
	
	public void init()
	{
		for(int i=0; i < this.LENGTHT; i++)
		{
			for (int j = 0 ; j < this.WIDTH ; j++)
			{
				cellTab[i][j] = new Cell(i,j);
			}
		}
	}
	
	
	public void run()
	{
		while(true)
		{
			try
			{
				Thread.sleep(10);
				
			}
			catch(InterruptedException ex) {System.out.println("?");}
		}
	}
}