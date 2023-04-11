package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_Explicit_wait_1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait expliciWait;
	
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Not_enough_time() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		expliciWait = new WebDriverWait(driver,1); 
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
	
	}

	@Test
	public void TC_02_Enough_time() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		expliciWait = new WebDriverWait(driver,5); 
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
	
	}
	
	@Test
	public void TC_03_More_time() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		expliciWait = new WebDriverWait(driver,10); 
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
				
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
	
	}

	@Test
	public void TC_04_visible() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		expliciWait = new WebDriverWait(driver,2); 
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
	
	}

	@Test
	public void TC_05_invisible() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		expliciWait = new WebDriverWait(driver,5); 
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
	
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