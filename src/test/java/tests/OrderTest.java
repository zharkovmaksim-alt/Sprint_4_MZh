package tests;

import pages.HomePage;
import pages.OrderPage;
import pages.OrderPageSecond;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;

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

    public OrderTest(String name, String surname, String address, String metro, String phone, String date, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Иван", "Петров", "ул. Ленина 1", "Парк культуры", "+79991112233", "01.01.2026", "Позвоните за 10 минут"},
                {"Мария", "Иванова", "ул. Пушкина 2", "Охотный ряд", "+78889994455", "02.01.2026", "Без комментариев"}
        });
    }

    @Test
    public void testOrderFlow() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
        homePage.clickTopOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderForm(name, surname, address, metro, phone);

        OrderPageSecond orderPageSecond = orderPage.clickNextAndGoToSecondPage();
        orderPageSecond.fillOrderFormSecond(date, comment);

        System.out.println("Тест выполнен для: " + name + " " + surname);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}