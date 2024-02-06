package ch.epfl.moocprog;

/**
 * Classe modélisant la nourriture consommable par les fourmis
 */
public final class Food extends Positionable{
	ToricPosition tp;
	double quantityOfFood;
	
	public Food(ToricPosition tp, double quantityOfFood) {
		super(tp);
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
		if(takeQty < 0.0)
			throw new IllegalArgumentException("La quantité de nourriture à prélever ne peut pas être négative.");
		
		if(takeQty <= quantityOfFood)
			quantityOfFood -= takeQty;
		else
			quantityOfFood = 0.0;
		
		return takeQty;
	}

	@Override
	public String toString() {		
		return getPosition() + "\n" + String.format("Quantity : %.2f", getQuantity());
	}

	
}
