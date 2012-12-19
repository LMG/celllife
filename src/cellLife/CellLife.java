package cellLife;

/**
 *
 */
public final class CellLife
{
    /**
     * @param args
     */
    private static Terminal theTerminal;
    /**
     * @param args
     */
    private static View theView;
    /**
     * @param args
     */
    private static World theWorld;

    /**
     * @param args
     */
    private CellLife()
    {
        System.out.println("Création du monde.");
        theWorld = new World(theView);
        theWorld.start();

        System.out.println("Création des vues.");
        theTerminal = new Terminal(theWorld);
        theView = new View(theWorld);

        theTerminal.start();
        theView.start();
    }

    /** Simulation start.
     * 
     * @param args arguments
     */
    //On autorise cette méthode principale: c'est le point d'entrée du programme
    //CHECKSTYLE:OFF
    public static void main(final String[] args)
    //CHECKSTYLE:ON
    {
        new CellLife();
    }

}
