package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Element Locators
    @FindBy(css = "div.product-content h2")
    private WebElement productName;
    
    @FindBy(css = "div.product-content h3")
    private WebElement productPrice;
    
    @FindBy(xpath = "//div[@id='more-information']/p")
    private WebElement productDescription;
    
    @FindBy(xpath = "//a[contains(text(),'Add to cart')]")
    private WebElement addToCartButton;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOf(productName)).getText();
    }

    public double getProductPrice() {
        String priceText = productPrice.getText().replace("$", "");
        return Double.parseDouble(priceText);
    }

    public String getProductDescription() {
        return productDescription.getText();
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }
}