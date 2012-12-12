
public class World extends Thread{
	
	public static final int HEIGHT = 30 ;
	public static final int WIDTH= 30 ;
	
	public static final int NUMBER_RABBIT = 10;
	public static final int NUMBER_CANNIBAL = 10;
	public static final int NUMBER_ERRATIC = 10;
	public static final int NUMBER_GLUTTON = 10;
	
	public Cell cellTab [][] = new Cell[HEIGHT][WIDTH];
	
	
	public void init()
	{
		int numberRabbit = NUMBER_RABBIT;
		int numberCannibal = NUMBER_CANNIBAL;
		int numberErratic = NUMBER_ERRATIC;
		int numberGlutton = NUMBER_GLUTTON;
			
		int randHeigth;
		int randWidth;
		
		for(int i=0; i < HEIGHT; i++)
		{
			for (int j = 0 ; j < WIDTH ; j++)
			{
				cellTab[i][j] = new Cell(i,j);
			}
		}
		
		//placement des erratic
		while(numberErratic != 0)
		{
			randHeigth = ((int) Math.random()) % HEIGHT;
			randWidth = ((int) Math.random()) % WIDTH;
			cellTab[randHeigth][randWidth].getSubjects().add( new Erratic(cellTab[randHeigth][randWidth], 100));
			numberErratic--;
		}
	}
	
	
	
	public void run()
	{
		init();
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