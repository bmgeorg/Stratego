package model;

public class Piece {
	private Type type;
	private Color color;
	
	Piece(Type type, Color color) {
		this.type = type;
		this.color = color;
	}
	Type getType() {
		return type;
	}
	Color getColor() {
		return color;
	}
	int getRank() {
		return type.ordinal();
	}
}
