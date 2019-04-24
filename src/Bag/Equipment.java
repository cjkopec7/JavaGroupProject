package Bag;

import Item.Held;
import Item.Item;
import Item.Worn;

public class Equipment {
	private Worn hat, top, bottom;
	private Held hold;

	public Equipment() {

	}

	public void checkSlots() {

	}

	public Worn getHat() {
		return hat;
	}

	public void setHat(Worn hat) {
		this.hat = hat;
	}

	public Worn getTop() {
		return top;
	}

	public void setTop(Worn top) {
		this.top = top;
	}

	public Worn getBottom() {
		return bottom;
	}

	public void setBottom(Worn bottom) {
		this.bottom = bottom;
	}

	public Held getHold() {
		return hold;
	}

	public void setHold(Held hold) {
		this.hold = hold;
	}

	@Override
	public String toString() {
		return "Equipment [hat=" + hat + ", top=" + top + ", bottom=" + bottom + ", hold=" + hold + "]";
	}
	
	

}
