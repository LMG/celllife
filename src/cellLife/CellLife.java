package cellLife;

public class CellLife 
{
	static Terminal theTerminal;
	static View theView;
	static World theWorld;
	
	public CellLife()
	{
		System.out.println("Création du monde.");
		theWorld = new World(theView);
		theWorld.start();

		System.out.println("Création des vues.");
		theTerminal = new Terminal(theWorld);
		theView = new View(theWorld);
		
		theTerminal.start();
		theView.start();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		new CellLife();
	}

}
