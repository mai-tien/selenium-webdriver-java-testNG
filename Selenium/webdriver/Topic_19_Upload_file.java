package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Upload_file {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	
	String foodfileName = "food.png";
	String treefileName = "tree.png";
	String sunflowerfileName = "sunflower.png";
	
	String foodFilePath = projectPath + "/Uploadfile/" + foodfileName;
	String treeFilePath = projectPath + "/Uploadfile/" + treefileName;
	String sunflowerFilePath = projectPath + "/Uploadfile/" + sunflowerfileName;

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
		jsExecutor = (JavascriptExecutor) driver;
		
	}

	@Test
	public void TC_01_one_file_per_time() {
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	
		// load file
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(foodFilePath);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(treeFilePath);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(sunflowerFilePath);
		
		// verify file load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + foodfileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + treefileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + sunflowerfileName + "']")).isDisplayed());

		// click upload
		List<WebElement> buttonload = driver.findElements(By.cssSelector("table button.start"));
		
		for (WebElement button : buttonload) {
			button.click();
			sleepInSecond(7);
		}
		//verify upload thanh công (link)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + foodfileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + treefileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + sunflowerfileName + "']")).isDisplayed());

		//verify upload thanh công (hình)
		
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + foodfileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + treefileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + sunflowerfileName + "')]"));

	}
	@Test
	public void TC_02_Multiple_file_per_time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		// load file
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(foodFilePath + "\n" + treeFilePath + "\n" + sunflowerFilePath);

		
		// verify file load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + foodfileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + treefileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + sunflowerfileName + "']")).isDisplayed());

		// click upload
		List<WebElement> buttonload = driver.findElements(By.cssSelector("table button.start"));
		
		for (WebElement button : buttonload) {
			button.click();
			sleepInSecond(7);
		}
		//verify upload thanh công (link)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + foodfileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + treefileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + sunflowerfileName + "']")).isDisplayed());

		//verify upload thanh công (hình)
		
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + foodfileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + treefileName + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + sunflowerfileName + "')]"));

	}	
		
		
		
	
	
	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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