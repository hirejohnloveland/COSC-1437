package Lab8.Abstractions;

import java.util.ArrayList;

import Lab8.PlayerData.Player;

public abstract class Useable extends Item {
    public abstract Item Combine(ArrayList<Item> otherItems);

    public abstract void Use(Player owner);
}
