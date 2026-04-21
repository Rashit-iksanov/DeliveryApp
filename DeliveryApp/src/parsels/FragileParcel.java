package parsels;

public class FragileParcel extends Parcel {
    public static final int BASE_COST = 3;

    public FragileParcel(String description, double weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    protected int getBaseCost() {
        return BASE_COST;
    }
}
