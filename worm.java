package snake;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class worm extends JPanel implements KeyListener
{
	//some variables
	private boolean isDead;
	private int wormSize;
	private int wormDirection;//1=up 2=left 3=down 4=right
        private int counter;
	//private int[][] xy;//array to store coordinatezs of tail 1st dimension is tail number 2nd 1 is x and 2 is y
	int[][] xy = new int[1000][2];
	//private int[][] rick;//array to copy coordinates of tail into
	int rick[][] = new int[1000][2];//can't do private int arrays?
	int input = 0;

    //constructor
    public worm(int x,int y, int direction, GameEngine e) 
    {
    	xy[0][0] = x;
    	xy[0][1] = y;
    	wormDirection = direction;
    	isDead = false;
        setFocusable(true);
    }
    
    public int getWormX()
    {
    	return xy[0][0];
    }
    

    	
    public int getWormY()
    {
    	return xy[0][1];
    }
    
    public boolean isDead()
    {
    	return isDead;
    }
    
    public int getInput()
    {
    	return input;
    }

    public void keyPressed(KeyEvent e) 
    {
	    // Move right
	    if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
	    {
	        wormDirection = 4;
	    }
	    
	    // Move left
	    else if (e.getKeyCode() == KeyEvent.VK_LEFT) 
	    {
	        wormDirection = 2;
	    }
	    
	    // Move up
	    else if (e.getKeyCode() == KeyEvent.VK_UP) 
	    {
	        wormDirection = 1;
	    }	    
	    // Move down
	    
	    else if (e.getKeyCode() == KeyEvent.VK_DOWN) 
	    {
	        wormDirection = 3;
	    }	    
	    
	    else if (e.getKeyCode() == KeyEvent.VK_1) 
	    {
	        input = 1;
	    }
		
		else if (e.getKeyCode() == KeyEvent.VK_2) 
	    {
	        input = 2;
	    }
		
		else if (e.getKeyCode() == KeyEvent.VK_3) 
	    {
	        input = 3;
	    }
		
		else if (e.getKeyCode() == KeyEvent.VK_4)
	    {
	        input = 4;
	    }
	    //System.out.println(input);
    }

    public void checkIfAlive()
    {
        for(counter=1;counter<1000;counter++)
        {
            if(xy[0][0]==xy[counter][0] && xy[0][1] == xy[counter][1])
                isDead = true;
        }
    }

    public void move1 (int speed, int size)
    {
        wormSize = size;
        for(counter = wormSize;counter>=0;counter--)
        {
            rick[counter][0] = xy[counter][0];
            rick[counter][1] = xy[counter][1];
        }

    	if (wormDirection == 1)//1 = up
    	{
            if (xy[0][1] < 1)
	       	isDead = true;        
	    else 
               {
                    xy[0][1] = xy[0][1] - speed;
               }
    	}
    	
    	if (wormDirection == 2)//2 = left
    	{
    		if (xy[0][0] < 1) 
	        	isDead = true;
	    	else 
    			xy[0][0] = xy[0][0] - speed;
    	}
    	
    	if (wormDirection == 3)//3 = down
    	{
    		if (xy[0][1] > 370) 
	        	isDead = true;
	      	else 
    			xy[0][1] = xy[0][1] + speed;
    	}
    		
    	if (wormDirection == 4)//4 = right
	    {
	    	if (xy[0][0] > 390) 
	        	isDead = true;  
	    	else 
    			xy[0][0] = xy[0][0] + speed;    	
   	    }
        for(counter=wormSize;counter>0;counter--)
        {
            xy[counter][0]=rick[(counter-1)][0];
            xy[counter][1]=rick[(counter-1)][1];
        }
    }
    
    public void render(Graphics g) 
    {
	    //Worm
	    g.setColor(Color.green);
	    for(counter = 0;counter<wormSize;counter++)
            {
                g.fillOval(xy[(counter)][0], xy[(counter)][1], 8, 8);
            }
    }
    
    //methods required for keylistener implementation but are not used
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}      
}