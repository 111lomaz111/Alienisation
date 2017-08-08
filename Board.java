import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.util.Random;

public class Board extends JPanel implements ActionListener {

    private final int IPLAYER_X = 50; 
    private final int IPLAYER_Y = 225;
	private static final long serialVersionUID = 1L;
	private Player player;
    private Timer timer;
    private final int DELAY = 5;
	private Image background;
	private final int B_WIDTH = 500; 
    private final int B_HEIGHT = 500;
    private ArrayList<Alien> aliens;
    private boolean ingame;
    private boolean restart;
    private int n; // (100 <= m)  
    private int m; // (30 <= s <= 400) 
    private int iloscZabojstw;
    private int iloscEnemy = 8;
    int[][] pos;


    public Board() 
    {
    	initBoard();
    }
    
    public void initBoard() //
    {        
    	ingame = true;
		restart = false;
        loadImage();        
        int w = background.getWidth(this);
        int h =  background.getHeight(this);
        setPreferredSize(new Dimension(w, h));
        addKeyListener(new TAdapter());
        setFocusable(true);
        player = new Player(IPLAYER_X, IPLAYER_Y);        
        timer = new Timer(DELAY, this);
        timer.start(); 
        aliens = new ArrayList<>();
        updateResp();      
    }   
    
    private void loadImage()
    {        
        ImageIcon ii = new ImageIcon("Assety/Grafiki/background.png");
        background = ii.getImage();        
    }
    
    private void initLos()
    {
    	Random generator = new Random();
    	m = generator.nextInt(370)+30;
    	n = generator.nextInt(100)+500;
    }
    
    private int[][] pos()
    {    	
    	initLos();
    	pos = new int[][] {{n, m}, };	
    	return pos;
    }

    public void updateResp()
    {	
    	if (aliens.size() < iloscEnemy)
    	{
        	pos();
        	initAliens();
    	}
    }    

	public void actionPerformed(ActionEvent e) 
    {        
    	inGame();
    	player.move();
    	updateResp();
        checkCollisions();
        updateMissiles();
        updatePlayer();
        updateAliens();
        repaint();  
    }
    
    private void inGame() 
    {     
        if (!ingame) 
        {
            timer.stop();
        }       
    }
    
    public void restart() //restart...
    {
    	 System.out.println("");
    	 System.out.println("befor restart = " + Boolean.toString(restart));
    	 restart = true;
    	 System.out.println("");
         System.out.println("after restart = " + Boolean.toString(restart));
         System.out.println("ingame = " + Boolean.toString(ingame));
         checkInGame();
   }
    
    private void checkInGame() 
    {
    	if(restart == true && ingame == false)
    	{
    		System.out.println("");
    		restart = false;
    		initBoard();
            System.out.println("incheckingame restart = " + Boolean.toString(restart));
            System.out.println("incheckingame ingame = " + Boolean.toString(ingame));
    		System.out.println("checkInGame done");
    	}
		else
		{
			System.out.println("U are in game right now!");	
		}
    }
    
      
    private void updateMissiles() 
    {
        ArrayList<Missile> ms = player.getMissiles();
        for (int i = 0; i < ms.size(); i++) 
        {
            Missile m = ms.get(i);            
            if (m.isVisible()) 
            {
                m.move();
            } else 
            {
                ms.remove(i);
            }
        }
    }    
    
    
    public void initAliens() {
        //aliens = new ArrayList<>();
        for (int[] p : pos) {
            aliens.add(new Alien(p[0], p[1]));            
        }
    }
    
    private void updateAliens() 
    {
        if (aliens.isEmpty()) 
        {        	
        	ingame = false;
           	return;
        }
        
        for (int i = 0; i < aliens.size(); i++) 
        {
            Alien a = aliens.get(i);
            if (a.isVisible()) {
                a.move();
            } else {
                aliens.remove(i);
            }
        }
    }
    
    private void updatePlayer() 
    {
    	if (player.isVisible()) 
    	{
            player.move();
        }
    }

    private class TAdapter extends KeyAdapter 
    {
        @Override
        public void keyReleased(KeyEvent e) 
        {
        	player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e)
        {
        	player.keyPressed(e);
        }
    }   

    public void paintComponent(Graphics g) 
    { 	       
        super.paintComponent(g);    
        if (ingame) 
        {
            doDrawing(g);
        } 
        else 
        {
            drawGameOver(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void doDrawing(Graphics g)
    {
    	g.drawImage(background, 0, 0, null);
        Graphics2D g2p = (Graphics2D) g;
        Graphics2D g2m = (Graphics2D) g;
        Graphics2D g2a = (Graphics2D) g;

        if (player.isVisible()) 
        {
            g2p.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        ArrayList<Missile> ms = player.getMissiles();

        for (Missile m : ms) 
        {
            if (m.isVisible()) 
            {
                g2m.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
        }

        for (Alien a : aliens) 
        {
            if (a.isVisible()) 
            {
                g2a.drawImage(a.getEnemy(), a.getX(), a.getY(), this);
            }
        }
        
        String move = "Sterring WSAD + SPACJA";
        String enemys = "Enemys Killed: ";
        Font small = new Font("Arial", Font.BOLD, 20);
        g.setFont(small);
        //g.drawString(enemys + aliens.size(), 100, 420); //iloscZabojstw
        g.drawString(enemys + iloscZabojstw, 30, 460);
        g.drawString(move, 30, 50);
    }
    
    private void drawGameOver(Graphics g) 
    {
        String end0 = "Koniec gry";
    	if (aliens.size()>0)
    	{
            //String end1 = "Przegra³eœ, pozosta³o przeciwników: ";
            String end2137 = "You killed: ";
            Font small = new Font("Arial", Font.BOLD, 20);
            FontMetrics fm = getFontMetrics(small);
            g.setColor(Color.black);
            g.setFont(small);
            g.drawString(end0, (B_WIDTH - fm.stringWidth(end0)) / 2, B_HEIGHT / 2);
           // g.drawString(end1 + aliens.size() , (B_WIDTH - fm.stringWidth(end1)) / 2, B_HEIGHT / 3); 
            g.drawString(end2137 + iloscZabojstw , (B_WIDTH - fm.stringWidth(end2137)) / 2, B_HEIGHT / 3);
    	}
    	else
    	{
	        String end2 = "GJ! You killed everyone enemy!"; // poprawnosc polityczna musi byc XD
	        Font small = new Font("Arial", Font.BOLD, 20);
	        FontMetrics fm = getFontMetrics(small);
	        g.setColor(Color.black);
	        g.setFont(small);
	        g.drawString(end0, (B_WIDTH - fm.stringWidth(end0)) / 2, B_HEIGHT / 2);
	        g.drawString(end2, (B_WIDTH - fm.stringWidth(end2)) / 2, B_HEIGHT / 3);

    	}
    }

    public void checkCollisions()
    {
        Music music = new Music();
        Rectangle r3 = player.getBounds();
        for (Alien alien : aliens) {
            Rectangle r2 = alien.getBounds();

            if (r3.intersects(r2)) 
            {
                player.setVisible(false);
                alien.setVisible(false);
                ingame = false;
            }
        }

        ArrayList<Missile> ms = player.getMissiles();

        for (Missile m : ms) 
        {
            Rectangle r1 = m.getBounds();
            for (Alien alien : aliens) 
            {
                Rectangle r2 = alien.getBounds();                
                if (r1.intersects(r2)) 
                {
                    music.throwSound("Assety/Audio/getHit.wav");
                    m.setVisible(false);
                    alien.setVisible(false);
                    iloscZabojstw++;
                }
            }
        }
    }
}