package parsels;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private final int maxWeight;
    private final List<T> parcels;
    private int currentWeight;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
        this.parcels = new ArrayList<>();
        this.currentWeight = 0;
    }

    public List<T> getAllParcels() {
        return new ArrayList<>(parcels);
    }

    public boolean addParcel(T parcel) {
        if (parcel == null) {
            return false;
        }
        double parcelWeight = parcel.getWeight();
        if (currentWeight + parcelWeight > maxWeight) {
            System.out.println("Превышен максимальный вес коробки (" + maxWeight + " кг). " +
                    "Посылка <<" + parcel.getDescription() + ">> не добавлена.");
            return false;
        }
        parcels.add(parcel);
        currentWeight += parcelWeight;
        System.out.println("Посылка <<" + parcel.getDescription() + ">> добавлена в коробку");
        return true;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public int getParcelsCount() {
        return parcels.size();
    }
}
