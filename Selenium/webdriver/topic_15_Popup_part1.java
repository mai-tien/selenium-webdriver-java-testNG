package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_15_Popup_part1 {
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

	//@Test
	public void TC_01_popup_in_DOM() {
		driver.get("https://ngoaingu24h.vn/");
		
		By loginPopup = By.cssSelector("div#modal-login-v1 div.modal-content");
		// verify popup không hiển thị
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
		// click vào button login
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(3);
		// // verify popup hiển thị
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationFC");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationFC");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");

	}

	@Test
	public void TC_02_popup_in_DOM() {
		driver.get("https://skills.kynaenglish.vn/");
		
		By loginPopup = By.cssSelector("div#k-popup-account-login");
		// verify popup không hiển thị
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
		// click vào button login
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);
		// // verify popup hiển thị
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");

	}

	//@Test
	public void TC_03_popup_not_in_DOM() {
		
		driver.get("https://tiki.vn/");
		
		By loginPopup = By.cssSelector("div#k-popup-account-login");
		// verify popup không hiển thị
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
		// click vào button login
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);
		// // verify popup hiển thị
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

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