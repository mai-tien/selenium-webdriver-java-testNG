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

public class Topic_15_Random_popup_Part3 {
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
	public void TC_01_Random_in_DOM() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(20);
		
		By Popup = By.cssSelector("div#tve-p-scroller");
		
		if (driver.findElement(Popup).isDisplayed()) {
			//close popup
			driver.findElement(By.cssSelector("div#tve-p-scroller div.thrv_icon")).click();
			sleepInSecond(3);
		}
		driver.findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
		sleepInSecond(5);
		Assert.assertEquals(driver.getTitle(),"Lịch khai giảng các khóa học tại VNK EDU | VNK EDU");

	}
	
	@Test
	public void TC_02_Random_Not_In_DOM() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(10);
		
		By Popup = By.cssSelector("div.popup-content");
		
		if (driver.findElements(Popup).size() > 0 && driver.findElements(Popup).get(0).isDisplayed()) {
			driver.findElement(By.id("popup-name")).sendKeys("Mai hoa");
			driver.findElement(By.id("popup-email")).sendKeys("maihoa123@gmail.com");
			driver.findElement(By.id("popup-phone")).sendKeys("03389432178");
			sleepInSecond(5);
			
			driver.findElement(By.cssSelector("button#close-popup")).click();
			sleepInSecond(3);
		}
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(3);
		
		String courseName = "Khóa học thiết kế hệ thống Cơ điện Công trình";
		driver.findElement(By.id("search-courses")).sendKeys(courseName);
		driver.findElement(By.cssSelector("button#search-course-button")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElements(By.cssSelector("div.course")).size(), 1);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.course-content>h4")).getText(),courseName);
		
		
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