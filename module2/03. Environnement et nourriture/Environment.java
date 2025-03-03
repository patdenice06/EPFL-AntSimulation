package ch.epfl.moocprog;

import java.util.LinkedList;
import java.util.List;
import ch.epfl.moocprog.utils.Time;
import ch.epfl.moocprog.utils.Utils;

public final class Environment implements FoodGeneratorEnvironmentView{

	// Collection d'animaux
	// ...
	// Collection de nourritures
	// ...
	// Collection de phéromones
	// ...
	// etc
	// ...
	// Générateur de nourriture qui aura pour vocation de placer aléatoirement des sources de nourriture dans
	// l’environnement
	// FoodGenerator ...
	
	private FoodGenerator fg;
	private List<Food> foods;
	
	public Environment() {
		fg = new FoodGenerator();
		foods = new LinkedList<Food>();
	}

	/**
	 * Appelle toutes les méthodes de « mise-à-jour » des entités 
	 * @param dt	Compteur de temps
	 */
	public void	update(Time dt) {
		fg.update(this, dt);
		foods.removeIf(food -> food.getQuantity() <= 0);
	}

	@Override
	public void addFood(Food food) {
		Utils.requireNonNull("L'entité Food ne doit pas être nulle.", food);
		foods.add(food);
	}

	/**
	 * Retourne la liste des <B>quantités</B> associés à chaque nourriture présente dans
	 * l’environnemen	//foods.???
	 * @return
	 */
	public List<Double> getFoodQuantities() {
		List<Double> qtyOfFoods = new LinkedList<Double>();
		for (Food food : foods) {			
		  	qtyOfFoods.add(food.getQuantity());
		}		
		return qtyOfFoods;
	}
		
}
