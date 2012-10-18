package utility;

public enum Angles {
	UP (0),
	RIGHT (90),
	DOWN (180),
	LEFT (270);
	
	public static final int DEGREES_IN_CIRCLE = 360;
	private final int angleInDegrees;
	
	Angles(int angleInDegrees) {
		this.angleInDegrees = angleInDegrees;
	}
	
	public int value() {
		return angleInDegrees;
	}
}
