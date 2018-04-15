
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
class View extends JPanel {
    
    final int runFrameCount = 10;
    final int jumpFrameCount = 8;
    final int fireFrameCount = 4;
    
    final int imageCount = 8;
    BufferedImage[][] runPics;
    BufferedImage[][] jumpPics;
    BufferedImage[][] firePics;

    final int picSize = 165;
    final int frameStartSize = 800;
    
    int xloc = 100;
    int yloc = 100;
    Direction dir = Direction.NORTHEAST;
    Movement movementType = Movement.RUN;;
    boolean paused = false;
    int picNum = 0;

    boolean dirChange = false;
    boolean movChange = false;


    // button-related 
    private JPanel buttonContainer;
    private JButton pauseButton;
    private JButton dirButton;

    
    public View(){
        
    	runPics = new BufferedImage[imageCount][runFrameCount];
        
        int j = 0;
        for(Direction dir:Direction.values()){
            BufferedImage img = createImage("./../images/orc/orc_forward_" + dir.getName() + ".png");
            for(int i = 0; i < runFrameCount; i++) {
                runPics[j][i] = img.getSubimage(picSize*i, 0, picSize, picSize);
            }
            j++;
        }
        
        jumpPics = new BufferedImage[imageCount][jumpFrameCount];
        
        j = 0;
        for(Direction dir:Direction.values()){
            BufferedImage img = createImage("./../images/orc/orc_jump_" + dir.getName() + ".png");
            for(int i = 0; i < jumpFrameCount; i++) {
                jumpPics[j][i] = img.getSubimage(picSize*i, 0, picSize, picSize);
            }
            j++;
        }
        
        firePics = new BufferedImage[imageCount][fireFrameCount];
        
        j = 0;
        for(Direction dir:Direction.values()){
            BufferedImage img = createImage("./../images/orc/orc_fire_" + dir.getName() + ".png");
            for(int i = 0; i < fireFrameCount; i++) {
                firePics[j][i] = img.getSubimage(picSize*i, 0, picSize, picSize);
            }//for
            j++;
        }//for

        // create buttons
        pauseButton = new JButton(new AbstractAction("PAUSE"){
            @Override
            public void actionPerformed(ActionEvent e) {
                // pause/unpause on click
                if (paused) {
                    paused = !paused;
                    pauseButton.setText("PAUSE");
                    // recenter focus to allow keyboard input (only makes sense to allow ctrl after resuming game)
                    requestFocusInWindow();
                }//if
                else{
                    paused = !paused;
                    pauseButton.setText("PLAY");
                }//else if
            }//actionPerformed
        });//JButton pauseButton

        dirButton = new JButton(new AbstractAction("DIRECTION"){
            @Override
            public void actionPerformed(ActionEvent e){
                    //Switching direction.
                    dir = dir.next();
                    dirChange = true;
                    requestFocusInWindow();
            }//actionPerformed
        });//JButton dirButton

        buttonContainer = new JPanel();
        buttonContainer.add(pauseButton);
        buttonContainer.add(dirButton);

        add(buttonContainer);

        setFocusable(true);
        addKeyListener( new KeyListener() {

            @Override
            public void keyTyped( KeyEvent e ) {}

            @Override
            public void keyPressed( KeyEvent e ) {
                int key = e.getKeyCode();

                switch (key) {
                    case (KeyEvent.VK_LEFT):
                        dir=Direction.WEST;
                        dirChange = true;
                        break;
                    
                    case (KeyEvent.VK_UP):
                        dir=Direction.NORTH;
                        dirChange = true;
                        break;

                    case (KeyEvent.VK_RIGHT):
                        dir=Direction.EAST;
                        dirChange = true;
                        break;

                   case (KeyEvent.VK_DOWN):
                        dir=Direction.SOUTH;
                        dirChange = true;
                        break;
                }

                if (key == KeyEvent.VK_F) {
                    // do stuff
                    movementType = Movement.FIRE; 
                    movChange = true;

                }
                else if (key == KeyEvent.VK_J) {
                    // do stuff
                    movementType = Movement.JUMP;
                    movChange = true;
                }
            }

            @Override
            public void keyReleased( KeyEvent e ) {}

        } );



    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.setColor(Color.gray);

        // display whatever action the orc is doing
        switch (movementType.getName()) {
            case ("run"):
                g.drawImage(runPics[dir.getIndex()][(picNum++) % runFrameCount], this.xloc, this.yloc, Color.gray, this);
                break;
            case ("jump"):
                g.drawImage(jumpPics[dir.getIndex()][(picNum++) % jumpFrameCount], this.xloc, this.yloc, Color.gray, this);
                break;
            case ("fire"):
                g.drawImage(firePics[dir.getIndex()][(picNum++) % fireFrameCount], this.xloc, this.yloc, Color.gray, this);
                break;
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(frameStartSize, frameStartSize);
    }
    
    public void update(int xCoor, int yCoor, Direction direction, Movement movement){
    	
        if(!paused){
            // assign new position attributes
            this.xloc = xCoor;
            this.yloc = yCoor;

            if(!dirChange){
                this.dir = direction;
            }

            if(!movChange){
                this.movementType = movement;
            }
            
            // redraw board
            repaint();

        }
        
    }
    
    public int getWidth(){return frameStartSize;}
    
    public int getHeight(){return frameStartSize;}
    
    public int getImageWidth(){return picSize;}
    
    public int getImageHeight(){return picSize;}
    
    
        //Read image from file and return
    public BufferedImage createImage(String filename){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(filename));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}
