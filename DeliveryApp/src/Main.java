//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import parsels.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

        private static final Scanner scanner = new Scanner(System.in);
        private static List<Parcel> allParcels = new ArrayList<>();

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
            System.out.println("0 — Завершить");
        }

        // реализуйте методы ниже

        private static void addParcel() {
            System.out.println("Выбор типа посылки: ");
            System.out.println("1 - «стандартная посылка»  2 - «хрупкая посылка»  3 - «скоропортящаяся посылка»");
            int type = scanner.nextInt();
            System.out.println("Описание: ");
            String description = scanner.nextLine();
            System.out.print("Вес (кг): ");
            int weight = scanner.nextInt();
            System.out.print("Адрес доставки: ");
            String address = scanner.nextLine();
            System.out.print("День отправки (1-31): ");
            int sendDay = scanner.nextInt();

            Parcel parcel = null;
            if(type == 1) {
                parcel = new StandardParcel(description, weight, address, sendDay );
            } else if (type == 2) {
                parcel = new FragileParcel(description, weight, address, sendDay);
            } else if (type == 3) {
                System.out.println("Срок годности посылки: ");
                int expirationDate = scanner.nextInt();
                parcel = new PerishableParcel(description,weight, address, sendDay, expirationDate);
            } else {
                System.out.println("Не правильный выбор типа посылки!");
                return;
            }
            allParcels.add(parcel);
            System.out.println("Посылка добавлена в список!");
        }

        private static void sendParcels() {
            // Пройти по allParcels, вызвать packageItem() и deliver()
            if (allParcels.isEmpty()) {
                System.out.println("Нет посылок для отправки.");
                return;
            }
            for (Parcel parcel : allParcels) {
                parcel.packageItem();
                parcel.deliver();

                if (parcel instanceof PerishableParcel) {
                    PerishableParcel pp = (PerishableParcel) parcel;
                    System.out.print("Текущий день для проверки срока: ");
                    int currentDay = scanner.nextInt();
                    if (pp.isExpired(currentDay)) {
                        System.out.println("Внимание: посылка " + parcel.getDescription() + " могла испортиться!");
                    }
                }
            }
        }

        private static void calculateCosts() {
            // Посчитать общую стоимость всех доставок и вывести на экран
            if (allParcels.isEmpty()) {
                System.out.println("Нет посылок для расчёта.");
                return;
            }

            double total = 0;
            System.out.println("Стоимость доставки:");
            for (Parcel parcel : allParcels) {
                double cost = parcel.calculateDeliveryCost();
                total += cost;
                //System.out.printf("- %s (%d кг): %.2f руб.%n",
                //        parcel.getDescription(), parcel.getWeight(), cost);
            }
            System.out.printf("Итого: " +  total + "рублей");
        }

    }
