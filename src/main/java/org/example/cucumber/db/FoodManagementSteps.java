package org.example.cucumber.db;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import static org.junit.Assert.*;
import java.sql.*;

public class FoodManagementSteps {

    private Connection connection;
    private Statement statement;

    @Before
    public void setUp() throws Exception {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "user", "pass");
        statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS FOOD (FOOD_NAME VARCHAR(255), FOOD_TYPE VARCHAR(255), FOOD_EXOTIC BOOLEAN);");
    }

    @Given("база данных инициализирована")
    public void база_данных_инициализирована() {
        // База данных уже инициализирована в @Before

    }

    @When("я добавляю продукт {string} типа {string} с экзотичностью {string}")
    public void я_добавляю_продукт_с_типом_и_экзотичностью(String name, String type, String exotic) throws Exception {
        boolean isExotic = "да".equals(exotic);
        statement.executeUpdate(String.format("INSERT INTO FOOD (FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) VALUES ('%s', '%s', %b);", name, type, isExotic));
    }

    @Then("я могу найти продукт {string} в базе данных")
    public void я_могу_найти_продукт_в_базе_данных(String name) throws Exception {
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM FOOD WHERE FOOD_NAME = '%s';", name));
        assertTrue("Продукт не найден: " + name, resultSet.next());
    }

    @When("я удаляю продукт {string} из базы данных")
    public void я_удаляю_продукт_из_базы_данных(String name) throws Exception {
        statement.executeUpdate(String.format("DELETE FROM FOOD WHERE FOOD_NAME = '%s';", name));
    }

    @Then("продукт {string} не должен присутствовать в базе данных")
    public void продукт_не_должен_присутствовать_в_базе_данных(String name) throws Exception {
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM FOOD WHERE FOOD_NAME = '%s';", name));
        assertFalse("Продукт всё ещё присутствует: " + name, resultSet.next());
    }

    @After
    public void tearDown() throws Exception {
        if (statement != null) statement.close();
        if (connection != null) connection.close();
    }
}
