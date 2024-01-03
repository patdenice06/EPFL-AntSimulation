package ch.epfl.moocprog.tests;

import ch.epfl.moocprog.app.ApplicationInitializer;
import ch.epfl.moocprog.config.ImmutableConfigManager;
import java.io.File;
import static ch.epfl.moocprog.app.Context.getConfig;
import static ch.epfl.moocprog.config.Config.WORLD_HEIGHT;
import static ch.epfl.moocprog.config.Config.WORLD_WIDTH;
import ch.epfl.moocprog.utils.Vec2d;
import ch.epfl.moocprog.ToricPosition;
import ch.epfl.moocprog.Positionable;
public class Main {

    // Printing colored text in the Java console
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String TEST_PASSED = GREEN + " TRUE" + RESET;
    private static final String TEST_FAILED = RED + " FALSE" + RESET;
	
    public static void main(String[] args) {
        ApplicationInitializer.initializeApplication(
            new ImmutableConfigManager(
                new File("res/app.cfg")
            )
        );
        final int width  = getConfig().getInt(WORLD_WIDTH);
        final int height = getConfig().getInt(WORLD_HEIGHT);
        
        /************************************************************/        
        /*	 			TESTS INITIAUX 								*/        
        /************************************************************/        
        ToricPosition tp1 = new ToricPosition();
        ToricPosition tp2 = new ToricPosition(1.2, 2.3);
        ToricPosition tp3 = new ToricPosition(new Vec2d(4.5, 6.7));
        ToricPosition tp4 = tp3.add(tp2);
        ToricPosition tp5 = new ToricPosition(width, height);
        ToricPosition tp6 = new ToricPosition(width/2, height/2);
        ToricPosition tp7 = tp4.add(tp6.add(new Vec2d(width/2, height/2)));
        ToricPosition tp8 = new ToricPosition(3, 4);
        Vec2d v1 = tp2.toricVector(tp3);

        System.out.println("Some tests for ToricPosition");
        double x,y;
        System.out.println("Default toric position : " + tp1);
        x = tp1.toVec2d().getX();
        y = tp1.toVec2d().getY();
        if (x == 0.0 && y == 0.0  )
			System.out.println(TEST_PASSED);
        else
        	System.out.println(TEST_FAILED);
        
        System.out.println("tp2 : " + tp2);
        x = tp2.toVec2d().getX();
        y = tp2.toVec2d().getY();
        if (x == 1.2 && y == 2.3  )
			System.out.println(TEST_PASSED);
        else
        	System.out.println(TEST_FAILED);
        
        System.out.println("tp3 : " + tp3);
        x = tp3.toVec2d().getX();
        y = tp3.toVec2d().getY();;
        if (x == 4.5 && y == 6.7 )
			System.out.println(TEST_PASSED);
        else
        	System.out.println(TEST_FAILED);
       
        System.out.println("tp4 (tp2 + tp3) : " + tp4);
        x = tp4.toVec2d().getX();
        y = tp4.toVec2d().getY();
        if (x == 5.7 && y == 9.0 )
			System.out.println(TEST_PASSED);
        else
        	System.out.println(TEST_FAILED);
        
        System.out.println("Toric vector between tp2 and tp3 : " + v1);
        x = v1.getX();
        y = v1.getY();
        if (x == 3.3 && y == 4.4 )
			System.out.println(TEST_PASSED);
        else
        	System.out.println(TEST_FAILED);
                
        
        System.out.println("World dimension (clamped) : " + tp5);
        x = tp5.toVec2d().getX();
        y = tp5.toVec2d().getY();
        if (x == 0.0 && y == 0.0 )
			System.out.println(TEST_PASSED);
        else
        	System.out.println(TEST_FAILED);
                
        System.out.println("Half world dimension : " +tp6);
        x = tp6.toVec2d().getX();
        y = tp6.toVec2d().getY();
        if (x == 500.0 && y == 350.0 )
			System.out.println(TEST_PASSED);
        else
        	System.out.println(TEST_FAILED);
                
        System.out.println("tp3 + 2 * half world dimension = " + tp7);
        x = tp7.toVec2d().getX();
        y = tp7.toVec2d().getY();
        if (x == 5.7 && y == 9.0 )
			System.out.println(TEST_PASSED);
        else
        	System.out.println(TEST_FAILED);
        
        System.out.println("Length of vector (3, 4) : " + tp1.toricDistance(tp8));
        if (tp1.toricDistance(tp8) == 5.0 )
			System.out.println(TEST_PASSED);
        else
        	System.out.println(TEST_FAILED);
               

        Positionable p1 = new Positionable();
        Positionable p2 = new Positionable(tp4);

        System.out.println();
        System.out.println("Some tests for Positionable");
        System.out.println("Default position : " + p1.getPosition());
        x = p1.getPosition().toVec2d().getX();
        y = p1.getPosition().toVec2d().getY();
        if (x == 0.0 && y == 0.0)
			System.out.println(TEST_PASSED);
        else
        	System.out.println(TEST_FAILED);
        
        System.out.println("Initialized at tp4 : " + p2.getPosition());
        x = p2.getPosition().toVec2d().getX();
        y = p2.getPosition().toVec2d().getY();
        if (x == 5.7 && y == 9.0)
			System.out.println(TEST_PASSED);
        else
        	System.out.println(TEST_FAILED);
        
        
        /************************************************************/        
        /*	 			TESTS COMPLEMENTAIRES						*/        
        /************************************************************/
        System.out.println("************************************");
        System.out.println("Test (800,600).toricVetor(100,100)");
        ToricPosition thisTP = new ToricPosition(800, 600);
        ToricPosition thatTP = new ToricPosition(new Vec2d(100, 100));
        Vec2d v2 = thisTP.toricVector(thatTP);
        System.out.println("toricVetor = "+ v2.toString());
        System.out.println("toric position = "+ new ToricPosition(v2).toString());
        
        System.out.println("************************************");
        System.out.println("Distance torique entre tp2(1.2, 2.3) et tp3(4.5, 6.7):");
        System.out.println(tp2.toricDistance(tp3));
        
        System.out.println("************************************");
        System.out.println("Distance torique entre tp1(0, 0) et tp9(3.3, 4.4):");
        ToricPosition tp9 = new ToricPosition(3.3, 4.4);
		System.out.println(tp1.toricDistance(tp9 ));
        
		System.out.println("************************************");
		System.out.println("toVec2DCorrectlyReturnsWithVecConstructorWithClamp. X > WIDTH");
		ToricPosition tp10 = new ToricPosition(new Vec2d(1100, 50));
		System.out.println(tp10.toVec2d().toString());
		System.out.println("toVec2DCorrectlyReturnsWithXYConstructorWithClamp. X > WIDTH");		
		ToricPosition tp11 = new ToricPosition(1100, 50);
		System.out.println(tp11.toVec2d().toString());
		System.out.println("toVec2DCorrectlyReturnsWithXYConstructorWithClamp. X > WIDTH, Y > HEIGHT");		
		ToricPosition tp12 = new ToricPosition(1100, 750);
		System.out.println(tp12.toVec2d().toString());
		System.out.println("tp13 + 3 * world dimension (3000,2100)");
		ToricPosition tp13 = new ToricPosition(3 * width, 3 * height);
		System.out.println(tp13.toVec2d().toString());
		System.out.println("tp14 + 3 *  - world dimension = (- 3000, -2100)");
		ToricPosition tp14 = new ToricPosition(-3000, -2100);
		System.out.println(tp14.toVec2d().toString());

		System.out.println("************************************");
		System.out.println("Test d'égalité entre 2 vecteurs");
		Vec2d vec1 = new Vec2d(3, 4);
		Vec2d vec2 = new Vec2d(3, 4);
		System.out.println(vec1.equals(vec2));	// TRUE
		vec2 = new Vec2d(4, 5);
		System.out.println(vec1.equals(vec2));	// FALSE

		System.out.println("************************************");
		System.out.println("Test d'égalité entre 2 positions toriques");
		System.out.println(tp11.toVec2d().equals(tp12.toVec2d()));	// TRUE
		System.out.println(tp11.toVec2d().equals(tp13.toVec2d()));	// FALSE
		
		
		
    }
}
