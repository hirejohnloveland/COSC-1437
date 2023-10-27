package Lab8.Items;

import java.util.ArrayList;

import Lab8.Abstractions.IWeapon;
import Lab8.Abstractions.Item;
import Lab8.Abstractions.Useable;
import Lab8.PlayerData.Player;

public class WoodStick extends Useable implements IWeapon {
    int conditionRate = 1;
    int condition = 100;
    int offense = 5;
    int defense = 3;

    public void takeOff(Player owner) {
        owner.offense -= offense;
        owner.defense -= defense;

    }

    public void putOn(Player owner) {
        owner.offense += offense;
        owner.defense += defense;

    }

    public Item Combine(ArrayList<Item> otherItems) {
        System.out.println(this + " + " + otherItems.get(0));
        if (otherItems.get(0) instanceof Match) {
            System.out.println("I have created fire");
            return new Fire();
        }
        return null;
    }

    @Override
    public void Use(Player owner) {
        System.out.println("Oww");
        owner.calories -= 1;
    }

    @Override
    public boolean UpdateCondition() {
        condition -= conditionRate;
        if (condition <= 0)
            return true;
        return false;
    }
}
