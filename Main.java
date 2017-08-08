
import java.awt.EventQueue;
import javax.swing.JFrame;


public class Main extends JFrame {
    
	private static final long serialVersionUID = 1L;
	public Main() 
    {
		//JFrame frame = new JFrame();
		//frame.dispose();
        initUI();
    }

    private void initUI() 
    {
        add(new Board());
        setSize(505, 530);
        setTitle("Rocket Man");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }    
    
    public static void main(String[] args) 
    {       
        EventQueue.invokeLater(new Runnable() {
        	@Override
            public void run() {
               Main main = new Main();
               main.setVisible(true);
               Music music = new Music();
               music.playMusic(null);
            }
        });
    }
   }
