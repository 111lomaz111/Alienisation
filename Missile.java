

public class Missile extends Sprite {

    private final int BOARD_WIDTH = 450;
    private final int MISSILE_SPEED = 2;

    public Missile(int x, int y) 
    {
        super(x, y);        //pozycja startowa pocisku
        initMissile();
    }
    
    private void initMissile() 
    {        
        loadImage("kremowka.png");  
        getImageDimensions();
    }


    public void move()
    {        
        x += MISSILE_SPEED;        
        if (x > BOARD_WIDTH) {
            vis = false;
        }

    }
}
