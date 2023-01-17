import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HTML {
	public WebDriver driver;
	
	@BeforeTest
	public void Login() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();

		

	}
	@Test()
	public void Sort_items_low_to_high() throws InterruptedException
	{
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[3]")).click();
		List <WebElement> priceList = driver.findElements(By.className("inventory_item_price"));
		List <Double> newListPrice = new ArrayList<>();
		for(int i=0;i<priceList.size();i++) {
//			System.out.println(priceList.get(i).getText().trim().replace("$", " ").trim());
			Double a = Double.parseDouble(priceList.get(i).getText().trim().replace("$", " ").trim());
//			double d=Double.parseDouble(a);  //or you can do as this instead the previous line
			newListPrice.add(a);
		}
//		System.out.println(newListPrice.size());
		for(int k=0;k<newListPrice.size();k++) {
			boolean checkProcess = newListPrice.get(0) < newListPrice.get(newListPrice.size()-1);
			Assert.assertEquals(checkProcess, true);
			
		}
		
	}
	@Test()
	public void sort_items_high_to_low() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[4]")).click();
		List <WebElement> priceList = driver.findElements(By.className("inventory_item_price"));
		List <Double> newListPrice = new ArrayList<>();
		for(int i=0;i<priceList.size();i++) {
			Double a = Double.parseDouble(priceList.get(i).getText().trim().replace("$", " ").trim());
			newListPrice.add(a);
		}
		for(int k=0;k<newListPrice.size();k++) {
			boolean checkProcess = newListPrice.get(0) > newListPrice.get(newListPrice.size()-1);
			Assert.assertEquals(checkProcess, true);
			
		}
	}
	
	@Test()
	public void check_add_to_cart_process() {
		
	}
	

}
