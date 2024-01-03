package ch.epfl.moocprog;

public class Positionable {
	
	private ToricPosition tp;
	
	/**
	 * Constructeur par défaut qui initialise la position torique (0.0, 0.0)
	 */
	public Positionable() {
		tp = new ToricPosition(0.0, 0.0);		
	}
	
	/**
	 *	Constructeur initialisant la position à la ToricPosition passée en paramètre 
	 * @param tp Une poition torique 
	 */
	public Positionable(ToricPosition tp) {
		this.tp = new ToricPosition(tp.toVec2d().getX(), tp.toVec2d().getY());
	}

	/**
	 * Retourne la position torique d'une entité
	 * @return	La position torique d'une entité
	 */
	public ToricPosition getPosition() {
		return tp;
	}

	/**
	 * Modifie la position d'une entité
	 * @param position	La nouvelle position torique de l'entité
	 */
	protected final void setPosition(ToricPosition position) {
		tp = new ToricPosition(position.toVec2d().getX(), position.toVec2d().getY());
	}

	@Override
	public String toString() {
		return "" + tp;
		
	}
	
	
	
}
