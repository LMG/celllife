package cellLife;

/**
 * Classe principale du programme.
 */
public final class CellLife
{
    /**
     * Le terminal.
     */
    private static Terminal theTerminal;
    /**
     * La vue.
     */
    private static View theView;
    /**
     * Le monde.
     */
    private static World theWorld;

    /**
     * Constructeur: instanciation des éléments.
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


    /** 
     * Démarrage de la simulation.
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
