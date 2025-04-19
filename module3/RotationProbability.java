package ch.epfl.moocprog;

import ch.epfl.moocprog.utils.Utils;

/**
 * Classe permettant de gérer les changements de direction aléatoires
 */
public class RotationProbability {

	private double[] angles = {}; // en radians
	private double[] probabilities = {};
	
	public RotationProbability(double[] angles, double[] probabilities) {		
		// throw IllegalArgumentException if data are invalids
        Utils.requireNonNull(angles);
        Utils.requireNonNull(probabilities);
        Utils.require(angles.length == probabilities.length);
		this.angles = angles.clone();
		this.probabilities = probabilities.clone();

	}
	
	
	/**
	 * @return a copy of the angles array
	 */
	public double[] getAngles() {
		return angles.clone();
	}
	
	/**
	 * @return a copy of the probabilities array
	 */
	public double[] getProbabilities() {
		return probabilities.clone();
	}

	
	
}
