package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_07_Web_browser_PII {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_URL() throws InterruptedException {
		driver.get("http://live.techpanda.org/");
		
		// click on my account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond (2);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		// click on create an account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond (2);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

		
	}

	@Test
	public void TC_02_Title() {
		driver.get("http://live.techpanda.org/");
		
		// click on my account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond (2);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		// click on create an account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond (2);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	@Test
	public void TC_03_Navigate() {
		driver.get("http://live.techpanda.org/");
		
		// click on my account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond (2);
		
		// click on create an account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond (2);
		
		// back lại
		driver.navigate().back();
		sleepInSecond (2);
		Assert.assertEquals(driver.getTitle(), "Customer Login");

		//forward
		driver.navigate().forward();
		sleepInSecond (2);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	@Test
	public void TC_04_Page_Source_HTML() {
		driver.get("http://live.techpanda.org/");
		
		// click on my account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond (2);
		// verify page html có chứa chuỗi như mong muốn
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		// click on create an account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond (2);
		
		// verify page html có chứa chuỗi như mong muốn
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

	}
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 1000ms = 1s
	}

	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}