package org.example.ui.pages;

import org.example.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//span[@class='navbar-toggler-icon']")
    private WebElement navBar;

    @FindBy(xpath = "//a[normalize-space()='Песочница']")
    private WebElement dropDown;

    @FindBy(xpath = "//a[contains(@class, 'dropdown-item') and @href='/food']")
    private WebElement food;

    @FindBy(xpath = "//a[text()='Сброс данных']")
    private WebElement reset;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickNavBar() {
        click(navBar);
    }

    public void clickDropDown() {
        click(dropDown);
    }

    public boolean isDropDownVisible() {
        return isVisible(dropDown);
    }

    public void clickFood() {
        click(food);
    }

    public boolean isFoodVisible() {
        return isVisible(food);
    }

    public void clickReset() {
        click(reset);
    }
}
