/**
 *
 * @author Louis-Marie Givel
 */
public class Glutton extends Subject{
	

    /******************** Constructeurs ********************/
	public Glutton(Cell position)
	{
		super(position);
	}
	
	
	public Glutton(Cell position ,int energy)
	{
		super(position , energy);

	}
    
    /******************** Méthodes ********************/
    
    protected Cell direction()
    {
        return this.position; //A modifier
    }
    
    protected void eat()
    {   
        //A completer
    }  
    
}
