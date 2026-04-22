package parsels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PerishableParcelTest {

    @Test
    void shouldNotBeExpiredWhenWithinTTL() {
        PerishableParcel p = new PerishableParcel("Йогурт", 1, "Москва", 10,
                5);
        // sendDay=10, TTL=5 → годен до дня 14 включительно
        assertFalse(p.isExpired(14), "Посылка не должна быть испорчена на 14-й день");
    }

    @Test
    void shouldBeExpiredWhenBeyondTTL() {
        PerishableParcel p = new PerishableParcel("Йогурт", 1, "Москва", 10,
                5);
        // sendDay=10, TTL=5 → портится с 15-го дня
        assertTrue(p.isExpired(15), "Посылка должна быть испорчена на 15-й день");
    }

    //Граничный тест: ровно на границе срока
    @Test
    void shouldBeExpiredOnBoundary() {
        PerishableParcel p = new PerishableParcel("Сыр", 2, "СПб", 1,
                3);
        // годен: 1+3=4 → портится с 5-го дня
        assertFalse(p.isExpired(4), "На 4-й день ещё не испорчена");
        assertTrue(p.isExpired(5), "На 5-й день уже испорчена");
    }
}