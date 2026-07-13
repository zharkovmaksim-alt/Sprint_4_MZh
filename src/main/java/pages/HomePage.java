package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;

    // Локаторы
    private final By topOrderButton = By.xpath(".//button[contains(@class, 'Button_Button__ra12g') and text()='Заказать']");
    private final By bottomOrderButton = By.xpath(".//button[contains(@class, 'Button_Button__ra12g') and text()='Заказать']");
    private final By cookieConsent = By.className("App_CookieConsent__1yUIN");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptCookies() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(cookieConsent));

            WebElement consentElement = driver.findElement(cookieConsent);
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", consentElement);

            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOfElementLocated(cookieConsent));

            System.out.println("Куки-баннер скрыт через JavaScript");
        } catch (Exception e) {
            System.out.println("Куки-баннер не найден, продолжаем");
        }
    }

    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    public void clickBottomOrderButton() {
        driver.findElement(bottomOrderButton).click();
    }
}