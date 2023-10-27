package Lab8.Abstractions;

import Lab8.PlayerData.Player;

public interface IWearable extends IConditional {

    public void takeOff(Player owner);

    public void putOn(Player owner);
}
