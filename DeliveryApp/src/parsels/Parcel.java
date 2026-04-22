package parsels;

public abstract class Parcel {
    protected String description;
    protected double weight;
    protected String deliveryAddress;
    protected int sendDay;

    protected Parcel(String description, double weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public void packageItem() {
        System.out.println("Посылка <<" + description + ">> упакована!");
    }

    public void deliver() {
        System.out.println("Посылка <<" + description + ">> доставлена по адресу: <<" + deliveryAddress + ">>");
    }

    public final double calculateDeliveryCost() {
        System.out.println("Вычислить стоимость доставки: ");
        if (weight <= 0) {
            System.out.println("Вес должен быть больше 0");
        }
        return weight * getBaseCost();
    }

    protected abstract int getBaseCost();

    public String getDescription() {
        return description;
    }

    public double getWeight() {
        return weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public int getSendDay() {
        return sendDay;
    }
}


