package snake;

/*
 * SN4K3 
 * Developed By
 * Mike Rosenberg
 * Josh Haimson
 * http://mikerosenberger.com
 * http://www.josh-haimson.com/
 */
 import java.awt.Toolkit;
 import java.awt.Dimension;
 import javax.swing.*;
 import javax.imageio.ImageIO;
 import java.awt.image.BufferedImage;
 import java.io.IOException;
 
 
public class Snake{
    
    public static void main(String[] args) {
    

     System.out.println("Sn4k3 is awesome");

     //Have it open in the middle of the screen
     Toolkit tk;
     tk=Toolkit.getDefaultToolkit();
     Dimension ScreenSize;
     ScreenSize=tk.getScreenSize();
     int screenHeight, screenWidth;
     screenHeight=ScreenSize.height;
     screenWidth=ScreenSize.width;

     JFrame frame = new JFrame("SN4K3");
     frame.setSize(400,400);
     // Set it up so that if the window is closed, the application terminates:
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
     frame.setResizable(false);
     frame.setLocation(screenWidth/3, screenHeight/4); //places form in center screen
     GameEngine engine = new GameEngine();
     frame.setContentPane(engine); // Set the JPanel/canvas to be our GameEngine
     frame.pack(); // some kind of magic
     frame.setVisible(true); // make it visible

     //Loads Sn4K3 icon.png
     BufferedImage image = null;
     try
     {
         image = ImageIO.read(frame.getClass().getResource("/snake/icon.png"));
     }
     catch (IOException e)
     {
         e.printStackTrace();
     }
     frame.setIconImage(image);
   
     
	//Check the input from menu and if there is no input display menu
     while(engine.getInput() == 0)
     engine.checkIfMenu();
     {
        //put in a 15 milisecond delay so that it doesn't kill the processor
	    try
	    {
	        Thread.sleep(15);
	    }
	    catch(InterruptedException exc)
	    {
	        exc.printStackTrace();
	    }

     }

	 int millisecs = 0;
	 //change speed based on user input
     if(engine.getInput() == 1)
         millisecs = 60;
     if(engine.getInput() == 2)
         millisecs = 45;
     if(engine.getInput() == 3)
         millisecs = 30;
     if(engine.getInput() == 4)
         millisecs = 15;

     //add a 1 second delay so that the user has time to position their hands
     //menu.removeKeyListener();
     try
	 {
     	Thread.sleep(1000);
	 }
	 catch(InterruptedException exc)
	 {
	     exc.printStackTrace();
	 }
	 
	 /* Create a timer, which "ticks" like a clock. */
     Timer timer = new Timer(millisecs, engine);
     // Start the timer, which starts the game. Here's how it works:
     /* Every millisec milliseconds, timer calls engine.actionPerformed(), 
      * which updates the game state and re-paints/renders the scene.
      */
     timer.start();

	     
    }
	
}
