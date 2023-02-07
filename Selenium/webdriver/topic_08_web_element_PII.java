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

public class topic_08_web_element_PI2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	By emailTextbox = By.id("email");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By educationTextArea = By.cssSelector("#edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
	By jobrole1dropdown = By.cssSelector("#job1");
	By jobrole2dropdown = By.cssSelector("#job2");
	By developmentcheckbox = By.cssSelector("#development");
	By slider1 = By.cssSelector("#slider-1");
	By passwordTextbox = By.cssSelector("#disable_password");
	By biographyTextarea = By.cssSelector("#bio");
	By jobrole3dropdown = By.cssSelector("#job3");
	By interestCheckbox = By.cssSelector("#check-disbaled");
	By slider2 = By.cssSelector("#slider-2");

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
	public void TC_01_displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// textbox có thì nhập
		if (driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("Selenium WebDriver");
			System.out.println("Email Textbox is displayed");
		} else
			System.out.println("Email Textbox is not displayed");

		// textarea
		if (driver.findElement(educationTextArea).isDisplayed()) {
			driver.findElement(educationTextArea).sendKeys("Selenium grid");
			System.out.println("education TextArea is displayed");
		} else
			System.out.println("educationTextArea is not displayed");

		// radio button
		if (driver.findElement(ageUnder18Radio).isDisplayed()) {
			driver.findElement(ageUnder18Radio).click();
			System.out.println("age Under 18 Radio is displayed");
		} else
			System.out.println("age Under 18 Radio is not displayed");

		// name
		if (driver.findElement(nameUser5Text).isDisplayed()) {

			System.out.println("name User 5 Text is displayed");
		} else
			System.out.println("name User 5 Text is not displayed");

	}

	@Test
	public void TC_02_enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// email
		if (driver.findElement(emailTextbox).isEnabled()) {
			System.out.println("Email Textbox is Enabled");
		} else
			System.out.println("Email Textbox is disabled");

		// age
		if (driver.findElement(ageUnder18Radio).isEnabled()) {
			System.out.println("age Under 18 Radio is Enabled");
		} else
			System.out.println("age Under 18 Radio is disabled");

		// education
		if (driver.findElement(educationTextArea).isEnabled()) {
			System.out.println("education TextArea is Enabled");
		} else
			System.out.println("education TextArea is disabled");

		// nameUser 5 Text
		if (driver.findElement(nameUser5Text).isEnabled()) {
			System.out.println("nameUser 5 Text is Enabled");
		} else
			System.out.println("nameUser 5 Text is disabled");

		// job role 1 dropdown
		if (driver.findElement(jobrole1dropdown).isEnabled()) {
			System.out.println("job role 1 dropdown is Enabled");
		} else
			System.out.println("job role 1 dropdown is disabled");

		// job role 2 dropdown
		if (driver.findElement(jobrole2dropdown).isEnabled()) {
			System.out.println("job role 2 dropdown is Enabled");
		} else
			System.out.println("job role 2 dropdown is disabled");

		// development checkbox
		if (driver.findElement(developmentcheckbox).isEnabled()) {
			System.out.println("development checkbox is Enabled");
		} else
			System.out.println("development checkbox is disabled");

		// slider1
		if (driver.findElement(slider1).isEnabled()) {
			System.out.println("slider 1 is Enabled");
		} else
			System.out.println("slider 1 is disabled");

		// password Textbox
		if (driver.findElement(passwordTextbox).isEnabled()) {
			System.out.println("password Textbox is Enabled");
		} else
			System.out.println("password Textbox is disabled");

		// biography Textarea
		if (driver.findElement(biographyTextarea).isEnabled()) {
			System.out.println("biography Textarea is Enabled");
		} else
			System.out.println("biography Textarea is disabled");

		// job role 3 dropdown
		if (driver.findElement(jobrole3dropdown).isEnabled()) {
			System.out.println("job role 3 dropdown is Enabled");
		} else
			System.out.println("job role 3 dropdown is disabled");

		// interest Checkbox
		if (driver.findElement(interestCheckbox).isEnabled()) {
			System.out.println("interest Checkbox is Enabled");
		} else
			System.out.println("interest Checkbox is disabled");

		// slider 2
		if (driver.findElement(slider2).isEnabled()) {
			System.out.println("slider 2 is Enabled");
		} else
			System.out.println("slider 2 is disabled");

	}

	@Test
	public void TC_03_selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
	// verify checkbox/radio button are selected
	Assert.assertFalse(driver.findElement(ageUnder18Radio).isSelected());
	Assert.assertFalse(driver.findElement(developmentcheckbox).isSelected());
	
	// click to checkbox/radio
	driver.findElement(ageUnder18Radio).click();
	driver.findElement(developmentcheckbox).click();
	

	//verify checkbox/radio
	Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
	Assert.assertTrue(driver.findElement(developmentcheckbox).isSelected());
	
		
	}

	@Test
	public void TC_04_mailchimp() {
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.id("email")).sendKeys("nguyenvan12@gmail.com");
		
		By passwordTextbox = By.id("new_password");
		By signupbutton = By.id("create-account-enabled");
		
		driver.findElement(passwordTextbox).sendKeys("abc");
		
		//verify lowercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("ABC");
		
		//verify uppercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123");
		
		//verify number
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		

		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("@#$");
		
		//verify special char
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		

		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("GSJUEKNHDSK");
		
		//verify > 8 char
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("GSJah12$%#");
		
		//verify full data
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
	
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}