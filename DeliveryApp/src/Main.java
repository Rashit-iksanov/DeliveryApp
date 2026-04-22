//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import parsels.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackableItems = new ArrayList<>();
    private static ParcelBox<StandardParcel> standardBox = new ParcelBox<>(50);
    private static ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(30);
    private static ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(40);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    trackItems();
                    break;
                case 5:
                    showBox();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Обновить статус отслеживаемых отправлений");
        System.out.println("5 — Показать содержимое коробок");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void addParcel() {
        System.out.println("Выбор типа посылки: ");
        System.out.println("1 - «стандартная посылка»  2 - «хрупкая посылка»  3 - «скоропортящаяся посылка»");
        int type = Integer.parseInt(scanner.nextLine());
        System.out.println("Описание: ");
        String description = scanner.nextLine();
        System.out.print("Вес (кг): ");
        int weight = Integer.parseInt(scanner.nextLine());
        System.out.print("Адрес доставки: ");
        String address = scanner.nextLine();
        System.out.print("День отправки (1-31): ");
        int sendDay = Integer.parseInt(scanner.nextLine());

        Parcel parcel = null;

        if (type == 1) {
            parcel = new StandardParcel(description, weight, address, sendDay);
        } else if (type == 2) {
            parcel = new FragileParcel(description, weight, address, sendDay);
        } else if (type == 3) {
            System.out.println("Срок годности посылки: ");
            int expirationDate = scanner.nextInt();
            parcel = new PerishableParcel(description, weight, address, sendDay, expirationDate);
        } else {
            System.out.println("Не правильный выбор типа посылки!");
            return;
        }

        allParcels.add(parcel);
        if (parcel instanceof Trackable) {
            trackableItems.add((Trackable) parcel);
        }
        if (parcel instanceof StandardParcel) {
            standardBox.addParcel((StandardParcel) parcel);
        } else if (parcel instanceof FragileParcel) {
            fragileBox.addParcel((FragileParcel) parcel);
        } else if (parcel instanceof PerishableParcel) {
            perishableBox.addParcel((PerishableParcel) parcel);
        }

        System.out.println("Посылка добавлена в список!");
        System.out.println("   ***");
    }

    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        if (allParcels.isEmpty()) {
            System.out.println("Нет посылок для отправки.");
            System.out.println("   ***");
            return;
        }
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
            System.out.println("   ***");

            if (parcel instanceof PerishableParcel) {
                PerishableParcel pp = (PerishableParcel) parcel;
                System.out.println("Текущий день для проверки срока: ");
                int currentDay = Integer.parseInt(scanner.nextLine());
                if (pp.isExpired(currentDay)) {
                    System.out.println("Внимание: посылка " + parcel.getDescription() + " могла испортиться!");
                    System.out.println("   ***");
                }
            }
            System.out.println("С посылкой все в порядке!");
            System.out.println("   ***");

        }
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        if (allParcels.isEmpty()) {
            System.out.println("Нет посылок для расчёта.");
            System.out.println("   ***");
            return;
        }

        double total = 0;
        System.out.println("Стоимость доставки:");
        for (Parcel parcel : allParcels) {
            double cost = parcel.calculateDeliveryCost();
            total += cost;
        }
        System.out.println("Итого: " + total + " рублей");
        System.out.println("   ***");
    }

    private static void trackItems() {
        if (trackableItems.isEmpty()) {
            System.out.println("Нет отправлений с трекингом.");
            return;
        }

        System.out.print("Введите новое местоположение: ");
        String location = scanner.nextLine();

        System.out.println("Обновление статусов:");
        for (Trackable item : trackableItems) {
            item.reportStatus(location);
        }
    }

    private static void showBox() {
        System.out.println("Выберите тип коробки: ");
        System.out.println("1 — Стандартные посылки");
        System.out.println("2 — Хрупкие посылки");
        System.out.println("3 — Скоропортящиеся посылки");
        System.out.print("Ваш выбор: ");

        int choice = Integer.parseInt(scanner.nextLine());

        List<? extends Parcel> contents;
        String boxName;
        int currentWeight, maxWeight;

        switch (choice) {
            case 1:
                contents = standardBox.getAllParcels();
                boxName = "Стандартные";
                currentWeight = standardBox.getCurrentWeight();
                maxWeight = standardBox.getMaxWeight();
                break;
            case 2:
                contents = fragileBox.getAllParcels();
                boxName = "Хрупкие";
                currentWeight = fragileBox.getCurrentWeight();
                maxWeight = fragileBox.getMaxWeight();
                break;
            case 3:
                contents = perishableBox.getAllParcels();
                boxName = "Скоропортящиеся";
                currentWeight = perishableBox.getCurrentWeight();
                maxWeight = perishableBox.getMaxWeight();
                break;
            default:
                System.out.println("Неверный выбор.");
                return;

        }

        System.out.println("Коробка: " + boxName + " (вес: " + currentWeight + "/" + maxWeight + " кг)");
        if (contents.isEmpty()) {
            System.out.println("  (пусто)");
        } else {
            for (Parcel p : contents) {
                System.out.println("  - " + p.getDescription());
            }
        }
    }
}
