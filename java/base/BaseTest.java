package base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import utils.Constants;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {
	 protected WebDriver driver;
	    
	    @BeforeMethod
	    public void setUp() {
	    	 WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get(Constants.URL);
	    }
	    
	    @AfterMethod
	    public void tearDown(ITestResult result) {
	        if (result.getStatus() == ITestResult.FAILURE) {
	            captureScreenshot(result.getName());
	        }
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	    
	    private void captureScreenshot(String testName) {
	        try {
	            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	            FileUtils.copyFile(src, new File("screenshots/" + testName + "_" + System.currentTimeMillis() + ".png"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

}
