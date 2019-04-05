package Bag;

import java.util.Arrays;

import Item.Item;

public class Bag {
	private int capacity, filled, empty;
	private double weight;
	private Item[] contents;

	public Bag(int i) {
		weight = 0.0;
		capacity = i;
		filled = 0;
		empty = capacity - filled;
		contents = new Item[capacity];

	}

	public void storeItem(Item i) {
		contents[filled] = i;
		weight += i.getWeight();
		filled++;
		empty--;
	}

	public void removeItem(int slot) {
		weight -= contents[slot].getWeight();
;		for (int i = slot; i < contents.length - 1; i++) {
			contents[i] = contents[i + 1];
		}
		empty++;
		filled--;

	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getFilled() {
		return filled;
	}

	public void setFilled(int filled) {
		this.filled = filled;
	}

	public int getEmpty() {
		return empty;
	}

	public void setEmpty(int empty) {
		this.empty = empty;
	}

	public double getWeight() {
		return weight;
	}
//test
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Item[] getContents() {
		return contents;
	}

	public void setContents(Item[] contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "Bag [capacity=" + capacity + ", filled=" + filled + ", empty=" + empty + ", weight=" + weight
				+ ", contents=" + Arrays.toString(contents) + "]";
	}

}
