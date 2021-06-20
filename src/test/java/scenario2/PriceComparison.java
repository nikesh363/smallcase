package scenario2;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import scenario1.FlipkartPages;
import util.Library;
import util.WindowHandle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static configs.TestData.*;
import static util.WindowHandle.waitForLoad;

public class PriceComparison extends Library {

    @Test
    public void comparePrice(){
        enterURL(getFlipkartHomepageUrl());
        FlipkartPages flipkartPages = new FlipkartPages(driver);
        AmazonPages amazonPages = new AmazonPages(driver);
        try {
            flipkartPages.closeLoginPopUp();
            flipkartPages.enterSearchText(itemToSearch);
            waitForLoad(driver);
            flipkartPages.clickFirstItem();
            WindowHandle.switchWindow(driver);

            String firstItemName = flipkartPages.getFirstItemName();
            double flipkartPrice = flipkartPages.getProductPrice();

            String[] itemNameArray = firstItemName.replaceAll("[^A-Z,a-z,0-9,\" \"]","").toLowerCase().split(" ");
           // Doing this extra reverse step to avoid click on Sponsored product with same configuration.
            List<String> itemWordList = Arrays.asList(itemNameArray);
            Collections.reverse(itemWordList);
            String[] itemWordsArray = itemWordList.toArray(itemNameArray);

            enterURL(getAmazonHomePageUrl());
            amazonPages.enterTextInSearchBox(firstItemName);

            List<WebElement> allSearchResult = amazonPages.getAllItems();
            for (WebElement e : allSearchResult){
                String itemName = e.getText().replaceAll("[^A-Z,a-z,0-9,\" \"]","").toLowerCase();
                boolean found = false;
                for(String eachWord : itemWordsArray ){
                   found =  itemName.contains(eachWord)?true:false;
                }
                if(found == true ){
                    e.click();
                    WindowHandle.switchWindow(driver);
                }
                break;
            }

            double amazonPrice = amazonPages.getAmazonPrice();
            System.out.println("******************************");
            System.out.println("Price on flipkart is: " + flipkartPrice);
            System.out.println("Price on Amazon is: " + amazonPrice);
            System.out.println("******************************");

            if(flipkartPrice > amazonPrice){
                System.out.println("##################################");
                System.out.println("Price on Amazon is cheaper, rest is your call.");
                System.out.println("Price difference is: "+ (flipkartPrice - amazonPrice));
                System.out.println("##################################");

            }
            else if(amazonPrice > flipkartPrice){
                System.out.println("##################################");
                System.out.println("Price on Flipkart is cheaper, rest is your call.");
                System.out.println("Price difference is: "+ (amazonPrice - flipkartPrice));
                System.out.println("##################################");
            }
            else{
                System.out.println("##################################");
                System.out.println("Price on both sites is same.");
                System.out.println("##################################");

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
