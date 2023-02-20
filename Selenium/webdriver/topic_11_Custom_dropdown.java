package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_11_Custom_dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;

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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 30);
	}

	@Test
	public void TC_01_Jquery() {	
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		// 1. click vào 1 thẻ bất kì làm sao xổ ra các item
		driver.findElement(By.cssSelector("span#speed-button")).click();
		// 2. chờ cho tất cả list load thành công
		// locator phải lấy để đại diện cho tất cả item
		// lấy đến thẻ chứa text

		explicitWait.until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));

		// đưa hết tất cả item trong dropdown vào 1 list

		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector("ul#speed-menu div[role='option']"));

		// 3. tìm item xem đúng cái mình đang cần hay không
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);

			// 4. kiểm tra cái text của item có đúng cái mình muốn hay không
			if (itemText.equals("Slower")) {

			// 5. click vào item đó
				tempItem.click();
			// thoát khỏi vòng lặp và không xét các case tiếp theo
				break;
			}
		}

	}



	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}