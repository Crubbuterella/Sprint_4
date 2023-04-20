package ru.yandex.praktikum.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private final WebDriver driver;
    // Локаторы
    private final By ButtonsOrder = By.xpath(".//button[text()='Заказать']"); // кнопки Заказать сверху
    private final By QuestionsSector = By.xpath(".//div[text()='Вопросы о важном']"); // Блок вопросов
    private final By cookieButton = By.id("rcc-confirm-button"); // кнопка с куками
    private final By questions = By.className("accordion__button"); // вопросы
    private final By answers = By.className("accordion__panel"); // ответы

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCookie() {
        driver.findElement(cookieButton).click();
    } // принимаем куки

    public void clickOrderButton(int index) {
        driver.findElements(ButtonsOrder).get(index).click();
    } // нажать на кнопу Заказать (первую или вторую)

    public void scrollToQuestions() {
        WebElement element = driver.findElement(QuestionsSector);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    } // проскролить до гармошки вопросов

    public String getAnswerText(int index) {
        driver.findElements(questions).get(index).click();
        return driver.findElements(answers).get(index).getText();
    } // раскрыть вопрос и получить ответ

}
