package org.example.api;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.entity.ContentType;
import org.example.utils.PropertyLoader;
import org.example.dto.Food;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import static org.example.utils.PropertyLoader.getUrl;

public class SimpleTest {
    private final int POSITIVE_STATUS = 200;
    private final String FOOD_FROM_JSON_PATH = "src/main/resources/food.json";

    @Test
    public void getFoodList() throws IOException, InterruptedException {
        URL url = getUrl("host");
        Response response = RestAssured.given()
                .contentType(String.valueOf(ContentType.APPLICATION_JSON))
                .get(url);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Food> foodList = objectMapper.readValue(response.body().asString(), new TypeReference<List<Food>>() {
        });
        for (Food foods : foodList) {
            System.out.println(foods.getName() + " " + foods.getType() + " " + foods.isExotic());
        }
        String foodFromJson = new String(Files.readAllBytes(Paths.get(FOOD_FROM_JSON_PATH)));
        List<Food> foodList2 = objectMapper.readValue(foodFromJson, new TypeReference<List<Food>>() {
        });
        Assertions.assertEquals(foodList.toString(), foodList2.toString(), "Полученный список отличается от ожидаемого");
    }

    @Test
    public void updateFoodList() throws IOException {
        URL url = getUrl("host");
        String body = "{\"name\":\"Банан\",\"type\":\"FRUIT\",\"exotic\":true}";
        Response response = RestAssured.given()
                .contentType(String.valueOf(ContentType.APPLICATION_JSON))
                .body(body)
                .post(url);
        Assertions.assertEquals(POSITIVE_STATUS, response.statusCode(), "Статус не совпадает");
        Response responseAfter = RestAssured.given()
                .contentType(String.valueOf(ContentType.APPLICATION_JSON))
                .get(url);
        Assertions.assertEquals(POSITIVE_STATUS, responseAfter.statusCode(), "Статус не совпадает");
    }


    @Test
    public void resetFoodList() throws IOException, InterruptedException {
        URL url = getUrl("hostReset");
        String body = "";
        Response response = RestAssured.given()
                .contentType(String.valueOf(ContentType.APPLICATION_JSON))
                .body(body)
                .post(url);
        Assertions.assertEquals(POSITIVE_STATUS, response.statusCode(), "Статус не совпадает");
    }

}