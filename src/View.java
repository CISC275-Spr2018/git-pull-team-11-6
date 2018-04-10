
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
    Direction dir;
    Movement mov;
    int picNum = 0;
    
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
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.setColor(Color.gray);

        // display whatever action the orc is doing
        switch (mov.getName()) {
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
    	// assign new position attributes
    	this.xloc = xCoor;
    	this.yloc = yCoor;
    	this.dir = direction;
        this.mov = movement;

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
