package scenario1;

import org.testng.Assert;
import org.testng.annotations.Test;
import util.Library;
import util.WindowHandle;

import static configs.TestData.*;

public class FlipkartScenario extends Library {
    @Test
    public void testGetPrice(){
        enterURL(getFlipkartHomepageUrl());
        FlipkartPages homePage = new FlipkartPages(driver);
        try{
            homePage.closeLoginPopUp();
            homePage.enterSearchText(itemToSearch);
            homePage.clickFirstItem();
            WindowHandle.switchWindow(driver);
            double singleItemPrice = homePage.getProductPrice();
            System.out.println("###########################################");
            System.out.println("Price of One product is: " + singleItemPrice);
            System.out.println("###########################################");

            homePage.clickAddToCart();
            homePage.clickIncreaseQuantityButton();
            double priceAfterIncreasingQuantity = homePage.priceOnCartPage();
            System.out.println("###########################################");
            System.out.println("Price of product after increasing quantity is: " + priceAfterIncreasingQuantity);
            System.out.println("###########################################");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
