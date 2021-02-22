package DemoWebShop;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Display_Notebook_by12 {

	public AndroidDriver driver;
	
	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability(MobileCapabilityType.DEVICE_NAME, "Smriti");
		capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capability.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capability);
		driver.get("http://demowebshop.tricentis.com/login");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void login() throws Exception {
		driver.findElement(By.xpath("//*[@id='Email']")).sendKeys("smritisukul123@gmail.com");
		driver.findElement(By.xpath("//*[@id='Password']")).sendKeys("smriti@123");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//*[@value='Log in']")).click();
		String expected = "smritisukul123@gmail.com";
		String actual = driver.findElement(By.xpath("//a[contains(text(),'smriti')]")).getText();
		Assert.assertEquals(actual, expected, "Passed");
	}	
		
	@Test(dependsOnMethods = "login")	
	public void notebook() throws Exception {
		driver.findElement(By.xpath("//span[@class='icon']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Computers')]//following-sibling::span")).click();
		driver.findElement(By.xpath("//li[@class='active']//ul//a[contains(text(),'Notebooks')]")).click();
	
		String title = driver.findElement(By.xpath("//div[@class='page-title']")).getText();
		System.out.println(title);
		Assert.assertEquals(title, "Notebooks","Passed");
	}
	
	@Test(dependsOnMethods = "notebook") 
	public void display12() throws Exception {
		Select display =new Select(driver.findElement(By.xpath("//select[@id='products-pagesize']")));
        display.selectByVisibleText("12");
        
        String itemname = driver.findElement(By.xpath("//h2[@class='product-title']")).getText();
        System.out.println(itemname);
        Assert.assertEquals(itemname, "14.1-inch Laptop","Passed");
	}

	@AfterClass
	public void afterClass() {
		 driver.quit();
	}

}
