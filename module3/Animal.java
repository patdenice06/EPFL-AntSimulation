package ch.epfl.moocprog;

import ch.epfl.moocprog.random.UniformDistribution;
import ch.epfl.moocprog.utils.Time;
import ch.epfl.moocprog.utils.Utils;
import ch.epfl.moocprog.utils.Vec2d;

import static ch.epfl.moocprog.app.Context.getConfig;
import static ch.epfl.moocprog.config.Config.*;

import ch.epfl.moocprog.config.Config;

abstract public class Animal extends Positionable{
	private double angle;		// l'angle de direction pour les déplacements
	private int hitpoints;		// points de vie
	private Time lifespan; 		// durée maximale de vie
	private Time rotationDelay; // mesure le temps écoulé depuis la précédente rotation

	public Animal(ToricPosition tp, int hitpoints, Time lifespan) {
		super. setPosition(tp);
		this.hitpoints = hitpoints;
		this.lifespan = lifespan;
		this.angle = UniformDistribution.getValue(0, 2 * Math.PI);
		rotationDelay = Time.ZERO;
	}

	/**
	 * @return L’angle de direction
	 */
	public final double getDirection() {
		return angle;
		// return angle*Math.PI/180;	// pour obtenir des radians
	}
	
	/**
	 * Retourne le nombre de points de vie
	 * @return	le nombre de points de vie
	 */
	public final int getHitpoints() {
		return hitpoints;
	}

	/**
	 * Retourne la durée maximale de vie
	 * @return	la durée maximale de vie
	 */
	public final Time getLifespan() {
		return lifespan;
	}

	/**
	 * @return	La vitesse de déplacement calculée
	 */
	public abstract double getSpeed();
	
	
	/**
	 * Modifie la valeur de l'angle de direction
	 * @param angle	La valeur de l'angle de direction
	 */
	public final void setDirection(double angle) { // ne doit pas être redéfinie par les classes filles
		this.angle = angle;
	}
	
	/**
	 * Retourne true si le nombre de points de vie de l'animal ou la durée de vie
	 * est inférieur(e) ou égal(e) à zéro
	 * @return true ou false
	 */
	public final boolean isDead() {	// ne doit pas être redéfinie par les classes filles
		return hitpoints <= 0 || lifespan.compareTo(Time.fromSeconds(0.0)) <= 0;
	}
	
	
	/**
	 * Rendu graphique des animaux
	 * @param visitor
	 * @param s
	 */
	public abstract void accept(AnimalVisitor visitor, RenderingMedia s);
	
	
	/**
	 * Fait évoluer l'animal d'un "pas de temps" {@link dt} et
	 * soustrait à la durée de vie {@link lifespan} le pas de temps {@link dt}
	 * multiplié par {@link ANIMAL_LIFESPAN_DECREASE_FACTOR}	   
	 * @param env	Vue sur l'environnement animal
	 * @param dt	Un pas de temps
	 */
	public void update(AnimalEnvironmentView env, Time dt) {
		if(!isDead()) {
			lifespan = lifespan.minus(dt.times(getConfig().getDouble(ANIMAL_LIFESPAN_DECREASE_FACTOR)));
			move(dt);			
		}
	}
	
	protected final void move(Time dt) {		
		final Time delay = getConfig().getTime(ANIMAL_NEXT_ROTATION_DELAY);
		rotationDelay = rotationDelay.plus(dt);
		while (rotationDelay.compareTo(delay) >= 0) {
			rotate();
			rotationDelay = rotationDelay.minus(delay);
		}
		this.getPosition().toVec2d();
		setPosition(this.getPosition().add(Vec2d.fromAngle(getDirection()).scalarProduct(dt.toSeconds()*(getSpeed()))));
	}
	
	
	private void rotate() {
		RotationProbability rp = computeRotationProbs();
		setDirection(getDirection() + Utils.pickValue(rp.getAngles(), rp.getProbabilities()));
	}
	
	
	
	/**
	 * Associe un tableau d’angles à un tableau de probabilité
	 * @return	a RotationProbability
	 */
	protected RotationProbability computeRotationProbs() {
		// Angles de déplacement
		double[] anglesInDegrees = new double[] {-180, -100, -55, -25, -10, 0, 10, 25, 55, 100, 180}; 
		//  Probabilités associées aux angles de déplacement
		double[] probabilities = new double[] {0.0000, 0.0000, 0.0005, 0.0010, 0.0050, 
				0.9870, 0.0050, 0.0010, 0.0005, 0.0000, 0.0000};
		double[] anglesInRadians = new double[11];
		
		// Convertion des angles de degrés en radians
		for (int i = 0; i < anglesInDegrees.length; i++) {
			anglesInRadians[i] = Math.toRadians(anglesInDegrees[i]);
		}			
		
		return new RotationProbability(anglesInRadians, probabilities);		
	}
	
	
	@Override
	public String toString() {
		return "" + getPosition() + "\n"
				  +	"Speed : " + getSpeed() + "\n"
				  +	"HitPoints : " + hitpoints + "\n"
				  + "LifeSpan : " + lifespan;
	}
	
}

