package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_14_Action_Part3 {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	//@Test
	public void TC_01_double_click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// scroll xuống
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		sleepInSecond(1);
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");
	
	}
	//@Test
	public void TC_02_right_click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
		
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(3);
		
		driver.switchTo().alert().accept();
		sleepInSecond(3);
		
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
	
	}
	//@Test
	public void TC_03_drag_and_drop() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");

		WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
		
		WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));

		action.dragAndDrop(smallCircle, bigCircle).perform();
		sleepInSecond(3);
		//verify text
		Assert.assertEquals(bigCircle.getText(),"You did great!");
		//verify color
		String bigCircleRGB = bigCircle.getCssValue("background-color");
		System.out.println(bigCircleRGB);
		
		String bigCircleHexa = Color.fromString(bigCircleRGB).asHex();
		System.out.println(bigCircleHexa);

		bigCircleHexa = bigCircleHexa.toUpperCase();
		System.out.println(bigCircleHexa);
		
		Assert.assertEquals(bigCircleHexa,"#03A9F4");
		
		
	}
	@Test
	public void TC_04_drag_and_drop_html5() {
	

	
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