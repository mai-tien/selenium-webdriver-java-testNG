package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_14_Action_Part2 {
	WebDriver driver;
	Actions action;
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
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	//@Test
	public void TC_01_click_and_hold_block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		//1.list vào 1 số - giữ chuột và nhả ra
		action.clickAndHold(listNumber.get(0))
		//3. di chuột đến số (target)
		.moveToElement(listNumber.get(7))
		// 4. nhả chuột ra
		.release()
		// execute
		.perform();
	
		sleepInSecond(2);
		List<WebElement> listselectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));

		Assert.assertEquals(listselectedNumber.size(),8);

	}

	@Test
	public void TC_02click_and_hold_random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// chạy cho cả win/mac
		Keys key = null;
		if (osName.contains("Windown")) {
			key = Keys.CONTROL;
		}else {
			key = Keys.COMMAND;
		}
		
		List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		// nhấn command xuống
		action.keyDown(key).perform();
		
		// click chọn các số random
		action.click(listNumber.get(0))
		.click(listNumber.get(3))
		.click(listNumber.get(5))
		.click(listNumber.get(7)).perform();
		
		// nhả phím command ra
		action.keyUp(key).perform();
		
		sleepInSecond(2);
		List<WebElement> listselectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));

		Assert.assertEquals(listselectedNumber.size(),4);

	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} // 1000ms = 1s
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}