
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


@SuppressWarnings("serial")
class View extends JPanel {
    
    final int frameCount = 10;
    final int imageCount = 8;
    BufferedImage[][] pics;
    

    final int picSize = 165;
    final int frameStartSize = 800;
    
    int xloc = 100;
    int yloc = 100;
    Direction dir;
    int picNum = 0;
    
    public View(){
        
    	pics = new BufferedImage[imageCount][frameCount];
        
        int j = 0;
        for(Direction dir:Direction.values()){
            BufferedImage img = createImage("./../images/orc/orc_forward_" + dir.getName() + ".png");
            for(int i = 0; i < frameCount; i++) {
                pics[j][i] = img.getSubimage(picSize*i, 0, picSize, picSize);
            }
            j++;
        }
        
    }
    
        	

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.setColor(Color.gray);
        //picNum = (picNum + 1) % frameCount;
        //g.drawImage(pics[picNum], this.xloc, this.yloc, Color.gray, this);
        g.drawImage(pics[dir.getIndex()][(picNum++) % frameCount], this.xloc, this.yloc, Color.gray, this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(frameStartSize, frameStartSize);
    }
    
    public void update(int xCoor, int yCoor, Direction direction){
    	// assign new position attributes
    	this.xloc = xCoor;
    	this.yloc = yCoor;
    	this.dir = direction;

    	// redraw board
    	repaint();
        
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