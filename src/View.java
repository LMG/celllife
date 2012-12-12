import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class View extends Thread{
	class CellLifeComponent extends JComponent {
		//car elle est serializable apparement.
		private static final long serialVersionUID = 1L;
		private int i=0;
		
		public Dimension getMinimumSize() {
	        return new Dimension(100, 100);
	    }

	    public Dimension getPreferredSize() {
	        return new Dimension(400, 300);
	    }

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
		}
	}
	
	public void run()
	{
		JFrame window = new JFrame("Cell Life !");
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CellLifeComponent component = new CellLifeComponent();		
		window.getContentPane().add(component, BorderLayout.CENTER);

		window.pack();
		window.setVisible(true);
		
		while(true)
		{
			window.repaint();
			try
			{
				Thread.sleep(500);
			}
			catch(InterruptedException ex) {System.out.println("?");}
		}
	}
}