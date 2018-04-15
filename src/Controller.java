
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.Timer;

// imports added to assist jbutton
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;

public class Controller extends JFrame{
    
    Action drawAction;
    final int drawDelay = 30; //msec

    View view = new View();
    Model model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());

    public Controller() {
        drawAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                // update based on whether the game is "paused"

                model.updateLocationAndDirection(view.paused);
                view.update(model.getX(), model.getY(), model.getDirect(), model.getMovement());

                if(view.dirChange){
                    model.dir = view.dir;
                    view.dirChange = false;
                }

                if(view.movChange){
                    model.changeMovement(view.movementType);
                    view.movChange = false;
                }


                //if
            }//actionPerformed
        };//AbastractAction

        // adding image features

        // add icons (can be used later on...)
        // ImageIcon playIcon = new ImageIcon("./../images/play.png", "PLAY");
        // ImageIcon pauseIcon = new ImageIcon("./../images/pause.png", "PAUSE");
        
        
        // position on board (needed or not?)
        // playButton.setVerticalTextPosition(AbstractButton.CENTER);
        // playButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        // pauseButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        // pauseButton.setHorizontalTextPosition(AbstractButton.CENTER);
        
        // set action from button
        // pauseButton.setActionCommand("pause");       // **MAY BE NEEDED LATER ON...**

        // for some reason, view has to be added AFTER pauseButton to be able to see the button...
        
        add(view);
        
        // add the settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());                // i added this (helps display the button correctly)
        setSize(view.getWidth(), view.getHeight());
        setVisible(true);
        // pack();      // sets frame to its "preferred" size (doesn't help when using a button)
        
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

