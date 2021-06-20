package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Library {
    public WebDriver driver;

    @BeforeMethod
    public WebDriver initializeBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        return driver;
    }

    public void enterURL(String url){
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDownDriver(){
        driver.close();
        driver.quit();
    }

}
