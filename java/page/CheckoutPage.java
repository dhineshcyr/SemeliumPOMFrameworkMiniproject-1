package page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "name")
    private WebElement nameField;
    
    @FindBy(id = "country")
    private WebElement countryField;
    
    @FindBy(id = "city")
    private WebElement cityField;
    
    @FindBy(id = "card")
    private WebElement cardField;
    
    @FindBy(id = "month")
    private WebElement monthField;
    
    @FindBy(id = "year")
    private WebElement yearField;
    
    @FindBy(xpath = "//button[contains(text(),'Purchase')]")
    private WebElement purchaseButton;
    
    @FindBy(css = "div.sweet-alert h2")
    private WebElement confirmationMessage;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void enterDetails(String name, String country, String city, 
                            String card, String month, String year) {
        nameField.sendKeys(name);
        countryField.sendKeys(country);
        cityField.sendKeys(city);
        cardField.sendKeys(card);
        monthField.sendKeys(month);
        yearField.sendKeys(year);
    }

    public void completePurchase() {
        wait.until(ExpectedConditions.elementToBeClickable(purchaseButton)).click();
    }

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOf(confirmationMessage)).getText();
    }

    public String getOrderDetails() {
        return driver.findElement(By.cssSelector("div.sweet-alert p")).getText();
    }}