import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean vis;
    protected Image image;
    protected Image enemy;
    
    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        vis = true;
    }

    protected void loadImage(String imageName) 
    {
    	ImageIcon ii = new ImageIcon("Assety/Grafiki/missile.png"); //35px x 35px
        image = ii.getImage();
    	ImageIcon ee = new ImageIcon("Assety/Grafiki/enemy.png"); //40px x 60px
        enemy = ee.getImage();
    }
    
    protected void getImageDimensions() 
    {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }    
    
    protected void getEnemyDimensions() 
    {
        width = enemy.getWidth(null);
        height = enemy.getHeight(null);
    } 

    public Image getImage() 
    {
        return image;
    }
    
    public Image getEnemy(){
    	return enemy;
    }

    public int getX() 
    {
        return x;
    }

    public int getY() 
    {
        return y;
    }

    public boolean isVisible() 
    {
        return vis;
    }

    public void setVisible(Boolean visible) 
    {
        vis = visible;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
}