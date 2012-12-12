
public class World extends Thread{
	public void run()
	{
		while(true)
		{
			try
			{
				Thread.sleep(10);
			}
			catch(InterruptedException ex) {System.out.println("?");}
		}
	}
}