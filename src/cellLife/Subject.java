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
        CellLife.theWorld.cellTab[this.position.x + deplacementX][this.position.y
                + deplacementY].getSubjects().add(this);

        // refresh local position
        this.position = CellLife.theWorld.cellTab[this.position.x
                + deplacementX][this.position.y + deplacementY];

        // less energy after moving
        this.energy = this.energy - ENERGY_MOVE;
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
