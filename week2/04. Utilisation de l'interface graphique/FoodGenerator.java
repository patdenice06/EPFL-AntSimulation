package ch.epfl.moocprog;

import ch.epfl.moocprog.config.Config;
import ch.epfl.moocprog.random.NormalDistribution;
import ch.epfl.moocprog.random.UniformDistribution;
import ch.epfl.moocprog.utils.Time;
import static ch.epfl.moocprog.app.Context.getConfig;
import static ch.epfl.moocprog.config.Config.NEW_FOOD_QUANTITY_MIN;
import static ch.epfl.moocprog.config.Config.NEW_FOOD_QUANTITY_MAX;
import static ch.epfl.moocprog.config.Config.WORLD_HEIGHT;
import static ch.epfl.moocprog.config.Config.WORLD_WIDTH;


/**
 * Génèrere périodiquement, à des espaces de temps réguliers, de la nourriture dans l’environnement
 */
public final class FoodGenerator {
	
	// 	Compteur, permettant de mesurer le temps écoulé depuis la précédente génération
	//	de nourriture
	private Time time;

	public FoodGenerator() {
		time = Time.ZERO;
	}
	
	/**
	 * Gére l’évolution de ses instances à chaque écoulement de pas de temps dt.
	 * @param env	Vue de l'environnement restreint à l'entité nourriture
	 * @param dt	Compteur de temps
	 */
	public void update(FoodGeneratorEnvironmentView env, Time dt) {
		final Time foodGenDelay = getConfig().getTime(Config.FOOD_GENERATOR_DELAY);
		// la quantité de nourriture à placer est déterminée par une valeur tirée
		// au hasard uniformément entre les deux bornes MIN et MAX
		double quantityOfFood = UniformDistribution.getValue(getConfig().getDouble(NEW_FOOD_QUANTITY_MIN),
															 getConfig().getDouble(NEW_FOOD_QUANTITY_MAX));
		// les coordonnées de la position où placer la nourriture sont chacune
		// tirée au hasard
        final int width  = getConfig().getInt(WORLD_WIDTH);
        final int height = getConfig().getInt(WORLD_HEIGHT);
		double mu_X = width / 2.0;
		double sigma2_X = (width * width) / 16.0 ;
		double mu_Y = height / 2.0;
		double sigma2_Y = (height * height) / 16.0 ;
		double x = NormalDistribution.getValue(mu_X, sigma2_X);
		double y = NormalDistribution.getValue(mu_Y, sigma2_Y);
		ToricPosition tp = new ToricPosition(x, y);
		
		// augmente le compteur interne de dt		
		time = time.plus(dt);
		while(time.compareTo(foodGenDelay) >= 0 ) {
			time = time.minus(foodGenDelay);
		// ajoute à l’environnement une quantité de nourriture placée aléatoirement
			env.addFood(new Food(tp, quantityOfFood));
		}
	}
	
}
