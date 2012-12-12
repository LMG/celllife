

public class Erratic extends Subject
{
    /******************** Constructeurs ********************/
	
	
	public Erratic(Cell position)
	{
		super(position);
	}
	
	
	public Erratic(Cell position ,int energy)
	{
		super(position , energy);

	}
	


	protected Cell direction()
	{
		//déplacement aléaoire 
		int randx = (((int) Math.random()) % 3)-1;
		int randy = (((int) Math.random()) % 3)-1;
		
		while(randx == 0 && randy == 0 )
		{
			randx = (((int) Math.random()) % 3)-1;
			randy = (((int) Math.random()) % 3)-1;
		}
			
		//TODO
		//récupérer la cellule actuelle
		//trouver la cellule x+randx et y+randy dans World
		//renvoyer cette cellule
		
		
		//RETURN TEMPORAIRE POUR COMMIT ( en attente de la clase world)
		return this.getPosition();
	}
	
}
