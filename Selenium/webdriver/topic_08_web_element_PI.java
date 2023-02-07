package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.seleniumhq.jetty9.server.Response.OutputType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_08_web_element_PI {
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
	public void TC_01_web_element() {
		WebElement element = driver.findElement(By.className(""));
		
		// dùng cho các textbox/ testarea/ dropdown
		// xóa dữ liệu đi trước khi nhập text
		element.clear();
		
		// dùng cho các textbox/ testarea/ dropdown
		// nhập liệu
		element.sendKeys("");
		
		// click vào các button / link/ checkbox/ radio/ image
		element.click();
		String searchAtribute = element.getAttribute("placeholder");
		String emailTextboxAtribute = element.getAttribute("value");
		
		//GUI: font/size/color/ location/ position/...
		element.getCssValue("background-color");
		
		// vị trí của element
		element.getLocation();
		
		//kích thước của element
		element.getSize();
		
		// location+ size
		element.getRect();
		
		//chụp hình testcase fail
		
		//Cần lấy tên thẻ html của element đó
		driver.findElement(By.id("Email")).getTagName();
		
		// lấy text từ error message
		element.getText();
		
		// dùng verify xem 1 element hiển thị hay không
		// phạm vi tất cả các element
		Assert.assertTrue(element.isDisplayed());
		Assert.assertFalse(element.isDisplayed());
		
		// dùng verify xem 1 element có thao tác được hay không
		// phạm vi tất cả các element
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		

		// dùng verify xem 1 element có chọn được hay không
		// phạm vi tất cả các element
		Assert.assertTrue(element.isSelected());
		Assert.assertFalse(element.isSelected());
		
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