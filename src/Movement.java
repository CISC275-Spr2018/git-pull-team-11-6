public enum Movement {

	// NORTH("north", 0),
	// NORTHEAST("northeast", 1),
	// EAST("east", 2),
	// SOUTHEAST("southeast", 3),
	// SOUTH("south", 4),
	// SOUTHWEST("southwest", 5),
	// WEST("west", 6),
	// NORTHWEST("northwest", 7);
	
	RUN("run", 0),
	JUMP("jump", 1),
	FIRE("fire", 2);

	private String name = null;
	private int index;
	private String imageName;
	private static Movement[] movements = values();
    
	private Movement(String s, int i){
		name = s;
		index = i;
	}
	
	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}
	
	public String getImageName() {
		return imageName;
	}
    
    public Movement next(){
        return movements[(this.ordinal()+1) % movements.length];
    }//next
}
