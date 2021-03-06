/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/


class Model{
    
    // object dimensions
    int screenWidth;
    int screenHeight;
    int imageWidth;
    int imageHeight;

    // orc increment rate
    final int xIncr = 8;    // 8
    final int yIncr = 5;    // 5

    // orc's current position on the board
    int xloc = 100;
    int yloc = 100;
    Direction dir;

    Movement movementType = Movement.RUN;
    int movementCounter = 0;

    // i just copied these var's from View.java, not sure if this is the best way to do it...
    final int runFrameCount = 10;
    final int jumpFrameCount = 8;
    final int fireFrameCount = 4;
    
    
    public Model(int screenWidth, int screenHeight, int imageWidth, int imageHeight){
        
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;

        dir = Direction.NORTHEAST;
        
    }
    
    public void updateLocationAndDirection(boolean paused){
        // check orc orientation and modify accordingly

        // this.dir = dir;
        // this.movementType = movementType;

        if(!paused){

            if (movementType == Movement.FIRE) {
                if (movementCounter >= fireFrameCount) {
                    movementCounter = 0;
                    changeMovement(Movement.RUN);
                }
                else {
                    movementCounter++;
                }
                return;
            }
            else if (movementType == Movement.JUMP) {
                if (movementCounter >= jumpFrameCount) {
                    movementCounter = 0;
                    changeMovement(Movement.RUN);
                }
                else {
                    movementCounter++;
                }
            }

            switch (dir) {

                case NORTH:
                    if ((yloc-yIncr) <= 0) {
                        dir = Direction.SOUTH;   // set direction/orientation S
                    }
                    else {
                        yloc-=yIncr;
                    }
                    break;

                // prev NE
                case NORTHEAST:
                    if ((xloc+xIncr+imageWidth) >= screenWidth) {
                        
                        dir = Direction.NORTHWEST;   // set direction/orientation NW 
                    }
                    else if ((yloc-yIncr) <= 0) {
                        
                        dir = Direction.SOUTHEAST;   // set direction/orientation SE
                    }
                    else {
                        xloc+=xIncr;
                        yloc-=yIncr;
                    }
                    break;

                // prev E
                case EAST:
                    if ((xloc+xIncr+imageWidth) >= screenWidth) {
                        
                        dir = Direction.WEST;   // set direction/orientation W
                    }
                    else {
                        xloc+=xIncr;
                    }
                    break;

                // prev SE
                case SOUTHEAST:
                    if ((xloc+xIncr+imageWidth) >= screenWidth) {
                        
                        dir = Direction.SOUTHWEST;   // set direction/orientation SW
                    }
                    else if ((yloc+yIncr+imageHeight) >= screenHeight) {
                        
                        dir = Direction.NORTHEAST;   // set direction/orientation NE
                    }
                    else {
                        xloc+=xIncr;
                        yloc+=yIncr;
                    }
                    break;

                // prev S
                case SOUTH:
                    if ((yloc+yIncr+imageHeight) >= screenHeight) {
                        
                        dir = Direction.NORTH;   // set direction/orientation N
                    }
                    else {
                        yloc+=yIncr;
                    }
                    break;

                // prev SW
                case SOUTHWEST:
                    if ((xloc-xIncr) <= 0) {
                        
                        dir = Direction.SOUTHEAST;   // set direction/orientation SE
                    }
                    else if ((yloc+yIncr+imageHeight) >= screenHeight) {
                        
                        dir = Direction.NORTHWEST;   // set direction/orientation NW
                    }
                    else {
                        xloc-=xIncr;
                        yloc+=yIncr;
                    }
                    break;

                // prev W
                case WEST:
                    if ((xloc-xIncr) <= 0) {
                        
                        dir = Direction.EAST;   // set direction/orientation E
                    }
                    else {
                        xloc-=xIncr;
                    }
                    break;

                // prev NW
                case NORTHWEST:
                    if ((xloc-xIncr) <= 0) {
                        
                        dir = Direction.NORTHEAST;   // set direction/orientation NE
                    }
                    else if ((yloc-yIncr) <= 0) {
                        
                        dir = Direction.SOUTHWEST;   // set direction/orientation SW
                    }
                    else {
                        xloc-=xIncr;
                        yloc-=yIncr;
                    }
                    break;
            }

        }
    }
    
    
    public int getX(){return xloc;}
    
    public int getY(){return yloc;}
    
    public Direction getDirect(){return dir;}

    public Movement getMovement(){return movementType;}

    public void changeMovement(Movement m) {
        this.movementType = m;
    }
    
}
