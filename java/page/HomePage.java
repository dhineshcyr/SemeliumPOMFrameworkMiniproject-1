package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {

	
	 private WebDriver driver;
	    private WebDriverWait wait;
	    
	    @FindBy(id = "signin2")
	    private WebElement signUpButton;
	    
	    @FindBy(id = "login2")
	    private WebElement loginButton;
	    
	    @FindBy(id = "nameofuser")
	    private WebElement welcomeMessage;
	    
	    public HomePage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        PageFactory.initElements(driver, this);
	    }
	    
	    public void clickSignUp() {
	        wait.until(ExpectedConditions.elementToBeClickable(signUpButton)).click();
	    }
	    
	    public void clickLogin() {
	        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
	    }
	    
	    public boolean isWelcomeDisplayed() {
	        return wait.until(ExpectedConditions.visibilityOf(welcomeMessage)).isDisplayed();
	    }
}
