/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

class View  extends JPanel {
    
    // object dimensions
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;

    JFrame frame;

    // image import characteristics
    BufferedImage[][] pics;
    final int imageCount = 8;
    final int frameCount = 10;
    // NOTE: all images must be in path './images/orc/'
    //final String[] image_pathes = {"./../images/orc/orc_forward_north.png", "./../images/orc/orc_forward_northeast.png",
    //                            "./../images/orc/orc_forward_east.png", "./../images/orc/orc_forward_southeast.png",
    //                            "./../images/orc/orc_forward_south.png", "./../images/orc/orc_forward_southwest.png",
    //                            "./../images/orc/orc_forward_west.png", "./../images/orc/orc_forward_northwest.png",
    //                            "./../images/orc/orc_jump_north.png", "./../images/orc/orc_jump_northeast.png",
    //                            "./../images/orc/orc_jump_east.png", "./../images/orc/orc_jump_southeast.png",
    //                            "./../images/orc/orc_jump_south.png", "./../images/orc/orc_jump_southwest.png",
    //                            "./../images/orc/orc_jump_west.png", "./../images/orc/orc_jump_northwest.png"};
    //
    // orc's current position on the board
    int xloc = 0;
    int yloc = 0;
    Direction dir;
    int picNum = 0;

    public View(){
        
        frame = new JFrame();
    	frame.getContentPane().add(this);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);

    	// grab the orc images from path
    	importImages();
        
    }
    
    public int getWidth(){return frameWidth;}
    
    public int getHeight(){return frameHeight;}
    
    public int getImageWidth(){return imgWidth;}
    
    public int getImageHeight(){return imgHeight;}
    
    public void update(int xCoor, int yCoor, Direction direction){
    	// assign new position attributes
    	this.xloc = xCoor;
    	this.yloc = yCoor;
    	this.dir = direction;

    	// redraw board
    	frame.repaint();
        
        // add delay
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // draw the board
    public void paint(Graphics g) {
    	g.drawImage(pics[dir.getIndex()][(picNum++) % frameCount], this.xloc, this.yloc, Color.gray, this);
    }

    // grabs the images from path specified in image_pathes[] and adds to pics[][]
    private void importImages() {
    	pics = new BufferedImage[imageCount][frameCount];
        
        int j = 0;
        for(Direction dir:Direction.values()){
            BufferedImage img = createImage("./../images/orc/orc_forward_" + dir.getName() + ".png");
            for(int i = 0; i < frameCount; i++) {
                pics[j][i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
            }
            j++;
        }
    }

    //Read image from file and return
    private BufferedImage createImage(String path){
    	BufferedImage bufferedImage;

    	try {
    		bufferedImage = ImageIO.read(new File(path));     // imports from specified path
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
}