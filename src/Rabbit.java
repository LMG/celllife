/**
 *
 * @author gonsevi
 */
public class Rabbit extends Subject{
    
    /******************** Méthodes ********************/
    
    protected Cell direction()
    {
        return this.position; //A modifier
    }
    
    protected void eat()
    {   
        while (this.energy != ENERGY_MAX && position.getVegetables()!= 0)
        {
            //Tant qu'il y a des légumes et que le lapin n'a pas son énergie au max
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
    }  
    
}
