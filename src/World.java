import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class World extends Thread{
	
	public static final int HEIGHT = 30 ;
	public static final int WIDTH= 30 ;
	
	public static final int NUMBER_RABBIT = 10;
	public static final int NUMBER_CANNIBAL = 10;
	public static final int NUMBER_ERRATIC = 10;
	public static final int NUMBER_GLUTTON = 10;
	
	public Cell cellTab [][] = new Cell[HEIGHT][WIDTH];
	public ArrayList<Subject> subjects = new ArrayList<Subject>();
	
	private boolean run=true, simulationRunning=true;
	private View theView;

	public Cell getCell(int height ,int width)
	{
		return cellTab[height][width];
	}

	
	public void startSimulation()
	{
		run=true;
	}
	public void stopSimulation()
	{
		run=false;
	}
	public void endSimulation()
	{
		simulationRunning=false;
		theView.endSimulation();
	}
	
	World(View theView)
	{
		this.theView = theView;
	}
	
	public void init()
	{	
		int randHeigth;
		int randWidth;
		Subject subject;
		
		for(int i=0; i < HEIGHT; i++)
		{
			for (int j = 0 ; j < WIDTH ; j++)
			{
				cellTab[i][j] = new Cell(i,j);
			}
		}
		
		//Adding all the subjects to the world
		//placement des erratics
		for(int numberErratic = NUMBER_ERRATIC; numberErratic >= 0; numberErratic--)
		{
			randHeigth = (int) ((Math.random())*100) % HEIGHT;
			randWidth = (int) ((Math.random())*100) % WIDTH;
			
			subject = new Erratic(cellTab[randHeigth][randWidth], 100);
			cellTab[randHeigth][randWidth].getSubjects().add(subject);
			subjects.add(subject);
		}
		
		//placement des Rabbits
		for(int numberRabbit = NUMBER_RABBIT; numberRabbit>= 0; numberRabbit--)
		{
			
			randHeigth = (int) ((Math.random())*100) % HEIGHT;
			randWidth = (int) ((Math.random())*100) % WIDTH;
			
			subject = new Rabbit(cellTab[randHeigth][randWidth], 100);
			cellTab[randHeigth][randWidth].getSubjects().add(subject);
			subjects.add(subject);
		}
		
		//placement des Cannibals
		for(int numberCannibal= NUMBER_CANNIBAL; numberCannibal>= 0; numberCannibal--)
		{
			randHeigth = (int) ((Math.random())*100) % HEIGHT;
			randWidth = (int) ((Math.random())*100) % WIDTH;
			
			subject = new Cannibal(cellTab[randHeigth][randWidth], 100);
			cellTab[randHeigth][randWidth].getSubjects().add(subject);
			subjects.add(subject);
		}
		
		//placement des Gluttons
		for(int numberGlutton= NUMBER_GLUTTON; numberGlutton>= 0; numberGlutton--)
		{
			randHeigth = (int) ((Math.random())*100) % HEIGHT;
			randWidth = (int) ((Math.random())*100) % WIDTH;
			
 			subject = new Glutton(cellTab[randHeigth][randWidth], 100);
			cellTab[randHeigth][randWidth].getSubjects().add(subject);
			subjects.add(subject);
		}
	}
	
	/*
	 * @param subjectList une liste de sujets quelconque.
	 * @return orderedList une nouvelle liste des sujets non modifiée.
	 */
	ArrayList<Subject> sortByEnergy(ArrayList<Subject> subjectList)
	{
		ArrayList<Subject> orderedList = new ArrayList<Subject>(subjectList);
		
		Collections.sort(orderedList, new Comparator<Subject> () {
			public int compare(Subject a, Subject b)
			{
				return a.energy - b.energy;
			}
		});
		
		return orderedList;
	}
	
	public void step()
	{
		//Creating the list of subjects, ordered by energy.
		ArrayList<Subject> subjectsByEnergy = sortByEnergy(subjects);
		
		for(Subject s: subjectsByEnergy)
		{
			s.move();
		}
	}
	
	public void run()
	{
		init();
		
		while(simulationRunning)
		{
			if(run)
			{
				step();
			}
			try
			{
				Thread.sleep(100);
			}
			catch(InterruptedException ex) {System.out.println("?");}
		}
	}
}