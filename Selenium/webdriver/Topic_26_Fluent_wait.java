package webdriver;

import java.time.Duration;
import java.util.Date;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_26_Fluent_wait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	FluentWait <WebElement> fluentElement;
	FluentWait <WebDriver> fluentDriver;
	
	long alltime = 15; // second
	long pollingTime =100; //Milisecond
	
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
	public void TC_01_fluent() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		findElement("//div[@id='start']/button").click();
		
		Assert.assertEquals(findElement("//div[@id='finish']/h4").getText(),"Hello World!");
		
	}
	
	//@Test
	public void TC_02_fluent() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countdownTime = findElement("//div[@id='javascript_countdown_time']");
		fluentElement = new FluentWait<WebElement> (countdownTime);
		
		fluentElement.withTimeout(Duration.ofSeconds(alltime))
		.pollingEvery(Duration.ofMillis(pollingTime))
		.ignoring(NoSuchElementException.class);
		
		fluentElement.until(new Function<WebElement, Boolean>(){
			public Boolean apply (WebElement element) {
				String text = element.getText();
				System.out.println(text);
				return text.endsWith("00");
			}
		});

	}
	public 	WebElement findElement(String xpathLocator) {
		fluentDriver = new FluentWait<WebDriver>(driver);
		fluentDriver.withTimeout(Duration.ofSeconds(alltime)).pollingEvery(Duration.ofMillis(pollingTime)).ignoring(NoSuchElementException.class);
		return fluentDriver.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpathLocator));
			}
		});
		
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