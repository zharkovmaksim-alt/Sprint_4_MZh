package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPageSecond {

    private final WebDriver driver;

    // Локаторы (вынесены в поля класса)
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.className("Dropdown-control");
    private final By colorCheckboxBlack = By.xpath(".//label[@for='black']");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath(".//button[contains(@class, 'Button_Button__ra12g') and text()='Заказать']");

    public OrderPageSecond(WebDriver driver) {
        this.driver = driver;
    }

    public void fillOrderFormSecond(String date, String comment) {
        // Заполнение даты
        WebElement dateElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(dateField));
        dateElement.sendKeys(date);

        // Выбор срока аренды
        driver.findElement(rentalPeriodField).click();
        WebElement rentOption = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[text()='сутки']")));
        rentOption.click();

        // Выбор цвета (чёрный жемчуг)
        driver.findElement(colorCheckboxBlack).click();

        // Заполнение комментария
        driver.findElement(commentField).sendKeys(comment);

        // Нажатие кнопки "Заказать"
        WebElement orderButtonElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(orderButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", orderButtonElement);
    }
}