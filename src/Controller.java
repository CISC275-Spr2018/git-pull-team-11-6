
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.Timer;

// imports added to assist jbutton
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractButton;
import java.awt.FlowLayout;

public class Controller extends JFrame implements ActionListener {
    
    Action drawAction;
    final int drawDelay = 30; //msec

    // button-related vars
    private JButton pauseButton;
    boolean paused = false;
    
    View view = new View();
    Model model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());

    public Controller() {
    	drawAction = new AbstractAction(){
    		public void actionPerformed(ActionEvent e){
    			// update based on whether the game is "paused"
    			if (!paused) {
	                model.updateLocationAndDirection();
	    			view.update(model.getX(), model.getY(), model.getDirect());
	    		}
    		}
    	};

    	// adding image features

    	// add icons (can be used later on...)
        // ImageIcon playIcon = new ImageIcon("./../images/play.png", "PLAY");
        // ImageIcon pauseIcon = new ImageIcon("./../images/pause.png", "PAUSE");

        // create buttons
        pauseButton = new JButton("PAUSE");
        // pauseButton = new JButton("PAUSE", pauseIcon);

        // position on board (needed or not?)
        // playButton.setVerticalTextPosition(AbstractButton.CENTER);
        // playButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        // pauseButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        // pauseButton.setHorizontalTextPosition(AbstractButton.CENTER);

        // not sure what this does...
        pauseButton.setMnemonic(KeyEvent.VK_E);

        // set action from button
        // pauseButton.setActionCommand("pause");		// **MAY BE NEEDED LATER ON...**

        pauseButton.addActionListener(this);

        // for some reason, view has to be added AFTER pauseButton to be able to see the button...
        add(pauseButton);
        add(view);
        
        // add the settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());				// i added this (helps display the button correctly)
    	setSize(view.getWidth(), view.getHeight());
    	setVisible(true);
    	// pack();		// sets frame to its "preferred" size (doesn't help when using a button)
    	
    }

    // actively listen for button events
    public void actionPerformed(ActionEvent e) {
        // if ("pauseOrc".equals(e.getActionCommand())) {
            // pauseButton.setEnabled(true);		// i think this "grays out" the button (can't be used)

    	// pause/unpause on click
    	if (paused) {
    		paused = !paused;
    		System.out.println("playing");
    		pauseButton.setText("PAUSE");
    	}
    	else if (!paused) {
    		paused = !paused;
    		System.out.println("pausing");
    		pauseButton.setText("PLAY");
    	}

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

