package org.example.cucumber.db;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

    @RunWith(Cucumber.class)
    @CucumberOptions(
            features = "C:\\Users\\pinkp\\apache-maven-3.9.6-src\\HW_IBS_Shebankov\\src\\main\\resources\\food.feature",
            glue = "org.example.cucumber.db",
            plugin = {"pretty", "html:target/cucumber-reports"}
    )
    public class RunCucumberTest {


    }


