package snake;

import java.awt.*;

public class food 
{
	private int foodX;
	private int foodY;
	private boolean isEaten;

    public food(int x, int y) 
    {
    	foodX = x;
    	foodY = y;
    	isEaten = false;   
    }

	public void render(Graphics g) 
    {
	    //Food
	    g.setColor(Color.yellow);
	    g.fillOval(foodX, foodY, 8, 8);
    }    
    
    public void setFood(int x, int y)
    {
    	foodX = x;
    	foodY = y;
    	isEaten = false;
    }
    
    public int getFoodX()
    {
    	return foodX;
    }
    
    public int getFoodY()
    {
    	return foodY;
    }
    
    public void setIsEaten(boolean carl)
    {
    	isEaten = carl;
    }
}