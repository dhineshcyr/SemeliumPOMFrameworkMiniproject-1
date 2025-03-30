package test;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.ProductPage;

public class ProductTests extends BaseTest {

    @Test
    public void testProductDetailsDisplay() {
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = homePage.selectProduct("Samsung Galaxy S23");
        
        Assert.assertEquals(productPage.getProductPrice(), 799.99, 0.01, "Price mismatch");
        Assert.assertTrue(productPage.getProductDescription().contains("Samsung"), "Description validation failed");
    }

    @Test
    public void testAddToCartFunctionality() {
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = homePage.selectProduct("Nexus 6");
        productPage.addToCart();
        
        Assert.assertEquals(homePage.getCartItemCount(), 1, "Cart count not updated");
    }
}