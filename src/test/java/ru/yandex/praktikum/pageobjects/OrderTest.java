package ru.yandex.praktikum.pageobjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static ru.yandex.praktikum.pageobjects.MainPage.ButtonMiddleOrder;
import static ru.yandex.praktikum.pageobjects.MainPage.ButtonTopOrder;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;

    private final By orderButton;
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String rentPeriod;
    private final String color;
    private final String comment;

    public OrderTest(By orderButton, String firstname, String lastname, String address, String metroStation, String phoneNumber, String date, String rentPeriod, String color, String comment) {
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

    @Parameterized.Parameters
    public static Object [][] getCredentials(){
        return new Object[][] {
                {ButtonTopOrder, "Семен", "Васильев", "г. Москва, ул. Незабудкина, д. 77", "Сокольники", "89112223344", "01.05.2023", "трое суток", "black", "Хочу новый"},
                {ButtonMiddleOrder, "Анна", "Петрова", "г. Москва, ул. Забудкина, д. 55", "Динамо", "89114443322", "04.05.2023", "сутки", "grey", "Хочу красивый"}
        };
    }
    @Before
    public void Init(){
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        //driver = new FirefoxDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }
    @Test
    public void OrderMainTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.MainPageOpen();
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
    public void EndTest() {
        driver.quit();
    }

}