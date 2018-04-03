
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Animation4Thread extends JFrame {
    
    Action drawAction;
    final int drawDelay = 30; //msec
    
    DrawPanel drawPanel = new DrawPanel();
    Model model = new Model(drawPanel.getWidth(), drawPanel.getHeight(), drawPanel.getImageWidth(), drawPanel.getImageHeight());

    public Animation4Thread() {
    	drawAction = new AbstractAction(){
    		public void actionPerformed(ActionEvent e){
                model.updateLocationAndDirection();
    			drawPanel.update(model.getX(), model.getY(), model.getDirect());
    		}
    	};
    	
    	add(drawPanel);

   
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(drawPanel.getWidth(), drawPanel.getHeight());
    	setVisible(true);
    	pack();
    }
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Animation4Thread a = new Animation4Thread();
				Timer t = new Timer(a.drawDelay, a.drawAction);
				t.start();
			}
		});
	}
    
}

