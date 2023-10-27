package Lab8.PlayerData;

import Lab8.Abstractions.IWearable;

public class WearableSlot {
    public WearableSlot(Slot slot, IWearable currentItem) {
        this.slot = slot;
        this.currentItem = currentItem;
    }

    public IWearable putOnItem(IWearable newItem) {
        IWearable old = currentItem;
        currentItem = newItem;
        return old;
    }

    public Slot slot;
    public IWearable currentItem;
}
