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
	public Cannibal(Cell position)
	{
		super(position);
	}
	
	
	public Cannibal(Cell position ,int energy)
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
        //TODO
    }  
    
}
