package Lab8.Items;

import Lab8.Abstractions.IConditional;
import Lab8.Abstractions.IConsumable;
import Lab8.Abstractions.Item;
import Lab8.PlayerData.Player;

public class Food extends Item implements IConsumable, IConditional {
    int condition;
    String name;
    int calories, energy, focus, warmth, fatigue;

    public Food(String name, int calories, int energy, int focus, int warmth, int fatigue) {
        this.name = name;
        this.calories = calories;
        this.energy = energy;
        this.focus = focus;
        this.warmth = warmth;
        this.fatigue = fatigue;
        condition = 1;
    }

    // Percentage 0-1f
    public boolean Consume(float percentage, Player p) {
        if (condition <= 0)
            return true; // and don't eat

        int amt = (int) (calories * percentage);
        calories -= amt;
        p.calories += amt;
        amt = (int) (energy * percentage);
        energy -= amt;
        p.energy += amt;
        amt = (int) (focus * percentage);
        focus -= amt;
        p.focus += amt;
        amt = (int) (warmth * percentage);
        warmth -= amt;
        p.warmth += amt;
        amt = (int) (fatigue * percentage);
        fatigue -= amt;
        p.fatigue += amt;

        if (calories <= 0)
            return true;
        return false;
    }

    public boolean UpdateCondition() {
        condition--;
        // the food has spoiled, but it will stay in your bag until you look at it
        return false;
    }

    public String toString() {
        String s = "";
        if (condition < 0)
            s += "spoiled!!! ";
        s += name;
        return s;
    }
}
