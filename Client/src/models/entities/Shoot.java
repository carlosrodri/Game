package models.entities;

import java.awt.Rectangle;

import models.dao.Hability;

public class Shoot {
	private Rectangle rectangle;
	private Hability typeOfHablility;
	
	public Shoot(Rectangle rectangle, Hability typeOfHablility) {
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