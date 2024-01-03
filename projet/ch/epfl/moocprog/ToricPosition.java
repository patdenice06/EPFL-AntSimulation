package ch.epfl.moocprog;

import ch.epfl.moocprog.utils.Vec2d;
import static ch.epfl.moocprog.app.Context.getConfig;
import static ch.epfl.moocprog.config.Config.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de représenter une position dans un monde torique
 * à deux dimensions, lequel sera représenté comme un rectangle de hauteur
 * et largeur données
 * 
 * @author Patrick GIRAUD
 *
 */

public final class ToricPosition {
	private final Vec2d position;

    
    /**
     * Constructeur par défaut initialisant les deux coordonnées du Vec2d interne à 0.0
     */
    public ToricPosition() {
		position = new Vec2d(0.0, 0.0);
	}
    
    /**
     * Constructeur qui transformer les coordonnées passées en arguments pour qu’elles
     * correspondent à une position valide dans un environnement torique
     * @param x	Coordonnée usuelle x
     * @param y	Coordonnée usuelle y
     */
	public ToricPosition(double x, double y) {
		position = clampedPosition(x, y);
	}

	/**
	 *  Constructeur initialisant l’attribut de type Vec2d à partir d’un Vec2d passé en paramètre,
	 *  en appliquant toujours l’algorithme de « projection »
	 * @param v	Un vecteur
	 */
	public ToricPosition(Vec2d v) {
		position = clampedPosition(v.getX(), v.getY());
	}


	/**
     * Projection de coordonnées usuelles dans le monde torique
     * @param x	coordonnée usuelle x
     * @param y	coordonnée usuelle y
     * @return	Un vecteur Vec2d « projeté » dans le monde torique
     */
    private static Vec2d clampedPosition(double x, double y) {    
        final int width  = getConfig().getInt(WORLD_WIDTH);
        final int height = getConfig().getInt(WORLD_HEIGHT);
               
    	while (x < 0.0)
			x += width;
    	
       	while (x >= width)
    		x -= width;
       	
    	while (y < 0.0)
			y += height;
    	
       	while (y >= height)
    		y -= height;
        
    	return new Vec2d(x, y);
    }

	/**
	 * Retourne une nouvelle ToricPosition correspondant à l’addition des 
	 * positions de l’instance courante {@code this} et de {@code that} 
	 * @param that	Position de l'instance courante de {@code that}
	 * @return Une nouvelle ToricPosition
	 */
	public ToricPosition add(ToricPosition that) {
		return new ToricPosition(this.position.add(that.position));
	}

	/**
	 * Retourne une nouvelle ToricPosition correspondant à l’addition des 
	 * positions de l’instance courante {@code this} et du vecteur {@code v} 
	 * @param v	Un vecteur
	 * @return	Une nouvelle ToricPosition
	 */
	public ToricPosition add(Vec2d v) {
		return new ToricPosition(this.position.add(v));
	}
	
	/**
	 * Retourne l’objet interne Vec2d 
	 * @return	L’objet interne Vec2d
	 */
	public Vec2d toVec2d() {
		return position;
	}

/**
 * Calcul le plus petit vecteur allant de l’instance courante ({@code this})
 * à {@code that} dans le monde torique
 * @param that	La position torique du vecteur distant
 * @return Le plus petit vecteur entre {@code this} et {@code that} 
 */
	public Vec2d toricVector(ToricPosition that) {
        final int width  = getConfig().getInt(WORLD_WIDTH);
        final int height = getConfig().getInt(WORLD_HEIGHT);
        
		double[] distances = new double[9];
		List<Vec2d> thatVectors = new ArrayList<Vec2d>(9);
      	int k = -1;
      	double minDistance = (double) width * height;
      	Vec2d minVector = null;
        // Calcul des 9 trajets possibles vers that dans le monde torique
        for (int i = -1; i < 2; i++) {
        	for (int j = -1; j < 2; j++) {
	        	++k;
	        	thatVectors.add(k, that.position.add(new Vec2d(i * width, j * height)));
	        	distances[k] = this.position.distance(thatVectors.get(k));
	        	// Recherche de la distance minimum et du vecteur correspondant
	        	if(distances[k] < minDistance) {
	        		minDistance = distances[k];
	        		minVector = thatVectors.get(k);
	        	}
        	}
        }
        
        return minVector.minus(this.position);
	}

/**
 * Calcule la plus courte distance (dans le monde torique) entre ({@code this})
 * et {@code that}
 * @param that	La position torique de {@code that}	
 * @return	la plus courte distance (dans le monde torique) entre {@code this} et {@code that}	
 */
public double toricDistance(ToricPosition that) {	
	return this.toricVector(that).length(); 
}

@Override
public String toString() {
	return "" + position;
}
    

}
