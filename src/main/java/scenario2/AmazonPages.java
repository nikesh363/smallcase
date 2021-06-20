package scenario2;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AmazonPages {

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBox;
    @FindBy(css = " div h2 a span")
    List<WebElement> searchResult;

    @FindBy(id = "priceblock_ourprice")
    WebElement amazonPrice;

    private WebDriver driver;
    private WebDriverWait wait;

    public AmazonPages(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver,60);
    }

    public void enterTextInSearchBox(String item){
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.sendKeys(item);
        searchBox.sendKeys(Keys.ENTER);
    }

    public List<WebElement> getAllItems(){
        wait.until(ExpectedConditions.elementToBeClickable(searchResult.get(0)));
        return searchResult;
    }
    public double getAmazonPrice(){
        wait.until(ExpectedConditions.visibilityOf(amazonPrice));
        double price = Double.valueOf(amazonPrice.getText().replaceAll("[^\\d.]", ""));
        return price;
    }
}
