package cellLife;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * @author Louis-Marie Givel
 *
 * Thread affichant le jeu.
 */
public class View extends Thread
{
    /**
     * Hauteur d'un sprite, en pixels.
     */
    public static final int SPRITE_HEIGHT = 16;
    /**
     * Largeur d'un sprite, en pixels.
     */
    public static final int SPRITE_WIDTH = 16;
    /**
     * Hauteur de la zone de jeu, en pixels.
     */
    public static final int HEIGHT = SPRITE_HEIGHT * World.HEIGHT;
    /**
     * Largeur de la zone de jeu, en pixels.
     */
    public static final int WIDTH = SPRITE_WIDTH * World.WIDTH;
    
    /**
     * Durée d'un cycle d'affichage.
     */
    public static final int STEP_DURATION = 10;

    /**
     * Liste des sprites.
     */
    private Map<String, BufferedImage> sprites = new Hashtable<String, 
            BufferedImage>();
    /**
     * Lien vers le monde.
     */
    private World theWorld;
    /**
     * Permet de gérer l'arrêt de l'affichage de la simulation.
     */
    private boolean simulationRunning = true;

    /**
     * Constructeur.
     * @param aWorld World avec qui lier.
     */
    View(final World aWorld)
    {
        this.theWorld = aWorld;
    }
    /**
     * Arrêt de l'affichage de la simulation. 
     */
    public final void endSimulation()
    {
        this.simulationRunning = false;
    }

    /**
     * Permet d'afficher un sprite donné à l'écran.
     * @param sprite Le nom du sprite
     * @param x La position en x (cases)
     * @param y La position en y (cases)
     * @param g Le graphics où afficher.
     */
    private void drawSprite(final String sprite, final int x, final int y, 
            final Graphics g)
    {
        final Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(this.sprites.get(sprite), null, x * SPRITE_WIDTH, y
                * SPRITE_HEIGHT);
    }

    /* (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    @Override
    public final void run()
    {
        // open window
        final JFrame window = new JFrame("Cell Life !");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final CellLifeComponent component = new CellLifeComponent();
        window.getContentPane().add(component, BorderLayout.CENTER);

        // load sprites
        try
        {
            this.sprites.put("cannibal",
                    ImageIO.read(new File("ressources/cannibal.png")));
            this.sprites.put("glutton",
                    ImageIO.read(new File("ressources/glutton.png")));
            this.sprites.put("erratic",
                    ImageIO.read(new File("ressources/erratic.png")));
            this.sprites.put("rabbit",
                    ImageIO.read(new File("ressources/rabbit.png")));
            this.sprites.put("void", 
                    ImageIO.read(new File("ressources/void.png")));
            this.sprites.put("vegetable",
                    ImageIO.read(new File("ressources/vegetables.png")));
            this.sprites.put("meat", 
                    ImageIO.read(new File("ressources/meat.png")));
        } catch (IOException ex)
        {
            System.out.println("Erreur au chargement des fichiers");
            System.out.println(ex);
        }

        window.pack();
        window.setVisible(true);

        // display loop
        while (this.simulationRunning)
        {
            window.repaint();
            try
            {
                Thread.sleep(STEP_DURATION);
            } catch (InterruptedException ex)
            {
                System.out.println("?");
            }
        }

        window.setVisible(false);
        window.dispose();
    }
    
    /**
     * @author givello
     * Le composant pour l'affichage du graphics
     */
    class CellLifeComponent extends JComponent
    {
        /**
         * Pour la serialization de la classe.
         */
        private static final long serialVersionUID = 1L;
        /**
         * Hauteur préférée.
         */
        private static final int WINDOW_HEIGHT = 100;
        /**
         * Largeur préférée.
         */
        private static final int WINDOW_WIDTH = 100;

        /**
         * Constructeur par défaut.
         */
        CellLifeComponent()
        {
            super();
        }
        
        /* (non-Javadoc)
         * @see javax.swing.JComponent#getMinimumSize()
         */
        @Override
        public Dimension getMinimumSize()
        {
            return new Dimension(WINDOW_HEIGHT, WINDOW_WIDTH);
        }

        /* (non-Javadoc)
         * @see javax.swing.JComponent#getPreferredSize()
         */
        @Override
        public Dimension getPreferredSize()
        {
            return new Dimension(View.WIDTH, View.HEIGHT);
        }

        /* (non-Javadoc)
         * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
         */
        @Override
        public void paintComponent(final Graphics g)
        {
            super.paintComponent(g);

            for (int i = 0; i < World.WIDTH; i += 1)
            {
                for (int j = 0; j < World.HEIGHT; j += 1)
                {
                    // draw background
                    View.this.drawSprite("void", i, j, g);

                    // draw food
                    if (View.this.theWorld.getCell(i, j).getVegetables() > 0)
                    {
                        View.this.drawSprite("vegetable", i, j, g);
                    }
                    if (View.this.theWorld.getCell(i, j).getMeat() > 0)
                    {
                        View.this.drawSprite("meat", i, j, g);
                    }

                    // draw subjects
                    Subject subject = null;
                    final List<Subject> currentSubjects = 
                            View.this.theWorld.getCell(i, j).getSubjects();
                    for (int nbSubjects = currentSubjects.size() - 1;
                            nbSubjects >= 0; 
                            nbSubjects -= 1)
                    {
                        subject = currentSubjects.get(nbSubjects);
                        if (subject instanceof Cannibal)
                        {
                            View.this.drawSprite("cannibal", i, j, g);
                        } else if (subject instanceof Glutton)
                        {
                            View.this.drawSprite("glutton", i, j, g);
                        } else if (subject instanceof Erratic)
                        {
                            View.this.drawSprite("erratic", i, j, g);
                        } else if (subject instanceof Rabbit)
                        {
                            View.this.drawSprite("rabbit", i, j, g);
                        }
                    }
                }
            }
        }
    }
}
