package webdriver;

import java.util.Random;
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

public class topic_08_web_element_PIII {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress, firstName, lastName, fullName, password;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		rand = new Random();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		emailAddress ="maitien" + rand.nextInt(9999)+ "@gmail.com";
		firstName = "tien";
		lastName = "mai";
		fullName = firstName + " " + lastName;
		password = "123456";
		
	}

	@Test
	public void TC_01_login_empty_email_pass() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-email")).getText(),"This is a required field.");
		Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-pass")).getText(),"This is a required field.");

	}

	@Test
	public void TC_02_invalid_email() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("1234321@3453612812");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");

		
	}
	@Test
	public void TC_03_pass_less_than_6_char() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("maitien123@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		
		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");

	}
	@Test
	public void TC_04_incorrect_email_pass() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("maitien123@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),"Invalid login or password.");

	}
	
	@Test
	public void TC_05_create_new_account() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		
		driver.findElement(By.cssSelector("a[title ='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);

		driver.findElement(By.cssSelector("button[title ='Register']")).click();

		
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"Thank you for registering with Main Website Store.");
		String contactInformationText=driver.findElement(By.xpath("//h3[text()='Contact Information']/parent:: div/following-sibling::div/p")).getText();
		System.out.println(contactInformationText);
		
		Assert.assertTrue(contactInformationText.contains(fullName));
		Assert.assertTrue(contactInformationText.contains(emailAddress));

		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.gif')]")).isDisplayed());
	}

	@Test
	public void TC_06_valid_email_pass() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys(password);
		
		driver.findElement(By.id("send2")).click();
		String contactInformationText=driver.findElement(By.xpath("//h3[text()='Contact Information']/parent:: div/following-sibling::div/p")).getText();
		System.out.println(contactInformationText);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}