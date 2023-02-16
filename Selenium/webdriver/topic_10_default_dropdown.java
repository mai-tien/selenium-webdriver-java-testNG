package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_10_default_dropdown {
	WebDriver driver;
	Random rand;
	Select select;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, companyName, emailAddress, password, day, month, year;

	String country, city, address1, zipCode, phoneNumber;
	
	
	
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
		firstName = "Nacy";
		lastName= "Mai";
		companyName= "Docosan";
		emailAddress ="nacymai" + rand.nextInt(9999)+ "@gmail.com";
		password= "Password123!";
		day = "15";
		month = "May";
		year = "1998";
		country = "Viet Nam";
		city = "Ho Chi Minh";
		address1 = "10 kha van can";
		zipCode = "700000";
		phoneNumber = "03344445678";
	
	}

	@Test
	public void TC_01_Register() {
		driver.get("https://demo.nopcommerce.com/register");

		driver.findElement(By.cssSelector("a.ico-register")).click();

		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(day);
		
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);

		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");

		driver.findElement(By.cssSelector("a.ico-login")).click();
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();
		driver.findElement(By.cssSelector("a.ico-account")).click();
		
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"),lastName);

		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);

		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"),emailAddress);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"),companyName);

	
	}

	@Test
	public void TC_02_add_address() {
		driver.findElement(By.cssSelector("li.customer-addresses>a")).click();
		driver.findElement(By.cssSelector("button.add-address-button")).click();
// action
		driver.findElement(By.id("Address_FirstName")).sendKeys(firstName);
		driver.findElement(By.id("Address_LastName")).sendKeys(lastName);
		driver.findElement(By.id("Address_Email")).sendKeys(emailAddress);
		new Select(driver.findElement(By.id("Address_CountryId"))).selectByVisibleText(country);
		driver.findElement(By.id("Address_City")).sendKeys(city);
		driver.findElement(By.id("Address_Address1")).sendKeys(address1);
		driver.findElement(By.id("Address_ZipPostalCode")).sendKeys(zipCode);
		driver.findElement(By.id("Address_PhoneNumber")).sendKeys(phoneNumber);
		driver.findElement(By.cssSelector("button.save-address-button")).click();
// verify
		Assert.assertEquals(driver.findElement(By.cssSelector("li.name")).getText(),firstName + " "+ lastName);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.email")).getText().contains(emailAddress));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.phone")).getText().contains(phoneNumber));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.address1")).getText(),address1);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(city));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(zipCode));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.country")).getText(),country);
	
		
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