

public enum Direction {

	NORTH("north", 0),
	NORTHEAST("northeast", 1),
	EAST("east", 2),
	SOUTHEAST("southeast", 3),
	SOUTH("south", 4),
	SOUTHWEST("southwest", 5),
	WEST("west", 6),
	NORTHWEST("northwest", 7);
	
	private String name = null;
	private int index;
	private String imageName;
	
	private Direction(String s, int i){
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
}
