package Bag;

import Item.Bottom;
import Item.Hat;
import Item.Held;
import Item.Item;
import Item.Top;
import Item.Worn;

public class Equipment {
	private Hat hat;
	private Top top;
	private Bottom bottom;
	private Held hold;
	

	public Equipment() {

	}


	public Hat getHat() {
		return hat;
	}

	public void setHat(Hat hat) {
		this.hat = hat;
	}

	public Top getTop() {
		return top;
	}

	public void setTop(Top top) {
		this.top = top;
	}

	public Bottom getBottom() {
		return bottom;
	}

	public void setBottom(Bottom bottom) {
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
