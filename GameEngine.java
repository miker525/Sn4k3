package snake;

 

import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class GameEngine extends JPanel implements MouseListener, 
                                       MouseMotionListener, ActionListener {
  // Constants
  
  // Fields / Instance Variables
  private worm myWorm;
  private food myFood;
  private int randX;
  private int randY;
  private int size;
  private Random rand;
  boolean menu; 
  int input2;
  
  // Collections 
  //private List enemies;

  
  // Bookeeping fields
  private boolean gameOver;
  private int score;  
  final private int SPEED = 6;
  
  // Constructor
  public GameEngine() {
    // Create a new Ship and place it at x=200 in this JPanel
    rand = new Random();
    randX = rand.nextInt(389);
    randY = rand.nextInt(365);
    myWorm = new worm(200,200,4,this); //x coordinate, y coordinate, size, direction, game engine

    myFood = new food(randX,(randY+1));
    // Initialize the starting game state
    initBookeepingFields();
    initGraphics();
    size = 1;
    menu = true;

  }

  private void initBookeepingFields() {
    gameOver = false;
    score = 0;  
  
  }
  
  public int getInput()
  {
  	return input2;
  }
  
  private void initGraphics()
  {
    setPreferredSize(new Dimension(400,400));
    addMouseListener(this);
    addMouseMotionListener(this);
    setFocusable(true);
    addKeyListener(myWorm);
  }

  public void checkIfMenu()
  {
  	    if (menu == true)
    {
    	input2 = myWorm.getInput();
    	if (input2 == 1 || input2 == 2 || input2 == 3 || input2 == 4)
    	{
    		menu = false;
    	}
    }
  }

  public void checkIfEaten()
  {
  	if(Math.abs(myWorm.getWormX()-myFood.getFoodX()) <= 8 && Math.abs(myWorm.getWormY()-myFood.getFoodY()) <= 8)
  	{
  		myFood.setIsEaten(true);
  		myFood.setFood(rand.nextInt(389),rand.nextInt(365));
  		System.out.println("Yo, we gots some new food up in here, dawg");
  		score++;
  		size++;
  	}
  }
  
  /* The timer calls this method every time it "ticks". */
  public void actionPerformed(ActionEvent event) {
    if (gameOver) 
    {
      return;   
    }
    if (gameOver == false && menu == false)
    {
      myWorm.move1(SPEED, size);
      myWorm.checkIfAlive();
      repaint();
      checkIfEaten();
      return;
    }
  }
    
    // The game is still in play, so call step() for all the objects
  
  /* Paints ("renders") the scene */
    @Override
  public void paintComponent(Graphics g) 
  	{
  	if (menu==true)
  	{
		super.paintComponent(g); // magic incantation
	    // Paint the background
	    g.setColor(Color.black);
	    g.fillRect(0,0,400,400);   
  		
		g.setColor(Color.white);
        g.drawString("Select a difficulty to begin", 130, 100);
        g.drawString("1-easy, 2-medium, 3-hard, 4-very hard", 100, 115);
        g.drawString("Sn4k3", 175, 200);
        g.drawString("Designed in Java by:", 135, 215);
        g.drawString("Josh Haimson", 150, 230);
        g.drawString("Mike Rosenberger", 140, 245);
        g.drawString("http://www.miker525.info/", 120, 260);
  	}
  	
  	else
  	{
	    super.paintComponent(g); // magic incantation
	    // Paint the background
	    g.setColor(Color.black);
	    g.fillRect(0,0,400,400);     
	    // Display the score in yellow
	    g.setColor(Color.green);
	    g.drawLine(0,375,400,375);
	    g.drawString("Score: " + score, 5, 387);
	    
	    // If myShip is dead, the game is over, so just display a "GAME OVER" screen and return.
	    if (gameOver || myWorm.isDead()) {
	      gameOver = true;  
	      g.setColor(Color.white);
	      g.drawString("GAME OVER", 170, 200);
	      //System.out.println("Errors: " + errors);
	      return;
	    }    
	    // Otherwise, the game is still on, so render all the objects.
	    myWorm.render(g);
	    myFood.render(g);
  	}
  }
  
  /* These methods are required by the interfaces MouseListener/MouseMotionListener 
   * but are not needed for this application, so their bodies are empty.
   */
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }
  public void mousePressed(MouseEvent e) { }
  public void mouseReleased(MouseEvent e) { }
  public void mouseClicked(MouseEvent e) { }
  public void mouseMoved(MouseEvent e) { }
  public void mouseDragged(MouseEvent e) {}
}
