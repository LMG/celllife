package cellLife;


public class Erratic extends Subject
{
    /******************** Constructeurs ********************/
	
	
	public Erratic(Cell position, World world)
	{
		super(position, world);
	}
	
	
	public Erratic(Cell position ,int energy, World world)
	{
		super(position , energy, world);

	}
	


	protected Cell direction()
	{
		//current position
		int positionX=this.position.x;
		int positionY=this.position.y;
		
		//déplacement aléaoire 
		int randx = ((int) (Math.random() * 2))-1;
		int randy = ((int) (Math.random() *2))-1;
		
		while(randx == 0 && randy == 0 )
		{
			randx = ((int) (Math.random() * 2))-1;
			randy = ((int) (Math.random() * 2))-1;
		}
		
		if (positionX+randx<=World.HEIGHT && positionX+randx>= 0 )
			positionX = positionX+randx;
		
		if (positionY+randy<=World.HEIGHT && positionY+randy>= 0 )
			positionY = positionY+randy;
		
		return(world.getCell(positionX, positionY));
		
			}
	
}
