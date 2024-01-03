package ch.epfl.moocprog.tests;

import static ch.epfl.moocprog.app.Context.getConfig;
import static ch.epfl.moocprog.config.Config.*;

import java.io.File;

import ch.epfl.moocprog.app.ApplicationInitializer;
import ch.epfl.moocprog.config.ImmutableConfigManager;
 
public class Exemple {

	public static void main(String[] args) {
        ApplicationInitializer.initializeApplication(
                new ImmutableConfigManager(
                    new File("res/app.cfg")
                )
            );
		double termite_speed = getConfig().getDouble(TERMITE_SPEED);
		System.out.println("TERMITE_SPEED = "+ termite_speed);
	}

}
