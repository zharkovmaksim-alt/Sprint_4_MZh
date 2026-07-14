package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageFaq {
    private WebDriver driver;

    private final By[] questionLocators = new By[8];
    private final By[] answerLocators = new By[8];

    public HomePageFaq(WebDriver driver) {
        this.driver = driver;
        initLocators();
    }

    private void initLocators() {
        for (int i = 0; i < 8; i++) {
            questionLocators[i] = By.id("accordion__heading-" + i);
            answerLocators[i] = By.xpath("//div[@id='accordion__panel-" + i + "']/p");
        }
    }

    public void clickQuestion(int index) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(questionLocators[index]))
                .click();
    }

    public String getAnswerText(int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocators[index]));
        return driver.findElement(answerLocators[index]).getText();
    }

    public boolean isAnswerDisplayed(int index) {
        return driver.findElement(answerLocators[index]).isDisplayed();
    }
}