/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author gonsevi
 */
public class Cannibal extends Subject
{
	/******************** Constructeurs ********************/
	public Cannibal(Cell position, World world)
	{
		super(position, world);
	}

	public Cannibal(Cell position, int energy, World world)
	{
		super(position, energy, world);

	}

	/******************** Méthodes ********************/

	protected Cell direction()
	{
		// near meat is found or not
		boolean foodFound = false;

		// no meat on the world
		boolean fullMake = false;

		// nearly cell with meat position
		int positionX = this.position.x;
		int positionY = this.position.y;

		// compter
		int cmptXmin = 0;
		int cmptXmax = 0;
		int cmptYmin = 0;
		int cmptYmax = 0;

		while (foodFound != true && fullMake != true)
		{
			// check heigth
			for (int i = cmptXmin; i <= cmptXmax; i++)
			{
				// *** <== check this raw
				// *X*
				// ***
				if (CellLife.theWorld.getCell(this.position.x + i,
				        this.position.y - cmptYmin).getMeat() != 0)
				{
					foodFound = true;
					positionX = this.position.x + i;
					positionY = this.position.y - cmptYmin;
				}

				// ***
				// *X*
				// *** <== check this raw
				if (CellLife.theWorld.getCell(this.position.x + i,
				        this.position.y + cmptYmax).getMeat() != 0)
				{
					foodFound = true;
					positionX = this.position.x + i;
					positionY = this.position.y + cmptYmax;
				}
			}
			for (int i = cmptYmin; i <= cmptYmax; i++)
			{
				// I**
				// IX*
				// I**
				if (CellLife.theWorld.getCell(this.position.x - cmptXmin,
				        this.position.y + i).getMeat() != 0)
				{
					foodFound = true;
					positionX = this.position.x - cmptXmin;
					positionY = this.position.y + i;
				}

				// **I
				// *XI
				// **I
				if (CellLife.theWorld.getCell(this.position.x + cmptXmax,
				        this.position.y + i).getMeat() != 0)
				{
					foodFound = true;
					positionX = this.position.x + cmptXmax;
					positionY = this.position.y + i;
				}
			}
		}

		// look if all the world as been checked
		if (this.position.x - cmptXmin <= 0
		        && this.position.x + cmptXmax > World.HEIGHT)
			if (this.position.y - cmptYmin <= 0
			        && this.position.y + cmptYmax > World.HEIGHT)
				fullMake = true;

		// protection for overflow
		cmptXmin = this.position.x - cmptXmin > 0 ? cmptXmin++ : cmptXmin;
		cmptXmax = this.position.x + cmptXmax < World.HEIGHT ? cmptXmax++
		        : cmptXmax;

		// protection
		cmptYmin = this.position.y - cmptYmin > 0 ? cmptYmin++ : cmptYmin;
		cmptYmax = this.position.y + cmptYmax > World.WIDTH ? cmptYmax++
		        : cmptYmax;

		// return the most efficient cell
		return (CellLife.theWorld.getCell(positionX, positionY));

	}
	
	protected void eat()
    {   
        while (this.energy != ENERGY_MAX && 
                (position.getVegetables()!= 0 || position.getMeat()!= 0 || position.getSubjects().size()>1))
        {
            if(position.getSubjects().size()>1)
            {
                for(Subject sub:position.getSubjects())
                {
                    if(!sub.equals(this))
                    {
                        this.energy = ENERGY_MAX;
                        sub.die();
                        return;
                    }
                }
            }
            //Tant qu'il y a de la nourriture et que l'individu n'a pas son énergie au max
            else if(position.getVegetables() > position.getMeat())
            {
                if (this.energy + position.getVegetables() > ENERGY_MAX)
                {
                    position.setVegetables(position.getVegetables() - (ENERGY_MAX - this.energy));
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
                    position.setMeat(position.getMeat() - (ENERGY_MAX - this.energy));
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
