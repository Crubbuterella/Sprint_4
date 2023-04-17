package ru.yandex.praktikum.pageobjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.hamcrest.core.Is.is;

@RunWith(Parameterized.class)
public class QuestionsTest {

    private WebDriver driver;
    private final By question;
    private final By answer;
    private final String answerText;

    //конструктор класса
    public QuestionsTest(By question, By answer, String answerText) {
        this.question = question;
        this.answer = answer;
        this.answerText = answerText;
    }

    //параметризация
    @Parameterized.Parameters
    public static Object[][] getCredentials(){
        return new Object[][] {
                {MainPage.question1, MainPage.answer1, MainPage.answer1Text},
                {MainPage.question2, MainPage.answer2, MainPage.answer2Text},
                {MainPage.question3, MainPage.answer3, MainPage.answer3Text},
                {MainPage.question4, MainPage.answer4, MainPage.answer4Text},
                {MainPage.question5, MainPage.answer5, MainPage.answer5Text},
                {MainPage.question6, MainPage.answer6, MainPage.answer6Text},
                {MainPage.question7, MainPage.answer7, MainPage.answer7Text},
                {MainPage.question8, MainPage.answer8, MainPage.answer8Text}
        };

    }

    @Before
    public void Init() {
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        //driver = new FirefoxDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Test
    public void shownAnswers() {
        MainPage mainPage = new MainPage(driver);
        mainPage.MainPageOpen();
        mainPage.clickCookie();
        mainPage.ScrollToQuestions();
        mainPage.clickQuestion(question);
        mainPage.getAnswerText(answer);
        MatcherAssert.assertThat(mainPage.getAnswerText(answer), is(answerText));
    }

    @After
    public void EndTest() {
        driver.quit();
    }

}
