package org.example.cucumber.ui;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C:\\Users\\pinkp\\apache-maven-3.9.6-src\\HW_IBS_Shebankov\\src\\main\\resources\\food.ui.feature",
        glue = {"org.example.cucumber.ui"},
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class RunCucumberTestUi {

}

