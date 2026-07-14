package tests;

import pages.HomePageFaq;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class FaqTest {

    private WebDriver driver;
    private final int questionIndex;
    private final String expectedAnswer;

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    @Parameterized.Parameters(name = "Вопрос {0}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет. Но если что — перезвоните."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на 8 суток — даже если будете кататься без передышки и без подзарядки. А вот зарядку не привозим."},
                {6, "Да, если самокат ещё не привезли. Отменить можно в статусе «В поиске»."},
                {7, "Да, привозим. Только в пределах МКАД."}
        });
    }

    public FaqTest(int questionIndex, String expectedAnswer) {
        this.questionIndex = questionIndex;
        this.expectedAnswer = expectedAnswer;
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(BASE_URL);
    }

    @Test
    public void testFaqAnswerIsDisplayed() {
        HomePageFaq homePageFaq = new HomePageFaq(driver);
        homePageFaq.clickQuestion(questionIndex);
        assertTrue("Ответ на вопрос " + questionIndex + " не отображается",
                homePageFaq.isAnswerDisplayed(questionIndex));
    }

    @Test
    public void testFaqAnswerTextIsCorrect() {
        HomePageFaq homePageFaq = new HomePageFaq(driver);
        homePageFaq.clickQuestion(questionIndex);
        String actualAnswer = homePageFaq.getAnswerText(questionIndex);
        assertEquals("Ответ на вопрос " + questionIndex + " не совпадает",
                expectedAnswer, actualAnswer);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}