package ch.epfl.moocprog;

/**
 * La vue qu’ont les générateurs de nourriture de l’environnement
 */
public interface FoodGeneratorEnvironmentView {

	/**
	 * FoodGenerator a besoin d’interagir avec l’environnement de façon spécifique.
	 * Il a une vue restreinte à l'entité nourriture dans l'environnement.
	 * @param food	La quantité de nourriture à ajoutée et placée aléatoirement dans l'environnement
	 * @return 
	 */
	void addFood(Food food);
	
}
