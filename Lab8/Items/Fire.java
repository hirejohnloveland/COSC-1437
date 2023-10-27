package Lab8.Items;

import java.util.ArrayList;

import Lab8.Abstractions.Item;
import Lab8.Abstractions.Useable;
import Lab8.PlayerData.Player;

public class Fire extends Useable {
    @Override
    public Item Combine(ArrayList<Item> otherItems) {
        if (otherItems.get(0) instanceof WoodStick)
            return new FireHardenedStick(8, 3);
        return null;
    }

    @Override
    public void Use(Player owner) {
        owner.warmth += 100;
    }
}
