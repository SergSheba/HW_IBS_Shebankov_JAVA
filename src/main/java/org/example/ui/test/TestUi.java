
package org.example.ui.test;

import org.example.ui.pages.HomePage;
import org.example.ui.pages.ProdPage;
import org.junit.Test;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

public class TestUi extends BaseTest {

    @Test
    @DisplayName("Добавление в список товаров 'Банан' с типом 'Фрукт', без чек-бокса 'экзотический'")
    @Tag("positive")
    public void addBananaTest() {
        HomePage homePage = new HomePage(driver);
        ProdPage prodPage = new ProdPage(driver);

        homePage.clickDropDown();
        Assert.assertTrue("Выпадающее меню должно быть видимым после нажатия на NavBar", homePage.isDropDownVisible());

        Assert.assertTrue("Раздел с продуктами должен быть видимым", homePage.isFoodVisible());
        homePage.clickFood();


        Assert.assertTrue("Кнопка \"Добавить\" должна быть видимой", prodPage.isButtonAddVisible());
        prodPage.clickButtonAdd();

        String enteredText = prodPage.enterAndVerifyTextInNameProd("Банан");
        Assert.assertEquals("Введенный текст должен соответствовать", "Банан", enteredText);

        prodPage.selectType("FRUIT");
        prodPage.clickButtonSave();

        boolean isProductVisible = prodPage.isProductDisplayedOnPage("Банан");
        Assert.assertTrue("Добавленный продукт \"Банан\" должен быть видим на странице", isProductVisible);
    }


    @Test
    @DisplayName("Добавление в список товаров 'Ананас' с типом 'Фрукт', с чек-боксом 'экзотический'")
    @Tag("positive")
    public void addPineappelTest() {
        HomePage homePage = new HomePage(driver);
        ProdPage prodPage = new ProdPage(driver);

        homePage.clickDropDown();
        Assert.assertTrue("Выпадающее меню должно быть видимым после нажатия на NavBar", homePage.isDropDownVisible());

        Assert.assertTrue("Раздел с продуктами должен быть видимым", homePage.isFoodVisible());
        homePage.clickFood();


        Assert.assertTrue("Кнопка \"Добавить\" должна быть видимой", prodPage.isButtonAddVisible());
        prodPage.clickButtonAdd();

        String enteredText = prodPage.enterAndVerifyTextInNameProd("Ананас");
        Assert.assertEquals("Введенный текст должен соответствовать", "Ананас", enteredText);

        prodPage.clickCheckBox();
        prodPage.selectType("FRUIT");
        prodPage.clickButtonSave();

        boolean isProductVisible = prodPage.isProductDisplayedOnPage("Ананас");
        Assert.assertTrue("Добавленный продукт \"Ананас\" должен быть видим на странице", isProductVisible);
    }

    @Test
    @DisplayName("Добавление в список товаров 'Картошка' с типом 'Овощь', без чек-бокса 'экзотический'")
    @Tag("positive")
    public void addPotatoTest() {
        HomePage homePage = new HomePage(driver);
        ProdPage prodPage = new ProdPage(driver);

        homePage.clickDropDown();
        Assert.assertTrue("Выпадающее меню должно быть видимым после нажатия на NavBar", homePage.isDropDownVisible());

        Assert.assertTrue("Раздел с продуктами должен быть видимым", homePage.isFoodVisible());
        homePage.clickFood();


        Assert.assertTrue("Кнопка \"Добавить\" должна быть видимой", prodPage.isButtonAddVisible());
        prodPage.clickButtonAdd();

        String enteredText = prodPage.enterAndVerifyTextInNameProd("Картошка");
        Assert.assertEquals("Введенный текст должен соответствовать", "Картошка", enteredText);

        prodPage.selectType("vegetable");
        prodPage.clickButtonSave();

        boolean isProductVisible = prodPage.isProductDisplayedOnPage("Картошка");
        Assert.assertTrue("Добавленный продукт \"Картошка\" должен быть видим на странице", isProductVisible);
    }


    @Test
    @DisplayName("Добавление в список товаров 'Пепино' с типом 'Овощь', с чек-боксом 'экзотический' и последующим удалением")
    @Tag("positive")
    public void addPepinoTest() {
        HomePage homePage = new HomePage(driver);
        ProdPage prodPage = new ProdPage(driver);

        homePage.clickDropDown();
        Assert.assertTrue("Выпадающее меню должно быть видимым после нажатия на NavBar", homePage.isDropDownVisible());

        Assert.assertTrue("Раздел с продуктами должен быть видимым", homePage.isFoodVisible());
        homePage.clickFood();


        Assert.assertTrue("Кнопка \"Добавить\" должна быть видимой", prodPage.isButtonAddVisible());
        prodPage.clickButtonAdd();

        String enteredText = prodPage.enterAndVerifyTextInNameProd("Пепино");
        Assert.assertEquals("Введенный текст должен соответствовать", "Пепино", enteredText);

        prodPage.clickCheckBox();
        prodPage.selectType("vegetable");
        prodPage.clickButtonSave();

        boolean isProductVisible = prodPage.isProductDisplayedOnPage("Пепино");
        Assert.assertTrue("Добавленный продукт \"Пепино\" должен быть видим на странице", isProductVisible);

        prodPage.clickButtonNav();
        prodPage.clickButtonReset();
    }

}
