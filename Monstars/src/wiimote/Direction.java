package wiimote;

public enum Direction {
	UP("UP"), ASIDE("ASIDE"), DOWN("DOWN"), NONE("NONE");
	
	private String str;
	
	private Direction(String str){
		this.str = str;
	}
	
	public String toString(){
		return str;
	}
	
};