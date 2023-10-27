package Lab8.PlayerData;

import java.util.ArrayList;
import java.util.Arrays;

import Lab8.Abstractions.IConditional;
import Lab8.Abstractions.IConsumable;
import Lab8.Abstractions.IWearable;
import Lab8.Abstractions.Item;

public class Inventory {
	private ArrayList<Item> bag = new ArrayList<>();
	public Player owner;

	ArrayList<WearableSlot> wearables = new ArrayList<>(Arrays.asList(new WearableSlot[] {
			new WearableSlot(Slot.Torso, null),
			new WearableSlot(Slot.Pants, null),
			new WearableSlot(Slot.Hands, null),
			new WearableSlot(Slot.Shoes, null),
			new WearableSlot(Slot.Weapon, null),
			new WearableSlot(Slot.Vehicle, null),
	}));

	public Inventory() {
	}

	public Inventory(Inventory i) {
		this.bag = i.bag;
		this.owner = i.owner;
		this.wearables = i.wearables;
	}

	public void add(Item i) {
		bag.add(i);
	}

	public String print() {
		return bag.toString();
	}

	public void updateCondition() {
		for (int i = 0; i < bag.size(); i++) {
			if (bag.get(i) instanceof IConditional)
				((IConditional) bag.get(i)).UpdateCondition();
		}
		for (int i = 0; i < wearables.size(); i++) {
			if (wearables.get(i) instanceof IConditional)
				if (((IConditional) wearables.get(i)).UpdateCondition()) {
					wearables.remove(i);
					i--;
				}

		}
	}

	public Item remove(int index) {
		return bag.remove(index);
	}
	// note that there is no get, remove the item do what you will to it and return
	// it to the bag.

	public void putOn(Slot s, IWearable item) {
		if (getSlot(s).currentItem != null)
			getSlot(s).currentItem.takeOff(this.owner);

		getSlot(s).currentItem = item;
		item.putOn(this.owner);

	}

	public void eat(IConsumable c, float percent) {
		if (c.Consume(percent, owner))
			bag.remove(c);

	}

	public WearableSlot getSlot(Slot s) {
		for (WearableSlot w : wearables)
			if (w.slot == s)
				return w;
		return null;
	}
}
