package Player;

import Bag.Bag;
import Bag.Equipment;
import Bag.Storage;
import Item.Item;
import Item.Resource;
import Item.Worn;

public class Player {
	private String name, password;
	private double level, experience, expTillLevel;
	private int money, health, stamina, strength, magic, archery, defence;
	private Bag bag;
	private Equipment equips;
	private Storage storage;

	public Player(String user, String pass) {
		name = user;
		password = pass;
		level = 1;
		strength = 0;
		magic = 0;
		archery = 0;
		health = 100;
		stamina = 100;
		expTillLevel = expSet();
		experience = 0;
		money = 100;
		bag = new Bag(20);
		equips = new Equipment();
		storage = new Storage(10);
		bag.storeItem(bag.getDatabase().get(7));
//		bag.storeItem(bag.getDatabase().get(2));
//		bag.removeItem(0);
	}

	public void gainExp(double xp) {
		experience += xp;
		expTillLevel -= xp;
		while (expTillLevel <= 0) {
			double rollover = Math.abs(expTillLevel);
			levelUp();
			expTillLevel -= rollover;
//			System.out.println(name + " leveled up to " + (int) level);
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

	public void useItem(Item i) {

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

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getMagic() {
		return magic;
	}

	public void setMagic(int magic) {
		this.magic = magic;
	}

	public int getArchery() {
		return archery;
	}

	public void setArchery(int archery) {
		this.archery = archery;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
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
				+ ", strength=" + strength + ", magic=" + magic + ", archery=" + archery + ", defence=" + defence
				+ ", bag=" + bag + ", equips=" + equips + ", storage=" + storage + "]";
	}

	public static void main(String[] args) {
		Player p = new Player("Coobs", "123");

	}

}
