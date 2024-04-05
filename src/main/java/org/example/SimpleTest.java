package org.example;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.example.dto.Food;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Flow;


public class SimpleTest {
    private final int POSITIVE_STATUS = 200;
    private final String FOOD_FROM_JSON_PATH = "src/main/resources/food.json";

    @Test
    public void testSomething() throws IOException {
        Properties conf = PropertyLoader.loadProperties();
        String property = conf.getProperty("host");
        System.out.println(property);
    }

    @Test
    public void getFoodList() throws IOException, InterruptedException {
        Properties conf = PropertyLoader.loadProperties();
        String propertyHost = conf.getProperty("host");
        URL url = new URL(propertyHost);
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(propertyHost)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(POSITIVE_STATUS, response.statusCode(), "Статус не совпадает");
        Assertions.assertNotNull(response.body(), "Вернулось пустое тело запроса");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Food> foodList = objectMapper.readValue(response.body().toString(), new TypeReference<List<Food>>() {
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
    public void updateFoodList() throws IOException, InterruptedException {
        Properties conf = PropertyLoader.loadProperties();
        String propertyHost = conf.getProperty("host");
        URL url = new URL(propertyHost);
        HttpClient client = HttpClient.newBuilder().build();
        Food food = new Food();
        food.setName("Банан");
        food.setType("VEGETABLE");
        food.setExotic(false);
//        Gson gson = new Gson();
//        String json = gson.toJson(food);
//        HttpRequest request = HttpRequest.newBuilder()
//                .POST(HttpRequest.BodyPublishers.ofString("{\"name\":\"Банан\",\"type\":\"VEGETABLE\",\"exotic\":true}"))
//                .uri(URI.create(propertyHost))
//                .setHeader("Content-Type", "application/json")
//                .build();
        HttpPost httpPost = new HttpPost(String.valueOf(url));
        String body = "{\"name\":\"Банан\",\"type\":\"VEGETABLE\",\"exotic\":true}";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        StringEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);

        CloseableHttpResponse response = httpclient.execute(httpPost);

  //     HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
  //      Assertions.assertEquals(POSITIVE_STATUS, response.get, "Статус не совпадает");
        HttpRequest requestAfter = HttpRequest.newBuilder().uri(URI.create(propertyHost)).build();
        HttpResponse<String> responseAfter = client.send(requestAfter, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(POSITIVE_STATUS, responseAfter.statusCode(), "Статус не совпадает");
        Assertions.assertNotNull(responseAfter.body(), "Вернулось пустое тело запроса");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Food> foodList = objectMapper.readValue(responseAfter.body().toString(), new TypeReference<List<Food>>() {
        });
        for (Food foods : foodList) {
            System.out.println(foods.getName() + " " + foods.getType() + " " + foods.isExotic());
        }
        System.out.println(foodList.toString());

    }


    @Test
    public void resetFoodList() throws IOException, InterruptedException {
        Properties conf = PropertyLoader.loadProperties();
        String propertyHost = conf.getProperty("hostReset");
        URL url = new URL(propertyHost);
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().POST(new HttpRequest.BodyPublisher() {
            @Override
            public long contentLength() {
                return 0;
            }

            @Override
            public void subscribe(Flow.Subscriber<? super ByteBuffer> subscriber) {

            }
        }).uri(URI.create(propertyHost)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(POSITIVE_STATUS, response.statusCode(), "Статус не совпадает");

    }

}