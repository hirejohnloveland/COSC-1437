package Lab8.PlayerData;

public class Player {
    private AccountInfo account;
    public int calories, energy, focus, warmth, fatigue, defense, thermalInsulation, offense;
    private Inventory inventory = new Inventory();

    public Player(AccountInfo acc) {
        account = new AccountInfo(acc);
        // player baseline
        calories = 800;
        energy = 100;
        focus = 50;
        warmth = 100;
        fatigue = 0;
        defense = 0;
        thermalInsulation = 1;
        offense = 1;
    }

    public void updateInventory(Inventory i) {
        inventory = i;
    }

    public Inventory getInventory() {
        return new Inventory(inventory);
    }

    public AccountInfo getAccountInfo() {
        return account;
    }

    public void PrintInfo() {
        System.out.println("Player");
        System.out.println("Calories: " + this.calories);
        System.out.println("Energy: " + this.energy);
        System.out.println("Focus: " + this.focus);
        System.out.println("Warmth: " + this.warmth);
        System.out.println("Fatigue: " + this.fatigue);
        System.out.println("Defense: " + this.defense);
        System.out.println("ThermalInsulation: " + this.thermalInsulation);
        System.out.println("Offense: " + this.offense);
        System.out.println("Bag: " + this.inventory.print());
    }
}
