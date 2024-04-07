package org.example.ui.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.utils.PropertyLoader;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.example.utils.PropertyLoader.getUrl;

public abstract class BaseTest {

    protected WebDriver driver;

    @Before
    public void setup() throws IOException {
        URL url = getUrl("baseUrl");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(String.valueOf(url));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
