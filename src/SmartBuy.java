import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SmartBuy {
	
	public WebDriver driver;
	public int numberOfTry=2;
	SoftAssert softAssertProcess= new SoftAssert();
	
	@BeforeTest()
	public void smart_Buy() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.get("https://smartbuy-me.com/smartbuystore/en/");
	driver.manage().window().maximize();
	}
	@Test()
	public void Test_Adding_item_Samsung_50_inch() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		for(int i =0; i<numberOfTry;i++) {
		driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[2]/div/div[3]/div[1]/div/div/form[1]/div[1]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/a[2]")).click();
		}
	}
	
	@Test()
	public void check_the_correct_price() {
		String initialPrice = driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div/p")).getText();
		String[] seconderyPrice = initialPrice.split("JOD");
	seconderyPrice[0].trim();
	double finalPrice= Double.parseDouble(seconderyPrice[0]);
		String actualTitle= driver.getTitle();
		softAssertProcess.assertEquals(actualTitle, "ahmad");
		softAssertProcess.assertEquals(finalPrice, 3547);
		softAssertProcess.assertAll();
		
	}
	
}
