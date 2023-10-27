package Lab8.Items;

import java.util.ArrayList;

import Lab8.Abstractions.Item;
import Lab8.Abstractions.Useable;
import Lab8.PlayerData.Player;

public class Match extends Useable {

    @Override
    public Item Combine(ArrayList<Item> otherItems) {
        System.out.println(this + " + " + otherItems.get(0));
        if (otherItems.get(0) instanceof WoodStick) {
            System.out.println("I have created fire");
            return new Fire();
        }
        return null;
    }

    @Override
    public void Use(Player owner) {
        System.out.println("Ahhh, warmth.... aw.. so short and fleeting");
        owner.warmth += 10;
    }

}
