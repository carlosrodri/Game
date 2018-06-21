package models.entities;

import java.awt.Rectangle;

public class Shoot {
	private Rectangle rectangle;
	private Hability typeOfHablility;
	
	public Shoot(Rectangle rectangle, Hability typeOfHablility) {
		super();
		this.rectangle = rectangle;
		this.typeOfHablility = typeOfHablility;
	}
	public Rectangle getRectangle() {
		return rectangle;
	}
	public Hability getTypeOfHablility() {
		return typeOfHablility;
	}
}