package ru.yandex.praktikum.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    //Локаторы
    private final String ManePageUrl = "https://qa-scooter.praktikum-services.ru/"; // URL главной страницы
    public static By ButtonTopOrder = By.xpath(".//button[@class='Button_Button__ra12g']"); // кнопка Заказать сверху
    public static By ButtonMiddleOrder = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM')]"); // кнопка Заказать середина
    private final By QuestionsSector = By.xpath(".//div[text()='Вопросы о важном']"); // Блок вопросов
    private final By cookieButton = By.id("rcc-confirm-button"); // кнопка с куками

    //Блок Вопросы-Ответы
    public static By question1 = By.id("accordion__heading-0"); // вопрос 1
    public static By answer1 = By.id("accordion__panel-0"); // ответ 1
    public static String answer1Text = "Сутки — 400 рублей. Оплата курьеру — наличными или картой."; // текст ответана 1
    public static By question2 = By.id("accordion__heading-1"); // вопрос 2
    public static By answer2 = By.id("accordion__panel-1"); // ответ 2
    public static String answer2Text = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."; // текст ответана 2
    public static By question3 = By.id("accordion__heading-2"); // вопрос 3
    public static By answer3 = By.id("accordion__panel-2"); // ответ 3
    public static String answer3Text = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."; // текст ответана 3
    public static By question4 = By.id("accordion__heading-3"); // вопрос 4
    public static By answer4 = By.id("accordion__panel-3"); // ответ 4
    public static String answer4Text = "Только начиная с завтрашнего дня. Но скоро станем расторопнее."; // текст ответана 4
    public static By question5 = By.id("accordion__heading-4"); // вопрос 5
    public static By answer5 = By.id("accordion__panel-4"); // ответ 5
    public static String answer5Text = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."; // текст ответана 5
    public static By question6 = By.id("accordion__heading-5"); // вопрос 6
    public static By answer6 = By.id("accordion__panel-5"); // ответ 6
    public static String answer6Text = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."; // текст ответана 6
    public static By question7 = By.id("accordion__heading-6"); // вопрос 7
    public static By answer7 = By.id("accordion__panel-6"); // ответ 7
    public static String answer7Text = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."; // текст ответана 7
    public static By question8 = By.id("accordion__heading-7"); // вопрос 8
    public static By answer8 = By.id("accordion__panel-7"); // ответ 8
    public static String answer8Text = "Да, обязательно. Всем самокатов! И Москве, и Московской области."; // текст ответана 8

    private WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void MainPageOpen() {
        driver.get(ManePageUrl);
    } // метод открыть страницу

    public void clickCookie() {
        driver.findElement(cookieButton).click();
    } // принимаем куки

    public void clickOrderButton(By orderButton) {
        driver.findElement(orderButton).click();
    } //нажать на кнопу Заказать

    public void ScrollToQuestions() {
        WebElement element = driver.findElement(QuestionsSector);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    } // проскроллить до блока вопросов

    public void clickQuestion(By question) {
        driver.findElement(question).click();
    } // нажать на вопрос

    public String getAnswerText(By answer){
        return driver.findElement(answer).getText();
    } // получить текст ответа на вопрос

}
