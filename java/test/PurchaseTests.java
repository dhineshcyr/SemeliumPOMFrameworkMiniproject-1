package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

public class PurchaseTests extends BaseTest {

    @Test
    public void testCompletePurchaseFlow() {
        HomePage homePage = new HomePage(driver);
        
        // Add product
        ProductPage productPage = homePage.selectProduct("iPhone 13");
        productPage.addToCart();
        
        // Navigate to cart
        CartPage cartPage = homePage.navigateToCart();
        CheckoutPage checkoutPage = cartPage.clickPlaceOrder();
        
        // Fill checkout details
        checkoutPage.enterDetails(
            "John Doe",
            "United States",
            "New York",
            "4111111111111111",
            "12",
            "2025"
        );
        checkoutPage.completePurchase();
        
        // Verify confirmation
        Assert.assertTrue(checkoutPage.getConfirmationMessage().contains("Thank you"), "Purchase confirmation missing");
        Assert.assertTrue(checkoutPage.getOrderDetails().contains("iPhone 13"), "Order details incorrect");
    }

    @Test
    public void testEmptyCartPurchase() {
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = homePage.navigateToCart();
        cartPage.clickPlaceOrder();
        
        Assert.assertTrue(cartPage.isEmptyCartMessageDisplayed(), "Empty cart handling failed");
    }
}
