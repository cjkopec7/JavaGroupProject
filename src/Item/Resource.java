package Item;

public class Resource extends Item {
	private boolean craftable;
	
	public Resource(int id) {
		super(id);
		setStackable(true);
		
	}
	public Resource(int id, int lvlr, double lbs, String name, String desc, int stk) {
		super(id, lvlr, lbs, name, desc);
		setStackable(true);
		setStack(stk);
	}

}
