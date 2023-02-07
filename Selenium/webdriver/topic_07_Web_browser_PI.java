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

public class topic_07_Web_browser_PI {
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
	public void TC_01() {
		// >=2 thì close tab/window đang đứng
		// = 1 thì close browser đó
		driver.close();
		
		// không quan tâm tab/window > browser
		driver.quit();
		
		// có thể lưu thành 1 biến để các steps sau sử dụng được
		//clean code
		//tìm 1 element
		WebElement EmailTextbox = driver.findElement(By.xpath("input[@id='email']"));
		EmailTextbox.clear();
		EmailTextbox.sendKeys("");
		
		// mở ra 1 url nào đó
		driver.get("https://www.facebook.com/");
		
		//trả về url của page hiện tại
		Assert.assertEquals(driver.getCurrentUrl(),"https://vi-vn.facebook.com/" );
		
		//trả về source code
		//verify tương đối
		Assert.assertTrue(driver.getCurrentUrl().contains("Đăng nhập hoặc đăng ký"));
		Assert.assertTrue(driver.getCurrentUrl().contains("hoặc đăng ký"));
		
		//trả về title hiện tại
		Assert.assertTrue(driver.getTitle().contains("Đăng nhập hoặc đăng ký"));

		// lấy ra id của window/tab đang đứng
		String loginWindowID = driver.getWindowHandle();
		
		//láy ra id của tất cả window/tab
		Set<String> allIDs = driver.getWindowHandles();
		
		//cookie/cache
		Options opt = driver.manage();
		opt.getCookies();
		opt.logs();
		Timeouts time = opt.timeouts();
		
		// khoảng tg chờ element xuất hiện trong x giây
		time.implicitlyWait(5, TimeUnit.SECONDS);
		time.implicitlyWait(5000, TimeUnit.MILLISECONDS);
		time.implicitlyWait(5000000, TimeUnit.MICROSECONDS);

		
		// khoảng tg chờ pageload xuất hiện trong x giây
		time.pageLoadTimeout(5, TimeUnit.SECONDS);

		Window win = opt.window();
		win.fullscreen();
		win.maximize();
		
		// test FUI/GUI
		
		win.getPosition();
		win.getSize();
		
		Navigation nav = driver.navigate();
		nav.back();
		nav.refresh();
		nav.forward();
		nav.to("https://www.facebook.com/");
		
		TargetLocator tar = driver.switchTo();
		//web driver api alert/auth alert
		tar.alert();
		//web driver api- frame/if frame
		tar.frame("");
		//web driver api- windows/tabs
		tar.window("");
	
	}

	@Test
	public void TC_02() {

	}

	@Test
	public void TC_03() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}