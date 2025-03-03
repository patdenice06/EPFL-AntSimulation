package ch.epfl.moocprog;

public final class AntSoldier extends Ant{

	public AntSoldier(ToricPosition tp) {
		super(tp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public
	void accept(AnimalVisitor visitor, RenderingMedia s) {
		visitor.visit(this, s);
	}

}
