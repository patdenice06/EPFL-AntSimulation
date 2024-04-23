package ch.epfl.moocprog;

import ch.epfl.moocprog.utils.Utils;

/**
 * Classe modélisant la nourriture consommable par les fourmis
 */
public final class Food extends Positionable{
	private double quantityOfFood;
	
	public Food(final ToricPosition tp, double quantityOfFood) {
		super.setPosition(tp);
		if(quantityOfFood < 0)
			quantityOfFood = 0.0;
		this.quantityOfFood = quantityOfFood;				
	}
	
	/**
	 * Connaître la quantité de nourriture disponible
	 * @return La quantité de nourriture disponible
	 */
	public double getQuantity() {
		return quantityOfFood;
	}
	
	/**
	 *  Prélève une quantité donnée de nourriture. Retourne la quantité de nourritue qui
	 *  a pu être prélevée (on ne peut pas prélever plus que ce qui est disponible)
	 * @param quantity La quantité de nourriture à prélever
	 * @return La quantité qui a pu être prélevée
	 */
	public double takeQuantity(double takeQty) {
		Utils.require("La quantité de nourriture à prélever ne peut pas être négative.", takeQty >= 0.0);
		double taken = 0;
		if(takeQty <= quantityOfFood) {
			taken = takeQty;
			quantityOfFood -= takeQty;
		}
		else { // takeQty > quantityOfFood
			taken = quantityOfFood;
			quantityOfFood = 0.0;
		}
		return taken;
	}

	@Override
	public String toString() {		
		return getPosition() + "\n" + String.format("Quantity : %.2f", getQuantity());
	}

	
}
