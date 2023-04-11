package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_Explicit_wait_2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait expliciWait;
	
	
	String foodfileName = "food.png";
	String treefileName = "tree.png";
	String sunflowerfileName = "sunflower.png";
	
	String foodFilePath = projectPath + "/Uploadfile/" + foodfileName;
	String  treeFilePath = projectPath + "/Uploadfile/" + treefileName;
	String sunflowerFilePath = projectPath + "/Uploadfile/" + sunflowerfileName;

	
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
	public void TC_01_visible() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		expliciWait = new WebDriverWait(driver,2); 
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
	
	}

	@Test
	public void TC_02_invisible() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		expliciWait = new WebDriverWait(driver,5); 
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
	
	}
	@Test
	public void TC_03_AJAX_loading() {

		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		expliciWait = new WebDriverWait(driver,15); 
		//wait cho date picker hiển thị		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));
		//verify cho k có ngày nào được chọn
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),"No Selected Dates to display.");
		//wait cho ngày được chọn đc click
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='19']")));
		// click vào ngày chọn
		driver.findElement(By.xpath("//a[text()='19']")).click();
		//wait cho ajax icon loading biến mất
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));
		// wait cho ngày vừa được click là được phép click
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']//a[text()='19']")));
		//verify ngày được chọn
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),"Wednesday, April 19, 2023");

	}
	@Test
	public void TC_04_upload_file() {

		driver.get("https://gofile.io/uploadFiles");
		
		expliciWait = new WebDriverWait(driver,15); 
		
		//wait cho file button hiển thị		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#filesUpload button.filesUploadButton")));
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(foodFilePath + "\n" +treeFilePath);
		//wait các loading icon biến mất
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#mainUpload")));
		//wait cho upload mess thành công
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Your files have been successfully uploaded']")));
		//veryfy mess hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Your files have been successfully uploaded']")).isDisplayed());
	
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