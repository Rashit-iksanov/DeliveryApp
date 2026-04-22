package parsels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParcelBoxTest {
    @Test
    void shouldAddParcelWhenWeightLimitNotExceeded() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(20);
        StandardParcel p1 = new StandardParcel("Книга", 5, "Москва", 1);
        StandardParcel p2 = new StandardParcel("Журнал", 3, "СПб", 2);

        assertTrue(box.addParcel(p1), "Первая посылка должна добавиться");
        assertTrue(box.addParcel(p2), "Вторая посылка должна добавиться");
        assertEquals(2, box.getParcelsCount(), "В коробке должно быть 2 посылки");
        assertEquals(8, box.getCurrentWeight(), "Общий вес должен быть 8 кг");
    }

    @Test
    void shouldNotAddParcelWhenWeightLimitExceeded() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        StandardParcel p1 = new StandardParcel("Тяжёлая книга", 8, "Москва", 1);
        StandardParcel p2 = new StandardParcel("Ещё книга", 5, "СПб", 2);

        assertTrue(box.addParcel(p1), "Первая посылка должна добавиться");
        assertFalse(box.addParcel(p2), "Вторая посылка НЕ должна добавиться (превышение веса)");
        assertEquals(1, box.getParcelsCount(), "В коробке должна остаться 1 посылка");
        assertEquals(8, box.getCurrentWeight(), "Вес должен остаться 8 кг");
    }

    // Граничный тест: добавление посылки, которая ровно заполняет коробку
    @Test
    void shouldAddParcelWhenWeightEqualsMaxLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        StandardParcel p1 = new StandardParcel("Ровно 10 кг", 10, "Москва", 1);

        assertTrue(box.addParcel(p1), "Посылка с весом ровно 10 кг должна добавиться");
        assertEquals(10, box.getCurrentWeight(), "Вес должен быть ровно 10 кг");

        StandardParcel p2 = new StandardParcel("Лёгкая", 1, "СПб", 2);
        assertFalse(box.addParcel(p2), "После заполнения коробки добавление должно блокироваться");
    }
}