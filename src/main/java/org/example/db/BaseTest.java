package org.example.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public abstract class BaseTest {

    protected Connection connection;
    protected Statement statement;

    @BeforeEach
    public void setup() throws Exception {
        // Инициализация соединения с базой данных
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/mem:testdb", "user", "pass");
        statement = connection.createStatement();
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Закрытие соединения и освобождение ресурсов
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}


