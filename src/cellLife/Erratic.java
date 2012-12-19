package cellLife;

/**
 * Classe erratic héritant de la classe Subject.
 * 
 * @author gonsevi
 * 
 */
public class Erratic extends Subject
{

    /**
     * @param position
     *            Cellule où placer l'erratic
     * @param world
     *            Le monde
     */
    public Erratic(final Cell position, final World world)
    {
        super(position, world);
    }

    /**
     * @param position
     *            Cellule où placer l'erratic
     * @param energy
     *            Energie initiale de l'erratic
     * @param world
     *            Le monde
     */
    public Erratic(final Cell position, final int energy, final World world)
    {
        super(position, energy, world);

    }

    /**
     * Cherche la prochaine destination de l'erratic.
     * 
     * @return Cell La cellule destination de l'erratic
     */
    protected Cell direction()
    {
        // current position
        int positionX = this.position.x;
        int positionY = this.position.y;

        // déplacement aléaoire
        int randx = ((int) (Math.random() * 2)) - 1;
        int randy = ((int) (Math.random() * 2)) - 1;

        while (randx == 0 && randy == 0)
        {
            randx = ((int) (Math.random() * 2)) - 1;
            randy = ((int) (Math.random() * 2)) - 1;
        }

        if (positionX + randx <= World.HEIGHT && positionX + randx >= 0)
            positionX = positionX + randx;

        if (positionY + randy <= World.HEIGHT && positionY + randy >= 0)
            positionY = positionY + randy;

        return (world.getCell(positionX, positionY));

    }

}
