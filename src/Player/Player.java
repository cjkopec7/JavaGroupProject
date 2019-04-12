package Player;

import Bag.Bag;
import Bag.Equipment;
import Bag.Storage;
import Item.Item;
import Item.Resource;

public class Player {
	private String name, password;
	private double level, experience, expTillLevel;
	private int money, health, stamina;
	private Bag bag;
	private Equipment equips;
	private Storage storage;

	public Player(String user, String pass) {
		name = user;
		password = pass;
		level = 1;
		health = 100;
		stamina = 100;
		expTillLevel = expSet();
		experience = 0;
		money = 100;
		bag = new Bag(20);
//		bag.storeItem(new Resource(69));
//		bag.storeItem(new Resource(69));
		bag.storeItem(new Item(420));
		equips = new Equipment();
//		gainExp(83);
//		bag.removeItem(0);
	}

	public void gainExp(double xp) {
		experience += xp;
		expTillLevel -= xp;
		double rollover = Math.abs(expTillLevel);
		if (expTillLevel <= 0) {
			levelUp();
			experience += rollover;
			expTillLevel -= rollover;
			System.out.println(name + " leveled up to " + level);
		}

	}

	private double expSet() {
		double exp = 75 * Math.pow(1.10177068, level);
		return exp;
	}

	private void levelUp() {
		level++;
		expTillLevel = expSet();
	}
	public Item craftItem(Resource[] items) {
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getLevel() {
		return level;
	}

	public void setLevel(double level) {
		this.level = level;
	}

	public double getExperience() {
		return experience;
	}

	public void setExperience(double experience) {
		this.experience = experience;
	}

	public double getExpTillLevel() {
		return expTillLevel;
	}

	public void setExpTillLevel(double expTillLevel) {
		this.expTillLevel = expTillLevel;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public Bag getBag() {
		return bag;
	}

	public void setBag(Bag bag) {
		this.bag = bag;
	}

	public Equipment getEquips() {
		return equips;
	}

	public void setEquips(Equipment equips) {
		this.equips = equips;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", password=" + password + ", level=" + level + ", experience=" + experience
				+ ", expTillLevel=" + expTillLevel + ", money=" + money + ", health=" + health + ", stamina=" + stamina
				+ ", bag=" + bag + ", equips=" + equips + "]";
	}

	public static void main(String[] args) {
		Player p = new Player("Coobs", "123");

	}

}
