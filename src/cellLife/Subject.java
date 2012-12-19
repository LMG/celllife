package cellLife;

/**
 * 
 * @author gonsevi
 * @since 12/12/12
 * 
 */

public abstract class Subject
{
    /******************** Constantes ********************/

    protected final int ENERGY_MAX = 100;
    public final int ENERGY_REPRODUCTION = 60;
    public final int ENERGY_MOVE = 10;

    /******************** Attributs ********************/

    protected int energy;
    protected Cell position;
    protected World world;

    /******************** Constructeurs ********************/

    public Subject()
    {
        this.energy = ENERGY_MAX;
    }

    public Subject(Cell position, World world)
    {
        this.energy = ENERGY_MAX;
        this.position = position;
        this.world = world;
    }

    public Subject(Cell position, int energy, World world)
    {
        this.energy = energy;
        this.position = position;
        this.world = world;
    }

    /******************** Accesseurs ********************/

    public void setEnergy(int energy)
    {
        this.energy = ENERGY_MAX;
    }

    public int getEnergy()
    {
        return this.energy;
    }

    public void setPosition(Cell position)
    {
        this.position = position;
    }

    public Cell getPosition()
    {
        return this.position;
    }

    /******************** Méthodes ********************/

    public void play()
    {

    }

    protected void reproduce()
    {
        setEnergy(getEnergy() - ENERGY_REPRODUCTION);
    }

    protected void move()
    {
    	  int deplacementX = 0;
          int deplacementY = 0;
          Cell currentCell = this.position;

          // new cell after moving
          Cell destinationCell = this.direction();

          // delete subjects on the old cell
          currentCell.getSubjects().remove(this);

          if (destinationCell.x - currentCell.x > 0)
              deplacementX = +1;
          if (destinationCell.x - currentCell.x < 0)
              deplacementX = -1;

          if (destinationCell.y - currentCell.y > 0)
              deplacementY = +1;
          if (destinationCell.y - currentCell.y < 0)
              deplacementY = -1;

          // add subject on the new cell
          world.cellTab[this.position.x + deplacementX][this.position.y
                  + deplacementY].getSubjects().add(this);

          // refresh local position
          this.position = world.cellTab[this.position.x
                  + deplacementX][this.position.y + deplacementY];

          // less energy after moving
          this.energy = this.energy - ENERGY_MOVE;
      }
    
