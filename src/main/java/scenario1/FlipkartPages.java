package scenario1;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FlipkartPages {

    @FindBy(css = "input[name='q']")
    WebElement searchBox;

    @FindBy(xpath = "//button[text()='✕']")
    WebElement loginPopUpClose;

    @FindBy(xpath = "//div//*[@data-id]//a")
    List<WebElement> firstItemOnSearchResultPage;

    @FindBy(xpath = "//span[starts-with(@id,'productRating')]/ancestor::div[3]/preceding-sibling::div//span")
    WebElement firstItemName;

    @FindBy(xpath = "//button[text()='ADD TO CART']")
    WebElement addToCartButton;

    @FindBy(xpath = "//span[starts-with(@id,'productRating')]/ancestor::div[3]/following-sibling::div[2]//div/div/div[1]")
    WebElement productPrice;

    @FindBy(xpath = "//button[text()='+']")
    WebElement increaseQuantityButton;

    @FindBy(xpath = "//div[contains(text(),'Total Amount')]/parent::div/following-sibling::span//span")
    WebElement increasedQuantityPrice;

    @FindBy(xpath = "//span[text()='Filters']")
    WebElement filter;

    private WebDriver driver;
    private WebDriverWait wait;

    public FlipkartPages(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver,60);
    }

    public void enterSearchText(String searchText){
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.sendKeys(searchText);
        searchBox.sendKeys(Keys.ENTER);
    }
    public void closeLoginPopUp(){
        wait.until(ExpectedConditions.visibilityOf(loginPopUpClose));
        loginPopUpClose.click();
    }

    public void clickFirstItem(){
        wait.until(ExpectedConditions.visibilityOf(filter));
        firstItemOnSearchResultPage.get(0).click();
    }

    public void clickAddToCart(){
        wait.until(ExpectedConditions.visibilityOf(firstItemName));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
    }
    public double getProductPrice(){
        wait.until(ExpectedConditions.visibilityOf(productPrice));
        return Double.valueOf(productPrice.getText().replaceAll("[^\\d]", ""));
    }
    public void clickIncreaseQuantityButton(){
        wait.until(ExpectedConditions.visibilityOf(increaseQuantityButton));
        increaseQuantityButton.click();
    }
    public double priceOnCartPage(){
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOf(increasedQuantityPrice));
        return Integer.valueOf(increasedQuantityPrice.getText().replaceAll("[^\\d]", ""));
    }

    public String getFirstItemName(){
        wait.until(ExpectedConditions.visibilityOf(firstItemName));
        return firstItemName.getText();
    }

}
