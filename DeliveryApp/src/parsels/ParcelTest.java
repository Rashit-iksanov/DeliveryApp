package parsels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParcelCostTest {

    @Test
    void shouldCalculateStandardParcelCost() {
        Parcel p = new StandardParcel("Книги", 5, "Москва", 1);
        assertEquals(10.0, p.calculateDeliveryCost(), "Стоимость: 5 кг × 2 = 10");
    }

    @Test
    void shouldCalculatePerishableParcelCost() {
        Parcel p = new PerishableParcel("Молоко", 3, "СПб", 1, 5);
        assertEquals(9.0, p.calculateDeliveryCost(), "Стоимость: 3 кг × 3 = 9");
    }

    @Test
    void shouldCalculateFragileParcelCost() {
        Parcel p = new FragileParcel("Ваза", 2, "Казань", 1);
        assertEquals(8.0, p.calculateDeliveryCost(), "Стоимость: 2 кг × 4 = 8");
    }

    //Граничный тест: вес = 0 (если валидация есть в Parcel)
    @Test
    void shouldHandleZeroWeight() {
        Parcel p = new StandardParcel("Пустая", 0, "Москва", 1);
        assertEquals(0.0, p.calculateDeliveryCost(), "Стоимость при весе 0 должна быть 0");
    }
}