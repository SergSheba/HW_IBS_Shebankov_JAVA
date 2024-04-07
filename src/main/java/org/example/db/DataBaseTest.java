package org.example.db;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataBaseTest extends BaseTest {

    @Test
    public void testAddAndFindPotato() throws Exception {
        // Добавление записи о новом продукте
        statement.executeUpdate("INSERT INTO FOOD (FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) VALUES ('Картошка', 'VEGETABLE', 0);");

        // Проверка наличия добавленного продукта и вывод в консоль
        ResultSet resultSet = statement.executeQuery("SELECT * FROM FOOD WHERE FOOD_NAME = 'Картошка';");
        assertTrue(resultSet.next(), "Продукт 'Картошка' не найден в базе данных.");

        // Чтение и вывод данных продукта
        String foodName = resultSet.getString("FOOD_NAME");
        String foodType = resultSet.getString("FOOD_TYPE");
        int foodExotic = resultSet.getInt("FOOD_EXOTIC");

        System.out.println("Найден продукт: ");
        System.out.println("Название: " + foodName);
        System.out.println("Тип: " + foodType);
        System.out.println("Экзотичность: " + foodExotic);

        assertTrue("Картошка".equals(foodName) && "VEGETABLE".equals(foodType) && foodExotic == 0, "Данные продукта не соответствуют ожидаемым.");
    }

    @Test
    public void testAddAndFindOgurdyn() throws Exception {
        // Добавление записи о новом продукте
        statement.executeUpdate("INSERT INTO FOOD (FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) VALUES ('Огурдыня','VEGETABLE', 1);");

        // Проверка наличия добавленного продукта и вывод в консоль
        ResultSet resultSet = statement.executeQuery("SELECT * FROM FOOD WHERE FOOD_NAME = 'Огурдыня';");
        assertTrue(resultSet.next(), "Продукт 'Огурдыня' не найден в базе данных.");

        // Чтение и вывод данных продукта
        String foodName = resultSet.getString("FOOD_NAME");
        String foodType = resultSet.getString("FOOD_TYPE");
        int foodExotic = resultSet.getInt("FOOD_EXOTIC");

        System.out.println("Найден продукт: ");
        System.out.println("Название: " + foodName);
        System.out.println("Тип: " + foodType);
        System.out.println("Экзотичность: " + foodExotic);

        assertTrue("Огурдыня".equals(foodName) && "VEGETABLE".equals(foodType) && foodExotic == 1, "Данные продукта не соответствуют ожидаемым.");
    }

    @Test
    public void testAddAndFindPineappel() throws Exception {
        // Добавление записи о новом продукте
        statement.executeUpdate("INSERT INTO FOOD (FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) VALUES ('Ананас', 'FRUIT', 1);");

        // Проверка наличия добавленного продукта и вывод в консоль
        ResultSet resultSet = statement.executeQuery("SELECT * FROM FOOD WHERE FOOD_NAME = 'Ананас';");
        assertTrue(resultSet.next(), "Продукт 'Ананас' не найден в базе данных.");

        // Чтение и вывод данных продукта
        String foodName = resultSet.getString("FOOD_NAME");
        String foodType = resultSet.getString("FOOD_TYPE");
        int foodExotic = resultSet.getInt("FOOD_EXOTIC");

        System.out.println("Найден продукт: ");
        System.out.println("Название: " + foodName);
        System.out.println("Тип: " + foodType);
        System.out.println("Экзотичность: " + foodExotic);

        assertTrue("Ананас".equals(foodName) && "FRUIT".equals(foodType) && foodExotic == 1, "Данные продукта не соответствуют ожидаемым.");
    }

    @Test
    public void testAddAndFindBanan() throws Exception {
        // Добавление записи о новом продукте
        statement.executeUpdate("INSERT INTO FOOD (FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) VALUES ('Банан', 'FRUIT', 0);");

        // Проверка наличия добавленного продукта и вывод в консоль
        ResultSet resultSet = statement.executeQuery("SELECT * FROM FOOD WHERE FOOD_NAME = 'Банан';");
        assertTrue(resultSet.next(), "Продукт 'Банан' не найден в базе данных.");

        // Чтение и вывод данных продукта
        String foodName = resultSet.getString("FOOD_NAME");
        String foodType = resultSet.getString("FOOD_TYPE");
        int foodExotic = resultSet.getInt("FOOD_EXOTIC");

        System.out.println("Найден продукт: ");
        System.out.println("Название: " + foodName);
        System.out.println("Тип: " + foodType);
        System.out.println("Экзотичность: " + foodExotic);

        assertTrue("Банан".equals(foodName) && "FRUIT".equals(foodType) && foodExotic == 0, "Данные продукта не соответствуют ожидаемым.");
    }

    @Test
    public void testDeletePineapple() throws Exception {
        // Удаление записи о продукте "Ананас"
        int rowsAffected = statement.executeUpdate("DELETE FROM FOOD WHERE FOOD_NAME = 'Ананас';");

        // Проверка, что запись была удалена
        assertTrue(rowsAffected > 0, "Продукт 'Ананас' не был удален.");

        // Дополнительная проверка отсутствия продукта в базе
        ResultSet resultSet = statement.executeQuery("SELECT * FROM FOOD WHERE FOOD_NAME = 'Ананас';");
        assertFalse(resultSet.next(), "Продукт 'Ананас' все еще присутствует в базе данных после удаления.");

        // Вывод в консоль для подтверждения удаления (опционально)
        System.out.println("Продукт 'Ананас' успешно удален из базы данных.");

        //Вывод в консоль всей базы данных
       resultSet = statement.executeQuery("SELECT * FROM FOOD;");
        System.out.println("Оставшиеся продукты в базе данных:");
        while (resultSet.next()) {
            String foodName = resultSet.getString("FOOD_NAME");
            String foodType = resultSet.getString("FOOD_TYPE");
            int foodExotic = resultSet.getInt("FOOD_EXOTIC");
            System.out.println("Название: " + foodName + ", Тип: " + foodType + ", Экзотичность: " + (foodExotic == 1 ? "Да" : "Нет"));
        }
    }

}
