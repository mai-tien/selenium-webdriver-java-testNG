package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
public class topic_06_Run_all_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@Test
	public void TC_01_Run_chrome() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.quit();
	}

	@Test
	public void TC_02_run_firefox() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.quit();
	}

	@Test
	public void TC_03_run_edge() {
		System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
		driver = new EdgeDriver();
		driver.quit();
	}
}