package Item;

import java.io.File;

public class Item {
	private int id, stack, levelReq;
	private double weight;
	private String name, description;
	private boolean stackable;
	private File file;

	public Item(int i) {
		id = i;
		stack = 1;
		weight = 1.0;
		name = "default";
		description = "default";
	}
	
	public void setItemAttributes(int id) {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStack() {
		return stack;
	}

	public void setStack(int stack) {
		this.stack = stack;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStackable() {
		return stackable;
	}

	public void setStackable(boolean stackable) {
		this.stackable = stackable;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", stack=" + stack + ", weight=" + weight + ", name=" + name + ", description="
				+ description + ", stackable=" + stackable + "]";
	}

	

}
