package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "table.table tbody tr")
    private List<WebElement> cartItems;
    
    @FindBy(css = "td.text-right h3")
    private WebElement totalPrice;
    
    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    private WebElement placeOrderButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public void removeItem(int index) {
        WebElement deleteLink = cartItems.get(index).findElement(By.linkText("Delete"));
        wait.until(ExpectedConditions.elementToBeClickable(deleteLink)).click();
    }

    public double getTotalPrice() {
        return Double.parseDouble(totalPrice.getText().replace("$", ""));
    }

    public CheckoutPage clickPlaceOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
        return new CheckoutPage(driver);
    }
}
