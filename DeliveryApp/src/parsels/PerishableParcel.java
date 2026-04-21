package parsels;

public class PerishableParcel extends Parcel {
    public static final int BASE_COST = 4;
    int timeToLive;
    int currentDay;

    public PerishableParcel(String description, double weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
        this.currentDay = currentDay;
    }

    public boolean isExpired(int currentDay) {
        if ((sendDay + timeToLive) >= this.currentDay) {
            return false;
        }
        return true;
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка " + description + " обернута в защитную плёнку!");
        super.packageItem();
    }

    @Override
    protected int getBaseCost() {
        return BASE_COST;
    }
}
