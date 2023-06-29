package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

	// @Test
	public void TC_01_Jquery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slower");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),
				"Slower");

		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slow");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),
				"Slow");

		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Fast");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),
				"Fast");

		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Faster");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),
				"Faster");

	}

	// @Test
	public void TC_02_reactJs() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemInDropdown("i.dropdown.icon", "span.text", "Elliot Fu");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Elliot Fu");

		selectItemInDropdown("i.dropdown.icon", "span.text", "Jenny Hess");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");

		selectItemInDropdown("i.dropdown.icon", "span.text", "Stevie Feliciano");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");

		selectItemInDropdown("i.dropdown.icon", "span.text", "Christian");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");

	}

	// @Test
	public void TC_03_VueJs() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");

		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
	}

	@Test
	public void TC_04_editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		selectItemInDropdown("input.search", "span.text", "Afghanistan");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Afghanistan");

		selectItemInDropdown("input.search", "span.text", "Benin");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Benin");

		selectItemInDropdown("input.search", "span.text", "Austria");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Austria");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 1000ms = 1s
	}

	public void selectItemInDropdown(String parentCss, String allItemCss, String expectedTextItem) {

		// 1. click vào 1 thẻ bất kì làm sao xổ ra các item
		driver.findElement(By.cssSelector(parentCss)).click();
		// 2. chờ cho tất cả list load thành công
		// locator phải lấy để đại diện cho tất cả item
		// lấy đến thẻ chứa text

		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));

		// đưa hết tất cả item trong dropdown vào 1 list

		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));

		// 3. tìm item xem đúng cái mình đang cần hay không
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);

			// 4. kiểm tra cái text của item có đúng cái mình muốn hay không
			if (itemText.trim().equals(expectedTextItem)) {

				// 5. click vào item đó
				tempItem.click();
				// thoát khỏi vòng lặp và không xét các case tiếp theo
				break;
			}
		}
	}

	public void enterandselectItemInDropdown(String textboxcss, String allItemCss, String expectedTextItem) {

		// 1. click vào 1 thẻ bất kì làm sao xổ ra các item
		driver.findElement(By.cssSelector(textboxcss)).clear();
		driver.findElement(By.cssSelector(textboxcss)).sendKeys(expectedTextItem);

		sleepInSecond(1);
		// 2. chờ cho tất cả list load thành công
		// locator phải lấy để đại diện cho tất cả item
		// lấy đến thẻ chứa text

		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));

		// đưa hết tất cả item trong dropdown vào 1 list

		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));

		// 3. tìm item xem đúng cái mình đang cần hay không
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);

			// 4. kiểm tra cái text của item có đúng cái mình muốn hay không
			if (itemText.trim().equals(expectedTextItem)) {
				sleepInSecond(2);
				// 5. click vào item đó
				tempItem.click();
				// thoát khỏi vòng lặp và không xét các case tiếp theo
				break;
			}
		}
	}
}