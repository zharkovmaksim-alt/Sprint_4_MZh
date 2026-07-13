package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    private By topOrderButton = By.xpath(".//button[contains(@class, 'Button_Button__ra12g') and text()='Заказать']");
    private By bottomOrderButton = By.xpath(".//button[contains(@class, 'Button_Button__ra12g') and text()='Заказать']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptCookies() {
        try {
            WebElement cookieConsent = driver.findElement(By.className("App_CookieConsent__1yUIN"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", cookieConsent);
            Thread.sleep(500);
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