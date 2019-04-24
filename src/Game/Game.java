package Game;

import Player.Player;

//So here is a basic concept for a game, where you'll be able to level a player
//and use items to perform tasks. The example here shows I added one item to my bag

public class Game {
	private Player player;
	private Hub hub;

	public Game(String s) {
		player = new Player(s, "123");
		hub = new Hub("Java Project BETA", player); 
		System.out.println("New Game");
	}
	
	public Player loadPlayer() {
		return null;
	}
	
	public void launchGame() {
		
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public static void main(String[] args) {
		Game g = new Game("Coobs");
	}

}
