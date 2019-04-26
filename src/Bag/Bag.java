package Bag;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

import Item.Bottom;
import Item.Consume;
import Item.Hat;
import Item.Item;
import Item.Resource;
import Item.Tool;
import Item.Top;
import Item.Weapon;
import Item.Worn;
import Item.Held;
import Player.Player;

public class Bag {
	private int capacity, filled, empty;
	private double weight;
	private Item[] contents;
	private File file;
	private Hashtable<Integer, Item> database;

	public Bag(int i) {
		weight = 0.0;
		capacity = i;
		filled = 0;
		empty = capacity - filled;
		contents = new Item[capacity];
		file = new File("data/database.txt");
		loadDatabase(file);

	}

	public void loadDatabase(File file) {
		if (database == null) {
			database = new Hashtable<Integer, Item>();
		}
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String lne = scan.nextLine();
				String[] data = lne.split(",,");
				int id = Integer.parseInt(data[0].trim());
				String type = data[1].trim();
				String name = data[2].trim();
				String desc = data[3].trim();
				double lbs = Double.parseDouble(data[4].trim());
				int lvlr = Integer.parseInt(data[5].trim());
				if (type.equals("H")) {
					database.put(id, new Hat(id, lvlr, lbs, name, desc));
				} else if (type.contentEquals("S")) {
					database.put(id, new Top(id, lvlr, lbs, name, desc));

				} else if (type.contentEquals("B")) {
					database.put(id, new Bottom(id, lvlr, lbs, name, desc));

				} else if (type.contentEquals("R")) {
					database.put(id, new Resource(id, lvlr, lbs, name, desc, 1));

				} else if (type.contentEquals("T")) {
					database.put(id, new Tool(id, lvlr, lbs, name, desc));

				} else if (type.contentEquals("C")) {
					database.put(id, new Consume(id, lvlr, lbs, name, desc));
				} else if (type.contentEquals("W")) {
					database.put(id, new Weapon(id, lvlr, lbs, name, desc));

				} else {
					database.put(id, new Item(id, lvlr, lbs, name, desc));
				}

			}
//			if (database.get(2) instanceof Worn) {
//				System.out.println("It is a worn item");
//			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void storeItem(Item i) {

		contents[filled] = i;
		weight += i.getWeight();
		filled++;
		empty--;

	}

	public void removeItem(int slot) {
		weight -= contents[slot].getWeight();
		;
		for (int i = slot; i < contents.length - 1; i++) {
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Hashtable<Integer, Item> getDatabase() {
		return database;
	}

	public void setDatabase(Hashtable<Integer, Item> database) {
		this.database = database;
	}

	@Override
	public String toString() {
		return "Bag [capacity=" + capacity + ", filled=" + filled + ", empty=" + empty + ", weight=" + weight
				+ ", contents=" + Arrays.toString(contents);
	}

	public static void main(String[] args) {
		Bag b = new Bag(20);
		System.out.println(b.getDatabase().toString());
	}

}
