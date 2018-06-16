package models.entities;

import java.awt.Rectangle;

public class Shoot {
	private Rectangle rectangle;
	private Hability typeOfHablility;
	private int damage;
	private String image;
	
	public Shoot(Rectangle rectangle, Hability typeOfHablility, int damage, String image) {
		super();
		this.rectangle = rectangle;
		this.typeOfHablility = typeOfHablility;
		this.damage = damage;
		this.image = image;
	}
	public Rectangle getRectangle() {
		return rectangle;
	}
	public Hability getTypeOfHablility() {
		return typeOfHablility;
	}
	public int getDamage() {
		return damage;
	}
	public String getImage() {
		return image;
	}
	@Override
	public String toString() {
		return "Shoot [rectangle=" + rectangle + ", typeOfHablility=" + typeOfHablility + ", damage=" + damage
				+ ", image=" + image + "]";
	}
}