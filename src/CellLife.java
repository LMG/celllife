
public class CellLife 
{
	static Terminal theTerminal;
	static View theView;
	static World theWorld;
	
	public CellLife()
	{
		System.out.println("Création des vues.");
		theTerminal = new Terminal();
		
		System.out.println("Création du monde.");
		theWorld = new World();
		theWorld.start();
		
		theView = new View(theWorld);
		theView.start();
		
		while(true){
			try
			{
				Thread.sleep(10);
			}
			catch(InterruptedException ex) {System.out.println("?");}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		new CellLife();
	}

}
