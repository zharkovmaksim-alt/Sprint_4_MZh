package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    private final WebDriver driver;

    // Локаторы (вынесены в поля класса)
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillOrderForm(String name, String surname, String address, String metro, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroField).sendKeys(metro);
        driver.findElement(phoneField).sendKeys(phone);
    }

    public OrderPageSecond clickNextAndGoToSecondPage() {
        WebElement nextButtonElement = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(nextButton));

        // Первый клик (может быть баг в Chrome)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButtonElement);

        // Ожидание для обхода бага
        new WebDriverWait(driver, Duration.ofSeconds(1));

        // Второй клик
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButtonElement);

        // Ожидание появления заголовка второй страницы
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElementLocated(
                        By.xpath(".//div[contains(@class, 'Order_Header__BZXOb')]"),
                        "Про аренду"
                ));

        return new OrderPageSecond(driver);
    }
}