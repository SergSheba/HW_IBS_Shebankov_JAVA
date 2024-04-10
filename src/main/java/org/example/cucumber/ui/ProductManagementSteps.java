package org.example.cucumber.ui;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.ui.pages.HomePage;
import org.example.ui.pages.ProdPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class ProductManagementSteps {
    private WebDriver driver;
    private HomePage homePage;
    private ProdPage prodPage;

    @Given("пользователь находится на главной странице")
    public void пользователь_находится_на_главной_странице() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080"); // Измените URL на актуальный
        homePage = new HomePage(driver);
        prodPage = new ProdPage(driver);
    }

    @When("пользователь переходит в раздел \"Песочница\"")
    public void пользователь_переходит_в_раздел_песочница() {
        homePage.clickDropDown();
    }

  @And("пользователь выбирает \"Товары\"")
  public void пользователь_выбирает_товары() {
        homePage.clickFood();
  }

    @And("пользователь выбирает \"Добавить продукт\"")
    public void пользователь_выбирает_добавить_продукт() {
        prodPage.clickButtonAdd();
    }

    @And("пользователь вводит название {string} для продукта")
    public void пользователь_вводит_название_для_продукта(String name) {
        prodPage.enterAndVerifyTextInNameProd(name);
    }

    @And("пользователь выбирает тип {string} для продукта")
    public void пользователь_выбирает_тип_для_продукта(String type) {
        prodPage.selectType(type.toUpperCase());
    }

    @And("пользователь отмечает продукт как экзотический")
    public void пользователь_отмечает_продукт_как_экзотический() {
        prodPage.clickCheckBox();
    }

    @And("пользователь сохраняет продукт")
    public void пользователь_сохраняет_продукт() {
        prodPage.clickButtonSave();
    }

    @Then("пользователь видит {string} в списке продуктов")
    public void пользователь_видит_в_списке_продуктов(String name) {
        assertTrue(prodPage.isProductDisplayedOnPage(name));
    }

    @io.cucumber.java.After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}