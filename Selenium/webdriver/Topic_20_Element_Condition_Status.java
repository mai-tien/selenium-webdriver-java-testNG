package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Element_Condition_Status {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		expliciWait = new WebDriverWait(driver, 10);
		
	}

	//@Test
	public void TC_01_Visible_Displayed_Visibility() {
		driver.get("https://www.facebook.com/");
		// có trên UI
		// Có trên HTML
		
		// chờ cho email textbox xuất hiện trong 10s
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		
	}

	//@Test
	public void TC_02_Invisible_Undisplayed_Invisibility_1() {
		driver.get("https://www.facebook.com/");
		// không có trên UI
		// Có trên HTML
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		// chờ cho email textbox xuất hiện trong 10s
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
		
	}
	
	//@Test
	public void TC_02_Invisible_Undisplayed_Invisibility_2() {
		driver.get("https://www.facebook.com/");
		// không có trên UI
		// không Có trên HTML
		// chờ cho email textbox không xuất hiện trong 10s
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}
	//@Test
	public void TC_03_Presence_1() {
		driver.get("https://www.facebook.com/");
		// có trên UI
		// Có trên HTML
		
		// chờ cho email textbox không xuất hiện trong 10s
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
	}
	@Test
	public void TC_03_Presence_2() {
		driver.get("https://www.facebook.com/");
		// không có trên UI
		// Có trên HTML
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

		// chờ cho re_enter email textbox không xuất hiện trong 10s
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
	}

	//@Test
	public void TC_04_Staleness() {
		driver.get("https://www.facebook.com/");
		// không có trên UI
		// không Có trên HTML
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

		// phase 1: element có trong cây HTML
		WebElement reEnterEmailAddressTextbox = driver.findElement(By.name("reg_email_confirmation__")); 
	
		// thao tác với element làm cho re enter không nằm trong dom nữa
		//...
	
		//close popup đi
		driver.findElement(By.cssSelector("img._8idr")).click();
		
		// chờ cho re-enter email textbox không còn trong DOM
		expliciWait.until(ExpectedConditions.stalenessOf(reEnterEmailAddressTextbox));
		
		
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