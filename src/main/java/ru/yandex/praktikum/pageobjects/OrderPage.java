package ru.yandex.praktikum.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    //Локаторы
    private final By FirstNameField = By.xpath(".//input[contains(@placeholder, 'Имя')]"); // Поле Имя
    private final By LastNameField = By.xpath(".//input[contains(@placeholder, 'Фамилия')]"); // Поле Фамилия
    private final By AddressField = By.xpath(".//input[contains(@placeholder, 'куда привезти заказ')]"); // Поле адрес доставки
    private final By MetroStationField = By.xpath(".//input[@placeholder='* Станция метро']"); // Поле станция метро
    private final By PhoneField = By.xpath(".//div[5]/input[contains(@class, 'Input_Input__1iN_Z')]"); // Поле телефон
    private final By NextButton = By.xpath(".//button[text()='Далее']"); // Кнопка далее
    private final By DateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']"); // Поле даты доставки
    private final By RentPeriodField = By.xpath(".//span[@class='Dropdown-arrow']"); // Поле срока аренды
    private final By DropDownList = By.xpath(".//div[contains(@class, 'Dropdown-menu')]"); // Список возможного выбора срока аренды
    private final By BlackColorField = By.id("black");  // Чекбокс черного цвета самоката
    private final By GreyColorField = By.id("grey"); // Чекбокс серого цвета самоката
    private final By CommentField = By.xpath(".//input[@placeholder='Комментарий для курьера']"); // Поле коментария
    private final By OrderButton = By.xpath(".//div[contains(@class, 'Order_Content')]//button[text()='Заказать']"); // Кнопка заказать для завершения оформления заказа
    private final By ConfirmOrderButton = By.xpath(".//div[contains(@class, 'Order_Content')]//button[text()='Да']"); // Кнопка подтвеждения заказа
    private final By OrderStatus = By.xpath(".//div[contains(@class, 'Order_Content')]//div[text()='Заказ оформлен']"); // Модальное окно с "Заказ оформлен"
    private final By CheckStatusButton = By.xpath(".//div[contains(@class, 'Order_Content')]//button[text()='Посмотреть статус']"); // Кнопка посмотреть статус


    private WebDriver driver;
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setFirstNameField(String firstname) {
        driver.findElement(FirstNameField).sendKeys(firstname);
    } // Вводим имя

    public void setLastNameField(String lastname) {
        driver.findElement(LastNameField).sendKeys(lastname);
    } // Вводим фамилию

    public void setAddressField(String address) {
        driver.findElement(AddressField).sendKeys(address);
    } // Вводим адрес

    public void setMetroStationField(String clientMetro) {
        driver.findElement(MetroStationField).sendKeys(clientMetro);
    } // Вводим метро

    public void selectMetroStationField(String clientMetro) {
        driver.findElement(
                By.xpath(".//div[contains(@class, 'Order_Content')]//div[contains(@class, 'Order_Text') and text()='" + clientMetro + "']")
        ).click();
    } // Выбираем метро

    public void setPhoneField(String number) {
        driver.findElement(PhoneField).sendKeys(number);
    } // Вводим телефон

    public void clickNextButton() {
        driver.findElement(NextButton).click();
    } // Переходим далее

    public void setDateField(String date) {
        driver.findElement(DateField).sendKeys(date);
    } // Вводим дату доставки

    public void setRentPeriodField() {
        driver.findElement(RentPeriodField).click();
    } // Вводим период аренды

    public void selectRentPeriodField(String option) {
        driver.findElement(DropDownList).findElement(By.xpath(".//div[text()='" + option + "']")).click();
    } // выбираем период аренды

    public void setColor(String color) {
        if (color.equals("black")) {
            driver.findElement(BlackColorField).click();
        } else {
            driver.findElement(GreyColorField).click();
        }
    } // Выбираем цвет

    public void setCommentField(String comment) {
        driver.findElement(CommentField).sendKeys(comment);
    } // Вводим коментарий

    public void clickOrderButton() {
        driver.findElement(OrderButton).click();
    } // Кликаем заказать

    public void clickConfirmOrderButton() {
        driver.findElement(ConfirmOrderButton).click();
    } // Подтверждение заказа (Баг в Chrome)

    public boolean isStatusButtonDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(driver.findElement(CheckStatusButton)));
        return driver.findElement(CheckStatusButton).isDisplayed();
    } // Проверка на отображение кнопки статуса

    public boolean isOrderStatusDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(driver.findElement(OrderStatus)));
        return driver.findElement(OrderStatus).isDisplayed();
    } // Проверка на отображение статуса заказа

    public void setPersonalBlock(String name, String lastName, String address, String metroStation, String phone) {
        setFirstNameField(name);
        setLastNameField(lastName);
        setAddressField(address);
        setMetroStationField(metroStation);
        selectMetroStationField(metroStation);
        setPhoneField(phone);
    } // Метод заполнения полей перс данных

    public void setAboutRentBlock(String date, String rentPeriod, String color, String comment) {
        setDateField(date);
        setRentPeriodField();
        selectRentPeriodField(rentPeriod);
        setColor(color);
        setCommentField(comment);
    } // Метод заполнения данных об аренде
}
