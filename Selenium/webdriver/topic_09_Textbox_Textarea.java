package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_09_Textbox_Textarea {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String employeeID = String.valueOf(rand.nextInt(99999));
	String passpostNumber = "111-555-666-444";
	String comment = "Lunar New Year Festival often falls /nbetween late January and early February";

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
	public void TC_01_create_new_employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//span[text()=\"PIM\"]")).click();
		driver.findElement(By.xpath("//a[text()=\"Add Employee\"]")).click();
		sleepInSecond(3);
		
		driver.findElement(By.name("firstName")).sendKeys("Automation");
		driver.findElement(By.name("lastName")).sendKeys("fc");
		sleepInSecond(5);
		driver.findElement(By.xpath("//label[text()=\"Employee Id\"]/parent::div/following-sibling::div/input")).sendKeys(Keys.chord(Keys.COMMAND, "a"));
	
		driver.findElement(By.xpath("//label[text()=\"Employee Id\"]/parent::div/following-sibling::div/input")).sendKeys(Keys.DELETE);
		sleepInSecond(3);

		driver.findElement(By.xpath("//label[text()=\"Employee Id\"]/parent::div/following-sibling::div/input")).sendKeys(employeeID);
		
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//label[text()=\"Username\"]/parent::div/following-sibling::div/input")).sendKeys("afc" + employeeID);
		driver.findElement(By.xpath("//label[text()=\"Password\"]/parent::div/following-sibling::div/input")).sendKeys("Password123!!");
		driver.findElement(By.xpath("//label[text()=\"Confirm Password\"]/parent::div/following-sibling::div/input")).sendKeys("Password123!!");
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();

		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Automation");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"),"fc");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()=\"Employee Id\"]/parent::div/following-sibling::div/input")).getAttribute("value"),employeeID);
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		
		driver.findElement(By.xpath("//label[text()=\"Number\"]/parent::div/following-sibling::div/input")).sendKeys(passpostNumber);
		driver.findElement(By.xpath("//label[text()=\"Comments\"]/parent::div/following-sibling::div/textarea")).sendKeys(comment);
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(5);
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()=\"Number\"]/parent::div/following-sibling::div/input")).getAttribute("value"),passpostNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()=\"Comments\"]/parent::div/following-sibling::div/textarea")).getAttribute("value"),comment);
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
		driver.findElement(By.xpath("//a[text()=\"Logout\"]")).click();
		sleepInSecond(5);
	

		driver.findElement(By.name("username")).sendKeys("afc" + employeeID);
		driver.findElement(By.name("password")).sendKeys("Password123!!");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(5);

		driver.findElement(By.xpath("//span[text()=\"My Info\"]")).click();

	/*	
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Automation");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"),"fc");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()=\"Employee Id\"]/parent::div/following-sibling::div/input")).getAttribute("value"),employeeID);
		sleepInSecond(5);
	*/	

		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();

		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()=\"Number\"]/parent::div/following-sibling::div/input")).getAttribute("value"),passpostNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()=\"Comments\"]/parent::div/following-sibling::div/textarea")).getAttribute("value"),comment);

		
	}

	@Test
	public void TC_02_verify_employee() {

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