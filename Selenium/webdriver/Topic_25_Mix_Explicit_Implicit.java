package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Mix_Explicit_Implicit {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait expliciWait;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Element_found() {
		//element có thể xuất hiện và không chờ hết timeout
		//dù có set cả loại wait thì cũng không ảnh hưởng
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		expliciWait = new WebDriverWait(driver,10); 
		
		// implicit wait : chỉ apply cho findElement/findElements
		// Explicit wait : cho các điều kiện của element
		
		driver.get("https://www.facebook.com/");
		
		//Explicit
		System.out.println("thoi gian bat đau explicit: " + getTimeStamp());
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		System.out.println("thoi gian kết thúc explicit: " + getTimeStamp());

		//implicit wait
		System.out.println("thoi gian bat đau implicit: " + getTimeStamp());
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("thoi gian kết thúc implicit: " + getTimeStamp());

		
	}
	@Test
	public void TC_02_Element_Not_found() {
		//element có thể xuất hiện và không chờ hết timeout
		//dù có set cả loại wait thì cũng không ảnh hưởng
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://www.facebook.com/");

		//implicit wait
		System.out.println("thoi gian bat đau implicit: " + getTimeStamp());
		try {
			driver.findElement(By.cssSelector("input#selenium"));
		} catch (Exception e) {
			System.out.println("thoi gian kết thúc implicit: " + getTimeStamp());
		}
	
	}
	@Test
	public void TC_03_Element_Not_found_implicit_explicit() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		expliciWait = new WebDriverWait(driver,5); 
		
		driver.get("https://www.facebook.com/");

		//implicit wait
		System.out.println("thoi gian bat đau implicit: " + getTimeStamp());
		try {
			driver.findElement(By.cssSelector("input#selenium"));
		} catch (Exception e) {
			System.out.println("thoi gian kết thúc implicit: " + getTimeStamp());
		}
		//Explicit
		System.out.println("thoi gian bat đau explicit: " + getTimeStamp());
		try {
			expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			System.out.println("thoi gian kết thúc explicit: " + getTimeStamp());

		}
	}
	@Test
	public void TC_04_Element_Not_found_explicit() {
		expliciWait = new WebDriverWait(driver,5); 
		driver.get("https://www.facebook.com/");
		
		//Explicit
		System.out.println("thoi gian bat đau explicit: " + getTimeStamp());
		try {
			expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			System.out.println("thoi gian kết thúc explicit: " + getTimeStamp());

		}
	}
	@Test
	public void TC_05_Element_Not_found_explicit_implicit() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		expliciWait = new WebDriverWait(driver,5); 
		
		driver.get("https://www.facebook.com/");
		System.out.println("thoi gian bat đau explicit: " + getTimeStamp());
		try {
			expliciWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#selenium"))));
		} catch (Exception e) {
			System.out.println("thoi gian kết thúc explicit: " + getTimeStamp());

		}
	}
		
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 1000ms = 1s
	}
	public String getTimeStamp() {
		Date date = new Date();
			return date.toString();
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}