package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Frame_iFrame {
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

	//@Test
	public void TC_01_Iframe() {
		driver.get("https://skills.kynaenglish.vn/");
		
		// verify facebook iframe hiển thị
		//facebook iframe vẫn là 1 element của trang kyna	
		Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")).isDisplayed());
		
		// cần switch vào đúng cái thẻ iframe
		// 1. dùng index
		//driver.switchTo().frame(0);
		
		// 2. nên dùng switch to bên dưới
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")));
		
		//verify số lượng lượt like
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']//parent::div//following-sibling::div")).getText(),"165K likes");
		
		// cần switch về main page
		driver.switchTo().defaultContent();
		
		// switch về iframe chat
		driver.switchTo().frame("cs_chat_iframe");
		
		// click vào chat iframe để show lên chat support
		driver.findElement(By.cssSelector("div.button_bar")).click();
		
		// nhập thông tin
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("mai hoa");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("03345678921");
		new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.name("message")).sendKeys("Tư vấn Khóa học");
		
		sleepInSecond(3);
		// về lại trang chính
		driver.switchTo().defaultContent();
		
		// nhập vào box search text excel
		driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
		
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));

		for (WebElement course : courseName) {
			Assert.assertTrue(course.getText().contains("Excel"));
		}
	}

	@Test
	public void TC_02_HDFC_bank_frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.name("fldLoginUserId")).sendKeys("nacy25");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(2);
		
		// switch về default
		driver.switchTo().defaultContent();
		
		Assert.assertTrue(driver.findElement(By.id("keyboard")).isDisplayed());
		driver.findElement(By.id("keyboard")).sendKeys("nacy2534");
	}

	@Test
	public void TC_03() {

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