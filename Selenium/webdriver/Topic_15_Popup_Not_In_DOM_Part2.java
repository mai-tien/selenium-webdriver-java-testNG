package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Popup_Not_In_DOM_Part2 {
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
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);

		driver = new FirefoxDriver(options);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_popup_not_in_DOM() {
		
		driver.get("https://tiki.vn/");
		// by: nó chưa đi tìm element
		By loginPopup = By.cssSelector("div.ReactModal__Content");
		
		// verify nó chưa hiển thị khi chưa click vào login
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		
		// click vào để bật popup lên
		driver.findElement(By.cssSelector("div[data-view-id*='header_account']")).click();
		sleepInSecond(2);
		
		// // verify popup hiển thị
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		Assert.assertEquals(driver.findElements(loginPopup).size(), 1);
		
		driver.findElement(By.cssSelector("input[name='tel']")).sendKeys("09087756134");
		sleepInSecond(2);
		
		//close popup
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(2);

		// verify nó không còn hiển thị
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);

	}

	@Test
	public void TC_02_popup_not_in_DOM_facebook() {
		driver.get("https://www.facebook.com/");
		
		By createAcountPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
		
		// verify nó chưa hiển thị khi chưa click vào login
		Assert.assertEquals(driver.findElements(createAcountPopup).size(),0);
		
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		
		//verify popup hiển thị
		Assert.assertEquals(driver.findElements(createAcountPopup).size(),1);

		driver.findElement(By.name("firstname")).sendKeys("mai");
		driver.findElement(By.name("lastname")).sendKeys("tien");
		driver.findElement(By.name("reg_email__")).sendKeys("09087756113");
		driver.findElement(By.name("reg_passwd__")).sendKeys("123456");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("23");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("May");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("1995");
		driver.findElement(By.xpath("//label[text()='Female']//following-sibling::input")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div//preceding-sibling::img")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElements(createAcountPopup).size(),0);
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