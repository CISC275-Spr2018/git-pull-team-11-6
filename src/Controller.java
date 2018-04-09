
import java.awt.Color;
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
import javax.swing.JPanel;

public class Controller extends JFrame{
    
    Action drawAction;
    final int drawDelay = 30; //msec

    // button-related vars
    private JPanel buttonContainer;
    private JButton pauseButton;
    private JButton dirButton;
    private JButton EButton;
    private JButton WButton;
    private JButton NButton;
    private JButton SButton;

    View view = new View();
    Model model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());

    public Controller() {
    	drawAction = new AbstractAction(){
    		public void actionPerformed(ActionEvent e){
    			// update based on whether the game is "paused"
    			if (!model.paused) {
	                model.updateLocationAndDirection();
	    			view.update(model.getX(), model.getY(), model.getDirect());
	    		}//if
    		}//actionPerformed
    	};//AbastractAction

    	// adding image features

    	// add icons (can be used later on...)
        // ImageIcon playIcon = new ImageIcon("./../images/play.png", "PLAY");
        // ImageIcon pauseIcon = new ImageIcon("./../images/pause.png", "PAUSE");

        // create buttons
        pauseButton = new JButton(new AbstractAction("PAUSE"){
            @Override
            public void actionPerformed(ActionEvent e) {
                // pause/unpause on click
                if (model.paused) {
                    model.paused = !model.paused;
                    System.out.println("playing");
                    pauseButton.setText("PAUSE");
                }//if
                else{
                    model.paused = !model.paused;
                    System.out.println("pausing");
                    pauseButton.setText("PLAY");
                }//else if
            }//actionPerformed
        });//JButton pauseButton

        dirButton = new JButton(new AbstractAction("DIRECTION"){
            @Override
            public void actionPerformed(ActionEvent e){
                    //Switching direction.
                    System.out.println("Changing direction from "+model.dir.getName()+" to "+model.dir.next().getName());
                    model.dir = model.dir.next();
            }//actionPerformed
        });//JButton dirButton
        
        EButton = new JButton(new AbstractAction("EAST"){
            @Override
            public void actionPerformed(ActionEvent e){
                    //Switching direction.
                    System.out.println("Changing direction to EAST");
                    model.dir = Direction.EAST;
            }//actionPerformed
        });//JButton dirButton
        
        WButton = new JButton(new AbstractAction("WEST"){
            @Override
            public void actionPerformed(ActionEvent e){
                    //Switching direction.
                    System.out.println("Changing direction to WEST");
                    model.dir = Direction.WEST;
            }//actionPerformed
        });//JButton dirButton

        NButton = new JButton(new AbstractAction("NORTH"){
            @Override
            public void actionPerformed(ActionEvent e){
                    //Switching direction.
                    System.out.println("Changing direction to NORTH");
                    model.dir = Direction.NORTH;
            }//actionPerformed
        });//JButton dirButton
        
        SButton = new JButton(new AbstractAction("SOUTH"){
            @Override
            public void actionPerformed(ActionEvent e){
                    //Switching direction.
                    System.out.println("Changing direction to SOUTH");
                    model.dir = Direction.SOUTH;
            }//actionPerformed
        });//JButton dirButton
        
        
        // position on board (needed or not?)
        // playButton.setVerticalTextPosition(AbstractButton.CENTER);
        // playButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        // pauseButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        // pauseButton.setHorizontalTextPosition(AbstractButton.CENTER);

        // not sure what this does...
        //pauseButton.setMnemonic(KeyEvent.VK_P);
        //dirButton.setMnemonic(KeyEvent.VK_D);
        
        // set action from button
        // pauseButton.setActionCommand("pause");		// **MAY BE NEEDED LATER ON...**

        // for some reason, view has to be added AFTER pauseButton to be able to see the button...
        
        buttonContainer = new JPanel();
        buttonContainer.setBackground(view.buttonBarBG);

        buttonContainer.add(pauseButton);
        buttonContainer.add(dirButton);
        buttonContainer.add(EButton);
        buttonContainer.add(WButton);
        buttonContainer.add(SButton);
        buttonContainer.add(NButton);
        view.add(buttonContainer);
        add(view);
        
        // add the settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());				// i added this (helps display the button correctly)
    	setSize(view.getWidth(), view.getHeight());
    	setVisible(true);
    	// pack();		// sets frame to its "preferred" size (doesn't help when using a button)
    	
    }//Controller Constructor
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Controller a = new Controller();
				Timer t = new Timer(a.drawDelay, a.drawAction);
				t.start();
			}//run
		});//runnable
	}//main
    
}//Controller

