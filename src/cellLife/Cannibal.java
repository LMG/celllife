package cellLife;

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

    /**
     * @param position Cellule où placer le cannibale
     * @param world Le monde
     */
    public Cannibal(final Cell position, final World world)
    {
        super(position, world);
    }

    /**
     * @param position Cellule où placer le cannibale
     * @param energy Energie initiale du cannibale
     * @param world Le monde
     */
    public Cannibal(final Cell position, final int energy, final World world)
    {
        super(position, energy, world);

    }

    /**
     * Cherche la prochaine destination du cannibale. 
     * 
     * @return Cell La cellule destination du cannibale
     */
    protected Cell direction()
    {
        return this.position;
    }
    

	protected void eat()

    {   
        while (this.energy != ENERGY_MAX && 
                (position.getVegetables()!= 0 || position.getMeat()!= 0 || position.getSubjects().size()>1))
        {
            if (position.getSubjects().size() > 1)
            {
                for (Subject sub : position.getSubjects())
                {
                    if (!sub.equals(this))
                    {
                        this.energy = ENERGY_MAX;
                        sub.die();
                        return;
                    }
                }
            }
            // Tant qu'il y a de la nourriture et que l'individu n'a pas son
            // énergie au max
            else if (position.getVegetables() > position.getMeat())
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
