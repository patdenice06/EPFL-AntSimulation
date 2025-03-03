package ch.epfl.moocprog;

import ch.epfl.moocprog.utils.Time;

public abstract class Ant extends Animal{

	private static int hitpoints;
	private static Time lifespan;

	public Ant(ToricPosition tp) {
		super(tp, hitpoints, lifespan);
	}

}
