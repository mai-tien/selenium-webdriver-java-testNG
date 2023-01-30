package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_05_Xpath_Css {
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
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Register_with_empty_data() {
		//action
		driver.findElement(By.id("txtFirstname")).sendKeys("");
		driver.findElement(By.id("txtEmail")).sendKeys("");
		driver.findElement(By.id("txtCEmail")).sendKeys("");
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.findElement(By.id("txtCPassword")).sendKeys("");
		driver.findElement(By.id("txtPhone")).sendKeys("");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//verify
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),("Vui lòng nhập họ tên"));
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),("Vui lòng nhập email"));
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),("Vui lòng nhập lại địa chỉ email"));
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),("Vui lòng nhập mật khẩu"));
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),("Vui lòng nhập lại mật khẩu"));
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),("Vui lòng nhập số điện thoại."));

	}
	

	@Test
	public void TC_02_Register_with_invalid_Email() {
		driver.findElement(By.id("txtFirstname")).sendKeys("Mai Tiên");
		driver.findElement(By.id("txtEmail")).sendKeys("123.456");
		driver.findElement(By.id("txtCEmail")).sendKeys("123.456");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0338931188");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
	
		//verify
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),("Vui lòng nhập email hợp lệ"));
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),("Email nhập lại không đúng"));
	
	}

	@Test
	public void TC_03_Register_with_incorrect_confirm_Email() {
		driver.findElement(By.id("txtFirstname")).sendKeys("Mai Tiên");
		driver.findElement(By.id("txtEmail")).sendKeys("maitien@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("123.456");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0338931188");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
	//verify
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),("Email nhập lại không đúng"));
	
	
	}
	@Test
	public void TC_04_Register_with_password_less_than_6_characters() {

		driver.findElement(By.id("txtFirstname")).sendKeys("Mai Tiên");
		driver.findElement(By.id("txtEmail")).clear();
		driver.findElement(By.id("txtEmail")).sendKeys("maitien@gmail.com");
		driver.findElement(By.id("txtCEmail")).clear();
		driver.findElement(By.id("txtCEmail")).sendKeys("maitien@gmail.com");
		driver.findElement(By.id("txtPassword")).clear();

		driver.findElement(By.id("txtPassword")).sendKeys("123");
		driver.findElement(By.id("txtCPassword")).clear();

		driver.findElement(By.id("txtCPassword")).sendKeys("123");
		driver.findElement(By.id("txtPhone")).sendKeys("0338931188");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
	//verify
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),("Mật khẩu phải có ít nhất 6 ký tự"));
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),("Mật khẩu phải có ít nhất 6 ký tự"));
	
	}
	@Test
	public void TC_05_Register_with_password_more_than_6_characters() {
		driver.findElement(By.id("txtFirstname")).sendKeys("Mai Tiên");
		driver.findElement(By.id("txtEmail")).sendKeys("maitien@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("maitien@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123");
		driver.findElement(By.id("txtPhone")).sendKeys("0338931188");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
	//verify
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),("Mật khẩu bạn nhập không khớp"));
	
	}
	
	@Test
	public void TC_06_Register_with_invalid_phone_number() {
		driver.findElement(By.id("txtFirstname")).sendKeys("Mai Tiên");
		driver.findElement(By.id("txtEmail")).sendKeys("maitien1@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("maitien1@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).clear();
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		//verify sai đầu số
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("13344556677");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),("Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08"));
		//verify it hơn 10 11 kí tự
				driver.findElement(By.id("txtPhone")).clear();
				driver.findElement(By.id("txtPhone")).sendKeys("03389311");
				driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
				Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),("Số điện thoại phải từ 10-11 số."));
				
		//verify lon hơn 10 11 kí tự
				driver.findElement(By.id("txtPhone")).clear();
				driver.findElement(By.id("txtPhone")).sendKeys("03389311888888");
				driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
				Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),("Số điện thoại phải từ 10-11 số."));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}