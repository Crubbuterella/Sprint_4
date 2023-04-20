package ru.yandex.praktikum.pageobjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;


@RunWith(Parameterized.class)
public class OrderTest {

    private final int orderButton;
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String rentPeriod;
    private final String color;
    private final String comment;
    private WebDriver driver;

    public OrderTest(int orderButton, String firstname, String lastname, String address, String metroStation, String phoneNumber, String date, String rentPeriod, String color, String comment) {
        this.orderButton = orderButton;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentPeriod = rentPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Заказ скутера. Получатель: {1} {2} {5}")
    public static Object[][] getCredentials() {
        return new Object[][]{
                {0, "Семен", "Васильев", "г. Москва, ул. Незабудкина, д. 77", "Сокольники", "89112223344", "01.05.2023", "трое суток", "black", "Хочу новый"},
                {1, "Анна", "Петрова", "г. Москва, ул. Забудкина, д. 55", "Динамо", "89114443322", "04.05.2023", "сутки", "grey", "Хочу красивый"}
        };
    }

    @Before
    public void driverInit() {
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        //driver = new FirefoxDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void orderMainTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookie();
        mainPage.clickOrderButton(orderButton);

        OrderPage orderPage = new OrderPage(driver);
        orderPage.setPersonalBlock(firstname, lastname, address, metroStation, phoneNumber);
        orderPage.clickNextButton();
        orderPage.setAboutRentBlock(date, rentPeriod, color, comment);
        orderPage.clickOrderButton();
        orderPage.clickConfirmOrderButton();
        Assert.assertTrue(orderPage.isOrderStatusDisplayed());
        Assert.assertTrue(orderPage.isStatusButtonDisplayed());
    }

    @After
    public void teardown() {
        driver.quit();
    }

}