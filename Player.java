import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Player extends Sprite{

	/*
	 * 	W tej klasie robimy postac gracza przez spawn wczytanie tekstur
	 * oraz jej poruszanie	plus strzelanie do enemy
 	*/
	private int dx;
    private int dy;
    private Image playerimg;
    private ArrayList<Missile> missiles;
	
    public Player(int x, int y) 
    {        
        super(x, y);
        initPlayer();
    }
    
    private void initPlayer() 
    {
    	loadImage("/Assety/Grafiki/player.png"); // 35px x 35px why i cannot delete this?!
        missiles = new ArrayList<Missile>();
        getImageDimensions();    
        ImageIcon ii = new ImageIcon("Assety/Grafiki/player.png"); // 35px x 35px
        playerimg = ii.getImage();       	
    }

    public void move() 
    {
        x += dx;
        y += dy;
        if (x < 20) 
        {
            x = 20;
        }
        
        if (x > 450) 
        {
            x = 450;
        }

        if (y >  430) 
        {
            y = 430;
        }

        if (y < 20 || y >  450) 
        {
            y = 20;
        }
    }

    public int getX() 
    {
        return x;
    }

    public int getY() 
    {
        return y;
    }

    public Image getImage() 
    {
        return playerimg;
    }
   

    public void keyPressed (KeyEvent e)
    {
    	Board board = new Board();
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) 
        {
            dx = -1;
        }

        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) 
        {
            dx = 1;
        }

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) 
        {
            dy = -1;
        }

        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
        {
            dy = 1;
        }
        if (key == KeyEvent.VK_SPACE) 
        {
            fire();   
        }   
        if (key == KeyEvent.VK_R) 
        {
            board.restart();
        }
        if (key == KeyEvent.VK_ESCAPE) 
        {
        	System.exit(0);
        }
    }

    public void keyReleased(KeyEvent e) 
    {
        
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) 
        {
            dx = 0;
        }

        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) 
        {
            dx = 0;
        }

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) 
        {
            dy = 0;
        }

        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
        {
            dy = 0;
        }

    }
    
    public void fire() //strzelanie pociskami
    {
        missiles.add(new Missile(x + width, y + height / 2));
    }
    
    public ArrayList<Missile> getMissiles() {
        return missiles;
    }
}

