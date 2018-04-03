
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Controller extends JFrame {
    
    Action drawAction;
    final int drawDelay = 30; //msec
    
    View view = new View();
    Model model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());

    public Controller() {
    	drawAction = new AbstractAction(){
    		public void actionPerformed(ActionEvent e){
                model.updateLocationAndDirection();
    			view.update(model.getX(), model.getY(), model.getDirect());
    		}
    	};
    	
    	
        add(view);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(view.getWidth(), view.getHeight());
    	setVisible(true);
    	pack();
    }
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Controller a = new Controller();
				Timer t = new Timer(a.drawDelay, a.drawAction);
				t.start();
			}
		});
	}
    
}

