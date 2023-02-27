package webdriver;

import java.awt.Desktop.Action;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_14_Action_PartI {
	WebDriver driver;
	Actions action;
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

		action = new Actions(driver);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
				"We ask for your age only for statistical purposes.");

	}

	// @Test
	public void TC_02_Myntra() {
		driver.get("https://www.myntra.com/");

		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']")))
				.perform();
		sleepInSecond(15);
		driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Home & Bath']")).click();
		sleepInSecond(15);
		Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");

		// Note: Trang này đã bị chặn

	}

	@Test
	public void TC_03() {
		driver.get("https://www.fahasa.com/");
		sleepInSecond(10);
		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		sleepInSecond(3);

		action.moveToElement(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
		sleepInSecond(3);

		driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Truyện Ngắn - Tản Văn']"))
				.click();

		Assert.assertTrue(driver
				.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Truyện ngắn - Tản Văn']"))
				.isDisplayed());

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
		//driver.quit();
	}
}