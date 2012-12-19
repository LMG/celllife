package cellLife;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Le monde.
 */
public class World extends Thread
{
    /**
     * Hauteur de la zone de jeu (en nombre de cases).
     */
    public static final int HEIGHT = 40;
    /**
     * Largeur de la zone de jeu (en nombre de cases).
     */
    public static final int WIDTH = 40;

    /**
     * La durée d'une itération.
     */
    public static final int STEP_DURATION = 100;

    /**
     * Nombre de lapins.
     */
    public static final int NUMBER_RABBIT = 10;
    /**
     * Nombre de cannibales.
     */
    public static final int NUMBER_CANNIBAL = 10;
    /**
     * Nombre de erratics.
     */
    public static final int NUMBER_ERRATIC = 10;
    /**
     * Nombre de glutton.
     */
    public static final int NUMBER_GLUTTON = 10;
    /**
     * Énergie par défaut.
     */
    public static final int DEFAULT_ENERGY = 100;

    /**
     * Tableaux des cellules.
     */
    private Cell[][] cellTab = new Cell[HEIGHT][WIDTH];
    /**
     * Lise des sujets.
     */
    private List<Subject> subjects = new ArrayList<Subject>();

    /**
     * Indique si on fait tourner la simulation (start dans Terminal).
     */
    private boolean run = true;
    /**
     * Permet d'arrêter entièrement la simulation.
     */
    private boolean simulationRunning = true;
    /**
     * La vue.
     */
    private View theView;

    /**
     * Constructeur: fait le lien avec vue.
     * 
     * @param aView
     *            une vue.
     */
    World(final View aView)
    {
        this.theView = aView;
    }

    /**
     * Renvoie la cellule correspondante.
     * 
     * @param height
     *            la coordonnée en y
     * @param width
     *            la coordonée en x
     * @return Une cellule du tableau
     */
    public final Cell getCell(final int height, final int width)
    {
        return this.cellTab[height][width];
    }

    /**
     * Démarre la simulation.
     */
    public final void startSimulation()
    {
        this.run = true;
    }

    /**
     * Stoppe (temporairement) la simulation.
     */
    public final void stopSimulation()
    {
        this.run = false;
    }

    /**
     * Arrête la simulation.
     */
    public final void endSimulation()
    {
        this.simulationRunning = false;
        this.theView.endSimulation();
    }

    /**
     * Intialisation du monde: ajout des objets...
     */
    public final void init()
    {
        int randHeigth;
        int randWidth;
        Subject subject;

        for (int i = 0; i < HEIGHT; i += 1)
        {
            for (int j = 0; j < WIDTH; j += 1)
            {
                this.cellTab[i][j] = new Cell(i, j);
            }
        }

        // Adding all the subjects to the world
        // placement des erratics
        for (int numberErratic = NUMBER_ERRATIC; 
                numberErratic >= 0; numberErratic -= 1)
        {
            randHeigth = (int) ((Math.random()) * HEIGHT) % HEIGHT;
            randWidth = (int) ((Math.random()) * WIDTH) % WIDTH;

            subject = new Erratic(this.cellTab[randHeigth][randWidth],
                    DEFAULT_ENERGY, this);
            this.cellTab[randHeigth][randWidth].getSubjects().add(subject);
            this.subjects.add(subject);
        }

        // placement des Rabbits
        for (int numberRabbit = NUMBER_RABBIT; 
                numberRabbit >= 0; numberRabbit -= 1)
        {

            randHeigth = (int) ((Math.random()) * HEIGHT) % HEIGHT;
            randWidth = (int) ((Math.random()) * WIDTH) % WIDTH;

            subject = new Rabbit(this.cellTab[randHeigth][randWidth],
                    DEFAULT_ENERGY, this);
            this.cellTab[randHeigth][randWidth].getSubjects().add(subject);
            this.subjects.add(subject);
        }

        // placement des Cannibals
        for (int numberCannibal = NUMBER_CANNIBAL; 
                numberCannibal >= 0; numberCannibal -= 1)
        {
            randHeigth = (int) ((Math.random()) * HEIGHT) % HEIGHT;
            randWidth = (int) ((Math.random()) * WIDTH) % WIDTH;

            subject = new Cannibal(this.cellTab[randHeigth][randWidth],
                    DEFAULT_ENERGY, this);
            this.cellTab[randHeigth][randWidth].getSubjects().add(subject);
            this.subjects.add(subject);
        }

        // placement des Gluttons
        for (int numberGlutton = NUMBER_GLUTTON; 
                numberGlutton >= 0; numberGlutton -= 1)
        {
            randHeigth = (int) ((Math.random()) * HEIGHT) % HEIGHT;
            randWidth = (int) ((Math.random()) * WIDTH) % WIDTH;

            subject = new Glutton(this.cellTab[randHeigth][randWidth],
                    DEFAULT_ENERGY, this);
            this.cellTab[randHeigth][randWidth].getSubjects().add(subject);
            this.subjects.add(subject);
        }
    }

    /**
     * range une liste de sujets par ordre d'énergie.
     * 
     * @param subjectList
     *            Une ArrayList à classer.
     * @return Une liste classée par ordre d'énergie.
     */
    final List<Subject> sortByEnergy(final List<Subject> subjectList)
    {
        final List<Subject> orderedList = new ArrayList<Subject>(subjectList);

        Collections.sort(orderedList, new Comparator<Subject>()
        {
            public int compare(final Subject a, final Subject b)
            {
                return a.energy - b.energy;
            }
        });

        return orderedList;
    }

    /**
     * Avance d'une itération seulement.
     */
    public final void step()
    {
        // Creating the list of subjects, ordered by energy.
        final List<Subject> subjectsByEnergy = this.sortByEnergy(this.subjects);

        for (Subject s : subjectsByEnergy)
        {
            s.move();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Thread#run()
     */
    @Override
    public final void run()
    {
        this.init();

        while (this.simulationRunning)
        {
            if (this.run)
            {
                this.step();
            }
            try
            {
                Thread.sleep(STEP_DURATION);
            }
            catch (InterruptedException ex)
            {
                System.out.println("?");
            }
        }
    }

    public List<Subject> getSubjects()
    {
        return this.subjects;
    }

    public void setSubjects(ArrayList<Subject> subj)

    {
        subjects = subj;
    }
}
