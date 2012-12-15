import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Terminal extends Thread {
	public enum Command { START, STOP, STEP, FINISH }
	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	
	private World theWorld;
	
	Terminal(World theWorld)
	{
		this.theWorld = theWorld;
	}
	
	Command getCommand()
	{
		Command theCommand=null;
		String theString = new String();

		while(theCommand == null)
		{
			System.out.print(">");
			try{
				theString = bufferedReader.readLine();
			}catch(IOException e){
				System.out.println("Fail: " + e);
			}
			
			if(theString.equals("stop"))
			{
				theCommand = Command.STOP;
			}
			else if(theString.equals("start"))
			{
				theCommand = Command.START;
			}
			else if(theString.equals("step")||theString.equals("s"))
			{
				theCommand = Command.STEP;
			}
			else if(theString.equals("finish"))
			{
				theCommand = Command.FINISH;
			}
			else
			{
				theCommand = null;
			}
		}
		
		return theCommand;
	}
	
	//returns : the running value of the loop.
	boolean actOnCommand()
	{
		boolean keepGoing =true;
		switch(getCommand())
		{
			case STOP:
				theWorld.stopSimulation();
				break;
			case START:
				theWorld.startSimulation();
				break;
			case STEP:
				theWorld.step();
				break;
			case FINISH:
				try{
				theWorld.endSimulation();
				}catch(Exception e){System.out.println(e);}
				keepGoing=false;
		}
		return keepGoing;
	}
	
	public void run ()
	{
		boolean running=true;
		while(running)
		{
			running = actOnCommand();
		}
	}
}