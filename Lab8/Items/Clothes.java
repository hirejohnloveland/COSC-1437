package Lab8.Items;

import Lab8.Abstractions.IWearable;
import Lab8.Abstractions.Item;
import Lab8.PlayerData.Player;
import Lab8.PlayerData.Slot;

public class Clothes extends Item implements IWearable {
	int conditionRate = 1;
	int condition = 100;
	Slot slotToBeWornOn;
	int defense;
	int thermalInsulation;
	int offense;

	public Clothes(Slot slot, int i, int j, int k) {
		slotToBeWornOn = slot;
		defense = i;
		thermalInsulation = j;
		offense = k;
	}

	@Override
	public void takeOff(Player owner) {
		owner.defense -= defense;
		owner.thermalInsulation -= thermalInsulation;
		owner.offense -= offense;
	}

	@Override
	public void putOn(Player owner) {
		owner.defense += defense;
		owner.thermalInsulation += thermalInsulation;
		owner.offense += offense;

	}

	@Override
	public boolean UpdateCondition() {
		condition -= conditionRate;
		if (condition <= 0)
			return true;
		return false;
	}
}
