package test;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.CartPage;
import page.HomePage;
import page.ProductPage;

public class CartTests extends BaseTest {

    @Test
    public void testCartItemRemoval() {
        // Add item first
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = homePage.selectProduct("MacBook Pro");
        productPage.addToCart();
        
        CartPage cartPage = homePage.navigateToCart();
        int initialCount = cartPage.getCartItemCount();
        cartPage.removeItem(0);
        
        Assert.assertEquals(cartPage.getCartItemCount(), initialCount - 1, "Item not removed");
    }

    @Test
    public void testTotalPriceCalculation() {
        HomePage homePage = new HomePage(driver);
        
        // Add first item
        ProductPage product1 = homePage.selectProduct("Sony vaio i5");
        product1.addToCart();
        
        // Add second item
        ProductPage product2 = homePage.selectProduct("Dell i7 8gb");
        product2.addToCart();
        
        CartPage cartPage = homePage.navigateToCart();
        double expectedTotal = product1.getProductPrice() + product2.getProductPrice();
        
        Assert.assertEquals(cartPage.getTotalPrice(), expectedTotal, 0.01, "Total price mismatch");
    }
}