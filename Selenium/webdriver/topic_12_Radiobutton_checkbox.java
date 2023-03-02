package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_12_Radiobutton_checkbox {
	WebDriver driver;
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
	}

	// @Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");

		driver.findElement(By.cssSelector("li.popup-login-tab-item")).click();

		By loginButton = By.cssSelector("button.fhs-btn-login");

		// verify login button disable

		Assert.assertTrue(driver.findElement(loginButton).isDisplayed());

		String loginbuttonbackground = driver.findElement(loginButton).getCssValue("background-image");
		Assert.assertTrue(loginbuttonbackground.contains("rgb(224, 224, 224)"));

		sleepInSecond(2);
		// verify login button enabled

		driver.findElement(By.cssSelector("input#login_username")).sendKeys("0338931888");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");

		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		loginbuttonbackground = driver.findElement(loginButton).getCssValue("background-color");

		Color loginbuttonbackgroundcolor = Color.fromString(loginbuttonbackground);
		Assert.assertEquals(loginbuttonbackgroundcolor.asHex().toUpperCase(), "#C92127");

	}

	// @Test
	public void TC_02_default_checkbox_radioButton_single() {
		driver.get("https://automationfc.github.io/multiple-fields/");

		// click checkbox
		driver.findElement(By.xpath("//label[contains(text(),'Gout')]//preceding-sibling::input")).click();

		// click radio button
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]//preceding-sibling::input")).click();

		// verify
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Gout')]//preceding-sibling::input"))
				.isSelected());
		Assert.assertTrue(
				driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]//preceding-sibling::input"))
						.isSelected());

		// checkbox có thể bỏ chọn
		// radio button không thể bỏ chọn
		driver.findElement(By.xpath("//label[contains(text(),'Gout')]//preceding-sibling::input")).click();
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]//preceding-sibling::input")).click();

		// verify
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),'Gout')]//preceding-sibling::input"))
				.isSelected());
		Assert.assertTrue(
				driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]//preceding-sibling::input"))
						.isSelected());

	}

	// @Test
	public void TC_03_default_checkbox_mutiple() {
		driver.get("https://automationfc.github.io/multiple-fields/");

		driver.findElement(By.xpath("//label[contains(text(),'Gout')]//preceding-sibling::input")).click();
		driver.findElement(By.xpath("//label[contains(text(),'Gallstones')]//preceding-sibling::input")).click();
		driver.findElement(By.xpath("//label[contains(text(),'Heart Disease')]//preceding-sibling::input")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Gout')]//preceding-sibling::input"))
				.isSelected());
		Assert.assertTrue(
				driver.findElement(By.xpath("//label[contains(text(),'Gallstones')]//preceding-sibling::input"))
						.isSelected());
		Assert.assertTrue(
				driver.findElement(By.xpath("//label[contains(text(),'Heart Disease')]//preceding-sibling::input"))
						.isSelected());

		// CHỌN TẤT CẢ

		List<WebElement> allcheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));
		for (WebElement checkbox : allcheckboxes) {
			checkbox.click();
			sleepInSecond(1);
		}
		for (WebElement checkbox : allcheckboxes) {
			Assert.assertTrue(checkbox.isSelected());
		}
		/*
		 * // CHỌN CHECKBOX CÓ TÊN X List<WebElement> allcheckboxes =
		 * driver.findElements(By.cssSelector("input.form-checkbox")); for(WebElement
		 * checkbox:allcheckboxes){ if (checkbox.getAttribute("value").equals("Gout")) {
		 * checkbox.click(); } }
		 */
	}

	@Test
	public void TC_04_Buttoncheckbox_radiobutton_exercise() {
		driver.get("https://material.angular.io/components/radio/examples");

		driver.findElement(By.xpath("//label[contains(text(),'Summer')]//preceding-sibling::div")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Summer')]//preceding-sibling::div"))
				.isSelected());

		driver.get("https://material.angular.io/components/checkbox/examples");
		// chọn checkbox
		driver.findElement(By.xpath("//label[contains(text(),'Checked')]//preceding-sibling::div")).click();
		driver.findElement(By.xpath("//label[contains(text(),'Indeterminate')]//preceding-sibling::div")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Checked')]//preceding-sibling::div"))
				.isSelected());

		Assert.assertTrue(
				driver.findElement(By.xpath("//label[contains(text(),'Indeterminate')]//preceding-sibling::div"))
						.isSelected());

		// bỏ chọn checkbox
		driver.findElement(By.xpath("//label[contains(text(),'Checked')]//preceding-sibling::div")).click();
		driver.findElement(By.xpath("//label[contains(text(),'Indeterminate')]//preceding-sibling::div")).click();
		sleepInSecond(2);
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),'Checked')]//preceding-sibling::div"))
				.isSelected());

		Assert.assertFalse(
				driver.findElement(By.xpath("//label[contains(text(),'Indeterminate')]//preceding-sibling::div"))
						.isSelected());

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
		// driver.quit();
	}
}