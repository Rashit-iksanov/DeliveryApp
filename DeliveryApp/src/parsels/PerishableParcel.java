package parsels;

public class PerishableParcel extends Parcel {
    public static final int BASE_COST = 3;
    int timeToLive;

    public PerishableParcel(String description, double weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public boolean isExpired(int currentDay) {
        if (sendDay + timeToLive > currentDay) {
            return false;
        }
        return true;
    }

    @Override
    protected int getBaseCost() {
        return BASE_COST;
    }
}
