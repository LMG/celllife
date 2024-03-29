package cellLife;

/**
 *
 * @author Louis-Marie Givel
 */
public class Glutton extends Subject{
	

    /******************** Constructeurs ********************/
	public Glutton(Cell position, World world)
	{
		super(position, world);
	}
	
	
	public Glutton(Cell position ,int energy, World world)
	{
		super(position , energy, world);

	}
    
    /******************** MÃ©thodes ********************/
    
    protected Cell direction()
    {
    	//near meat is found or not
    	boolean foodFound = false;
    	
    	//no meat on the world
    	boolean fullMake = false;
    	
    	//nearly cell with meat position
    	int positionX = this.position.x;
    	int positionY = this.position.y;
        	
    	//compter
    	int cmptXmin=0;
    	int cmptXmax=0;    	
    	int cmptYmin=0;
    	int cmptYmax=0;
    	
    	while(foodFound!=true && fullMake!=true)
    	{
    		//check heigth
	    	for(int i= cmptXmin ; i <= cmptXmax ; i++ )
	    	{
	    		//	*** <== check this raw
	    		//	*X*
	    		//	***
		    	if(world.getCell(this.position.x+i, this.position.y-cmptYmin).getMeat() !=0 || 
		    	        world.getCell(this.position.x+i, this.position.y-cmptYmin).getVegetables() !=0)
		    	{
		    		foodFound=true;	
		    		positionX=this.position.x+i;
		    		positionY=this.position.y-cmptYmin;
		    	}
		    	
	    		//	*** 
	    		//	*X*
	    		//	*** <== check this raw
		    	if(world.getCell(this.position.x+i, this.position.y+cmptYmax).getMeat() !=0 ||
		    	        world.getCell(this.position.x+i, this.position.y+cmptYmax).getVegetables()!=0)
		    	{
		    		foodFound=true;	
		    		positionX=this.position.x+i;
		    		positionY=this.position.y+cmptYmax;
		    	}
	    	}
	    	for(int i = cmptYmin ; i <= cmptYmax ; i++ )
	    	{
	    		//	I**
	    		//	IX*
	    		//	I**
	    		if(world.getCell(this.position.x-cmptXmin, this.position.y+i).getMeat() !=0 ||
	    		        world.getCell(this.position.x-cmptXmin, this.position.y+i).getVegetables() !=0)
	    		{
		    		foodFound=true;	
		    		positionX=this.position.x-cmptXmin;
		    		positionY=this.position.y+i;
		    	}

	    		//	**I
	    		//	*XI
	    		//	**I
	    		if(world.getCell(this.position.x+cmptXmax, this.position.y+i).getMeat() !=0 ||
	    		        world.getCell(this.position.x+cmptXmax, this.position.y+i).getVegetables() !=0 )
	    		{
		    		foodFound=true;	
		    		positionX=this.position.x+cmptXmax;
		    		positionY=this.position.y+i;
		    	}
	    	}
    	}
    	
    	
    	//look if all the world as been checked
    	if(this.position.x-cmptXmin<=0 && this.position.x+cmptXmax>World.HEIGHT)
    		if(this.position.y-cmptYmin<=0 && this.position.y+cmptYmax>World.HEIGHT)
    	    	fullMake = true;
    	
    	//protection for overflow
    	cmptXmin = this.position.x-cmptXmin>0?cmptXmin+1:cmptXmin;
    	cmptXmax = this.position.x+cmptXmax<World.HEIGHT?cmptXmax+1:cmptXmax;
    		
    	//protection
    	cmptYmin = this.position.y-cmptYmin>0?cmptYmin+1:cmptYmin;
    	cmptYmax = this.position.y+cmptYmax>World.WIDTH?cmptYmax+1:cmptYmax;
    	
    	//return the most efficient cell
    	return(world.getCell(positionX, positionY));
    				
    }
    
    protected void eat()
    {   
        //A completer
    }  
    
}
