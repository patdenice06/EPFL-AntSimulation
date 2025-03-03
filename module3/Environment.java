package ch.epfl.moocprog;

import static ch.epfl.moocprog.app.Context.getConfig;
import static ch.epfl.moocprog.config.Config.WORLD_HEIGHT;
import static ch.epfl.moocprog.config.Config.WORLD_WIDTH;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.moocprog.gfx.EnvironmentRenderer;
//import ch.epfl.moocprog.gfx.EnvironmentGraphicRenderer;
import ch.epfl.moocprog.utils.Time;
import ch.epfl.moocprog.utils.Utils;

public final class Environment implements FoodGeneratorEnvironmentView, AnimalEnvironmentView{

	
	// Collection de nourritures
	// ...
	// Collection de phéromones
	// ...
	// etc
	// ...
	
	// Générateur de nourriture qui aura pour vocation de placer aléatoirement des sources de nourriture dans
	// l’environnement	
	private FoodGenerator fg;
	private List<Food> foods;
	private List<Animal> animals;

	public Environment() {
		fg = new FoodGenerator();
		foods = new LinkedList<Food>();
		animals = new LinkedList<Animal>();
	}

	/**
	 * Appelle toutes les méthodes de « mise-à-jour » des entités
	 * @param dt	Compteur de temps
	 */
	public void	update(Time dt) {
		fg.update(this, dt);
		
		// suppression des animaux morts
		Iterator<Animal> itAnimal = (Iterator<Animal>) animals.iterator();
		while (itAnimal.hasNext()) {
			Animal a = itAnimal.next();
			if(a.isDead())
				itAnimal.remove();
			else // call Animal.update ???
				//a.move(dt);
				a.update(null,dt);
		}
		
		// suppression des nourriture dont la quantité est <= 0
		Iterator<Food> itFood = (Iterator<Food>) foods.iterator();
		while(itFood.hasNext()) {
			Food f = itFood.next();
			if(f.getQuantity() <= 0)
				itFood.remove();
		}
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

	/**
	 * @return La largeur de l'environnement
	 */
	public int getWidth() {
		return getConfig().getInt(WORLD_WIDTH);
	}

	/**
	 * @return La hauteur de l'environnement
	 */
	public int getHeight() {
		return getConfig().getInt(WORLD_HEIGHT);
	}

	/**
	 * Rendu graphique des constituants de l’environnement
	 * @param renderer le rendu graphique
	 */
//	public void renderEntities(EnvironmentGraphicRenderer renderer) {
//		foods.forEach(renderer::renderFood);	
//		animals.forEach(renderer::renderAnimal);
//	}
	
	
	public void renderEntities(EnvironmentRenderer environmentRenderer) {
		foods.forEach(environmentRenderer::renderFood);	
		animals.forEach(environmentRenderer::renderAnimal);
		}
	
	public void addAnthill(Anthill anthill) {
		
	}
	
	/**
	 * Ajoute des animaux à l'environnement
	 * @param animal	un animal de l'environnement
	 */
	public void addAnimal(Animal animal) {
		Utils.requireNonNull("L'entité Animal ne doit pas être nulle.", animal);
		animals.add(animal);
	}
	
	
	/**
	 * Renvoie la liste des positions des animaux
	 * @return la liste des positions des animaux
	 */
	public List<ToricPosition> getAnimalsPosition(){
		List<ToricPosition> toricPositions = new ArrayList<ToricPosition>();
		
		Iterator<Animal> itAnimal = (Iterator<Animal>) animals.iterator();
		while (itAnimal.hasNext()) {
			Animal a = itAnimal.next();
			toricPositions.add(a.getPosition());
		}
		
		return toricPositions;
	}
		
}
