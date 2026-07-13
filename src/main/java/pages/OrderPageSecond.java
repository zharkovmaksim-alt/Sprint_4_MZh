package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPageSecond {
    private WebDriver driver;

    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By rentalPeriodField = By.className("Dropdown-control");
    private By colorCheckboxBlack = By.xpath(".//label[@for='black']");
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private By orderButton = By.xpath(".//button[contains(@class, 'Button_Button__ra12g') and text()='Заказать']");

    public OrderPageSecond(WebDriver driver) {
        this.driver = driver;
    }

    public void fillOrderFormSecond(String date, String comment) {
        WebElement dateElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(dateField));
        dateElement.sendKeys(date);

        driver.findElement(rentalPeriodField).click();
        WebElement rentOption = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[text()='сутки']")));
        rentOption.click();

        driver.findElement(colorCheckboxBlack).click();

        driver.findElement(commentField).sendKeys(comment);

        WebElement orderButtonElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(orderButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", orderButtonElement);
    }
}