

public class Erratic extends Subject
{
    /******************** Constructeurs ********************/
	public Erratic()
	{
		super();
	}
	
	public Erratic(int energy)
	{
		super();
		this.setEnergy(energy);
	}
	
	public Erratic(Cell position)
	{
		super();
		this.setPosition(position);
	}
	
	public Erratic(int energy , Cell position)
	{
		super();
		this.setEnergy(energy);
		this.setPosition(position);
	}
	
    /******************** Accesseurs ********************/

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
