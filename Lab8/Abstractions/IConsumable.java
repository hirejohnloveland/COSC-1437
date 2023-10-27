package Lab8.Abstractions;

import Lab8.PlayerData.Player;

public interface IConsumable {
    public abstract boolean Consume(float percentage, Player p);
}
