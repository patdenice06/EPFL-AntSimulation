package ch.epfl.moocprog;

abstract public class Animal extends Positionable{
	private double angle;

	public Animal(ToricPosition tp) {
		super. setPosition(tp);
		this.angle = 0.0;
	}

	/**
	 * @return L’angle de direction
	 */
	public final double getDirection() {
		return angle;
	}
	
	/**
	 * Rendu graphique ddes animaux
	 * @param visitor
	 * @param s
	 */
	public abstract void accept(AnimalVisitor visitor, RenderingMedia s);
	
}
