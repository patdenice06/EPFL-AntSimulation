package ch.epfl.moocprog;

public final class AntWorker extends Ant{

	public AntWorker(ToricPosition tp) {
		super(tp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public
	void accept(AnimalVisitor visitor, RenderingMedia s) {
		visitor.visit(this, s);
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

}
