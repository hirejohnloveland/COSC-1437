
package Lab8;

import java.util.ArrayList;
import java.util.Arrays;

import Lab8.Abstractions.IWearable;
import Lab8.Abstractions.Item;
import Lab8.Abstractions.Useable;
import Lab8.Items.Clothes;
import Lab8.Items.Food;
import Lab8.Items.Match;
import Lab8.Items.WoodStick;
import Lab8.PlayerData.AccountInfo;
import Lab8.PlayerData.Inventory;
import Lab8.PlayerData.Player;
import Lab8.PlayerData.Slot;

public class Main {
	public static void main(String[] args) {
		// The given code here is just demonstrating and testing the system
		// hint: it is trying to lead you to some of the problems
		// anything here in main is not a part of the inventory system as a whole
		// and thus is not part of the lab, you may of course use main to test
		// things that to see how they work (or don't work)

		Player p = new Player(new AccountInfo("pass", 82392, "156 MiddleSet RD, Dallas, TX", "972-238-6942"));
		Inventory inventory = p.getInventory();
		inventory.owner = p;
		inventory.putOn(Slot.Torso, new Clothes(Slot.Torso, 0, 1, 0));
		inventory.putOn(Slot.Pants, new Clothes(Slot.Pants, 1, 1, 0));
		inventory.putOn(Slot.Hands, new Clothes(Slot.Hands, 1, 0, 1));
		inventory.add(new Food("Donut", 300, 10, -5, 10, -10));
		inventory.add(new WoodStick());
		inventory.add(new Match());
		inventory.add(new WoodStick());
		p.updateInventory(inventory);

		Useable stick = (Useable) inventory.remove(1);
		Useable match = (Useable) inventory.remove(1); // note we removed the previous thing so now the match is at
														// position 1
		Useable fire = (Useable) match.Combine(new ArrayList<Item>(Arrays.asList(stick)));
		System.out.println("Fire: " + fire);
		Item hardenedStick = fire.Combine(new ArrayList<Item>(Arrays.asList((Useable) inventory.remove(1))));
		inventory.putOn(Slot.Weapon, (IWearable) hardenedStick);
		p.updateInventory(inventory);
		p.PrintInfo();

		// lets let some time pass
		for (int time = 0; time < 50; time++) {
			inventory.updateCondition();
		}

		p.PrintInfo();
	}
}
