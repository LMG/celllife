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
        this.energy = 100;
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
    
    protected void die(){}
    
    protected void eat()
    {
         
    }  
}
