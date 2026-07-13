package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FaqTest {

    private WebDriver driver;
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(BASE_URL);
    }

    @Test
    public void testFaqAnswersAreDisplayed() {
        for (int i = 0; i < 8; i++) {
            By question = By.id("accordion__heading-" + i);
            By answer = By.xpath("//div[@id='accordion__panel-" + i + "']/p");
            driver.findElement(question).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(answer));
            String answerText = driver.findElement(answer).getText();
            assertTrue("Ответ на вопрос " + i + " пустой", answerText.length() > 0);
        }
    }

    @Test
    public void testFaqAnswerTextIsCorrect() {
        Object[][] faqData = {
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру наличными или картой."},
                {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"Можно ли заказать самокат прямо сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет. Но если что — перезвоните."},
                {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на 8 суток — даже если будете кататься без передышки и без подзарядки. А вот зарядку не привозим."},
                {"Можно ли отменить заказ?", "Да, если самокат ещё не привезли. Отменить можно в статусе «В поиске»."},
                {"Я живу за МКАДом, привезёте?", "Да, привозим. Только в пределах МКАД."}
        };

        for (Object[] data : faqData) {
            String questionText = (String) data[0];
            String expectedAnswer = (String) data[1];
            By questionLocator = By.xpath("//div[contains(@class, 'accordion__button') and text()='" + questionText + "']");
            driver.findElement(questionLocator).click();
            By answerLocator = By.xpath("//div[contains(@class, 'accordion__panel')]//p");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
            String actualAnswer = driver.findElement(answerLocator).getText();
            assertEquals("Ответ на вопрос: " + questionText, expectedAnswer, actualAnswer);
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}