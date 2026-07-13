package tests;

import pages.HomePage;
import pages.OrderPage;
import pages.OrderPageSecond;
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

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String comment;

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Иван", "Петров", "ул. Ленина 1", "Парк культуры", "+79991112233", "01.01.2026", "Позвоните за 10 минут"},
                {"Мария", "Иванова", "ул. Пушкина 2", "Охотный ряд", "+78889994455", "02.01.2026", "Без комментариев"}
        });
    }

    public OrderTest(String name, String surname, String address, String metro, String phone, String date, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.comment = comment;
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(BASE_URL);
    }

    @Test
    public void testOrderFlow() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        homePage.clickTopOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderForm(name, surname, address, metro, phone);

        OrderPageSecond orderPageSecond = orderPage.clickNextAndGoToSecondPage();
        orderPageSecond.fillOrderFormSecond(date, comment);

        // Ассерт: проверяем, что после оформления заказа появилось окно с сообщением
        boolean isOrderConfirmed = driver.getPageSource().contains("Заказ оформлен");
        assertTrue("Заказ не был оформлен!", isOrderConfirmed);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}