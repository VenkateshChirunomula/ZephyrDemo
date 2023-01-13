package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Test_001 {

	static WebDriver driver;
	@SuppressWarnings("deprecation")
	@BeforeSuite
	public void init() {
		driver = new ChromeDriver();
		driver.get("https://palluser:pall1234@stage-shop.pall.com/us/en");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("onetrust-accept-btn-handler")).click();
	}
	@Test(priority = 1)
	public void validateLogin() {
		driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
		driver.findElement(By.id("ShopLoginForm_Login")).sendKeys("venkatesh_chirunomula@pall.com");
		driver.findElement(By.id("ShopLoginForm_Password")).sendKeys("pall1234");
		driver.findElement(By.name("login")).click();
		Assert.assertEquals(driver.getTitle(), "My Account Overview");
	}
	@Test(priority = 3)
	public void validateLogout() {
		driver.findElement(By.xpath("//ul[@id='AccountNav']//a[contains(text(),'Logout')]")).click();
		Assert.assertEquals(driver.getTitle(), "Pall Shop");
	}
	@Test(priority = 2)
	public void validateAccountUsername() {
		String username = driver.findElement(By.xpath("//h4")).getText();
		Assert.assertTrue(username.contains("tester"));

	}
	@AfterSuite
	public void tearDown() {
		driver.quit();	
	}
}
