import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class View extends Thread{
	public static final int SPRITE_HEIGHT=16, SPRITE_WIDTH=16;
	public static final int HEIGHT=(SPRITE_HEIGHT)*World.HEIGHT, WIDTH=(SPRITE_WIDTH)*World.WIDTH;

	//sprites
	Hashtable<String, BufferedImage> sprites = new Hashtable<String, BufferedImage>();
	private World theWorld;
	private boolean simulationRunning=true;
	
	public void endSimulation()
	{
		simulationRunning=false;
	}
	
	private void drawSprite(String sprite, int x, int y, Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		BufferedImage currentSprite = null;
		currentSprite = sprites.get(sprite);
		g2d.drawImage(currentSprite, null, x*(SPRITE_WIDTH), y*(SPRITE_HEIGHT));
	}

	class CellLifeComponent extends JComponent {
		//car elle est serializable apparement.
		private static final long serialVersionUID = 1L;
		
		public Dimension getMinimumSize() {
	        return new Dimension(100, 100);
	    }

	    public Dimension getPreferredSize() {
	        return new Dimension(View.WIDTH, View.HEIGHT);
	    }

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			for(int i=0; i<World.WIDTH; i ++)
			{
				for(int j=0; j<World.HEIGHT; j++)
				{
					//draw background
					drawSprite("void", i, j, g);

					//draw food
					if(theWorld.cellTab[i][j].getVegetables()>0)
						drawSprite("vegetable", i, j, g);
					if(theWorld.cellTab[i][j].getMeat()>0)
						drawSprite("meat", i, j, g);
					
					//draw subjects
					Subject subject = null;
					for(int nbSubjects=theWorld.cellTab[i][j].getSubjects().size()-1; nbSubjects>0; nbSubjects--)
					{
						subject = theWorld.cellTab[i][j].getSubjects().get(nbSubjects);
						if( subject instanceof Cannibal)
						{
							drawSprite("cannibal", i, j, g);
						}
						else if(subject instanceof Glutton)
						{
							drawSprite("glutton", i, j, g);
						}
						else if(subject instanceof Erratic)
						{
							drawSprite("erratic", i, j, g);
						}
						else if(subject instanceof Rabbit)
						{
							drawSprite("rabbit", i, j, g);
						}
					}
				}
			}
		}
	}
	
	View(World theWorld)
	{
		this.theWorld= theWorld; 
	}
	
	public void run()
	{
		//open window
		JFrame window = new JFrame("Cell Life !");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CellLifeComponent component = new CellLifeComponent();	
		window.getContentPane().add(component, BorderLayout.CENTER);
		
		//load sprites
		try
		{
			sprites.put("cannibal", ImageIO.read(new File("ressources/cannibal.png")));
			sprites.put("glutton", ImageIO.read(new File("ressources/glutton.png")));
			sprites.put("erratic", ImageIO.read(new File("ressources/erratic.png")));
			sprites.put("rabbit", ImageIO.read(new File("ressources/rabbit.png")));
			sprites.put("void", ImageIO.read(new File("ressources/void.png")));
			sprites.put("vegetable", ImageIO.read(new File("ressources/vegetables.png")));
			sprites.put("meat", ImageIO.read(new File("ressources/meat.png")));
		}
		catch(IOException ex)
		{
			System.out.println("Erreur au chargement des fichiers");
			System.out.println(ex);
		}

		window.pack();
		window.setVisible(true);
		
		//display loop
		while(simulationRunning)
		{
			window.repaint();
			try
			{
				Thread.sleep(10);
			}
			catch(InterruptedException ex) {System.out.println("?");}
		}
		
		window.setVisible(false);
		window.dispose();
	}
}
