package cellLife;


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
		
		return(CellLife.theWorld.getCell(positionX, positionY));
		
			}
	
}