    protected Cell nearestCell(Subject sub )
    {
    	boolean foodFound = false;
    	
    	boolean fullMake= false;
    	
    	Cell.foodType currentFood = Cell.foodType.NONE ;
    	Cell pos = sub.position;
    	    	
    	int cellDirectionX = pos.x;
    	int cellDirectionY = pos.y;
    	
    	int cmptXmin = 0;
    	int cmptXmax = 0;
    	
    	int cmptYmin = 0;
    	int cmptYmax = 0;
    	
    	while(fullMake == false && foodFound == false)
    	{
    	
	    	/*******************Horizontal*********************/
	    	
	    	//			= = = X X 
		    //  		X ! ! ! X			= : currently checking
		    //			X ! O ! X			O : Subject
		    //			X ! ! ! X			! : always checked
		    //			= = = X X			X : not check    	
	    	for (int i = (pos.x + cmptXmin); i <= pos.x; i++)
	    	{
	    		
	    		//check =>	= = = X X 
	    	    //  		X ! ! ! X			= : currently checking
	    	    //			X ! O ! X			O : Subject
	    	    //			X ! ! ! X			! : always checked
	    	    //			X X X X X			X : not check
	    		currentFood = world.getCell(i , pos.y + cmptYmin).haveFood();
		    	if(( sub instanceof Cannibal || sub instanceof Glutton) && ( currentFood == Cell.foodType.MEAT))
		    	{
		    		foodFound = true;
		    		cellDirectionX = i;
		    		cellDirectionY = pos.y + cmptYmin;
		    	}
		    	if(( sub instanceof Glutton) && ( currentFood == Cell.foodType.VEGETABLES))
		    	{
		    		foodFound = true;
		    		cellDirectionX = i;
		    		cellDirectionY = pos.y + cmptYmin;
		    	}
		    	
		    	// 			X X X X X
		        //  		X ! ! ! X			= : currently checking
		        //			X ! O ! X			O : Subject
		        //			X ! ! ! X			! : always checked
		        //check =>	= = = X X			X : not check
		    	currentFood = world.getCell(i , pos.y + cmptYmax).haveFood();
		    	if(( sub instanceof Cannibal || sub instanceof Glutton) && ( currentFood == Cell.foodType.MEAT))
		    	{
		    		foodFound = true;
		    		cellDirectionX = i;
		    		cellDirectionY = pos.y + cmptYmax;
		    	}
		    	if(( sub instanceof Glutton) && ( currentFood == Cell.foodType.VEGETABLES))
		    	{
		    		foodFound = true;
		    		cellDirectionX = i;
		    		cellDirectionY = pos.y + cmptYmax;
		    	}
	     	}
	    	
	    	//			X X = = = 
		    //  		X ! ! ! X			= : currently checking
		    //			X ! O ! X			O : Subject
		    //			X ! ! ! X			! : always checked
		    //			X X = = =			X : not check    	
	    	for (int i = (pos.x + cmptXmax); i >= pos.x; i--)
	    	{
	    		
	    		//check =>	x x = = = 
	    	    //  		X ! ! ! X			= : currently checking
	    	    //			X ! O ! X			O : Subject
	    	    //			X ! ! ! X			! : always checked
	    	    //			X X X X X			X : not check
	    		currentFood = world.getCell(i , pos.y + cmptYmin).haveFood();
		    	if(( sub instanceof Cannibal || sub instanceof Glutton) && ( currentFood == Cell.foodType.MEAT))
		    	{
		    		foodFound = true;
		    		cellDirectionX = i;
		    		cellDirectionY = pos.y + cmptYmin;
		    	}
		    	if(( sub instanceof Glutton) && ( currentFood == Cell.foodType.VEGETABLES))
		    	{
		    		foodFound = true;
		    		cellDirectionX = i;
		    		cellDirectionY = pos.y + cmptYmin;
		    	}
		    	
		    	// 			X X X X X
		        //  		X ! ! ! X			= : currently checking
		        //			X ! O ! X			O : Subject
		        //			X ! ! ! X			! : always checked
		        //check =>	X X = = =			X : not check
		    	currentFood = world.getCell(i , pos.y + cmptYmax).haveFood();
		    	if(( sub instanceof Cannibal || sub instanceof Glutton) && ( currentFood == Cell.foodType.MEAT))
		    	{
		    		foodFound = true;
		    		cellDirectionX = i;
		    		cellDirectionY = pos.y + cmptYmax;
		    	}
		    	if(( sub instanceof Glutton) && ( currentFood == Cell.foodType.VEGETABLES))
		    	{
		    		foodFound = true;
		    		cellDirectionX = i;
		    		cellDirectionY = pos.y + cmptYmax;
		    	}
	     	}
	    	
	    	/*******************Vertical*********************/
		    //			= = = X X 
		    //  		X ! ! ! X			= : currently checking
		    //			X ! O ! X			O : Subject
		    //			X ! ! ! X			! : always checked
		    //			= = = X X			X : not check    	
			for (int i = (pos.y + cmptYmin); i <= pos.y; i++)
			{
				
				//check =>	= X X X X 
			    //  		= ! ! ! X			= : currently checking
			    //			= ! O ! X			O : Subject
			    //			X ! ! ! X			! : always checked
			    //			X X X X X			X : not check
				currentFood = world.getCell(pos.x + cmptXmin , i).haveFood();
		    	if(( sub instanceof Cannibal || sub instanceof Glutton) && ( currentFood == Cell.foodType.MEAT))
		    	{
		    		foodFound = true;
		    		cellDirectionX = pos.x + cmptXmin;
		    		cellDirectionY = i;
		    	}
		    	if(( sub instanceof Glutton) && ( currentFood == Cell.foodType.VEGETABLES))
		    	{
		    		foodFound = true;
		    		cellDirectionX = pos.x + cmptXmin;
		    		cellDirectionY = i;
		    	}
		    	
		    	// 			X X X X =
		        //  		X ! ! ! =			= : currently checking
		        //			X ! O ! =			O : Subject
		        //			X ! ! ! X			! : always checked
		        //check =>	X X X X X			X : not check
		    	currentFood = world.getCell(pos.x + cmptXmax , i).haveFood();
		    	if(( sub instanceof Cannibal || sub instanceof Glutton) && ( currentFood == Cell.foodType.MEAT))
		    	{
		    		foodFound = true;
		    		cellDirectionX = pos.x + cmptXmax;
		    		cellDirectionY = i;
		    	}
		    	if(( sub instanceof Glutton) && ( currentFood == Cell.foodType.VEGETABLES))
		    	{
		    		foodFound = true;
		    		cellDirectionX = pos.x + cmptXmax;
		    		cellDirectionY = i;
		    	}
		 	}
		    	
	    	
	    	
	    	
	    	
	    	//check =>	X X X X X 
		    //  		X ! ! ! X			= : currently checking
		    //			= ! O ! =			O : Subject
		    //			= ! ! ! =			! : always checked
		    //			= X X X =			X : not check    	
	    	for (int i = (pos.y + cmptYmax); i >= pos.y; i--)
	    	{
	    		
	    		//check =>	X X X X X 
	    	    //  		X ! ! ! X			= : currently checking
	    	    //			= ! O ! X			O : Subject
	    	    //			= ! ! ! X			! : always checked
	    	    //			= X X X X			X : not check
	    		currentFood = world.getCell(pos.x + cmptXmin , i ).haveFood();
		    	if(( sub instanceof Cannibal || sub instanceof Glutton) && ( currentFood == Cell.foodType.MEAT))
		    	{
		    		foodFound = true;
		    		cellDirectionX = pos.x + cmptXmin;
		    		cellDirectionY = i;
		    	}
		    	if(( sub instanceof Glutton) && ( currentFood == Cell.foodType.VEGETABLES))
		    	{
		    		foodFound = true;
		    		cellDirectionX = pos.x + cmptXmin;
		    		cellDirectionY = i;
		    	}
		    	
		    	// 			X X X X X
		        //  		X ! ! ! X			= : currently checking
		        //			X ! O ! =			O : Subject
		        //			X ! ! ! =			! : always checked
		        //check =>	X X X X =			X : not check
		    	currentFood = world.getCell(pos.x + cmptXmax , i).haveFood();
		    	if(( sub instanceof Cannibal || sub instanceof Glutton) && ( currentFood == Cell.foodType.MEAT))
		    	{
		    		foodFound = true;
		    		cellDirectionX =  pos.x + cmptXmax;
		    		cellDirectionY = i;
		    	}
		    	if(( sub instanceof Glutton) && ( currentFood == Cell.foodType.VEGETABLES))
		    	{
		    		foodFound = true;
		    		cellDirectionX = pos.x + cmptXmax;
		    		cellDirectionY = i;
		    	}
	     	}
	    	
	    	
	    	//look if all the world as been checked
	    	if(pos.x+cmptXmin<=0 && pos.x+cmptXmax>=World.HEIGHT)
	    	{
	    		if(pos.y+cmptYmin<=0 && pos.y+cmptYmax>=World.HEIGHT)
	    		{
	    	    	fullMake = true;
	    		}
	    	}
	    	
	    	//protection for overflow
	    	cmptXmin = ((pos.x+cmptXmin)>0)?cmptXmin-1:cmptXmin;
	    	cmptXmax = ((pos.x+cmptXmax)<World.HEIGHT)?cmptXmax+1:cmptXmax;
	    		
	    	//protection
	    	cmptYmin = ((pos.y+cmptYmin)>0)?cmptYmin-1:cmptYmin;
	    	cmptYmax = ((pos.y+cmptYmax)<World.WIDTH)?cmptYmax+1:cmptYmax;
    	}	
    	return world.getCell(cellDirectionX , cellDirectionY );
    }
    

    protected abstract Cell direction();

    protected void die()
    {
        if (this.energy <= 0)
        {
            this.position.getSubjects().remove(this);
            this.world.getSubjects().remove(this);
        }
    }

    protected void eat()
    {
        while (this.energy != ENERGY_MAX
                && (position.getVegetables() != 0 || position.getMeat() != 0))
        {
            // Tant qu'il y a de la nourriture et que l'individu n'a pas son
            // énergie au max
            if (position.getVegetables() > position.getMeat())
            {
                if (this.energy + position.getVegetables() > ENERGY_MAX)
                {
                    position.setVegetables(position.getVegetables()
                            - (ENERGY_MAX - this.energy));
                    this.energy = ENERGY_MAX;
                }
                else
                {
                    this.energy += position.getVegetables();
                    position.setVegetables(0);
                }
            }
            else
            {
                if (this.energy + position.getMeat() > ENERGY_MAX)
                {
                    position.setMeat(position.getMeat()
                            - (ENERGY_MAX - this.energy));
                    this.energy = ENERGY_MAX;
                }
                else
                {
                    this.energy += position.getMeat();
                    position.setMeat(0);
                }
            }

        }
    }
}
