package org.example.ui.pages;

import org.example.ui.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProdPage extends BasePage {

    @FindBy(xpath = "//button[text()='Добавить']")
    private WebElement buttonAdd;

    @FindBy(xpath = "//input[@placeholder='Наименование']")
    private WebElement nameProd;

    @FindBy(xpath = "//select[@id='type']/option[@value='VEGETABLE']")
    private WebElement typeVegetable;

    @FindBy(xpath = "//select[@id='type']/option[@value='FRUIT']")
    private WebElement typeFruit;

    @FindBy(xpath = "//*[@class='form-check-input']")
    private WebElement checkBox;

    @FindBy(xpath = "//button[text()='Сохранить']")
    private WebElement buttonSave;

    @FindBy(xpath = "//a[contains(text(), 'Песочница')]")
    private WebElement buttonNav;

    @FindBy(xpath = "//a[text()='Сброс данных']")
    private WebElement buttonReset;

    public ProdPage(WebDriver driver) {
        super(driver);
    }

    public void clickButtonAdd() {
        click(buttonAdd);
    }

    public boolean isButtonAddVisible() {
        return isVisible(buttonAdd);
    }

    public void clickNameProd() {
        click(nameProd);
    }

    public String enterAndVerifyTextInNameProd(String text) {
        WebElement nameProd = wait.until(ExpectedConditions.elementToBeClickable(By.id("name")));

        nameProd.click();
        nameProd.clear();
        nameProd.sendKeys(text);
        return nameProd.getAttribute("value");
    }


    public void selectType(String type) {
        WebElement typeElement = type.equalsIgnoreCase("vegetable") ? typeVegetable : typeFruit;
        click(typeElement);
    }

    public boolean isTypeVisible(String type) {
        WebElement typeElement = type.equalsIgnoreCase("vegetable") ? typeVegetable : typeFruit;
        return isVisible(typeElement);
    }

    public void clickCheckBox() {
        click(checkBox);
    }

    public boolean isCheckBoxVisible() {
        return isVisible(checkBox);
    }

    public void clickButtonSave() {
        click(buttonSave);
    }

    public boolean isButtonSaveVisible() {
        return isVisible(buttonSave);
    }

    public void clickButtonNav() {
        click(buttonNav);
    }

    public boolean isButtonNavVisible() {
        return isVisible(buttonNav);
    }

    public void clickButtonReset() {
        click(buttonReset);
    }

    public boolean isButtonResetVisible() {
        return isVisible(buttonReset);
    }

    public boolean isProductDisplayedOnPage(String productName) {
        try {
            WebElement productElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(., '" + productName + "')]")));
            return productElement != null && productElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
