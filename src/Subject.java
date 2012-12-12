/**
 *
 * @author gonsevi
 * @since 12/12/12
 * 
 */

public abstract class Subject 
{
    /******************** Constantes ********************/
    private final int ENERGY_MAX = 100;
    
    /******************** Attributs ********************/
    
    private int energy;
    private Cell position;
    
    
    /******************** Constructeurs ********************/
  
    public Subject ()
    {
        this.energy = ENERGY_MAX;
    }
    
    public Subject (Cell position)
    {
        this.energy = ENERGY_MAX;
        this.position = position;
    }
    
    public Subject (Cell position, int energy)
    {
        this.energy = ENERGY_MAX;
        this.position = position;
    }
    
    /******************** Accesseurs ********************/
    
    public void setEnergy (int energy)
    {
        this.energy = ENERGY_MAX;
    }
    
    public int getEnergy ()
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
    
    public void play(){}
    
    protected void reproduce(){}
    
    protected void move(){}
    
    abstract Cell direction();
    
    protected void die(){}
    
    protected void eat()
    {   
        while (this.energy != ENERGY_MAX && 
                (position.getVegetables()!= 0 || position.getMeat()!= 0))
        {
            //Tant qu'il y a de la nourriture et que l'individu n'a pas son énergie au max
            if(position.getVegetables() > position.getMeat())
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
